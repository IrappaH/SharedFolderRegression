package test.script;

import static org.testng.Assert.assertEquals;
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
import pages.OtherUserPersonalFolder;
import pages.OtherUserPersonalFolderCreation;
import pages.PersonalFolderDelete;
import pages.PersonalFolderSearch;
import tests.InitialTestSetup;

public class OtherPersonalFolderSmokeTest extends InitialTestSetup {
	private OtherUserPersonalFolderCreation otheruserpersonalFolderCreation = null;
	private OtherUserPersonalFolder otherPersonalFolder = null;
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
		otherPersonalFolder = homePage.gotoOtherUserPersonalFolder();
		Thread.sleep(2000);
		
	}

	@AfterMethod(alwaysRun = true)
	public void deleteAllFolders() throws Exception {
		System.out.println("This is Expected result: " + getLocalizedMessage("noFilesInTable").trim());
		otherPersonalFolder.uiControl("homeLink").click();
		Thread.sleep(2000);

	}
	
	
	  @Test(priority = 1, groups = { "Functional" }, enabled = true)	  
	 // @PriorityInClass(1) 
	  public void verifyFolderIsCreated() throws Exception {
	  setTestDesc(
	  "Verify that Admin can create folder in the root folder.Folder should be created in the root folder"
	  );
	  
	  otheruserpersonalFolderCreation = otherPersonalFolder.gotoOtherUserPersonalFolderCreation();
	  otheruserpersonalFolderCreation.createFolder("AutoTest");
	  assertEquals(otherPersonalFolder.firstRowName().getText(), "AutoTest");
	  
	  }
	 

	@Test(priority = 2,groups = { "Functional" }, enabled = true)
	//@PriorityInClass(2)
	public void verifySingleFileUpload() throws Exception {
		setTestDesc(
				"Verify that Admin can Upload a file to root folder.");
		otherPersonalFolder.uploadBtn().click();
		OtherUserPersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\");
		OtherUserPersonalFolder.uploadFileWithRobot("Single.pdf");
		Thread.sleep(8000);
		otherPersonalFolder.gotoSearchPersonalFolder("Single.pdf");				
		assertEquals(otherPersonalFolder.firstRowName().getText(), "Single.pdf");
		
	}
	@Test(priority = 3, groups = { "Functional" }, enabled = true)
	@PriorityInClass(1)
	public void verifyMultiFileUpload() throws Exception {
		setTestDesc(
				"Verify that Admin can Upload multi files to root folder.");
		otherPersonalFolder.uploadBtn().click();
		otherPersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\");
		otherPersonalFolder.uploadFileWithRobot("\"File1.pdf" + "\" \"File1.jpg"+ "\" \"File1.png" + "\" \"File1.jpeg"+ "\"");
		Thread.sleep(30000);
		otherPersonalFolder.gotoSearchPersonalFolder("File1.pdf");				
		assertEquals(otherPersonalFolder.firstRowName().getText(), "File1.pdf");	
	}
	
	@Test(priority = 4,groups = { "Functional" }, enabled = true)
	//@PriorityInClass(3)
	public void verifySingleFolderDownload() throws Exception {
		setTestDesc(
				"Verify that Admin can download sinlge folder/file from the root folder.file/folder should be available in the root folder");
				
		otherPersonalFolder.selectSinlgeCheckBox().click();
		Thread.sleep(3000);
		otherPersonalFolder.downloadBtn().click();
		Thread.sleep(3000);

	}
	@Test(priority = 5,groups = { "Functional" }, enabled = true)
	@PriorityInClass(3)
	public void verifyMultiFolderDownload() throws Exception {
		setTestDesc(
				"Verify that Admin can download multiple folder/file from the root folder.Folder should be available in the root folder");
		Thread.sleep(3000);
		otherPersonalFolder.selectAllCheckBox().click();
		Thread.sleep(3000);
		otherPersonalFolder.downloadBtn().click();
		Thread.sleep(8000);

	}
	
	
	@Test(priority = 6,groups = { "Functional" }, enabled = true)
	//@PriorityInClass(3)
	public void verifyFolderSearch() throws Exception {
		setTestDesc(
				"Verify that Admin can search folder/file from the root folder.Folder should be available in the root folder");
		String fineName = "File1.png";
		PersonalFolderSearch gotoSearchPersonalFolder = otherPersonalFolder.gotoSearchPersonalFolder(fineName);
		String searchResultStr = gotoSearchPersonalFolder.getSearchResultStr();		
		assertEquals(searchResultStr,"検索結果");		
		 assertEquals(otherPersonalFolder.firstRowName().getText(), fineName);

	}
	
	@Test(priority = 7,groups = { "Functional" }, enabled = true)
	@PriorityInClass(3)
	public void verifySearchPageReturnButton() throws Exception {
		setTestDesc(
				"Verify that Admin can search folder/file from the root folder and click on Return button to home page");
		String fineName = "File1.png";
		PersonalFolderSearch gotoSearchPersonalFolder = otherPersonalFolder.gotoSearchPersonalFolder(fineName);
		gotoSearchPersonalFolder.returnBtn().click();			
		assertEquals(otherPersonalFolder.getRootfolder().getText(), "個人フォルダー");

	}
	
	
	@Test(priority = 8,groups = { "Functional" }, enabled = true)
	@PriorityInClass(1)
	public void verifyContextMenuDownload() throws Exception {
		setTestDesc(
				"Verify that Admin can download folder/file from the context menu in the root folder");		
		otherPersonalFolder.OperationMenu().click();
		Thread.sleep(2000);
		otherPersonalFolder.downloadFromContextBtn().click();
		Thread.sleep(1000);
		boolean contextMenu = otherPersonalFolder.OperationMenu().isDisplayed();
		assertTrue(contextMenu);

	}
	@Test(priority = 9,groups = { "Functional" }, enabled = true)
	//@PriorityInClass(2)
	public void verifyContextMenuRename() throws Exception {
		setTestDesc(
				"Verify that Admin can Rename folder/file from the context menu in the root folder");	
		
		otherPersonalFolder.OperationMenu().click();
		Thread.sleep(2000);
		otherPersonalFolder.renameFromContextBtn().click();
		Thread.sleep(1000);			
		otherPersonalFolder.renameInput().sendKeys("Rename");
		Thread.sleep(1000);		
		otherPersonalFolder.renameOkBtn().click();
		Thread.sleep(1000);				
		otherPersonalFolder.gotoSearchPersonalFolder("Rename");		
	    assertEquals(otherPersonalFolder.firstRowName().getText().substring(0, 6), "Rename");
		
	}
	@Test(priority = 10,groups = { "Functional" }, enabled = true)
	//@PriorityInClass(2)
	public void verifyContextMenuFilePreview() throws Exception {
		setTestDesc(
				"Verify that Admin can open file preview from the context menu in the root folder");	
		
		otherPersonalFolder.OperationMenu().click();
		Thread.sleep(1000);
		otherPersonalFolder.openFilePreview().click();
		Thread.sleep(1000);		
		assertTrue(otherPersonalFolder.fileClose().exists());		
		otherPersonalFolder.fileClose().click();
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
		otherPersonalFolder.OperationMenu().click();
		Thread.sleep(1000);
		otherPersonalFolder.changeSaveDestinationBtnfrmContext().click();
		Thread.sleep(1000);
		otherPersonalFolder.SelectFolderInSameLevel().click();
		Thread.sleep(1000);
		otherPersonalFolder.renameOkBtn().click();
		Thread.sleep(1000);
		otherPersonalFolder.OpenCreatedFolder().click();
		Thread.sleep(1000);
		assertEquals(otherPersonalFolder.firstRowName().getText().substring(0, 4), "File");
	}
	
	
	@Test(priority = 12,groups = { "Functional" }, enabled = true)
	//@PriorityInClass(2)
	public void verifyContextMenuFileShare() throws Exception {
		setTestDesc(
				"Verify that Admin can share file from the context menu in the root folder. ");	
		otherPersonalFolder.OperationMenu().click();
		Thread.sleep(1000);
		otherPersonalFolder.fileShare().click();
		Thread.sleep(1000);
		otherPersonalFolder.clickShareBtn().click();
		Thread.sleep(1000);		
		assertEquals(otherPersonalFolder.shareFileHeader().getText(), "ファイルの一時保存先");
		Thread.sleep(1000);	
		otherPersonalFolder.shareOkBtn().click();
		Thread.sleep(1000);	
	}
	

	@Test(priority = 13,groups = { "Functional" }, enabled = true)
	@PriorityInClass(3)
	public void verifyContextMenuDelete() throws Exception {
		setTestDesc(
				"Verify that Admin can delete folder/file from the context menu in the root folder");
		String BeforeDltfileCnt = otherPersonalFolder.getFileCount();
		otherPersonalFolder.OperationMenu().click();
		Thread.sleep(2000);
		otherPersonalFolder.deleteBtnfromContext().click();
		Thread.sleep(2000);
		otherPersonalFolder.deleteMsgOkBtn().click();
		Thread.sleep(2000);
		String AfterDltfileCnt = otherPersonalFolder.getFileCount();
		assertNotEquals(BeforeDltfileCnt,AfterDltfileCnt);

	}
	
	@Test(priority = 13, groups = { "Functional" }, enabled = true)
