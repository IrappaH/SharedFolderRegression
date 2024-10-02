package cocoro.lib.reporter;

import java.io.File;

import org.openqa.selenium.By;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

import cocoro.enums.ConfigType;
import cocoro.lib.central.ContextConfiguration;
import cocoro.lib.utility.DataUtils;
import cocoro.tests.BaseTest;

public class ExtentReporter {

	public static ExtentReports createReporter(ContextConfiguration cConf) {
		/*
		 * ExtentReports extent = new
		 * ExtentReports(DataUtils.joinPaths(cConf.getConfigValue(ConfigType.REPORT_DIR)
		 * , "TestReport_" + cConf.getContextName()
		 * +"_"+(cConf.getConfigValue(ConfigType.EXECUTION_ENV) + ".html")), false,
		 * DisplayOrder.OLDEST_FIRST);
		 */
		
		  ExtentReports extent = new
		  ExtentReports(DataUtils.joinPaths(cConf.getConfigValue(ConfigType.REPORT_DIR)
		  , "TestReport_" + cConf.getContextName()+ ".html"), false,
		  DisplayOrder.OLDEST_FIRST);
		 

		extent.loadConfig(
				new File(DataUtils.joinPaths(cConf.getConfigValue(ConfigType.EXTENTREPORT_DIR), "ReportsConfig.xml")));

		System.setProperty("org.uncommons.reportng.escape-output", "false");
		// optional
		extent.addSystemInfo("Environment", cConf.getConfigValue(ConfigType.EXECUTION_ENV))
				.addSystemInfo("Execution Engineer", cConf.getConfigValue(ConfigType.EXECUTION_ENGR))
				//.addSystemInfo("Build Number", getDriver().findElement(By.id("lblVersion")).getText());
				.addSystemInfo("Build Number", cConf.getConfigValue(ConfigType.EXECUTION_BUILD));

		return extent;
	}
}
