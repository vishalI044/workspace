package com.msf.log;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Formatter;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;

public class JSLogLogger extends Logger {

	private static org.apache.logging.log4j.Logger log4jLogger = LogManager.getLogger(JSLogLogger.class);

	private Socket sockClient = null;
	private BufferedWriter out = null;

	private String sNamespace = null;
	private String sFilename = null;
	private String sUrl = null;

	private boolean isConnected = false;

	public static void setJSLogger(Properties properties) throws Exception {
		if (Logger.log == null) {
			Logger.log = new JSLogLogger(properties);
		}
	}

	public JSLogLogger(Properties properties) throws Exception {

		new Log4JLogger(properties);

		sNamespace = properties.getProperty("namespace");
		if (sNamespace == null) {
			throw new Exception(
					"Please set namespace in properties as the application name");
		}
		sFilename = properties.getProperty("filename");
		if (sFilename == null) {
			throw new Exception(
					"Please set filename in propertiesas the name of the log file");
		}

		sUrl = properties.getProperty("url");
		if (sUrl == null) {
			throw new Exception(
					"Please set url for the client to connect to central logger. Format is ip:port.");
		}

		init_slog_client();
	}

	private void init_slog_client() {
		String[] sList = sUrl.split(":");
		String sIP = sList[0];
		int iPort = 5679;
		try {
			if (sList[1] != null)
				iPort = Integer.parseInt(sList[1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {

			sockClient = new Socket(sIP, iPort);
			out = new BufferedWriter(new OutputStreamWriter(
					sockClient.getOutputStream()));
			if (sockClient.isConnected()) {
				isConnected = true;
				sendControlMessage();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void sendControlMessage() {
		String sControlMessage = "200" + sNamespace + ":" + sFilename + ":"
				+ "production";
		dispatch(sControlMessage);
	}

	public void log(String sMessage) {

		sMessage = Integer.toString(iLogLevel) + getFormatMessage() + sMessage;
		dispatch(sMessage);
	}

	public void log(Object obj) {
		log(obj.toString());
	}

	public void info(Object sMessage) {

		String sMessagestr = null;
		if (sMessage instanceof Throwable)
			sMessagestr = stackTraceToString((Throwable) sMessage);
		else
			sMessagestr = String.valueOf(sMessage);

		sMessagestr = Integer.toString(Level_INFO) + getFormatMessage()
				+ sMessagestr;
		dispatch(sMessagestr);
	}

	public void warn(String sMessage, Throwable t) {

		sMessage = Integer.toString(Level_WARN) + getFormatMessage() + sMessage
				+ "...." + stackTraceToString(t);
		dispatch(sMessage);

	}

	public void warn(Object sMessage) {

		String sMessagestr = null;
		if (sMessage instanceof Throwable)
			sMessagestr = stackTraceToString((Throwable) sMessage);
		else
			sMessagestr = String.valueOf(sMessage);

		sMessagestr = Integer.toString(Level_WARN) + getFormatMessage()
				+ sMessagestr;
		dispatch(sMessagestr);
	}

	public String getFormatMessage() {
		try {
			return InetAddress.getLocalHost() + " ["
					+ Thread.currentThread().getName() + "] ";
		} catch (Exception e) {
			return " [" + Thread.currentThread().getName() + "] ";
		}

	}

	public void debug(String sMessage, Throwable t) {
		if (iLogLevel != Level_INFO) {
			sMessage = Integer.toString(Level_DEBUG) + getFormatMessage()
					+ sMessage + "...." + stackTraceToString(t);
			dispatch(sMessage);
		}
	}

	public void debug(Object sMessage) {
		if (iLogLevel != Level_INFO) {
			String sMessagestr = null;
			if (sMessage instanceof Throwable)
				sMessagestr = stackTraceToString((Throwable) sMessage);
			else
				sMessagestr = String.valueOf(sMessage);

			sMessagestr = Integer.toString(Level_DEBUG) + getFormatMessage()
					+ sMessagestr;
			dispatch(sMessagestr);
		}
	}

	public void error(String sMessage, Throwable t) {

		sMessage = Integer.toString(Level_ERROR) + getFormatMessage()
				+ sMessage + "   " + stackTraceToString(t);
		dispatch(sMessage);

	}

	public void error(Object sMessage) {

		String sMessagestr = null;
		if (sMessage instanceof Throwable)
			sMessagestr = stackTraceToString((Throwable) sMessage);
		else
			sMessagestr = String.valueOf(sMessage);

		sMessagestr = Integer.toString(Level_ERROR) + getFormatMessage()
				+ sMessagestr;
		dispatch(sMessagestr);
	}

	public String stackTraceToString(Throwable ex) {
		StringWriter errors = new StringWriter();
		ex.printStackTrace(new PrintWriter(errors));
		return errors.toString();

	}

	public void close() throws IOException {
		if (out != null) {
			out.close();
			out = null;
		}
	}

	private void dispatch(String sMessage) {
		Formatter fmt = new Formatter();

		fmt.format("%09d", sMessage.getBytes().length);
		sMessage = fmt.toString() + sMessage;

		if (!isConnected) {
			init_slog_client();
		}

		try {
			out.write(sMessage);
			// log4jLogger.info("TEST............." + sMessage.length()
			// + "..................." + sMessage);

			out.flush();

		} catch (IOException e) {
			log4jLogger.error("   Messsage Transmission failed : " + sMessage,
					e);
			isConnected = false;
		}
	}
}
