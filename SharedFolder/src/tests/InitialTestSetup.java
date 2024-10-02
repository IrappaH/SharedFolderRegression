package tests;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.internal.BaseTestMethod;

import cocoro.enums.ConfigType;
import cocoro.tests.BaseTest;
import pages.LoginPage;

public class InitialTestSetup extends BaseTest{

	
	protected LoginPage goToLoginPage() throws Exception {		
		getDriver().get(this.config.getConfigValue(ConfigType.URL));
		return new LoginPage(getDriver());
	}
	
}
