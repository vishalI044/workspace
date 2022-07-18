package com.operations;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class FileOps2 extends Thread{
	//public static final Logger log=LogManager.getLogger(FileOps2.class);
	public static final Logger logger=Logger.getLogger(FileOps2.class.getName());
	String data="";
	char arr[]=new char[2000];
public void run() {

	try {
		FileReader fr=new FileReader("D:\\files\\demo.txt");
		BufferedReader input = new BufferedReader(fr);
		input.read(arr);
		data=new String(arr);
		//System.out.println(data);
		input.close();
	}catch(Exception e) {
		e.printStackTrace();
	}
}
	public static void main(String[] args) throws SecurityException, IOException {
		//FileOps2 obj2=new FileOps2();
		//obj2.start();
		boolean append = true;
        FileHandler handler = new FileHandler("D:\\javaprog\\default.log",append);
        //Logger logger = Logger.getLogger("com.operations");
        logger.addHandler(handler);
       SimpleFormatter formatter = new SimpleFormatter();
      handler.setFormatter(formatter);
        String req="{\"login_by\":\"MPin\",\"time-slot\":\"\",\"event-id\":\"MBLOGIN_1657616344499\",\"app-id\":\"d5c76d174e5d0bb1d12b94292a0c8305\",\"cust-id\":\"100852656\",\"addr-network\":\"2401:4900:4ac2:c164:4b60:129c:4bb0:4609\",\"user-id\":\"100852656\",\"succ-fail-flg\":\"S\",\"error-code\":\"0\",\"error-desc\":\"SUCCESS\",\"device-id\":\"e1e7461c80e3350c\",\"session-id\":\"\",\"sys-time\":\"12-07-2022 14:29:04.499\",\"device_city\":\"\",\"device_country\":\"\",\"current-login-ip\":\"2401:4900:4ac2:c164:4b60:129c:4bb0:4609\",\"country_code\":\"\",\"cust_card_id\":\"100852656\",\"account-id\":\"\",\"AddEntity1\":\"\",\"AddEntity2\":\"\",\"AddEntity3\":\"\",\"AddEntity4\":\"\",\"AddEntity5\":\"\",\"AddEntity6\":\"\",\"AddEntity7\":\"\",\"AddEntity8\":\"\",\"AddEntity9\":\"\",\"AddEntity10\":\"\",\"HostId\":\"F\",\"cust_id\":\"100852656\"}";
        logger.info(req);
	}

}