//	@PriorityInClass(4)
	public void verifySingleFileFolderDelete() throws Exception {
		setTestDesc(
				"Verify that Admin can delete folder/file from the root folder.Folder should be available in the root folder");
		String BeforeDltfileCnt = otherPersonalFolder.getFileCount();
		otherPersonalFolder.selectSinlgeCheckBox().click();
		PersonalFolderDelete deleteFolder = otherPersonalFolder.gotoPersonalFolderDelete();
		deleteFolder.okBtn().click();
		Thread.sleep(4000);
		String AfterDltfileCnt = otherPersonalFolder.getFileCount();
		assertNotEquals(BeforeDltfileCnt,AfterDltfileCnt);
	}
	
	@Test(priority = 14, groups = { "Functional" }, enabled = true)
//	@PriorityInClass(4)
	public void verifyMultiFileFolderDelete() throws Exception {
		setTestDesc(
				"Verify that Admin can delete folder/file from the root folder.Folder should be available in the root folder");
		String BeforeDltfileCnt = otherPersonalFolder.getFileCount();
		otherPersonalFolder.selectAllCheckBox().click();
		PersonalFolderDelete deleteFolder = otherPersonalFolder.gotoPersonalFolderDelete();
		deleteFolder.okBtn().click();
		Thread.sleep(4000);
		String AfterDltfileCnt = otherPersonalFolder.getFileCount();
		assertNotEquals(BeforeDltfileCnt,AfterDltfileCnt);

	}
	
	@Test(priority = 15, groups = { "Functional" }, enabled = true)	  
	 // @PriorityInClass(1) 
	  public void verifyFolderFileMove() throws Exception {
	  setTestDesc(
	  "Verify that Admin can move file to sub folders in the root folder."
	  );
	  otherPersonalFolder.uploadBtn().click();
	  otherPersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\");
	  otherPersonalFolder.uploadFileWithRobot("\"File1.pdf" + "\" \"File1.jpg"+ "\" \"File1.png" + "\" \"File1.jpeg"+ "\"");
		Thread.sleep(30000);
		otheruserpersonalFolderCreation = otherPersonalFolder.gotoOtherUserPersonalFolderCreation();
		otheruserpersonalFolderCreation.createFolder("AutoTest");
		Thread.sleep(1000);
		otherPersonalFolder.selectSinlgeCheckBox().click();
		Thread.sleep(1000);
		otherPersonalFolder.changeSaveDestinationBtn().click();
		Thread.sleep(1000);
		otherPersonalFolder.SelectFolderInSameLevel().click();
		Thread.sleep(1000);
		otherPersonalFolder.renameOkBtn().click();
		Thread.sleep(1000);
		otherPersonalFolder.OpenCreatedFolder().click();
		Thread.sleep(1000);
		assertEquals(otherPersonalFolder.firstRowName().getText().substring(0, 4), "File");
	  
	  
	  }
	
	@Test(priority = 16, groups = { "Functional" }, enabled = true)	  
	 // @PriorityInClass(1) 
	  public void verifyFolderMoveToSubFolder() throws Exception {
	  setTestDesc(
	  "Verify that Admin can move folder to sub folders in the root folder."
	  );

	  otheruserpersonalFolderCreation = otherPersonalFolder.gotoOtherUserPersonalFolderCreation();
		otheruserpersonalFolderCreation.createFolder("AutoTest1");
		Thread.sleep(1000);
		otherPersonalFolder.selectSinlgeCheckBox().click();
		Thread.sleep(1000);
		otherPersonalFolder.changeSaveDestinationBtn().click();
		Thread.sleep(1000);
		otherPersonalFolder.SelectFolderInSameLevel().click();
		Thread.sleep(1000);
		otherPersonalFolder.renameOkBtn().click();
		Thread.sleep(1000);
		otherPersonalFolder.OpenCreatedFolder().click();
		Thread.sleep(1000);
		assertEquals(otherPersonalFolder.firstRowName().getText().substring(0, 9), "AutoTest1");
	  
	  
	  }
	
	@Test(priority = 17, groups = { "Functional" }, enabled = true)	  
	 // @PriorityInClass(1) 
	  public void verifyMultipleFileFolderMoveToSubFolder() throws Exception {
	  setTestDesc(
	  "Verify that Admin can move multiple file/folder to sub folders in the root folder."
	  );
		String BeforeDltfileCnt = otherPersonalFolder.getFileCount();
		otherPersonalFolder.selectAllCheckBox().click();
		Thread.sleep(1000);
		otherPersonalFolder.selectSecondCheckBox().click();
		otherPersonalFolder.changeSaveDestinationBtn().click();
		Thread.sleep(1000);
		otherPersonalFolder.SelectFolderInSameLevel().click();
		Thread.sleep(1000);
		otherPersonalFolder.renameOkBtn().click();
		Thread.sleep(1000);
		String AfterDltfileCnt = otherPersonalFolder.getFileCount();
		assertNotEquals(BeforeDltfileCnt, AfterDltfileCnt);
	  
	  
	  }
	
	@Test(priority = 18, groups = { "Functional" }, enabled = true)	  
	 // @PriorityInClass(1) 
	  public void verifyFolderFileFolderMoveToSharedFolder() throws Exception {
	  setTestDesc(
	  "Verify that Admin can move multiple files/folders from personal folder to the Shared folders."
	  );

	  otherPersonalFolder.uploadBtn().click();
	  otherPersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\");
	  otherPersonalFolder.uploadFileWithRobot("\"File1.pdf" + "\" \"File1.jpg"+ "\" \"File1.png" + "\" \"File1.jpeg"+ "\"");
		Thread.sleep(40000);
		otheruserpersonalFolderCreation = otherPersonalFolder.gotoOtherUserPersonalFolderCreation();
		otheruserpersonalFolderCreation.createFolder("AutoTest1");
	  	String BeforeDltfileCnt = otherPersonalFolder.getFileCount();
	  	otherPersonalFolder.selectAllCheckBox().click();
		Thread.sleep(1000);
		otherPersonalFolder.changeSaveDestinationBtn().click();
		Thread.sleep(1000);
		otherPersonalFolder.SelectFileMovetoHome().click();
		Thread.sleep(1000);
		otherPersonalFolder.SelectFileMovetoSharedFolder().doubleClick();
		Thread.sleep(1000);
		otherPersonalFolder.renameOkBtn().click();
		Thread.sleep(4000);
		
		String AfterDltfileCnt = otherPersonalFolder.getFileCount();
		assertNotEquals(BeforeDltfileCnt, AfterDltfileCnt);	  
	  }
	
	@Test(priority = 19, groups = { "Functional" }, enabled = true)	  
	 // @PriorityInClass(1) 
	  public void verifysharedFilesList() throws Exception {
	  setTestDesc(
	  "Verify that Admin can Click 'Display of shared files' from the left panel."
	  );
	 
		otherPersonalFolder.DisplayOfSharedFiles().click();
		Thread.sleep(1000);
		
		assertTrue(otherPersonalFolder.CheckNewTabWindowDisplayed());	  
	  }
	

}
