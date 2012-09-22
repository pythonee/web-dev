package com.mashup.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.store.FSDirectory; //引用IKAnalyzer3.0的类
import org.apache.lucene.util.Version;
import org.apache.struts.action.DynaActionForm;
import org.wltea.analyzer.IKSegmentation;
import org.wltea.analyzer.Lexeme;
import org.wltea.analyzer.lucene.IKAnalyzer;
import org.wltea.analyzer.lucene.IKQueryParser;
import org.wltea.analyzer.lucene.IKSimilarity;

import com.mashup.action.BaseAction;
import com.mashup.domain.Category;
import com.mashup.domain.Product;
import com.mashup.domain.Result;
import com.mashup.search.CategoryMapFromDB;
import com.mashup.service.ISearchService;

public class SearchService extends BaseAction implements ISearchService {

	Analyzer analyzer = new IKAnalyzer();
	Directory directory;
	IndexSearcher isearcher;

	public Analyzer getAnalyzer() {
		return analyzer;
	}

	public void setAnalyzer(Analyzer analyzer) {
		this.analyzer = analyzer;
	}

	public Directory getDirectory() {
		return directory;
	}

	public void setDirectory(Directory directory) {
		this.directory = directory;
	}

	public IndexSearcher getIsearcher() {
		return isearcher;
	}

