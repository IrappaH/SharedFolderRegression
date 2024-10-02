package test.script;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import cocoro.testng.listener.PriorityInClass;
import pages.LoginPage;
import tests.InitialTestSetup;

public class Sample extends InitialTestSetup{

	/*
	 * public static void main(String[] args) throws Exception { // TODO
	 * Auto-generated method stub System.out.println("starting"); JsonLoader js =
	 * new JsonLoader(
	 * "E:\\Automationframework\\SharedFolder\\SharedFolder\\Locators\\Login.json",
	 * "Desktop","jp.co.sharp.bs.smartoffice.synappxgomobile");
	 * 
	 * }
	 */

	@Test(groups = {"groupname1"}, alwaysRun = true)
	@PriorityInClass(1)
	public void homePageDisplayed() throws Exception {
		//setTestDesc("1. Launch web browser.\n" + "2. Go to URL .\n"
	//			+ "3. Verify that home Page is displayed.");
		//this.launch();
		//assertTrue(this.homePg.homPageTab().exists());
		//JsonLoader js = new JsonLoader("E:\\Automationframework\\SharedFolder\\SharedFolder\\Locators\\Login.json","Desktop","jp.co.sharp.bs.smartoffice.synappxgomobile");
	System.out.println("In test");
		LoginPage loginPage = goToLoginPage();
		loginPage.userNameTxtbox().sendKeys("cocoro.tarou123@gmail.com");
		loginPage.passwordTxtbox().sendKeys("Demo88Demo");
		loginPage.login();
		assertEquals("actual", "expected");
	}
	
	

}
