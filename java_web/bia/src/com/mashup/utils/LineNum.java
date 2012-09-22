package com.mashup.utils;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.*;
import java.util.ArrayList;

import javax.swing.*;

import org.apache.poi.util.SystemOutLogger;

import com.sun.rmi.rmid.ExecOptionPermission;

import bsh.This;

public class LineNum extends JFrame
{
	private JPanel bottomPanel = new JPanel();
	private JTextArea filePathArea = new JTextArea(5, 20);
	
	private boolean isExplainStatue = false;
	
	private int totalCount = 0;
	
	
	private int packageCount = 0;
	private int packageExplainCount = 0;
	private int packageSpaceCount = 0;
	
	
	private int javaCount = 0;
	private int xmlCount = 0;
	private int jspCount = 0;
	private int jsCount = 0;
	private int cssCount = 0;
	private int otherCount = 0;
	
	

	private InputStream input = null;
	private BufferedReader br = null;

	private DecimalFormat myFormat = null;
	private static ArrayList<String> filelist = new ArrayList<String>(); 

	public LineNum(String title)
	{
		super(title);
		
		//设置JFrame面板  
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		bottomPanel.setLayout(new BorderLayout());
		bottomPanel.add(new JScrollPane(filePathArea));
		container.add(bottomPanel, BorderLayout.CENTER);
		String root = This.class.getClassLoader().getResource(".").toString();
		int index = root.indexOf("bia");
		root = root.substring(root.indexOf("/"),index+3);
		File[] files = {new File(root + "/src"),
						new File(root + "/test"),
						new File(root + "/WebRoot")
						};
		CalculateLineNum(files);
		filePathArea.setText(filePathArea.getText() + "\n" + " 总行数（含注释，不含空格）："	+ totalCount + "行");
		filePathArea.setText(filePathArea.getText() + "\n" + " java文件总行数（含注释，不含空格）："	+ javaCount + "行");
		filePathArea.setText(filePathArea.getText() + "\n" + " jsp文件总行数（不含注释，不含空格）："	+ jspCount + "行");
		filePathArea.setText(filePathArea.getText() + "\n" + " js文件总行数（不含注释，不含空格）："	+ jsCount + "行");
		filePathArea.setText(filePathArea.getText() + "\n" + " css文件总行数（不含注释，不含空格）："	+ cssCount + "行");
		filePathArea.setText(filePathArea.getText() + "\n" + " xml文件总行数（不含注释，不含空格）："	+ xmlCount + "行");
		filePathArea.setText(filePathArea.getText() + "\n" + " 其他文件总行数（不含注释，不含空格）："	+ otherCount + "行");
	}

	/* 
	 * 计算并显示统计信息 
	 */
	private void CalculateLineNum(File[] files)
	{
		for (File file : files)
		{ 
			packageCount = 0;
			packageExplainCount = 0;
			packageSpaceCount = 0;

			if (file.exists())
			{
				displayLineNum(file);
				myFormat = (DecimalFormat) NumberFormat.getPercentInstance();
				myFormat.applyPattern("00%");
				if (packageCount > 0)
				{
					double programPercent = (double) (packageCount - packageSpaceCount)
							/ (double) packageCount;
					double explainPercent = (double) packageExplainCount
							/ (double) packageCount;
					double spacePercent = (double) packageSpaceCount / (double) packageCount;
					try
					{
						filePathArea.setText(filePathArea.getText() + "\n" + file.getCanonicalPath()+" 总行数："	+ packageCount + "行");

					} catch (Exception e)
					{
						e.printStackTrace();
					}
					totalCount += packageCount - packageSpaceCount;
					filePathArea.setText(filePathArea.getText() + "\n" + " 程序行数："+ (packageCount  - packageSpaceCount) + "行,百分比："
							+ myFormat.format(programPercent));
					filePathArea.setText(filePathArea.getText() + "\n" + " 注释行数："+ packageExplainCount + "行,百分比："
							+ myFormat.format(explainPercent));
					filePathArea.setText(filePathArea.getText() + "\n" + " 空行行数："+ packageSpaceCount + "行,百分比："
									+ myFormat.format(spacePercent));
				} 
			}
		}
	}

