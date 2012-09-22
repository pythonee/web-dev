package com.mashup.search;

import java.util.Map;
import java.util.TreeMap;
import java.util.Iterator;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.TermFreqVector;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.store.FSDirectory;


import org.wltea.analyzer.lucene.IKAnalyzer;
import org.wltea.analyzer.lucene.IKQueryParser;
import org.wltea.analyzer.lucene.IKSimilarity;

public class WordsLikeDemo {

	 IndexReader reader;
     IndexSearcher searcher;

     // Writer
     Directory directory;
     IndexWriter writer;
     
     Map categoryMap;
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		WordsLikeDemo wordsLike=new WordsLikeDemo();
		wordsLike.testWordsLikeDemo_Simple();
		wordsLike.testWordsLikeDemo_Complex();

	}
	
	public void testWordsLikeDemo_Simple()throws Exception{
		buildCategory();
		buildCategoryAndTagsMap();
		System.out.println(getCategory_Simple("设计 语言")+"   should be "+"编程");
		System.out.println(getCategory_Simple("数据 数据库")+"   should be "+" 数据库");
		System.out.println(getCategory_Simple("算法 设计")+"   should be "+" 算法");
	}
	
	public String getCategory_Simple(String tagWords){
		String bestCategoryName="";
		String[] keyWords=tagWords.split(" ");
			
		Iterator categoryIterator=categoryMap.keySet().iterator();
		double bestAngle=Double.MAX_VALUE;
		while(categoryIterator.hasNext()){
			String categoryName=(String)categoryIterator.next();
			double angle=computeAngle_Simple(keyWords,categoryName);
			if(angle<bestAngle){
				bestAngle=angle;
				bestCategoryName=categoryName;
			}
		}
		return bestCategoryName;
	}
/**
 * 字符串向量相似度计算程序cos余弦定理，且假设词频为1，
 * 
 * */
	public double computeAngle_Simple(String[] keyWords,String categoryName) {
        // assume words are unique and only occur once
		Map tagsMap=(Map)categoryMap.get(categoryName);
		
        int numerator = 0; 
        double denominator;
        
        for (int i = 0; i < keyWords.length; i++) {
            String word = keyWords[i];
            if(tagsMap.containsKey(word)){
            	numerator+=1;//numerator+=((Integer)tagsMap.get(word)).intValue();
            }
        }
        denominator = Math.sqrt(tagsMap.size()) * Math.sqrt(keyWords.length);
        double ratio = numerator / denominator;
        return Math.acos(ratio);
	}
	
	public void buildCategoryAndTagsMap()throws Exception{
		categoryMap=new TreeMap();
		reader = IndexReader.open(directory);
        int maxDoc=reader.maxDoc();
        for(int i=0;i<maxDoc;i++){
        	if(!reader.isDeleted(i)){
        		Document doc=reader.document(i);
        		String categoryName=doc.get("categoryName");
        		
        		String[] tags=(doc.get("tags")).split(" ");
        		Map tagsMap=new TreeMap();
        		for(int j=0;j<tags.length;j++){
        			tagsMap.put(tags[j], new Integer(1));
        		}
        		categoryMap.put(categoryName,tagsMap);
        	}
        }
	}
	
	
	public void buildCategory()throws Exception{
		directory = new RAMDirectory();
        //directory = FSDirectory.getDirectory("index/categoryIndex");
		
        writer = new IndexWriter(directory,
        		new IKAnalyzer(), true,IndexWriter.MaxFieldLength.LIMITED);
         writer.setUseCompoundFile(true);
         
         String[] allCategoryTags=new String[]{
        		 "程序 设计 编程 算法 语言 C++ Java Erlang",
        		 "Datebase Oracle 数据库 数据管理 DB 关系 挖掘 数据 Mysql Hibernate",
        		 "算法 数据 结构 设计 计算 集合 structure Map"
         };
         
         String[] allCategoryNames=new String[]{
        		 "编程",
        		 "数据库",
        		 "算法"
         };
         
         for (int i = 0; i < allCategoryTags.length; i++) {
             Document doc = new Document();
             doc.add(new Field("categoryName", allCategoryNames[i],Field.Store.YES,Field.Index.ANALYZED));
             doc.add(new Field("tags", allCategoryTags[i],Field.Store.YES,Field.Index.ANALYZED));
             
             writer.addDocument(doc);
         }
	     writer.optimize();
	     writer.close();
	}
	
