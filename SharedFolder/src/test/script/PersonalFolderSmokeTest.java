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

public class PersonalFolderSmokeTest extends InitialTestSetup {
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
		loginPage.userNameTxtbox().sendKeys("test5.cocoro@gmail.com");
		loginPage.passwordTxtbox().sendKeys("Demo88Demo");
		homePage = loginPage.login();
		Thread.sleep(5000);

	}

	@BeforeMethod(alwaysRun = true)
	public void verifyFolderIsEmpty() throws Exception {
		personalFolder = homePage.gotoPersonalFolder();
		Thread.sleep(2000);
		//System.out.println("This is NoData from App: " + personalFolder.uiControl("noData").getText());
		//System.out.println("This is NoData from Expected: " + getLocalizedMessage("noFilesInTable").trim());
		//if (personalFolder.uiControl("noData").getText().trim().equals(getLocalizedMessage("noFilesInTable").trim())) {
		//	System.out.println("I'm Before() - No folders are found..!");
		//} else {
		//	personalFolder.selectAllCheckBox().click();
		//	PersonalFolderDelete deleteFolder = personalFolder.gotoPersonalFolderDelete();
		//	deleteFolder.okBtn().click();
		///	Thread.sleep(2000);
		//	System.out.println("I'm Before() Else part - No folders are found..!");
		//	assertEquals(personalFolder.uiControl("noData").getText(), getLocalizedMessage("noFilesInTable").trim());
		//}
	}

	@AfterMethod(alwaysRun = true)
	public void deleteAllFolders() throws Exception {
		 System.out.println("This is Expected result: "+getLocalizedMessage("noFilesInTable").trim());
		//if ((personalFolder.uiControl("noData").getText().trim()).equals(getLocalizedMessage("noFilesInTable").trim())) {
			//System.out.println("After Method: No data");		
		//	personalFolder.uiControl("homeLink").click();			
			//Thread.sleep(2000);
		//} else {
			//personalFolder.selectAllCheckBox().click();
			//PersonalFolderDelete deleteFolder = personalFolder.gotoPersonalFolderDelete();
			//deleteFolder.okBtn().click();
			//Thread.sleep(2000);
			// assertEquals(personalFolder.uiControl("noData").getText(),
			// getLocalizedMessage("noData"));
		 
			personalFolder.uiControl("homeLink").click();
			// getDriver().findElement(By.xpath("//A[@data-v-24bb6368=''][text()='ホーム']")).click();
			// this.getDriver().navigate().refresh();
			Thread.sleep(2000);
		//}
	}
	
	
	  @Test(priority = 1, groups = { "Functional" }, enabled = true)	  
	 // @PriorityInClass(1) 
	  public void verifyFolderIsCreated() throws Exception {
	  setTestDesc(
	  "Verify that Admin can create folder in the root folder.Folder should be created in the root folder"
	  );
	  
	  personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
	  personalFolderCreation.createFolder("AutoTest");
	  assertEquals(personalFolder.firstRowName().getText(), "AutoTest");
	  
	  }
	 

	@Test(priority = 2,groups = { "Functional" }, enabled = true)
	//@PriorityInClass(2)
	public void verifySingleFileUpload() throws Exception {
		setTestDesc(
				"Verify that Admin can Upload a file to root folder.");
		personalFolder.uploadBtn().click();
		personalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\");
		personalFolder.uploadFileWithRobot("Single.pdf");
		Thread.sleep(8000);
		personalFolder.gotoSearchPersonalFolder("Single.pdf");				
		assertEquals(personalFolder.firstRowName().getText(), "Single.pdf");
		
	}
	@Test(priority = 3, groups = { "Functional" }, enabled = true)
	@PriorityInClass(1)
	public void verifyMultiFileUpload() throws Exception {
		setTestDesc(
				"Verify that Admin can Upload multi files to root folder.");
		personalFolder.uploadBtn().click();
		personalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\");
		personalFolder.uploadFileWithRobot("\"File1.pdf" + "\" \"File1.jpg"+ "\" \"File1.png" + "\" \"File1.jpeg"+ "\"");
		Thread.sleep(30000);
		personalFolder.gotoSearchPersonalFolder("File1.pdf");				
		assertEquals(personalFolder.firstRowName().getText(), "File1.pdf");	
	}
	
	@Test(priority = 4,groups = { "Functional" }, enabled = true)
	//@PriorityInClass(3)
	public void verifySingleFolderDownload() throws Exception {
		setTestDesc(
				"Verify that Admin can download sinlge folder/file from the root folder.file/folder should be available in the root folder");
				
		personalFolder.selectSinlgeCheckBox().click();
		Thread.sleep(3000);
		personalFolder.downloadBtn().click();
		Thread.sleep(3000);

	}
	@Test(priority = 5,groups = { "Functional" }, enabled = true)
	@PriorityInClass(3)
	public void verifyMultiFolderDownload() throws Exception {
		setTestDesc(
				"Verify that Admin can download multiple folder/file from the root folder.Folder should be available in the root folder");
		Thread.sleep(3000);
		personalFolder.selectAllCheckBox().click();
		Thread.sleep(3000);
		personalFolder.downloadBtn().click();
		Thread.sleep(8000);

	}
	
	
	@Test(priority = 6,groups = { "Functional" }, enabled = true)
	//@PriorityInClass(3)
	public void verifyFolderSearch() throws Exception {
		setTestDesc(
				"Verify that Admin can search folder/file from the root folder.Folder should be available in the root folder");
		String fineName = "File1.png";
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
		String fineName = "File1.png";
		PersonalFolderSearch gotoSearchPersonalFolder = personalFolder.gotoSearchPersonalFolder(fineName);
		gotoSearchPersonalFolder.returnBtn().click();			
		assertEquals(personalFolder.getRootfolder().getText(), "個人フォルダー");

	}
	
	
	@Test(priority = 8,groups = { "Functional" }, enabled = true)
	@PriorityInClass(1)
	public void verifyContextMenuDownload() throws Exception {
		setTestDesc(
				"Verify that Admin can download folder/file from the context menu in the root folder");		
		personalFolder.OperationMenu().click();
		Thread.sleep(2000);
		personalFolder.downloadFromContextBtn().click();
		Thread.sleep(1000);
		boolean contextMenu = personalFolder.OperationMenu().isDisplayed();
		assertTrue(contextMenu);

	}
	@Test(priority = 9,groups = { "Functional" }, enabled = true)
	//@PriorityInClass(2)
	public void verifyContextMenuRename() throws Exception {
		setTestDesc(
				"Verify that Admin can Rename folder/file from the context menu in the root folder");	
		
		personalFolder.OperationMenu().click();
		Thread.sleep(2000);
		personalFolder.renameFromContextBtn().click();
		Thread.sleep(1000);			
		personalFolder.renameInput().sendKeys("Rename");
		Thread.sleep(1000);		
		personalFolder.renameOkBtn().click();
		Thread.sleep(1000);				
		personalFolder.gotoSearchPersonalFolder("Rename");		
	    assertEquals(personalFolder.firstRowName().getText().substring(0, 6), "Rename");
		
	}
	@Test(priority = 10,groups = { "Functional" }, enabled = true)
	//@PriorityInClass(2)
	public void verifyContextMenuFilePreview() throws Exception {
		setTestDesc(
				"Verify that Admin can open file preview from the context menu in the root folder");	
		
		personalFolder.OperationMenu().click();
		Thread.sleep(1000);
		personalFolder.openFilePreview().click();
		Thread.sleep(1000);		
		assertTrue(personalFolder.fileClose().exists());		
		personalFolder.fileClose().click();
	}
	
	@Test(priority = 11,groups = { "Functional" }, enabled = true)
	//@PriorityInClass(2)
	public void verifyContextMenuFileMove() throws Exception {
		setTestDesc(
				"Verify that Admin can move file from the context menu in the root folder. Files or folders should move to selected folder ");	
		//personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		//personalFolderCreation.createFolder("AutoTest");
		Thread.sleep(1000);
		//personalFolder.firstRowName()
		personalFolder.OperationMenu().click();
		Thread.sleep(1000);
		personalFolder.changeSaveDestinationBtnfrmContext().click();
		Thread.sleep(1000);
		personalFolder.SelectFolderInSameLevel().click();
		Thread.sleep(1000);
		personalFolder.renameOkBtn().click();
		Thread.sleep(1000);
		personalFolder.OpenCreatedFolder().click();
		Thread.sleep(1000);
		assertEquals(personalFolder.firstRowName().getText().substring(0, 4), "File");
	}
	

	@Test(priority = 12,groups = { "Functional" }, enabled = true)
	@PriorityInClass(3)
	public void verifyContextMenuDelete() throws Exception {
		setTestDesc(
				"Verify that Admin can delete folder/file from the context menu in the root folder");
		String BeforeDltfileCnt = personalFolder.getFileCount();
		personalFolder.OperationMenu().click();
		Thread.sleep(2000);
		personalFolder.deleteBtnfromContext().click();
		Thread.sleep(2000);
		personalFolder.deleteMsgOkBtn().click();
		Thread.sleep(2000);
		String AfterDltfileCnt = personalFolder.getFileCount();
		assertNotEquals(BeforeDltfileCnt,AfterDltfileCnt);

	}
	
	@Test(priority = 12,groups = { "Functional" }, enabled = true)
	//@PriorityInClass(2)
	public void verifyContextMenuFileShare() throws Exception {
		setTestDesc(
				"Verify that Admin can share file from the context menu in the root folder. ");	
		personalFolder.OperationMenu().click();
		Thread.sleep(1000);
		personalFolder.fileShare().click();
		Thread.sleep(1000);
		personalFolder.clickShareBtn().click();
		Thread.sleep(4000);		
		assertEquals(personalFolder.shareFileHeader().getText().trim(), "ファイルの一時保存先");
		Thread.sleep(1000);	
		personalFolder.shareOkBtn().click();
		Thread.sleep(1000);	
	}
	
	
	@Test(priority = 13, groups = { "Functional" }, enabled = true)