	public void setIsearcher(IndexSearcher isearcher) {
		this.isearcher = isearcher;
	}
	public List caSearch(String categoryId){
		
		//构造查询条件
		try {
			analyzer = new IKAnalyzer();
			// 建立内存索引对象
			//directory = new RAMDirectory();
			String classDir = "D:/index";
			directory = FSDirectory.open(new File(classDir));
			// 实例化搜索器
			isearcher = new IndexSearcher(directory);
			// 在索引器中使用IKSimilarity相似度评估器
			isearcher.setSimilarity(new IKSimilarity());
			// 使用IKQueryParser查询分析器构造Query对象
			
			//分类查询
		
			//搜索所有分类
			log.debug("categoryId "+categoryId);	
		
			Term categoryTerm=new Term("categoryId",categoryId);
			TermQuery categoryQuery=new TermQuery(categoryTerm);
			
			log.debug("categoryId "+categoryId);
			
			TopDocs topDocs = isearcher.search(categoryQuery, null,50);
			log.debug("命中：" + topDocs.totalHits);
			// 输出结果
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			List resultList = new ArrayList();
			for (int i = 0; i < topDocs.totalHits&&i<50; i++) {
				Document targetDoc = isearcher.doc(scoreDocs[i].doc);
				resultList.add(getCatResult(targetDoc));
				log.debug("内容：" + targetDoc.toString());
			}
			return resultList;
		} catch (CorruptIndexException e) {
			e.printStackTrace();
			return null;
		} catch (LockObtainFailedException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (isearcher != null) {
				try {
					isearcher.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (directory != null) {
				try {
					directory.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public List search(String keyword) throws ParseException {

		try {
			// 实例化IKAnalyzer分词器
			analyzer = new IKAnalyzer();
			// 建立内存索引对象
			//directory = new RAMDirectory();
			String classDir = "D:/index";
			directory = FSDirectory.open(new File(classDir));
			// 实例化搜索器
			isearcher = new IndexSearcher(directory);
			// 在索引器中使用IKSimilarity相似度评估器
			isearcher.setSimilarity(new IKSimilarity());
			// 使用IKQueryParser查询分析器构造Query对象
			Query query = IKQueryParser.parse("productName", keyword);
//			QueryParser parser = new QueryParser(Version.LUCENE_CURRENT, "productName", analyzer);
//			
//			parser.setPhraseSlop(10); //默认为0
//			parser.setDefaultOperator(QueryParser.AND_OPERATOR);
//			Query query = parser.parse(keyword);
			log.debug(query.toString());
			// 搜索相似度最高的50条记录
			// TopDocs topDocs = isearcher.search(query, null, 50, new
			// Sort());// null位置应该放过滤器

			TopDocs topDocs = isearcher.search(query, 50);
			log.debug("命中：" + topDocs.totalHits);
			// 输出结果
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			List resultList = new ArrayList();
			for (int i = 0; i < topDocs.totalHits&&i<50; i++) {
				Document targetDoc = isearcher.doc(scoreDocs[i].doc);
				resultList.add(getResult(targetDoc, query, keyword));
				log.debug("内容：" + targetDoc.toString());
			}
			return resultList;
		} catch (CorruptIndexException e) {
			log.debug(e);
			return null;
		} catch (LockObtainFailedException e) {
			log.debug(e);
			return null;
		} catch (IOException e) {
			log.debug(e);
			return null;
		} finally {
			if (isearcher != null) {
				try {
					isearcher.close();
				} catch (IOException e) {
					log.debug(e);
				}
			}
			if (directory != null) {
				try {
					directory.close();
				} catch (IOException e) {
					log.debug(e);
				}
			}
		}

	}

	public Result getResult(Document doc, Query query, String keyword) {
		String productName = doc.get("productName");
		try {
			SimpleHTMLFormatter shf = new SimpleHTMLFormatter("<span class=\"highlight\">", "</span>");

			Highlighter highlighter = new Highlighter(shf, new QueryScorer(query));
			highlighter.setTextFragmenter(new SimpleFragmenter(50));

			TokenStream tokenStream = (TokenStream) analyzer.tokenStream(
					"productName", new StringReader(productName));
			
			productName = highlighter.getBestFragments(tokenStream, doc.get("productName"),5, "......");
			log.debug(productName);
		} catch (Exception e) {
			log.debug(e);
		}
		Result result = new Result();
		result.setProductId(doc.get("productId"));
		result.setProductName(productName);
		result.setProductDesc(doc.get("productDesc"));
		result.setSource(doc.get("source"));
		result.setUrl(doc.get("url"));
		result.setProductImg(doc.get("productImg"));
		result.setCategoryId(doc.get("categoryId"));
		result.setLevelClick(doc.get("levelClick"));
		result.setScore(doc.get("score"));
		result.setPrice(doc.get("price"));
		
		if(result.getProductDesc().length()>150){
			result.setProductDesc(result.getProductDesc().substring(0, 150)+"…");
		}
		log.debug(doc.get("price"));
		return result;
	}
	
	public Result getCatResult(Document doc) {
		Result result = new Result();
		result.setProductId(doc.get("productId"));
		result.setProductName(doc.get("productName"));
		result.setProductDesc(doc.get("productDesc"));
		result.setSource(doc.get("source"));
		result.setUrl(doc.get("url"));
		result.setProductImg(doc.get("productImg"));
		result.setCategoryId(doc.get("categoryId"));
		result.setLevelClick(doc.get("levelClick"));
		result.setScore(doc.get("score"));
		result.setPrice(doc.get("price"));
		log.debug(doc.get("price"));
		return result;
	}
	
	public List searchRelated(String keyword) {

		try {
			// 实例化IKAnalyzer分词器
			analyzer = new IKAnalyzer();
			// 建立内存索引对象
			//directory = new RAMDirectory();
			String classDir = "D:/index";
			directory = FSDirectory.open(new File(classDir));
			// 实例化搜索器
			isearcher = new IndexSearcher(directory);
			// 在索引器中使用IKSimilarity相似度评估器
			isearcher.setSimilarity(new IKSimilarity());
			// 使用IKQueryParser查询分析器构造Query对象
			IKSegmentation ikSeg = new IKSegmentation(new StringReader(
					keyword), true);
			try {
				String orkeyword="";
				Lexeme lexeme = null;
				while ((lexeme = ikSeg.next()) != null) {
					orkeyword+=lexeme.getLexemeText()+" ";
				}
				keyword=orkeyword;
			} catch (IOException e) {
				log.debug(e);
			}
			
			Query query = IKQueryParser.parse("productName", keyword);
			
			log.debug(query.toString());
			// 搜索相似度最高的50条记录
			// TopDocs topDocs = isearcher.search(query, null, 50, new
			// Sort());// null位置应该放过滤器

			TopDocs topDocs = isearcher.search(query, 7);
			log.debug("命中：" + topDocs.totalHits);
			// 输出结果
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			List resultList = new ArrayList();
			for (int i = 1; i < topDocs.totalHits&&i<7; i++) {
				Document targetDoc = isearcher.doc(scoreDocs[i].doc);
				resultList.add(getResult(targetDoc, query, keyword));
				log.debug("内容：" + targetDoc.toString());
			}
			return resultList;
		} catch (CorruptIndexException e) {
			log.debug(e);
			return null;
		} catch (LockObtainFailedException e) {
			log.debug(e);
			return null;
		} catch (IOException e) {
			log.debug(e);
			return null;
		} finally {
			if (isearcher != null) {
				try {
					isearcher.close();
				} catch (IOException e) {
					log.debug(e);
				}
			}
			if (directory != null) {
				try {
					directory.close();
				} catch (IOException e) {
					log.debug(e);
				}
			}
		}

	}
	
	public List adSearch(DynaActionForm searchForm) {
		// TODO Auto-generated method stub
		//DynaActionForm searchForm = (DynaActionForm) form;
		String keyword=searchForm.getString("keyword");
		double price1=0;
		double price2=100;
		if(null!=searchForm.get("price1")){
			price1=Double.parseDouble(searchForm.get("price1").toString().trim());
			//格式化
		}
		if(null!=searchForm.get("price2")){
			price2=Double.parseDouble(searchForm.get("price2").toString().trim());
			if(price1>price2){
				double temp=price1;
				price1=price2;
				price2=temp;
			}
			//格式化
		}
		double score=Double.parseDouble(searchForm.get("score").toString());
		String source=searchForm.get("store").toString().trim();
		int sort=Integer.parseInt(searchForm.get("sort").toString());
		String categoryId=searchForm.get("categoryId").toString().trim();
		
		//构造查询条件
		try {
			analyzer = new IKAnalyzer();
			// 建立内存索引对象
			//directory = new RAMDirectory();
			String classDir = "D:/index";
			directory = FSDirectory.open(new File(classDir));
			// 实例化搜索器
			isearcher = new IndexSearcher(directory);
			// 在索引器中使用IKSimilarity相似度评估器
			isearcher.setSimilarity(new IKSimilarity());
			// 使用IKQueryParser查询分析器构造Query对象
			Query query = IKQueryParser.parse("productName", keyword);
			//价格范围查询
			Query priceQuery=NumericRangeQuery.newDoubleRange("price",price1,price2,true,true);
			//评分查询
			Query scoreQuery=NumericRangeQuery.newDoubleRange("score",score,5.2,true,true);
			
			 BooleanQuery q = new BooleanQuery();
			 q.add(query,  BooleanClause.Occur.MUST);
			 q.add(priceQuery, BooleanClause.Occur.MUST);
			 q.add(scoreQuery, BooleanClause.Occur.MUST);
			//价格低到高排序
			//价格高到低排序
			//评分从高到低排序
			 Sort mySort=new Sort();
			 if(sort==0){
				 
			 }else if(sort==1){
				 mySort.setSort(new SortField("price",SortField.DOUBLE,true));
			 }else if(sort==2){
				 mySort.setSort(new SortField("price",SortField.DOUBLE,false));
			 }else if(sort==3){
				 mySort.setSort(new SortField("score",SortField.DOUBLE,true));
			 }else if(sort==4){
				 mySort.setSort(new SortField("score",SortField.DOUBLE,false));
			 }
			//商场查询
			if(source.equals("不限")){
				//搜索所有商城
				log.debug("source "+source);
				
			}else{
				Term sourceTerm=new Term("source",source);
				TermQuery sourceQuery=new TermQuery(sourceTerm);
				q.add(sourceQuery, BooleanClause.Occur.MUST);
				log.debug("source "+source);
			}
			//分类查询
			if(categoryId.equals("-1")){
				//搜索所有分类
				log.debug("categoryId "+categoryId);
				
			}else{
				Term categoryTerm=new Term("categoryId",categoryId);
				TermQuery categoryQuery=new TermQuery(categoryTerm);
				q.add(categoryQuery, BooleanClause.Occur.MUST);
				log.debug("categoryId "+categoryId);
			}
			
			// 搜索相似度最高的50条记录
			// TopDocs topDocs = isearcher.search(query, null, 50, new
			// Sort());// null位置应该放过滤器
			
			TopDocs topDocs = isearcher.search(q, null,50,mySort);
			log.debug("命中：" + topDocs.totalHits);
			// 输出结果
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			List resultList = new ArrayList();
			for (int i = 0; i < topDocs.totalHits&&i<50; i++) {
				Document targetDoc = isearcher.doc(scoreDocs[i].doc);
				resultList.add(getResult(targetDoc, query, keyword));
				log.debug("内容：" + targetDoc.toString());
			}
			return resultList;
		} catch (CorruptIndexException e) {
			e.printStackTrace();
			return null;
		} catch (LockObtainFailedException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (isearcher != null) {
				try {
					isearcher.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (directory != null) {
				try {
					directory.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
