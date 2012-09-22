package com.mashup.spider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
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

public class TaoBaoSpider {
	Set<String> alreadyIncluded = new HashSet<String>();// 存储已搜索的链接
	Queue<String> URLQueue = new LinkedList<String>();// 存储待爬取的链接
	public static final String PATTERN_MASHUP_TAOBAO = "http://item.taobao.com/auction/item_detail.*";
	public static final String PATTERN_A_HREF = "<a\\s+href\\s*=\\s*\"([^\"]*)\"";
	// int instancecount=0;
	CategoryMapFromDB categoryMapFromDB;

	public CategoryMapFromDB getCategoryMapFromDB() {
		return categoryMapFromDB;
	}

	public void setCategoryMapFromDB(CategoryMapFromDB categoryMapFromDB) {
		this.categoryMapFromDB = categoryMapFromDB;
	}

	//从一个url产生与该url相关联的所有url，并将其加入待爬取队列
	private void extractURL(String url) {
		try {
			Parser parser = new Parser(url);// 创建解析器对象
			parser.setEncoding("gb2312");// 设置编码为gb2312，以支持简体中文
			// System.out.println(url);
			NodeFilter linkFilter = new NodeClassFilter(LinkTag.class);
			NodeList urlList = parser.extractAllNodesThatMatch(linkFilter);

			// System.out.println(urlList.size());

			String newUrl;
			for (int i = 0; i != urlList.size(); ++i) {
				LinkTag linkNode = (LinkTag) urlList.elementAt(i);
				newUrl = linkNode.extractLink();
				if (newUrl.matches(PATTERN_MASHUP_TAOBAO)) {
					URLQueue.offer(newUrl);// 如果新链接符合taobao的链接规则，则将其加入队列尾
				}
			}
		} catch (Exception e) {
			System.out.println(url);
			System.out.println("ExtractURL Exception occurs.");
		}
	}

	//从商品信息链接中解析出商品的名称、价格、属性、图片位置等信息
	private void extractURLMsg(String url) {
		final String PATTERN_MASHUP_PRICE = "(\\d+[.]\\d+)";
		try {
			Parser parser = new Parser(url);
			parser.setEncoding("gb2312");
			NodeFilter priceTagFilter = new TagNameFilter("li");
			NodeFilter priceAttributeFilter = new HasAttributeFilter("class",
					"detail-price clearfix");
			NodeFilter priceFilter = new AndFilter(priceTagFilter,
					priceAttributeFilter);

			NodeFilter nameTagFilter = new TagNameFilter("h3");
			NodeFilter nameParentTagFilter = new TagNameFilter("div");
			NodeFilter nameParentAttributeFilter = new HasAttributeFilter(
					"class", "detail-hd");
			NodeFilter nameParentFilter = new AndFilter(nameParentTagFilter,
					nameParentAttributeFilter);
			NodeFilter hasNameParentFilter = new HasParentFilter(
					nameParentFilter);
			NodeFilter nameFilter = new AndFilter(nameTagFilter,
					hasNameParentFilter);

			NodeFilter desTagFilter = new TagNameFilter("ul");
			NodeFilter desAttributeFilter = new HasAttributeFilter("class",
					"attributes-list");
			NodeFilter desFilter = new AndFilter(desTagFilter,
					desAttributeFilter);

			NodeFilter imgTagFilter = new NodeClassFilter(ImageTag.class);
			NodeFilter imgIdFilter = new HasAttributeFilter("id", "J_ImgBooth");
			NodeFilter imgFilter = new AndFilter(imgTagFilter, imgIdFilter);

			NodeFilter pnFilter = new OrFilter(priceFilter, nameFilter);
			NodeFilter pniFilter = new OrFilter(pnFilter, imgFilter);
			NodeFilter filter = new OrFilter(pniFilter, desFilter);

			NodeList nodeList = parser.extractAllNodesThatMatch(filter);

			// NodeList nameNode=nodeList.elementAt(0).getChildren();
			// String name=nameNode.elementAt(0).toPlainTextString().trim();
			String name = nodeList.elementAt(0).toPlainTextString().trim();
			System.out.println(name);

			Node priceNode = nodeList.elementAt(1);
			String price = priceNode.toPlainTextString().trim();
			Pattern pricePattern = Pattern.compile(PATTERN_MASHUP_PRICE);
			Matcher priceMatcher = pricePattern.matcher(price);
			if (priceMatcher.find()) {
				price = priceMatcher.group(1);
			} else {
				return;
			}
			// price=price.substring(price.length()-6,price.length()-1);
			System.out.println(price);

			ImageTag imgNode = (ImageTag) nodeList.elementAt(2);
			String imgLocation = imgNode.extractImageLocn();
			System.out.println(imgLocation);

			NodeList attriNodes = nodeList.elementAt(3).getChildren();
			String attribute = "";
			for (int i = 0; i != attriNodes.size(); ++i) {
				attribute += attriNodes.elementAt(i).toPlainTextString().trim()
						.replaceAll("\\s*", "").replaceAll("&nbsp;", " ")
						+ " ";
			}
			System.out.println(attribute);

			Product product = new Product();
			try {
				product.setProductName(name);
				product.setPrice(Double.parseDouble(price));
				product.setProductDesc(attribute);
				product.setProductImg(imgLocation);
				product.setUrl(url);
				product.setScore(3.1);
				product.setLevelClick(1);
				product.setSource("淘宝");

				categoryMapFromDB.addProductToDB(product);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			System.out.println(url);
			System.out.println("ExtractURLMsg Exception occurs");
		}
	}

	//读取种子链接文件
	private void readSeeds() {
		String path = this.getClass().getClassLoader().getResource("/")
				.getPath()
				+ "seeds.txt";
		try {
			path = java.net.URLDecoder.decode(path, "utf-8");
			System.out.println(path);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		File file = new File(path);
		System.out.println(path);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				URLQueue.offer(tempString);
			}
			int size = URLQueue.size();
			String url;
			for (int i = 0; i < size; ++i) {
				url = URLQueue.poll();
				extractURL(url);
				System.out.println(url);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {

			}
		}
	}

	//启动爬虫，开始爬取数据
	public void start() {
		// 为了测试是否单例
		// instancecount++;
		// System.out.println(instancecount+"\n\n\n\n\n");

		// 初始化插入数据库类

		// String
		// originalUrl="http://item.taobao.com/auction/item_detail-0db1-db40f69007d84f6030de63e6640cbf5e.htm";
		// originalUrl="http://item.taobao.com/auction/item_detail.htm?item_num_id=3395217987#";
		// URLQueue.offer(originalUrl);
		readSeeds();

		String url;
		// url="http://item.taobao.com/auction/item_detail.htm?xid=0db1&item_num_id=3760527691&cm_cat=50000697";
		// URLQueue.offer(url);
		int size = 0;
		while (!URLQueue.isEmpty()) {
			url = URLQueue.poll();
			if (!alreadyIncluded.contains(url)) {
				System.out.println(++size + "/" + URLQueue.size());
				extractURLMsg(url);
				if (URLQueue.size() < 100000) {
					extractURL(url);
				}
				System.out.println(url);
				alreadyIncluded.add(url);
			}
		}
	}
}