//	@PriorityInClass(4)
	public void verifySingleFileFolderDelete() throws Exception {
		setTestDesc(
				"Verify that Admin can delete folder/file from the root folder.Folder should be available in the root folder");
		String BeforeDltfileCnt = personalFolder.getFileCount();
		personalFolder.selectSinlgeCheckBox().click();
		PersonalFolderDelete deleteFolder = personalFolder.gotoPersonalFolderDelete();
		deleteFolder.okBtn().click();
		Thread.sleep(4000);
		String AfterDltfileCnt = personalFolder.getFileCount();
		assertNotEquals(BeforeDltfileCnt,AfterDltfileCnt);

	}
	
	@Test(priority = 14, groups = { "Functional" }, enabled = true)
//	@PriorityInClass(4)
	public void verifyMultiFileFolderDelete() throws Exception {
		setTestDesc(
				"Verify that Admin can delete folder/file from the root folder.Folder should be available in the root folder");
		String BeforeDltfileCnt = personalFolder.getFileCount();
		personalFolder.selectAllCheckBox().click();
		PersonalFolderDelete deleteFolder = personalFolder.gotoPersonalFolderDelete();
		deleteFolder.okBtn().click();
		Thread.sleep(4000);
		String AfterDltfileCnt = personalFolder.getFileCount();
		assertNotEquals(BeforeDltfileCnt,AfterDltfileCnt);
	}
	
	@Test(priority = 15, groups = { "Functional" }, enabled = true)	  
	 // @PriorityInClass(1) 
	  public void verifyFolderFileMove() throws Exception {
	  setTestDesc(
	  "Verify that Admin can move file to sub folders in the root folder."
	  );
	  	personalFolder.uploadBtn().click();
		personalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\");
		personalFolder.uploadFileWithRobot("\"File1.pdf" + "\" \"File1.jpg"+ "\" \"File1.png" + "\" \"File1.jpeg"+ "\"");
		Thread.sleep(30000);
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("AutoTest");
		Thread.sleep(1000);
		personalFolder.selectSinlgeCheckBox().click();
		Thread.sleep(1000);
		personalFolder.changeSaveDestinationBtn().click();
		Thread.sleep(1000);
		personalFolder.SelectFolderInSameLevel().click();
		Thread.sleep(1000);
		personalFolder.renameOkBtn().click();
		Thread.sleep(1000);
		personalFolder.OpenCreatedFolder().click();
		Thread.sleep(1000);
		assertEquals(personalFolder.firstRowName().getText().substring(0, 4), "File");
	  
	  
	  }
	
	@Test(priority = 16, groups = { "Functional" }, enabled = true)	  
	 // @PriorityInClass(1) 
	  public void verifyFolderMoveToSubFolder() throws Exception {
	  setTestDesc(
	  "Verify that Admin can move folder to sub folders in the root folder."
	  );

		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("AutoTest1");
		Thread.sleep(1000);
		personalFolder.selectSinlgeCheckBox().click();
		Thread.sleep(1000);
		personalFolder.changeSaveDestinationBtn().click();
		Thread.sleep(1000);
		personalFolder.SelectFolderInSameLevel().click();
		Thread.sleep(1000);
		personalFolder.renameOkBtn().click();
		Thread.sleep(1000);
		personalFolder.OpenCreatedFolder().click();
		Thread.sleep(1000);
		assertEquals(personalFolder.firstRowName().getText().substring(0, 9), "AutoTest1");
	  
	  
	  }
	
	@Test(priority = 17, groups = { "Functional" }, enabled = true)	  
	 // @PriorityInClass(1) 
	  public void verifyMultipleFileFolderMoveToSubFolder() throws Exception {
	  setTestDesc(
	  "Verify that Admin can move multiple file/folder to sub folders in the root folder."
	  );
		String BeforeDltfileCnt = personalFolder.getFileCount();
		personalFolder.selectAllCheckBox().click();
		Thread.sleep(1000);
		personalFolder.selectSecondCheckBox().click();
		personalFolder.changeSaveDestinationBtn().click();
		Thread.sleep(1000);
		personalFolder.SelectFolderInSameLevel().click();
		Thread.sleep(1000);
		personalFolder.renameOkBtn().click();
		Thread.sleep(1000);

		String AfterDltfileCnt = personalFolder.getFileCount();
		assertNotEquals(BeforeDltfileCnt, AfterDltfileCnt);
	  
	  
	  }
	
	@Test(priority = 18, groups = { "Functional" }, enabled = true)	  
	 // @PriorityInClass(1) 
	  public void verifyFolderFileFolderMoveToSharedFolder() throws Exception {
	  setTestDesc(
	  "Verify that Admin can move multiple files/folders from personal folder to the Shared folders."
	  );

	    personalFolder.uploadBtn().click();
		personalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\");
		personalFolder.uploadFileWithRobot("\"File1.pdf" + "\" \"File1.jpg"+ "\" \"File1.png" + "\" \"File1.jpeg"+ "\"");
		Thread.sleep(40000);
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("AutoTest1");
	  	String BeforeDltfileCnt = personalFolder.getFileCount();
		personalFolder.selectAllCheckBox().click();
		Thread.sleep(1000);
		personalFolder.changeSaveDestinationBtn().click();
		Thread.sleep(1000);
		personalFolder.SelectFileMovetoHome().click();
		Thread.sleep(1000);
		personalFolder.SelectFileMovetoOtherpersonalFolder().doubleClick();
		Thread.sleep(1000);
		personalFolder.renameOkBtn().click();
		Thread.sleep(1000);
		
		String AfterDltfileCnt = personalFolder.getFileCount();
		assertNotEquals(BeforeDltfileCnt, AfterDltfileCnt);
		
		
	  
	  }
	

}
