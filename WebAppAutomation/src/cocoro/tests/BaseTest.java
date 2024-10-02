package cocoro.tests;

import java.lang.reflect.Method;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import cocoro.enums.ConfigType;
import cocoro.lib.central.ContextConfiguration;
import cocoro.lib.central.COCOROSingleton;
import cocoro.lib.uiauto.DriverFactory;


public abstract class BaseTest {
	protected Logger logger;
	private RemoteWebDriver  driver = null;
	protected String contextName = null;
	protected ContextConfiguration config = null;
	protected ExtentReports reporter = null;
	protected ExtentTest test;
	protected String testDesc;
	//protected HomePage homePg = null;
	protected int waitSmall;
	protected int waitMedium;
	protected int waitHigh;
	protected static String httpURL = null;
	
	@BeforeSuite(alwaysRun = true)
	public void configureSuite(ITestContext context) throws Exception {
		COCOROSingleton.INSTANCE.init(context);
	}

	@BeforeTest(alwaysRun = true)
	public void processTestContext(ITestContext context) throws Exception {
		//COCOROSingleton.INSTANCE.init(context);
		COCOROSingleton.INSTANCE.processTestContext(context);
		
	}

	@BeforeClass(alwaysRun = true)
	//@Parameters({""})
	protected void setupClassCommonSteps(ITestContext context) throws Exception {
		this.contextName = context.getName();
		
		
		//COCOROSingleton.INSTANCE.init(context);
		//COCOROSingleton.INSTANCE.processTestContext(context);
		this.config = COCOROSingleton.INSTANCE.getConfiguration();
		logger = COCOROSingleton.INSTANCE.getLogger();
		
		reporter = COCOROSingleton.INSTANCE.getReporter();
		
		//driver = new DriverFactory().createDriver(this.config).getDriver();
		//waitSmall = COCOROSingleton.INSTANCE.getSmallWaitTime();
		//waitMedium = COCOROSingleton.INSTANCE.getMediumWaitTime();
		//waitHigh = COCOROSingleton.INSTANCE.getHighWaitTime();
	}

	protected RemoteWebDriver getDriver() {
		return this.driver;
	}

	protected void setDriver(RemoteWebDriver driver) {
	this.driver = driver;
	}

	protected String getAppVersion() {
		String AppBuildVersion = getDriver().findElement(By.id("lblVersion")).getText();
		System.out.println("The app buld version is "+ AppBuildVersion);
		return AppBuildVersion;
		
	}


	@AfterClass(alwaysRun = true)
	public void closeBrowser(ITestContext context) {
		this.driver.quit();
	}

	@BeforeMethod(alwaysRun = true)
	public void startApplication(Method m) throws Exception {
		test = reporter.startTest(m.getName());
	}

	// This will close the extent report and Driver
	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result) throws Exception {
		switch (result.getStatus()) {
		case ITestResult.SUCCESS:
			onTestSuccess();
			break;
		case ITestResult.FAILURE:
			onTestFailure(result);
			break;
		case ITestResult.SKIP:
			onTestSkipped();
			break;
		}

		if (reporter != null) {
			reporter.endTest(this.test);
			reporter.flush();
		}

	}

	private void onTestSuccess() {
		test.log(LogStatus.PASS, "TestCase Description : " + getTestDesc());
		test.log(LogStatus.PASS, "Test Passed");
	}

	private void onTestFailure(ITestResult result) throws Exception {
		//String screenshotPath = client.captureScreen();
		test.log(LogStatus.FAIL, "TestCase Description : " + getTestDesc());
		test.log(LogStatus.FAIL, "Test Failed");
		test.log(LogStatus.FAIL, "Test failed with Exception : " + result.getThrowable());
		//test.log(LogStatus.FAIL, "Screenshot of failed test " + test.addScreenCapture(screenshotPath));
	}

	private void onTestSkipped() {
		test.log(LogStatus.SKIP, "TestCase Description : " + getTestDesc());
		test.log(LogStatus.SKIP, "Test Skipped");
	}

	private String getTestDesc() {
		return this.testDesc;
	}

	protected void setTestDesc(String testDesc) {
		this.testDesc = testDesc;
	}

	/*protected List<String> getLocalizedMessage(String... ids) {
		return COCOROSingleton.INSTANCE.getLocalizedMessage(ids);
	}*/
	
	protected String getLocalizedMessage(String id) {
		return COCOROSingleton.INSTANCE.getLocalizedMessage(id);
	}
	
	/*protected Object[][] getData(String sheetName) {
		String excelfilePath = DataUtils.joinPaths(config.getConfigValue(ConfigType.INPUTS_DIR),
				Constants.INPUT_FILE_NAME);
		return ExcelLoader.getTestData(excelfilePath, sheetName);
	}*/
	
	protected String getConfiguredUser(){
		return config.getConfigValue(ConfigType.UNAME).toLowerCase();
	}

	/*protected User convertToUser(String userName) throws Exception{
		if (userName.toLowerCase().equals("custom")){
			throw new Exception("You have used the reserved word <custom> as user name.");
		}
		try{
			return User.builtin(UserType.valueOf(userName));
		} catch (Exception e){
			return User.custom(userName);
		}
	}*/
}
