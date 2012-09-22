package com.mashup.search;


import com.mashup.service.impl.*;
import com.mashup.service.*;
import com.mashup.dao.impl.AdcategoryDAO;
import com.mashup.domain.*;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.NumericField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.TermFreqVector;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.analysis.Analyzer;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.wltea.analyzer.lucene.IKAnalyzer;
import org.wltea.analyzer.lucene.IKQueryParser;
import org.wltea.analyzer.lucene.IKSimilarity;
import com.mashup.action.BaseAction;
import org.springframework.context.ApplicationContextAware;

public class CategoryMapFromDB {

	CategoryNode topCategoryNode;
	public Analyzer analyzer;
	IndexReader reader;
	IndexSearcher searcher;
	Directory directory;
	public IndexWriter writer;
	public RAMDirectory ramDir = new RAMDirectory();      
	public IndexWriter ramwriter ;
	WordsFreq wordsFreq;
	IProductService productService;
	ICategoryService categoryService;
	//public boolean stopInsert=false;
	//public boolean isInserting=false;
	public int count=0;
	private Logger log = Logger.getLogger(this.getClass().getName());

	public CategoryMapFromDB() throws CorruptIndexException,
			LockObtainFailedException, IOException {
		
		this.analyzer = new IKAnalyzer();
		this.wordsFreq = new WordsFreq();
		this.topCategoryNode = new CategoryNode(-1, "所有分类之母", "所有 分类 之母", -1);
		//directory = new RAMDirectory();
		String classDir = "D:\\index";
		directory = FSDirectory.open(new File(classDir));
		
		IndexSearcher isearcher = new IndexSearcher(directory);
		//log.debug("document size is " +isearcher.maxDoc());
		writer = new IndexWriter(directory, new IKAnalyzer(), false,IndexWriter.MaxFieldLength.LIMITED);
		ramwriter = new IndexWriter(ramDir,analyzer,true,IndexWriter.MaxFieldLength.LIMITED);  
		log.debug("Finish CategoryMapFromDB Constructor");
		log.debug(classDir);
	}

	public void initTopNode() {
		topCategoryNode = new CategoryNode(-1, "所有分类之母", "所有 分类 之母", -1);
		this.buildChildCategory(this.topCategoryNode);
		writer.setUseCompoundFile(true);
		log.debug("Finish initTopNode Method invocation");
		
	}

	public void buildChildCategory(CategoryNode categoryNode) {

		List categoryList = categoryService
				.findByFatherId(categoryNode.categoryId);
		if (categoryList != null && categoryList.size() != 0) {
			for (int i = 0; i < categoryList.size(); i++) {
				CategoryNode childNode = new CategoryNode(
						(Category) categoryList.get(i));
				categoryNode.categoryNodeList.add(childNode);
			}
			for (int i = 0; i < categoryNode.categoryNodeList.size(); i++) {
				buildChildCategory((CategoryNode) categoryNode.categoryNodeList
						.get(i));
			}
		}
	}

	public CategoryNode getCategoryNode(String tags, CategoryNode categoryNode) {
		if (categoryNode == null) {
			return categoryNode;
		}
		// 没有子分类，说明当前分类是最佳分类了。
		if (categoryNode.categoryNodeList == null
				|| categoryNode.categoryNodeList.size() == 0) {
			return categoryNode;
		}
		// 如果如果还有子分类
		String[] tagTerms = tags.split(" ");
		CategoryNode bestCategoryNode = null;
		double bestAngle = Double.MAX_VALUE;
		for (int i = 0; i < categoryNode.categoryNodeList.size(); i++) {
			double angle = computeAngle_Simple(tagTerms,
					(CategoryNode) categoryNode.categoryNodeList.get(i));
			if (angle < bestAngle) {
				bestAngle = angle;
				bestCategoryNode = (CategoryNode) categoryNode.categoryNodeList
						.get(i);
			}
		}

		return getCategoryNode(tags, bestCategoryNode);
	}

	public double computeAngle_Simple(String[] tagTerms,
			CategoryNode categoryNode) {
		// assume words are unique and only occur once
		Map tagsMap = (Map) categoryNode.tagsMap;

		int numerator = 0;
		double denominator;

		for (int i = 0; i < tagTerms.length; i++) {
			String word = tagTerms[i];
			if (tagsMap.containsKey(word)) {
				numerator += 1;// numerator+=((Integer)tagsMap.get(word)).intValue();
			}
		}
		denominator = Math.sqrt(tagsMap.size()) * Math.sqrt(tagTerms.length);
		double ratio = numerator / denominator;
		return Math.acos(ratio);
	}

