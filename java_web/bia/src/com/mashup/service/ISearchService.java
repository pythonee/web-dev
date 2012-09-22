package com.mashup.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.struts.action.DynaActionForm;
import org.wltea.analyzer.lucene.IKAnalyzer;
import org.wltea.analyzer.lucene.IKQueryParser;
import org.wltea.analyzer.lucene.IKSimilarity;


import com.mashup.domain.Product;

public interface ISearchService {
	public Analyzer getAnalyzer() ;

	public void setAnalyzer(Analyzer analyzer) ;
	public Directory getDirectory() ;

	public void setDirectory(Directory directory);

	public IndexSearcher getIsearcher() ;

	public void setIsearcher(IndexSearcher isearcher);


	public List search(String keyword) throws ParseException ;
	public List searchRelated(String keyword);
	public List caSearch(String categoryId);
	public List adSearch(DynaActionForm form) ;

	
}
