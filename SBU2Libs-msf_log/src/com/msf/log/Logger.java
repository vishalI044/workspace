package com.msf.log;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;

public abstract class Logger {

	public static String type = "local";
	public static Logger log;
	protected static int Level_INFO = 202;
	protected static int Level_WARN = 204;
	protected static int Level_DEBUG = 206;
	protected static int Level_ERROR = 208;
	protected static int Level_FATAL = 210;
	protected static int Level_AUDIT = 212;

	protected static int ConfigLevel_INFO = 1;
	protected static int ConfigLevel_WARN = ConfigLevel_INFO << 1;
	protected static int ConfigLevel_DEBUG = ConfigLevel_WARN << 1;
	protected static int ConfigLevel_ERROR = ConfigLevel_DEBUG << 1;
	protected static int ConfigLevel_FATAL = ConfigLevel_ERROR << 1;
	protected static int ConfigLevel_AUDIT = ConfigLevel_FATAL << 1;

	public static int LogMode_DEVELOPMENT = 1;
	public static int LogMode_LIVE = 2;

	protected int iLogMode = LogMode_DEVELOPMENT;
	protected static int iLogLevel = Level_INFO;

	public Logger() {

	}

	public static Logger getLogger(Class clazz) {
		return log;
	}

	public static void setLogger(Properties properties) throws Exception {

		String logType = properties.getProperty("type");
		String sLogLevel = properties.getProperty("log_level");

		if (logType.equalsIgnoreCase("REMOTE")) {

			JSLogLogger.setJSLogger(properties);

			type = "remote";

			try {
				int iConfigLevel = Integer.parseInt(sLogLevel);
				setLogLevel(iConfigLevel);
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("log_level should be set as integer 1, 2, 4, 8, 16 or 32.");
			}
		} else {
			
			Log4JLogger.setLog4JLogger(properties);
			
		}

	}

	private static void setLogLevel(int iConfigLevel) {

		if (iConfigLevel == JSLogLogger.ConfigLevel_AUDIT) {
			iLogLevel = Logger.Level_AUDIT;
		} else if (iConfigLevel == JSLogLogger.ConfigLevel_DEBUG) {
			iLogLevel = Logger.Level_DEBUG;
		} else if (iConfigLevel == JSLogLogger.ConfigLevel_ERROR) {
			iLogLevel = Logger.Level_ERROR;
		} else if (iConfigLevel == JSLogLogger.ConfigLevel_FATAL) {
			iLogLevel = Logger.Level_FATAL;
		} else if (iConfigLevel == JSLogLogger.ConfigLevel_INFO) {
			iLogLevel = Logger.Level_INFO;
		} else if (iConfigLevel == JSLogLogger.ConfigLevel_WARN) {
			iLogLevel = Logger.Level_WARN;
		}
		System.out.println("iLogLevel..." + iLogLevel);
	}

	public abstract void info(Object sMessage);

	public abstract void warn(String sMessage, Throwable t);

	public abstract void warn(Object sMessage);

	public abstract void debug(String sMessage, Throwable t);

	public abstract void debug(Object sMessage);

	public abstract void error(String sMessage, Throwable t);

	public abstract void error(Object sMessage);

	public abstract void close() throws IOException;

}
