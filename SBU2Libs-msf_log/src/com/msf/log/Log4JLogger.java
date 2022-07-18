package com.msf.log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

public class Log4JLogger extends Logger {

	private static org.apache.logging.log4j.Logger log4jlog = LogManager.getLogger(Log4JLogger.class);

	public static void setLog4JLogger(Properties properties) throws Exception {

		if (Logger.log == null) {
			Logger.log = new Log4JLogger(properties);
		}

	}

	public void close() throws IOException {
		// log4jlog.removeAllAppenders(); // log4j2 will automatically shutdown
	}

	public Log4JLogger(Properties properties) throws Exception {

			log4jlog.info("Log4jLogger initialized ....");

			String logConfigurationFile = properties.getProperty("log4jpath");

			ConfigurationSource source = new ConfigurationSource(new FileInputStream(logConfigurationFile));
			Configurator.initialize(null, source);
	}

	public void info(Object sMessage) {

		log4jlog.info(sMessage);
	}

	public void warn(String sMessage, Throwable t) {
		log4jlog.warn(sMessage, t);
	}

	public void warn(Object sMessage) {
		log4jlog.warn(sMessage);
	}

	public void debug(String sMessage, Throwable t) {
		log4jlog.debug(sMessage, t);
	}

	public void debug(Object sMessage) {
		log4jlog.debug(sMessage);
	}

	public void error(String sMessage, Throwable t) {
		log4jlog.error(sMessage, t);
	}

	public void error(Object sMessage) {
		log4jlog.error(sMessage);
	}
}
