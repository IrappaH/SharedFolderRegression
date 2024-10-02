package test.script;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import cocoro.lib.uiauto.DriverFactory;
import cocoro.testng.listener.PriorityInClass;
import pages.HomePage;
import pages.LoginPage;
import pages.PersonalFolder;
import pages.PersonalFolderCreation;
import pages.PersonalFolderDelete;
import pages.PersonalFolderSearch;
import tests.FileUpload;
import tests.InitialTestSetup;

public class SearchPersonalFolderTest extends InitialTestSetup {
	private PersonalFolderCreation personalFolderCreation = null;
	private PersonalFolder personalFolder = null;
	private HomePage homePage = null;
	String projectPath = System.getProperty("user.dir");
	@BeforeClass(alwaysRun = true)
	public void login(ITestContext context) throws Exception {
		setupClassCommonSteps(context);
		this.config.ReadConfig(context);

		setDriver(new DriverFactory().createDriver(this.config).getDriver());
		LoginPage loginPage = goToLoginPage();
		loginPage.userNameTxtbox().sendKeys("test1.cocoro@gmail.com");
		loginPage.passwordTxtbox().sendKeys("Demo88Demo");
		homePage = loginPage.login();
		Thread.sleep(5000);

	}


	@BeforeMethod(alwaysRun = true)
	public void verifyFolderIsEmpty() throws Exception {
		personalFolder = homePage.gotoPersonalFolder();
		Thread.sleep(2000);
		System.out.println("This is NoData from App: " + personalFolder.uiControl("noData").getText());
		System.out.println("This is NoData from Expected: " + getLocalizedMessage("noFilesInTable").trim());
		if (personalFolder.uiControl("noData").getText().trim().equals(getLocalizedMessage("noFilesInTable").trim())) {
			System.out.println("No folders are found..!");
		} else {
			personalFolder.selectAllCheckBox().click();
			PersonalFolderDelete deleteFolder = personalFolder.gotoPersonalFolderDelete();
			deleteFolder.okBtn().click();
			Thread.sleep(2000);
			assertEquals(personalFolder.uiControl("noData").getText(), getLocalizedMessage("noFilesInTable").trim());
		}
	}

	@AfterMethod(alwaysRun = true)
	public void deleteAllFolders() throws Exception {
		// System.out.println("This is Expected result:
		// "+getLocalizedMessage("noFilesInTable").trim());
		if ((personalFolder.uiControl("noData").getText().trim())
				.equals(getLocalizedMessage("noFilesInTable").trim())) {
			System.out.println("After Method: No data");
			//this.getDriver().navigate().refresh();
			personalFolder.uiControl("homeLink").click();
			//getDriver().findElement(By.xpath("//A[@data-v-24bb6368=''][text()='ホーム']")).click();
			Thread.sleep(2000);
		} else {
			personalFolder.selectAllCheckBox().click();
			PersonalFolderDelete deleteFolder = personalFolder.gotoPersonalFolderDelete();
			deleteFolder.okBtn().click();
			Thread.sleep(2000);
			// assertEquals(personalFolder.uiControl("noData").getText(),
			// getLocalizedMessage("noData"));
			personalFolder.uiControl("homeLink").click();
			//getDriver().findElement(By.xpath("//A[@data-v-24bb6368=''][text()='ホーム']")).click();
			//this.getDriver().navigate().refresh();
			Thread.sleep(2000);
		}
	}

	
	  @Test(priority = 1, groups = { "Functional" }, enabled = true)	  
	 // @PriorityInClass(1) 
	  public void verifyFolderIsCreated() throws Exception {
	  setTestDesc(
	  "Verify that Admin can create folder in the root folder.Folder should be created in the root folder"
	  );
	  
	  personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
	  personalFolderCreation.createFolder("AutoTest");
	  personalFolder.gotoSearchPersonalFolder("AutoTest");
	  assertEquals(personalFolder.firstRowName().getText(), "AutoTest");
	  Thread.sleep(2000);
	  assertEquals(personalFolder.checkReturnBtn().getText(), "戻る");
	  Thread.sleep(2000);
	  personalFolder.checkReturnBtn().click();
	  
	  }
	 

	@Test(priority = 2,groups = { "Functional" }, enabled = true)
	//@PriorityInClass(2)
	public void verifySingleFileUpload() throws Exception {
		setTestDesc(
				"Verify that Admin can Upload a file to root folder.");
		personalFolder.uploadBtn().click();
		PersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\");
		PersonalFolder.uploadFileWithRobot("Single.pdf");
		Thread.sleep(8000);
		personalFolder.gotoSearchPersonalFolder("Single.pdf");				
		assertEquals(personalFolder.firstRowName().getText(), "Single.pdf");
		Thread.sleep(2000);
		  personalFolder.checkReturnBtn().click();
		
	}
	@Test(priority = 3, groups = { "Functional" }, enabled = true)
	@PriorityInClass(1)
	public void verifyMultiFileUpload() throws Exception {
		setTestDesc(
				"Verify that Admin can Upload multi files to root folder.");
		personalFolder.uploadBtn().click();
		PersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\");
		PersonalFolder.uploadFileWithRobot("\"File1.pdf" + "\" \"File1.jpg"+ "\" \"File1.png" + "\" \"File1.jpeg"+ "\"");
		Thread.sleep(30000);
		personalFolder.gotoSearchPersonalFolder("File1.pdf");				
		assertEquals(personalFolder.firstRowName().getText(), "File1.pdf");	
		Thread.sleep(2000);
		  personalFolder.checkReturnBtn().click();
	}
	
	
	@Test(priority = 6,groups = { "Functional" }, enabled = true)
	//@PriorityInClass(3)
	public void verifyFolderSearch() throws Exception {
		setTestDesc(
				"Verify that Admin can search folder/file from the root folder.Folder should be available in the root folder");
		String fineName = "File1.png";
		PersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\");
		PersonalFolder.uploadFileWithRobot("\"File1.pdf" + "\" \"File1.jpg"+ "\" \"File1.png" + "\" \"File1.jpeg"+ "\"");
		Thread.sleep(30000);
		PersonalFolderSearch gotoSearchPersonalFolder = personalFolder.gotoSearchPersonalFolder(fineName);
		String searchResultStr = gotoSearchPersonalFolder.getSearchResultStr();		
		assertEquals(searchResultStr,"検索結果");		
		 assertEquals(personalFolder.firstRowName().getText(), fineName);

	}
	
	@Test(priority = 7,groups = { "Functional" }, enabled = true)
	@PriorityInClass(3)
	public void verifySearchPageReturnButton() throws Exception {
		setTestDesc(
				"Verify that Admin can search folder/file from the root folder and click on Return button to home page");
		PersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\");
		PersonalFolder.uploadFileWithRobot("\"File1.pdf" + "\" \"File1.jpg"+ "\" \"File1.png" + "\" \"File1.jpeg"+ "\"");
		Thread.sleep(30000);
		String fineName = "File1.png";
		PersonalFolderSearch gotoSearchPersonalFolder = personalFolder.gotoSearchPersonalFolder(fineName);
		gotoSearchPersonalFolder.returnBtn().click();			
		assertEquals(personalFolder.getRootfolder().getText(), "個人フォルダー");

	}
	
	

}
