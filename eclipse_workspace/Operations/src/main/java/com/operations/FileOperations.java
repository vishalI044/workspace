package com.operations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class FileOperations extends Thread{
	public static final Logger log=Logger.getLogger(FileOperations.class.getName());
	String data="";
	char arr[]=new char[2000];
	boolean append=true;
	public void run() {
		try {
			FileReader fr=new FileReader("D:\\files\\demo.txt");
			BufferedReader input = new BufferedReader(fr);
			input.read(arr);
			data=new String(arr);
			//System.out.println(data);
			input.close();
			for(int i=0;i<1;i++) {	
				long currentTime=System.currentTimeMillis();
				long timeMillis=(currentTime-(currentTime%(4*1000)));
				
				long demo1=currentTime%(4*1000);
				String ConvertedTime=Long.toString(timeMillis);
				String fileName="RGF_"+ConvertedTime+".cxmsg";
				System.out.println(fileName);
				System.out.println(timeMillis);
				System.out.println(currentTime);
				System.out.println(demo1);
				
				File f=new File("D:\\files\\"+fileName);
				
				if(f.createNewFile()) {
					System.out.println("Created file : "+fileName);	
					try {
						FileHandler handler=new FileHandler("D:\\files\\"+fileName,append);
						log.addHandler(handler);
						SimpleFormatter formatter = new SimpleFormatter();
					    handler.setFormatter(formatter);
					    log.info(data);
					    handler.close();
					}catch(Exception e) {
						e.printStackTrace();
					}
				}else {
					System.out.println("File already exists");
				}
			    Thread.sleep(5000);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String args[]) {
		FileOperations fobj=new FileOperations();
		fobj.start();
	}

}
