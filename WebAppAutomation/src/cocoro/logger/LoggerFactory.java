package cocoro.logger;

import java.io.File;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerFactory {

	public static Logger log = null;

	private static Logger createLogger(String logFileName, String loggerName) {
		String applicationLogFileName = System.getProperty("user.dir") + File.separator + logFileName + ".log";
		Properties pro = configureLogger(applicationLogFileName, loggerName);
		PropertyConfigurator.configure(pro);
		log = Logger.getLogger(loggerName);
		return log;
	}
	
	public static Logger createLogger() {
		return LoggerFactory.createLogger("Application","Main");	
	}

	public static Logger getLogger() {
		if (log != null) {
			return log;
		} else {
			log = createLogger("Application_Main", "Main");
			return log;
		}
	}

	private static Properties configureLogger(String applicationLogFileName, String loggerName) {
		Properties props = new Properties();
		// Application Logs
		props.put("log4j.logger." + loggerName, "debug, dest1");
		props.put("log4j.appender.dest1", "org.apache.log4j.RollingFileAppender");
		props.put("log4j.appender.dest1.maxFileSize", "2000KB");
		props.put("log4j.appender.dest1.maxBackupIndex", "5");
		props.put("log4j.appender.dest1.layout", "org.apache.log4j.PatternLayout");
		props.put("log4j.appender.dest1.layout.ConversionPattern", "%d{ISO8601} [%t] %5p %C{1}:%L - %m%n");
		props.put("log4j.appender.dest1.Append", "false");
		props.put("log4j.appender.dest1.File", applicationLogFileName);
		props.put("log4j.appender.dest1.Append", "false");
		return props;

	}
}
