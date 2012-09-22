package com.mashup.search;
import java.util.Map;
import java.util.TreeMap;
import java.util.Iterator;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.text.DecimalFormat; 
import java.util.Comparator;
import java.text.Collator;
import java.text.CollationKey;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class WordsFreq {

	Map wordsMap;
	Analyzer analyzer;
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		WordsFreq wordsFreq=new WordsFreq();
		wordsFreq.WordsFreqDemo();
	}
	
	public void WordsFreqDemo()throws IOException{ 
		String[] text=new String[]{
			"也也也也也也也也 也也们们们们们们们们们们们们们们们们也也也也也也也也我我我我我我我我我我我我我我本书采用了独创的自顶向下方法，即从应用层开始沿协议栈向下讲解计算机网络的基本原理，强调应用层范例和应用编程接口，内容深入浅出，注重教学方法，理论与实践相结合。第3版的内容相应更新并反映了网络领域的最新进展，如增加了无线和移动网络一章，扩充了对等网络、BGP、MPLS、网络安全、广播选路和因特网编址及转发方面的材料；还增加了一套实用的实验，并修订了习题。本书适合作为计算机、电子、通信工程相关专业的本科生和研究生的计算机网络课程教科书，对于网络业界甚至网络科研人员，本书也是一本不可多得的参考书。",
			"本书自第一版以来，已经成为世界范围内广泛使用的大学教材以及专业人员的标准参考手册。第二版增加了新的章节，如算法的地位和作用、概率分析与随机算法、线性编程，并且对书中各部分都做了更新。本书内容系统、丰富，从简单的算法到复杂的算法，都有很好的覆盖，适用于各层次的读者。 每章介绍一个算法、一个设计技术、一个应用领域或一个相关问题。书中算法以英语及伪码形式描述，使用了230余幅图来解释算法的工作流程，同时对算法的运行时间做了认真分析。各章具有较好地相对独立性，可作为独立的学习单元。全书共包括920余道练习、140余个问题。在每节后安排有练习，每章后安排有问题，以供课后的复习与自测。 内容：I.基础知识（计算中算法的作用、插入排序、渐进概念、迭代、概率分析与随机算法）、II.排序与统计（堆、快速排序、线性时间排序、中值统计）、III.数据结构（基本数据结构、哈希表、二叉查找树、红黑树、扩展的数据结构）、IV.高级设计与分析技术（动态程序设计、贪心算法）、V.高级数据结构（B-树、二项式堆、菲波那契堆、不相关集合的数据结构）、VI.图解算法（基本图解算法、最小生成树、全配对最短路径、最大流）、VII.相关问题（排序网、矩阵操作、线性程序设计、多项式与快速傅立叶变换、串匹配、计算几何、NP-完全性、近似算法）、VIII.附录 数学知识"
		};
		for(int i=0;i<text.length;i++){
			WordsFreqBySingleProduct(text[i]);
		}

		//System.out.println(this.toString("计算机网络"));
		//System.out.println(this.toWordsString());
	}
	
	public WordsFreq(){
		wordsMap=new TreeMap();
		analyzer =new IKAnalyzer();
	}
	public void WordsFreqBySingleProduct(String description)throws IOException{
			
			Reader reader = new StringReader(description);
			TokenStream tokenStream=(TokenStream)analyzer.tokenStream("", reader);
			TermAttribute termAtt = (TermAttribute)tokenStream.getAttribute(TermAttribute.class);
			//TypeAttribute typeAtt = (TypeAttribute)tokenStream.getAttribute(TypeAttribute.class);
			  
			  while (tokenStream.incrementToken())
			  {
			      String word=termAtt.term();
			      System.out.print(' ');
			      if (wordsMap.containsKey(word)) {
						Integer value = (Integer) wordsMap.get(word);
						wordsMap.put(word,
						new Integer(value.intValue() +1));
					} else {
						wordsMap.put(word, new Integer(1));
					}
			  }
			
	}

	public String toString(String categoryName){
		CollatorComparator comparator = new CollatorComparator(); 
		Map map=new TreeMap(comparator);
		DecimalFormat decimalFormat = new DecimalFormat("00000");
		String mapString="\n"+categoryName+"---分类词频情况如下：\n";
		Iterator wordsIterator1=wordsMap.keySet().iterator();
		while(wordsIterator1.hasNext()){
			String word=(String)wordsIterator1.next();
			map.put(decimalFormat.format(wordsMap.get(word))+"-"+word, wordsMap.get(word));
		}
		Iterator wordsIterator2=map.keySet().iterator();
		while(wordsIterator2.hasNext()){
			String word=(String)wordsIterator2.next();
			mapString+="	"+word;
			mapString+="	";
			mapString+=map.get(word).toString();
			mapString+="\n";
		}
		return mapString;
	}
	
	public String toWordsString(){
		CollatorComparator comparator = new CollatorComparator(); 
		Map map=new TreeMap(comparator);
		String keyWords="";
		Iterator wordsIterator1=wordsMap.keySet().iterator();
		while(wordsIterator1.hasNext()){
			String word=(String)wordsIterator1.next();
			keyWords+=word+" ";
		}
		
		return keyWords;
	}
	
	class CollatorComparator implements Comparator {  
		  
	    Collator collator = Collator.getInstance();  
	  
	    public int compare(Object element1, Object element2) {  
	        CollationKey key1 = collator.getCollationKey(element1.toString());  
	        CollationKey key2 = collator.getCollationKey(element2.toString());  
	        //return key1.compareTo(key2);  
	        return -key1.compareTo(key2);// 加负号将逆序  
	    }  
	  
	} 
	
	
	//test how to read category from xml///////////////////
	
	
	
	
}