/////////涉及词频//////////////////////////////////////////
	//////////////////////////////////////////////
	
	public void testWordsLikeDemo_Complex()throws Exception{
		buildCategory_Complex();
		buildCategoryAndTagsMap_Complex();
				
		System.out.println(getCategory__Complex("设计 语言")+"   should be "+"编程");
		System.out.println(getCategory__Complex("数据 数据库")+"   should be "+" 数据库");
		System.out.println(getCategory__Complex("算法 算法 设计 Hibernate DB")+"   should be "+" 算法");
		
		WordsFreq wordsFreq=new WordsFreq();
		wordsFreq.WordsFreqDemo();
		System.out.println(getCategory__Complex(wordsFreq.toWordsString())+"   should be "+"算法");
		System.out.println(wordsFreq.toString("算法"));
	}
	
	public void buildCategory_Complex()throws Exception{
		directory = new RAMDirectory();
        //directory = FSDirectory.getDirectory("index/categoryIndex");
		
        writer = new IndexWriter(directory,
                         new IKAnalyzer(), true,IndexWriter.MaxFieldLength.LIMITED);
         writer.setUseCompoundFile(true);
         
         String[] allCategoryTags=new String[]{
        		 "程序 设计 编程 算法 语言 C++ Java Erlang",
        		 "Datebase Oracle 数据库 数据管理 DB 关系 挖掘 数据 Mysql Hibernate",
        		 "算法 数据 结构 设计 计算 集合 structure Map 迭代 递归",
        		 "网络 计算机 因特网 协议 Internet"
         };
         
         String[] allCategoryNames=new String[]{
        		 "编程",
        		 "数据库",
        		 "算法",
        		 "网络"
         };
         
         for (int i = 0; i < allCategoryTags.length; i++) {
             Document doc = new Document();
             doc.add(new Field("categoryName", allCategoryNames[i],Field.Store.YES,Field.Index.ANALYZED,Field.TermVector.YES));
             doc.add(new Field("tags", allCategoryTags[i],Field.Store.YES,Field.Index.ANALYZED,Field.TermVector.YES));
             
             writer.addDocument(doc);
         }
	     writer.optimize();
	     writer.close();
		
	}
	public void buildCategoryAndTagsMap_Complex()throws Exception{
		categoryMap=new TreeMap();
		reader = IndexReader.open(directory);
        int maxDoc=reader.maxDoc();
        for(int i=0;i<maxDoc;i++){
        	if(!reader.isDeleted(i)){
        		Document doc=reader.document(i);
        		String categoryName=doc.get("categoryName");
        		TermFreqVector termFreqVector=reader.getTermFreqVector(i,"tags");
        		Map tagsMap=new TreeMap();
        		
        		addTermFreqToMap(tagsMap,termFreqVector);
        		
        		categoryMap.put(categoryName,tagsMap);
        	}
        }
		
	}
	
	private void addTermFreqToMap(Map vectorMap,
        TermFreqVector termFreqVector) {
		String[] terms = termFreqVector.getTerms();
		int[] freqs = termFreqVector.getTermFrequencies();
		
		for (int i = 0; i < terms.length; i++) {
			String term = terms[i];
			
			if (vectorMap.containsKey(term)) {
				Integer value = (Integer) vectorMap.get(term);
				vectorMap.put(term,
				new Integer(value.intValue() + freqs[i]));
			} else {
				vectorMap.put(term, new Integer(freqs[i]));
			}
		}
	}
	
	public String getCategory__Complex(String tagWords){
		String bestCategoryName="";
		String[] keyWords=tagWords.split(" ");
			
		Iterator categoryIterator=categoryMap.keySet().iterator();
		double bestAngle=Double.MAX_VALUE;
		while(categoryIterator.hasNext()){
			String categoryName=(String)categoryIterator.next();
			double angle=computeAngle_Complex(keyWords,categoryName);
			if(angle<bestAngle){
				bestAngle=angle;
				bestCategoryName=categoryName;
			}
		}
		return bestCategoryName;
	}
	
	public double computeAngle_Complex(String[] keyWords,String categoryName) {
        // assume words are unique and only occur once
		Map tagsMap=(Map)categoryMap.get(categoryName);
		int sumOfSquares=0;
        int numerator = 0; 
        double denominator;
        
        for (int i = 0; i < keyWords.length; i++) {
            String word = keyWords[i];
            int categoryWordFreq=0;
            if(tagsMap.containsKey(word)){
            	categoryWordFreq=((Integer)tagsMap.get(word)).intValue();
            }
            numerator+=categoryWordFreq;
            sumOfSquares+=categoryWordFreq*categoryWordFreq;
        }
        //denominator = Math.sqrt(tagsMap.size()) * Math.sqrt(keyWords.length);
        if(sumOfSquares==keyWords.length){
        	denominator = sumOfSquares;
        }else{
        	denominator = Math.sqrt(sumOfSquares) * Math.sqrt(keyWords.length);
        }
        
        double ratio = numerator / denominator;
        return Math.acos(ratio);
	}
}
