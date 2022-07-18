package com.operations;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.logging.Logger;


public class FileOps extends Thread{
	//public static final Logger logger=LogManager.getLogger(FileOps.class);
	public static final Logger logger=Logger.getLogger(FileOps.class.getName());
	String data="";
	char arr[]=new char[2000];
public void run() {

	try {
		FileReader fr=new FileReader("D:\\javaprog\\demo.txt");
		BufferedReader input = new BufferedReader(fr);
		input.read(arr);
		data=new String(arr);
		//System.out.println(data);
		input.close();
	}catch(Exception e) {
		e.printStackTrace();
	}
	boolean append=true;
	//String data2="hi welcome";
	for(int i=0;i<3;i++) {
	try {
			Thread.sleep(2000);
		FileWriter fw=new FileWriter("D:\\javaprog\\output2.txt",append);
			
			//FileHandler handler = new FileHandler("D:\\javaprog\\default.log",append);
			//logger.addHandler(handler);
		       //SimpleFormatter formatter = new SimpleFormatter();
		      //handler.setFormatter(formatter);
			BufferedWriter output = new BufferedWriter(fw);
		//byte[] dataBytes = data.getBytes();
		//System.out.println(data2);
		output.write(data+"\n");
		logger.info(data);
		//System.out.println(data);
		output.close();
	}catch(Exception e) {
		e.printStackTrace();
		}
	}
}
	public static void main(String[] args) {
		FileOps obj=new FileOps();
		obj.start();
	}
}
