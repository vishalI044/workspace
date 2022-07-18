package test;

import java.io.FileInputStream;
import java.util.Properties;

import com.msf.log.Logger;

public class test {

	private static Logger log = Logger.getLogger(test.class);

	public static void main(String[] args) throws Exception {

		Properties JSLogProperties = new Properties();

		String file = "C:\\Users\\chevvanthi.e\\Documents\\CUB-DOCS\\SBU2Libs-msf_log\\src\\test\\jslog.properties";
		JSLogProperties.load(new FileInputStream(file));
		Logger.setLogger(JSLogProperties);
		log = Logger.getLogger(test.class);
		
		log.debug("debugging mode");
	}

}