	// 根据product实例，将其插入到数据库中，而且已经分好类。
	public boolean addProductToDB(Product product) throws IOException {
		this.wordsFreq.WordsFreqBySingleProduct(product.getProductName()
				+ product.getProductDesc());
		CategoryNode categoryNode = this.getCategoryNode(wordsFreq
				.toWordsString(), this.topCategoryNode);
		categoryService.getCategoryById(categoryNode.categoryId);
		product.setCategory(categoryService
				.getCategoryById(categoryNode.categoryId));
		// 添加到数据库
		this.productService.insertProduct(product);
		List list=this.productService.findByExample(product);
		if(list!=null&&list.size()!=0){
			product=(Product)list.get(0);
			log.debug("product added to DB");
		}

		// 添加到索引
		addProductToLucene(product);
		return true;
	}

	public void addProductToLucene(Product product)
			throws CorruptIndexException, IOException {
		//writer = new IndexWriter(directory, new IKAnalyzer(), false,	IndexWriter.MaxFieldLength.LIMITED);
		Document doc = new Document();
		doc.add(new Field("productId", product.getProductId().toString(),
				Field.Store.YES, Field.Index.NO, Field.TermVector.NO));
		doc.add(new Field("productName", product.getProductName(),
				Field.Store.YES, Field.Index.ANALYZED, Field.TermVector.YES));
		doc.add(new Field("productDesc", product.getProductDesc(),
				Field.Store.YES, Field.Index.ANALYZED, Field.TermVector.YES));
		String categoryString=product.getCategory().getCategoryId().toString();
		Integer categoryId=product.getCategory().getCategoryId();
		//
		while (categoryId!=-1){
			categoryId=categoryService.getCategoryById(categoryId).getFatherId();
			categoryString+=" "+categoryId.toString();
		}
		doc.add(new Field("categoryId", categoryString, Field.Store.YES, Field.Index.ANALYZED,
				Field.TermVector.NO));
		NumericField pricefield=new NumericField("price",Field.Store.YES, true);
		pricefield.setDoubleValue(product.getPrice());
		doc.add(pricefield);
//		doc.add(new Field("price", product.getPrice().toString(), Field.Store.YES,
//				Field.Index.NO, Field.TermVector.NO));
		doc.add(new Field("source", product.getSource(), Field.Store.YES,
				Field.Index.ANALYZED, Field.TermVector.NO));
		doc.add(new Field("url", product.getUrl(), Field.Store.YES,
				Field.Index.NO, Field.TermVector.NO));
		doc.add(new Field("productImg", product.getProductImg(),
				Field.Store.YES, Field.Index.NO, Field.TermVector.NO));
//		doc.add(new Field("score", product.getScore().toString(),
//				Field.Store.YES, Field.Index.NO, Field.TermVector.NO));
		NumericField scorefield=new NumericField("score",Field.Store.YES, true);
		scorefield.setDoubleValue(product.getScore());
		doc.add(scorefield);	
//		while(stopInsert);
//		isInserting=true;
		ramwriter.addDocument(doc);		
//		isInserting=false;
		count++;
		while(count>=500){
			log.debug("count size is "+count);
			ramwriter.close();
			writer.addIndexesNoOptimize(new Directory[]{ramDir});
			writer.optimize();
			ramDir.close();
			ramDir=new RAMDirectory();
			//for test
			writer.close();
			directory.close();
			String classDir = "D:\\index";
			directory = FSDirectory.open(new File(classDir));
			writer = new IndexWriter(directory, new IKAnalyzer(), false,IndexWriter.MaxFieldLength.LIMITED);
			
			ramwriter= new IndexWriter(ramDir,analyzer,true,IndexWriter.MaxFieldLength.LIMITED);  
			count=0;
			log.debug("count size is "+count);
		}
		//writer.optimize();
		//writer.close();
		log.debug("product added to lucene");
	}

	public IProductService getProductService() {
		return productService;
	}

	public void setProductService(IProductService productService) {
		this.productService = productService;
	}

	public ICategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(ICategoryService categoryService) {
		this.categoryService = categoryService;
	}

	protected void finalize() throws Throwable {
		super.finalize();
		writer.close();
	}
}
