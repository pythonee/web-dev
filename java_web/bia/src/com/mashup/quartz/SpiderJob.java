package com.mashup.quartz;

import org.apache.log4j.Logger;

import com.mashup.spider.TaoBaoSpider;
import com.mashup.spider.eBaySpider;

;;
public class SpiderJob {

	TaoBaoSpider taobaoSpider;
	eBaySpider ebaySpider;
	private Logger log = Logger.getLogger(this.getClass().getName());

	public void taobaoWork() {
		taobaoSpider.getCategoryMapFromDB().initTopNode();
		log.debug("the only one taobaySpider: start SpiderJob ……");
		taobaoSpider.start();
		log.debug("the only one taobaySpider: start SpiderJob ……");
	}
	public void ebayWork() {
		ebaySpider.getCategoryMapFromDB().initTopNode();
		
		log.debug("the only one ebaySpider: start SpiderJob ……");
		ebaySpider.start();
		log.debug("the only one ebaySpider: start SpiderJob ……");
	}

	public void stop() {
		try {
			taobaoSpider.getCategoryMapFromDB().writer.optimize();
			taobaoSpider.getCategoryMapFromDB().writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.debug("the only one Spider: stop SpiderJob ……");
	}

	public void addToDisk() {

		// try {
		// taobaoSpider.getCategoryMapFromDB().stopInsert=true;
		// while(taobaoSpider.getCategoryMapFromDB().isInserting);
		// taobaoSpider.getCategoryMapFromDB().writer.addIndexesNoOptimize(new
		// Directory[]{taobaoSpider.getCategoryMapFromDB().ramDir});
		// taobaoSpider.getCategoryMapFromDB().writer.optimize();
		// taobaoSpider.getCategoryMapFromDB().ramDir.close();
		// taobaoSpider.getCategoryMapFromDB().ramDir=new RAMDirectory();
		// taobaoSpider.getCategoryMapFromDB().ramwriter= new
		// IndexWriter(taobaoSpider.getCategoryMapFromDB().ramDir,taobaoSpider.getCategoryMapFromDB().analyzer,true,IndexWriter.MaxFieldLength.LIMITED);
		// taobaoSpider.getCategoryMapFromDB().stopInsert=false;
		// } catch (CorruptIndexException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}

	public TaoBaoSpider getTaobaoSpider() {
		return taobaoSpider;
	}

	public void setTaobaoSpider(TaoBaoSpider taobaoSpider) {
		this.taobaoSpider = taobaoSpider;
	}

	public eBaySpider getEbaySpider() {
		return ebaySpider;
	}

	public void setEbaySpider(eBaySpider ebaySpider) {
		this.ebaySpider = ebaySpider;
	}
}
