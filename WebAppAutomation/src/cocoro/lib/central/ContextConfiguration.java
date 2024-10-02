package cocoro.lib.central;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.openqa.selenium.By;
import org.testng.ITestContext;

import cocoro.enums.ConfigType;
import cocoro.lib.uiauto.UIControl;
import cocoro.lib.utility.Constants;
import cocoro.lib.utility.DataUtils;

public class ContextConfiguration {

	private Map<ConfigType, String> configuration = new HashMap<ConfigType, String>();
	private String cName = null;

	public ContextConfiguration(ITestContext context) {
		
		System.out.println("context.getCurrentXmlTest().getName()  :  "+context.getCurrentXmlTest().getName());
		this.setContextName(context.getName());
		System.out.println("context.getName()  :  "+context.getAttribute("name"));
		// Adding from config.Properties File
		Properties config = new Properties();
		FileInputStream fis = null;
		System.out.println(context.getCurrentXmlTest().getParameter(("configfile").toLowerCase()));
		
		
		
		/*
		 * fis = new FileInputStream(
		 * DataUtils.joinPaths(System.getProperty("user.dir"), "Inputs",
		 * Constants.CONFIG_FILE_NAME));
		 */
		//if (context.getCurrentXmlTest().getParameter(("configfile").toLowerCase()) != null) {
			Map<String, String> params = context.getCurrentXmlTest().getAllParameters();
			if(!params.isEmpty()) {
			for (String key : params.keySet()) {

				if (params.get(key).toLowerCase().endsWith(".properties")) {
					try {
						System.out.println(DataUtils.joinPaths(System.getProperty("user.dir"),
								context.getCurrentXmlTest().getParameter(("configfile").toLowerCase())));
						fis = new FileInputStream(DataUtils.joinPaths(System.getProperty("user.dir"),
								context.getCurrentXmlTest().getParameter(("configfile").toLowerCase())));
						config.load(fis);

						for (Entry<Object, Object> entry : config.entrySet()) {
							System.out.println((String) entry.getKey() + "  : " + (String) entry.getValue());
							configuration.put(ConfigType.valueOf(entry.getKey().toString().contains(".")
									? ((String) entry.getKey()).replace(".", "_").toUpperCase()
									: ((String) entry.getKey()).toUpperCase()), ((String) entry.getValue()));
						}

					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} else {

					try {
						configuration.put(ConfigType.valueOf(key.replace(".", "_").toUpperCase()), params.get(key));
					} catch (Exception e) {
						System.err.println(e.getStackTrace());
						// System.exit(1);
					}
				}
			}
		}
		// Adding from ITestContext File

		String reportFolder = DataUtils.getDateTimeString();
		configuration.put(ConfigType.REPORT_DIR,
				DataUtils.joinPaths(System.getProperty("user.dir"), "Reports", reportFolder));
		configuration.put(ConfigType.INPUTS_DIR, DataUtils.joinPaths(System.getProperty("user.dir"), "inputs"));
		configuration.put(ConfigType.EXTENTREPORT_DIR,
				DataUtils.joinPaths(System.getProperty("user.dir"), "ExtentReport"));
	}

	public void ReadConfig(ITestContext context) {
	//	this.setContextName(context.getName());
		// Adding from config.Properties File
		Properties config = new Properties();
		FileInputStream fis = null;
		System.out.println("The xml test--: "+context.getCurrentXmlTest().getParameter(("configfile").toLowerCase()));

		/*
		 * fis = new FileInputStream(
		 * DataUtils.joinPaths(System.getProperty("user.dir"), "Inputs",
		 * Constants.CONFIG_FILE_NAME));
		 */
		//if (context.getCurrentXmlTest().getParameter(("configfile").toLowerCase()) != null) {
			Map<String, String> params = context.getCurrentXmlTest().getAllParameters();
			if(!params.isEmpty()) {
			for (String key : params.keySet()) {

				if (params.get(key).toLowerCase().endsWith(".properties")) {
					try {
						System.out.println(DataUtils.joinPaths(System.getProperty("user.dir"),
								context.getCurrentXmlTest().getParameter(("configfile").toLowerCase())));
						fis = new FileInputStream(DataUtils.joinPaths(System.getProperty("user.dir"),
								context.getCurrentXmlTest().getParameter(("configfile").toLowerCase())));
						config.load(fis);

						for (Entry<Object, Object> entry : config.entrySet()) {
							System.out.println((String) entry.getKey() + "  : " + (String) entry.getValue());
							configuration.put(ConfigType.valueOf(entry.getKey().toString().contains(".")
									? ((String) entry.getKey()).replace(".", "_").toUpperCase()
									: ((String) entry.getKey()).toUpperCase()), ((String) entry.getValue()));
						}

					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} else {

					try {
						configuration.put(ConfigType.valueOf(key.replace(".", "_").toUpperCase()), params.get(key));
					} catch (Exception e) {
						System.err.println(e.getStackTrace());
						// System.exit(1);
					}
				}
			}
		}
		// Adding from ITestContext File

	
	}
	
	
	
	public String getConfigValue(ConfigType conf) {
		return this.configuration.get(conf);
	}

	public String getContextName() {
		return cName;
	}

	private void setContextName(String cName) {
		this.cName = cName;
	}
}