	//循环访问目录及子目录，统计代码总行数，注释行数及空行行数  
	public void displayLineNum(File file)
	{
		String[] subPaths = file.list();
		if (subPaths.length == 0)
		{
			return;
		}
		//循环对子目录进行访问计算行数处理  
		for (int i = 0; i < subPaths.length; i++)
		{
			int count = 0;
			int fileExplainCount = 0;
			int fileSpaceCount = 0;
			File subFile = new File(file.getAbsolutePath() + "\\" + subPaths[i]);
			
			if (subFile.isFile())
			{
				String subFilePath = subFile.getAbsolutePath();
				String extendName = subFilePath.substring(subFilePath
						.lastIndexOf(".") + 1, subFilePath.length());
				
				if (extendName.equals("dtd"))
				{
					continue;
				}				
				
				try
				{
					input = new FileInputStream(subFile);
					BufferedReader br = new BufferedReader(
							new InputStreamReader(input));
					String lineValue = br.readLine();
					while (lineValue != null)
					{
						count++;
						if (lineValue.trim().equals(""))
						{
							fileSpaceCount++;
						}
						if (extendName.equals("java"))
						{
							if(isExplainStatue == false)  
	                        {  
	                            if(lineValue.trim().startsWith("//"))  
	                            {  
	                                fileExplainCount++;  
	                            }  
	                            if(lineValue.trim().equals(""))  
	                            {  
	                                fileSpaceCount++;  
	                            }  
	                            if(lineValue.trim().startsWith("/*"))  
	                            {  
	                                fileSpaceCount++;  
	                                isExplainStatue = true;  
	                            }
	                        }
							else {
								fileExplainCount++;
								if (lineValue.trim().equals("*/"))
								{
									isExplainStatue = false;
								}
							}
						}
						else if (extendName.equals("css"))
						{
							if (lineValue.trim().startsWith("/*") && lineValue.trim().endsWith("*/"))
							{
								fileExplainCount++;
							}
						}
						else if(extendName.equals("jsp")){
							if (lineValue.trim().startsWith("<!--") && lineValue.trim().endsWith("-->"))
							{
								fileExplainCount++;
							}
						}
						else if (extendName.equals("js"))
						{
							if (lineValue.trim().startsWith("//"))
							{
								fileExplainCount++;
							}
						}
						lineValue = br.readLine();
					}
					packageCount += count;
					packageExplainCount += fileExplainCount;
					packageSpaceCount += fileSpaceCount;
					//filePathArea.setText(filePathArea.getText() + "\n" + subFile.getAbsolutePath()); 
					
					if(extendName.equals("java")){
						javaCount += count - fileSpaceCount;
					}else if (extendName.equals("jsp"))
					{
						jspCount += count - fileExplainCount - fileSpaceCount;
						
					}else if (extendName.equals("js"))
					{
						jsCount += count - fileExplainCount - fileSpaceCount;
					}
					else if (extendName.equals("css"))
					{
						cssCount += count - fileExplainCount - fileSpaceCount;

					}else if (extendName.equals("xml"))
					{
						xmlCount += count - fileExplainCount - fileSpaceCount;
					}else {
						otherCount += count - fileExplainCount - fileSpaceCount;
					}					
					
					br.close();
					input.close();
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			} else
			{
				//去除相关的文件夹，并递归调用该方法，遍历文件夹
				if (!subFile.getAbsolutePath().contains("svn") 
					&& !subFile.getAbsolutePath().contains("lib") 
					&& !subFile.getAbsolutePath().contains("tld")
					&& !subFile.getAbsolutePath().contains("taobao")
					&& !subFile.getAbsolutePath().contains("images")
					&& !subFile.getAbsolutePath().contains("yaml")
					&& !subFile.getAbsolutePath().contains("flash")
					&& !subFile.getAbsolutePath().contains("fusionChart")
					&& !subFile.getAbsolutePath().contains("classes")
					&& !subFile.getAbsolutePath().contains("META-INF")
					&& !subFile.getAbsolutePath().contains("dao")
					&& !subFile.getAbsolutePath().contains("hbm")
					&& !subFile.getAbsolutePath().contains("domain")
					&& !subFile.getAbsolutePath().contains("jquery_pagination")
					&& !subFile.getAbsolutePath().contains("redmond"))
				{
					displayLineNum(subFile);
				}
				
			}
		}
	}
	
    public static void refreshFileList(String strPath) { 
        File dir = new File(strPath); 
        File[] files = dir.listFiles(); 
        
        if (files == null) 
            return; 
        for (int i = 0; i < files.length; i++) { 
            if (files[i].isDirectory()) { 
                refreshFileList(files[i].getAbsolutePath()); 
            } else { 
                String strFileName = files[i].getAbsolutePath().toLowerCase();
                System.out.println("---"+strFileName);
                filelist.add(files[i].getAbsolutePath());                    
            } 
        } 
    }


	public static void main(String args[])
	{
		LineNum lineFrame = new LineNum("Bia 项目代码行数统计");
		lineFrame.setBounds(212, 159, 600, 420);
		lineFrame.setVisible(true);
		lineFrame.setResizable(true);
		
		lineFrame.addWindowListener(new WindowAdapter(){
			public void  windowClosing(WindowEvent we){
				System.exit(0);
			}
		});
	}
}