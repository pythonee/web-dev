package com.mashup.service.impl;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.wltea.analyzer.IKSegmentation;
import org.wltea.analyzer.Lexeme;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.mashup.domain.Comment;
import com.mashup.domain.Sensitiveword;
import com.mashup.search.CategoryMapFromDB;
import com.mashup.service.ICommentFilter;

public class CommentFilter implements ICommentFilter {
	private static final Log log = LogFactory.getLog(CommentFilter.class);
	Map wordMap;
	
	public CommentFilter(List wordList) {
		wordMap = new TreeMap();
		for (int i=0;i<wordList.size();i++){
			Sensitiveword word=(Sensitiveword)wordList.get(i);
			String s="";
			for(int j=0;j<word.getSensitiveWord().length();j++){
				s+="*";
				log.debug("generate ***");
			}
			wordMap.put(word.getSensitiveWord(), s);
		}
		log.debug("CommentFilter constructor");
		// 从数据库读关键词
	}

	public Comment Filter(Comment comment) throws IOException {

		String commentContent = comment.getCommentContent();
		IKSegmentation ikSeg = new IKSegmentation(new StringReader(
				commentContent), true);

		try {
			Lexeme lexeme = null;
			while ((lexeme = ikSeg.next()) != null) {
				if (wordMap.containsKey(lexeme.getLexemeText())) {
					commentContent = commentContent.substring(0, lexeme
							.getBeginPosition())
							+ wordMap.get(lexeme.getLexemeText())
							+ commentContent.substring(lexeme.getEndPosition());
				}
			}
			comment.setCommentContent(commentContent);
		} catch (IOException e) {
			log.debug(e);
		}

		return comment;
	}

	public String Filter(String commentContent) throws IOException {
		// comment.setCommentStr("*************");

		IKSegmentation ikSeg = new IKSegmentation(new StringReader(
				commentContent), true);

		try {
			Lexeme lexeme = null;
			while ((lexeme = ikSeg.next()) != null) {
				log.debug(lexeme.getLexemeText());
				log.debug(" [");
				log.debug(lexeme.getBeginPosition() + "--");
				log.debug(lexeme.getEndPosition() + "]");
				if (wordMap.containsKey(lexeme.getLexemeText())) {

					commentContent = commentContent.substring(0, lexeme
							.getBeginPosition())
							+ wordMap.get(lexeme.getLexemeText())
							+ commentContent.substring(lexeme.getEndPosition());
				}
			}
			log.debug(commentContent);
		} catch (IOException e) {
			log.debug(e);
		}

		return commentContent;
	}

	public void AnalyzerDEMO() throws IOException {
		String text = "他从马上摔下来了。你马上下来一下。";
		StringReader reader=new StringReader(text);
		IKSegmentation ikSeg = new IKSegmentation(reader, true);
		try {
			Lexeme lexeme = null;
			while ((lexeme = ikSeg.next()) != null) {
				log.debug(lexeme.getLexemeText()+" | ");
			}
		} catch (IOException e) {
			log.debug(e);
		}
	}

}
