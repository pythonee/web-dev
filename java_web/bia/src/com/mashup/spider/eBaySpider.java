package com.mashup.spider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.HasParentFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;

import com.mashup.domain.Product;
import com.mashup.search.CategoryMapFromDB;

public class eBaySpider {
	Set<String> alreadyIncluded=new HashSet<String>();
	Queue<String> URLQueue=new LinkedList<String>();
	public static final String PATTERN_MASHUP_EBAY="http://item.eachnet.com/prd/\\d+_prd\\.html";
	public static final String PATTERN_A_HREF="<a\\s+href\\s*=\\s*\"([^\"]*)\"";
	
	CategoryMapFromDB categoryMapFromDB;
	
	public CategoryMapFromDB getCategoryMapFromDB() {
		return categoryMapFromDB;
	}

	public void setCategoryMapFromDB(CategoryMapFromDB categoryMapFromDB) {
		this.categoryMapFromDB = categoryMapFromDB;
	}
	
	private void extractURL(String url){
		try{
			Parser parser=new Parser(url);
			parser.setEncoding("gb2312");
			//System.out.println(url);
			NodeFilter linkFilter=new NodeClassFilter(LinkTag.class);
			NodeList urlList=parser.extractAllNodesThatMatch(linkFilter);
			
			//System.out.println(urlList.size());
			
			String newUrl;
			for(int i=0;i!=urlList.size();++i){
				LinkTag linkNode=(LinkTag)urlList.elementAt(i);
				newUrl=linkNode.extractLink();
				if(newUrl.matches(PATTERN_MASHUP_EBAY)){
					URLQueue.offer(newUrl);
					//System.out.println(newUrl);
				}
			}			
		}catch(Exception e){
			System.out.println(url);
			System.out.println("ExtractURL Exception occurs.");
		}
	}
	private void extractURLMsg(String url){
		final String PATTERN_MASHUP_PRICE="(\\d+[.]\\d+)";
		try{
			Parser parser=new Parser(url);
			parser.setEncoding("gb2312");
			NodeFilter priceTagFilter=new TagNameFilter("ul");
			NodeFilter priceParentTagFilter=new TagNameFilter("div");
			NodeFilter priceParentAttributeFilter=new HasAttributeFilter("class","itemPrice");
			NodeFilter priceParentFilter=new AndFilter(priceParentTagFilter,priceParentAttributeFilter);
			NodeFilter hasPriceParentFilter=new HasParentFilter(priceParentFilter);
			NodeFilter priceFilter=new AndFilter(priceTagFilter,hasPriceParentFilter);
			
			NodeFilter nameTagFilter=new TagNameFilter("h1");
			NodeFilter nameParentTagFilter=new TagNameFilter("div");
			NodeFilter nameParentAttributeFilter=new HasAttributeFilter("id","itemId");
			NodeFilter nameParentFilter=new AndFilter(nameParentTagFilter,nameParentAttributeFilter);
			NodeFilter hasNameParentFilter=new HasParentFilter(nameParentFilter);
			NodeFilter nameFilter=new AndFilter(nameTagFilter,hasNameParentFilter);
			
			NodeFilter desTagFilter=new TagNameFilter("ul");
			NodeFilter desAttributeFilter=new HasAttributeFilter("id","atb_info");
			NodeFilter desFilter=new AndFilter(desTagFilter,desAttributeFilter);
			
			NodeFilter imgTagFilter=new NodeClassFilter(ImageTag.class);
			NodeFilter imgIdFilter=new HasAttributeFilter("id","itemImage");
			NodeFilter imgFilter=new AndFilter(imgTagFilter,imgIdFilter);
			
			NodeFilter pnFilter=new OrFilter(priceFilter,nameFilter);
			NodeFilter pniFilter=new OrFilter(pnFilter,imgFilter);
			NodeFilter filter=new OrFilter(pniFilter,desFilter);
			
			NodeList nodeList=parser.extractAllNodesThatMatch(filter);
					
			String name=nodeList.elementAt(0).toPlainTextString().trim();
			System.out.println(name);
			
			ImageTag imgNode=(ImageTag)nodeList.elementAt(1);
			String imgLocation=imgNode.extractImageLocn();
			System.out.println(imgLocation);

			String price=nodeList.elementAt(2).toPlainTextString().trim();
			Pattern pricePattern=Pattern.compile(PATTERN_MASHUP_PRICE);
			Matcher priceMatcher=pricePattern.matcher(price);
			if(priceMatcher.find()){
				price=priceMatcher.group(1)+"0";
			}else{
				return;
			}
			System.out.println(price);
			
			NodeList attriNodes=nodeList.elementAt(3).getChildren();
			String attribute="";
			for(int i=0;i!=attriNodes.size();++i){
				attribute+=attriNodes.elementAt(i).toPlainTextString().trim().replaceAll("\\s*","").replaceAll("&nbsp;", " ")+" ";
			}
			System.out.println(attribute);
			
			Product product = new Product();
			try {
				product.setProductName(name);
				product.setPrice(Double.parseDouble(price));
				product.setProductDesc(attribute);
				product.setProductImg(imgLocation);
				product.setUrl(url);
				product.setScore(1.1);
				product.setLevelClick(1);
				product.setSource("易趣");

				categoryMapFromDB.addProductToDB(product);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			/*
			NodeList nodeList=parser.extractAllNodesThatMatch(filter);
			
			//NodeList nameNode=nodeList.elementAt(0).getChildren();
			//String name=nameNode.elementAt(0).toPlainTextString().trim();
			String name=nodeList.elementAt(0).toPlainTextString().trim();
			System.out.println(name);
			
			Node priceNode=nodeList.elementAt(1);
			String price=priceNode.toPlainTextString().trim();
			Pattern pricePattern=Pattern.compile(PATTERN_MASHUP_PRICE);
			Matcher priceMatcher=pricePattern.matcher(price);
			if(priceMatcher.find()){
				price=priceMatcher.group(1);
			}else{
				return;
			}
			//price=price.substring(price.length()-6,price.length()-1);
			System.out.println(price);
			
			ImageTag imgNode=(ImageTag)nodeList.elementAt(2);
			String imgLocation=imgNode.extractImageLocn();
			System.out.println(imgLocation);
			
			NodeList attriNodes=nodeList.elementAt(3).getChildren();
			String attribute="";
			for(int i=0;i!=attriNodes.size();++i){
				attribute+=attriNodes.elementAt(i).toPlainTextString().trim().replaceAll("\\s*","").replaceAll("&nbsp;", " ")+" ";
			}
			System.out.println(attribute);
			*/
		}catch(Exception e){
			System.out.println(url);
			System.out.println("ExtractURLMsg Exception occurs");
		}
	}
	private void readSeeds(){
		String path = this.getClass().getClassLoader().getResource("/")
				.getPath()
				+ "eBaySeeds.txt";
		try {
			path = java.net.URLDecoder.decode(path, "utf-8");
			System.out.println(path);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		File file=new File(path);
		BufferedReader reader=null;
		try{
			reader=new BufferedReader(new FileReader(file));
			String tempString=null;
			while((tempString=reader.readLine())!=null){
				URLQueue.offer(tempString);
			}
			int size=URLQueue.size();
			String url;
			for(int i=0;i<size;++i){
				url=URLQueue.poll();
				extractURL(url);
				System.out.println(url);
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try{
				reader.close();
			}catch(IOException e){
				
			}
		}
	}
	public void start(){
		//String originalUrl="http://item.taobao.com/auction/item_detail-0db1-db40f69007d84f6030de63e6640cbf5e.htm";
		//originalUrl="http://item.taobao.com/auction/item_detail.htm?item_num_id=3395217987#";
		//URLQueue.offer(originalUrl);
		
		readSeeds();
		String url;
		//url="http://item.eachnet.com/prd/1250844111217954_prd.html";
		//extractURL(url);
		//URLQueue.offer(url);
		int size=0;
		
		while(!URLQueue.isEmpty()){
			url=URLQueue.poll();
			if(!alreadyIncluded.contains(url)){
				System.out.println(++size+"/"+URLQueue.size());
				extractURLMsg(url);
				if(URLQueue.size()<100000){
					extractURL(url);
				}
				System.out.println(url);
				alreadyIncluded.add(url);
			}
		}
	}
}

