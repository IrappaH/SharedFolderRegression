package cocoro.lib.central;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import com.relevantcodes.extentreports.ExtentReports;

import cocoro.enums.ConfigType;
import cocoro.lib.loader.JsonLoader;
import cocoro.lib.loader.LocalizeStringsLoader;
import cocoro.lib.reporter.ExtentReporter;
import cocoro.lib.tdloader.LStringMap;
import cocoro.logger.LoggerFactory;

public enum COCOROSingleton {
	INSTANCE;
	private Logger logger;
	// private SettingsMenuLoader menuLoader;
	private ThreadLocal<String> contextName = new ThreadLocal<String>();
	private ThreadLocal<ContextConfiguration> contextConfig = new ThreadLocal<ContextConfiguration>();
	private ThreadLocal<ExtentReports> contextReporter = new ThreadLocal<ExtentReports>();
	private LStringMap localizeStringProvider;
	// private ThreadLocal<LocalizeStringsJsonLoader> localizeStringProvider = new
	// ThreadLocal<LocalizeStringsJsonLoader>();
	// private ThreadLocal<StatusMetaDataProvider> statusMetaDataProvider = new
	// ThreadLocal<StatusMetaDataProvider>();

	@SuppressWarnings("unused")
	// private Map<StatusType, StatusMetaData> statusMetaDataMap= new
	// HashMap<StatusType, StatusMetaData>();;
	// public final String SNMP_AGENT_PATH = "C:\\Simulator";
	// public final String SNMP_AGENT_VERSION = "2";
	// public final String OID_FILE_SOURCE_DIR = "/samples/source";
	// public final String OID_FILE_GEN_DIR = "/samples/gen";
	// private MFPSimulator simulator;

	public void init(ITestContext context) throws Exception {
		logger = LoggerFactory.createLogger();
		System.out.println(" init logger thread   :   " + Thread.currentThread().getId());
	}

	/*
	 * public SettingsMenuLoader getMenuLoader() { return menuLoader; }
	 */

	public Logger getLogger() {
		return logger;
	}

	public synchronized void processTestContext(ITestContext context) throws Exception {
		System.out.println("processTestContext    :   " + Thread.currentThread().getId());
		String cName = context.getName();
		contextName.set(cName);
		this.contextConfig.set(new ContextConfiguration(context));

		// if (context.getCurrentXmlTest().getParameter(("configfile").toLowerCase()) !=
		// null) {
		this.contextReporter.set(ExtentReporter.createReporter(this.contextConfig.get()));
		// loader = new JsonLoader();
		// Here name has to be converted to full path of Locator file
		// loader.load(System.getProperty("user.dir") + File.separator + "Locators" +
		// File.separator
		// + this.getClass().getSimpleName() + ".json");

		if (context.getCurrentXmlTest().getParameter(("configfile").toLowerCase()) != null) {
			this.localizeStringProvider = new LocalizeStringsLoader().load(getConfiguration().getConfigValue(ConfigType.INPUTS_DIR),getConfiguration().getConfigValue(ConfigType.LANGUAGE));
			System.out.println("The config values "+this.localizeStringProvider);
			// menuLoader = new SettingsMenuLoader(
			// getConfiguration().getConfigValue(ConfigType.INPUTS_DIR) +
			// "\\PermissionBasedList.xlsx");
			// this.statusMetaDataProvider.set(new
			// StatusMetaDataProvider(getConfiguration().getConfigValue(ConfigType.INPUTS_DIR)));
		}

	}

	public ContextConfiguration getConfiguration() {
		return this.contextConfig.get();
	}

	public ExtentReports getReporter() {
		return this.contextReporter.get();
	}

	public String getLocalizedMessage(String stringID) {
		return this.localizeStringProvider.getLString(stringID);
	}

	/*
	 * public void setStatusMetaDataMap(Map<StatusType, StatusMetaData> map) {
	 * this.statusMetaDataMap = map; }
	 * 
	 * public StatusMetaDataProvider getStatusMetaDataProvider() { return
	 * this.statusMetaDataProvider.get(); }
	 * 
	 * public void setCurrentSimulator(MFPSimulator simulator) { this.simulator =
	 * simulator; }
	 * 
	 * public boolean isAnySimulatorActive() { return this.simulator != null; }
	 * 
	 * public MFPSimulator getActiveSimulator() { return this.simulator; }
	 * 
	 * public String getOidFileName(MFPModel type) { switch (type) { case ARIES:
	 * return "Aries"; case PASTEL: return "Pastel"; case GRIFFIN: return "Griffin";
	 * case NEO: return "Neo"; case COUNTERGRAPH: return "Countergraph"; case
	 * LEXMARK: return "Lexmark"; default: return "Pastel"; } }
	 */

	public String getconfigValue(ConfigType type) {
		return this.contextConfig.get().getConfigValue(type);
	}

	public int getSmallWaitTime() {
		return Integer.parseInt(getconfigValue(ConfigType.WAIT_SMALL));
	}

	public int getMediumWaitTime() {
		return Integer.parseInt(getconfigValue(ConfigType.WAIT_MEDIUM));
	}

	public int getHighWaitTime() {
		return Integer.parseInt(getconfigValue(ConfigType.WAIT_HIGH));
	}

}
