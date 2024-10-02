package test.script;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import cocoro.lib.uiauto.DriverFactory;
import cocoro.lib.uiauto.UIControl;
import cocoro.testng.listener.PriorityInClass;
import pages.HomePage;
import pages.LoginPage;
import pages.PersonalFolder;
import pages.PersonalFolderCreation;
import pages.PersonalFolderDelete;
import pages.PersonalFolderSearch;
import pages.SharedFolder;
import pages.SharedFolderCreation;
import pages.SharedFolderDelete;
import pages.SharedFolderSearch;
import tests.Autoutil;
import tests.FileUpload;
import tests.InitialTestSetup;

public class MoveFileFoldersSharedFolderTest extends InitialTestSetup {
	private SharedFolderCreation sharedFolderCreation = null;
	private SharedFolder sharedFolder = null;
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
		sharedFolder = homePage.gotoSharedFolder();
		Thread.sleep(2000);
		System.out.println("This is NoData from App: " + sharedFolder.uiControl("noData").getText());
		System.out.println("This is NoData from Expected: " + getLocalizedMessage("noFilesInTable").trim());
		if (sharedFolder.uiControl("noData").getText().trim().equals(getLocalizedMessage("noFilesInTable").trim())) {
			System.out.println("No folders are found..!");

		} else {
			sharedFolder.selectAllCheckBox().click();
			SharedFolderDelete deleteFolder = sharedFolder.gotoSharedFolderDelete();
			deleteFolder.okBtn().click();
			Thread.sleep(5000);
			assertEquals(sharedFolder.uiControl("noData").getText(), getLocalizedMessage("noFilesInTable").trim());
		}
	}

	@AfterMethod(alwaysRun = true)
	public void deleteAllFolders() throws Exception {
		// System.out.println("This is Expected result:
		// "+getLocalizedMessage("noFilesInTable").trim());
		if ((sharedFolder.uiControl("noData").getText().trim()).equals(getLocalizedMessage("noFilesInTable").trim())) {
			System.out.println("After Method: No data");
			// this.getDriver().navigate().refresh();
			sharedFolder.uiControl("homeLink").click();
			Thread.sleep(2000);
		} else {
			sharedFolder.selectAllCheckBox().click();
			SharedFolderDelete deleteFolder = sharedFolder.gotoSharedFolderDelete();
			deleteFolder.okBtn().click();
			Thread.sleep(2000);
			// assertEquals(personalFolder.uiControl("noData").getText(),
			// getLocalizedMessage("noData"));
			// this.getDriver().navigate().refresh();
			sharedFolder.uiControl("homeLink").click();
			Thread.sleep(2000);
		}
	}
	/*
	 * @Test(groups = { "CreateFolder" }, alwaysRun = true)
	 * 
	 * @PriorityInClass(1) public void verifyFolderIsCreated() throws Exception {
	 * setTestDesc(
	 * "Verify that Admin can create folder in the root folder.Folder should be create in the root folder"
	 * ); sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	 * sharedFolderCreation.createFolder("AutoTest");
	 * assertEquals(sharedFolder.firstRowName().getText(), "AutoTest");
	 * 
	 * }
	 */
	/*
	// move file/folders through Save change destination context menu 
	
	@Test(priority = 1, groups = { "Functional" }, enabled = false)
	// @PriorityInClass(2)
	public void verifyContextMenuFileMoveSameLevel() throws Exception {
		setTestDesc("Verify that files can be moved to other folders in the same level");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("AutoTest");
		Thread.sleep(1000);
		sharedFolder.uploadBtn().click();
		SharedFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\");
		SharedFolder.uploadFileWithRobot("\"File1.pdf" + "\" \"File1.jpg" + "\" \"File1.png" + "\" \"File1.jpeg" + "\"");
		Thread.sleep(30000);		
		sharedFolder.OperationMenu().click();
		Thread.sleep(1000);
		sharedFolder.changeSaveDestinationBtnfrmContext().click();
		Thread.sleep(1000);
		sharedFolder.SelectFolderInSameLevel().click();
		Thread.sleep(1000);
		sharedFolder.renameOkBtn().click();
		Thread.sleep(1000);
		sharedFolder.OpenCreatedFolder().click();
		Thread.sleep(1000);
		assertEquals(sharedFolder.firstRowName().getText().substring(0, 4), "File");
	}
	
	
	@Test(priority = 2, groups = { "Functional" }, enabled = false)
	// @PriorityInClass(2)
	public void verifyContextMenuFileMoveSubfolder() throws Exception {
		setTestDesc("Verify that files can be moved to other folders in the subfolders");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("AutoTest");
		Thread.sleep(1000);
		sharedFolder.firstRowName().click();
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("AutoTest");
		Thread.sleep(1000);
		sharedFolder.uiControl("breadcrumRoot").click();
		Thread.sleep(1000);
		sharedFolder.uploadBtn().click();
		SharedFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\");
		SharedFolder.uploadFileWithRobot("\"File1.pdf" + "\" \"File1.jpg" + "\" \"File1.png" + "\" \"File1.jpeg" + "\"");
		Thread.sleep(30000);		
		sharedFolder.OperationMenu().click();
		Thread.sleep(1000);
		sharedFolder.changeSaveDestinationBtnfrmContext().click();
		Thread.sleep(1000);
		sharedFolder.SelectFolderInSameLevel().doubleClick();
		Thread.sleep(1000);
		sharedFolder.SelectFolderInSameLevel().doubleClick();
		Thread.sleep(1000);
		sharedFolder.renameOkBtn().click();
		Thread.sleep(1000);
		sharedFolder.OpenCreatedFolder().click();
		Thread.sleep(1000);
		sharedFolder.OpenCreatedFolder().click();
		Thread.sleep(1000);
		assertEquals(sharedFolder.firstRowName().getText().substring(0, 4), "File");
	}
	

	@Test(priority = 3, groups = { "Functional" }, enabled = false)
	// @PriorityInClass(2)
	public void verifyContextMenuFolderMoveSameLevel() throws Exception {
		setTestDesc("Verify that Folders can be moved to other folders in the same level ");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("AutoTest");
		Thread.sleep(1000);
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("Folder2");
		Thread.sleep(1000);		
		sharedFolder.OperationMenu().click();
		Thread.sleep(1000);
		sharedFolder.changeSaveDestinationBtnfrmContext().click();
		Thread.sleep(1000);		
		sharedFolder.SelectFolderInSameLevel().click();
		Thread.sleep(1000);	
		sharedFolder.renameOkBtn().click();
		Thread.sleep(1000);
		sharedFolder.OpenCreatedFolder().click();
		Thread.sleep(1000);	
		assertEquals(sharedFolder.firstRowName().getText(), "Folder2");
	}
	
	@Test(priority = 4, groups = { "Functional" }, enabled = false)
	// @PriorityInClass(2)
	public void verifyContextMenuFolderMoveSubfolder() throws Exception {
		setTestDesc("Verify that Folders can be moved to other folders in the subfolders");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("AutoTest");
		Thread.sleep(1000);
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("AutoTest1");
		Thread.sleep(1000);
		sharedFolder.firstRowName().click();
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("AutoTest");
		Thread.sleep(1000);
		sharedFolder.uiControl("breadcrumRoot").click();
		Thread.sleep(1000);				
		sharedFolder.OperationMenu().click();
		Thread.sleep(1000);
		sharedFolder.changeSaveDestinationBtnfrmContext().click();
		Thread.sleep(1000);
		sharedFolder.SelectFolderInSameLevel().doubleClick();
		Thread.sleep(1000);
		sharedFolder.SelectFolderInSameLevel().doubleClick();
		Thread.sleep(1000);
		sharedFolder.renameOkBtn().click();
		Thread.sleep(1000);
		sharedFolder.OpenCreatedFolder().click();
		Thread.sleep(1000);
		sharedFolder.OpenCreatedFolder().click();
		Thread.sleep(1000);
		assertEquals(sharedFolder.firstRowName().getText(), "AutoTest1");
	}
	
	@Test(priority = 5, groups = { "Functional" }, enabled = false)
	// @PriorityInClass(2)
	public void verifyContextMenuFileMoveRootfolder() throws Exception {
		setTestDesc("Verify that files can be moved from subfolders to root folder");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("AutoTest");
		Thread.sleep(1000);
		sharedFolder.firstRowName().click();	
		Thread.sleep(1000);
		sharedFolder.uploadBtn().click();
		SharedFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\");
		SharedFolder.uploadFileWithRobot("\"File1.pdf" + "\" \"File1.jpg" + "\" \"File1.png" + "\" \"File1.jpeg" + "\"");
		Thread.sleep(30000);		
		sharedFolder.OperationMenu().click();
		Thread.sleep(1000);
		sharedFolder.changeSaveDestinationBtnfrmContext().click();
		Thread.sleep(1000);

		sharedFolder.ClickUpArrowBtn().click();
		Thread.sleep(1000);
		sharedFolder.renameOkBtn().click();	
		Thread.sleep(1000);
		sharedFolder.uiControl("breadcrumRoot").click();		
		assertEquals(sharedFolder.firstRowName().getText().substring(0, 4), "File");
	}
	
	@Test(priority = 6, groups = { "Functional" }, enabled = false)
	// @PriorityInClass(2)
	public void verifyContextMenuFolderMoveRootfolder() throws Exception {
		setTestDesc("Verify that folder can be moved from subfolders to root folder");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("AutoTest");
		Thread.sleep(1000);
		sharedFolder.firstRowName().click();	
		Thread.sleep(1000);
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("AutoTest");
		Thread.sleep(1000);	
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("SubFolder");
		Thread.sleep(1000);
		sharedFolder.OperationMenu().click();
		Thread.sleep(1000);
		sharedFolder.changeSaveDestinationBtnfrmContext().click();
		Thread.sleep(1000);
		sharedFolder.ClickUpArrowBtn().click();
		Thread.sleep(1000);
		sharedFolder.renameOkBtn().click();	
		Thread.sleep(1000);
		sharedFolder.uiControl("breadcrumRoot").click();	
		Thread.sleep(1000);
		assertEquals(sharedFolder.secondRowName().getText(), "SubFolder");
	}
	
    // move file/folders through Save change destination button 
	
	@Test(priority = 7, groups = { "Functional" }, enabled = false)
	  // @PriorityInClass(1) 
	public void verifySingleFileMoveToSameLevelFolder() throws Exception {
	  setTestDesc("verify Single File can Move To Same Level Folder" ); 
	  sharedFolder.uploadBtn().click();
	  SharedFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\");
	  SharedFolder.uploadFileWithRobot("\"File1.pdf" + "\" \"File1.jpg" + "\" \"File1.png" + "\" \"File1.jpeg" + "\"");
	  Thread.sleep(30000);
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  Thread.sleep(1000);
	  sharedFolder.selectSinlgeCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	  sharedFolder.SelectFolderInSameLevel().click();
	  Thread.sleep(1000);
	  sharedFolder.renameOkBtn().click(); 
	  Thread.sleep(1000);
	  sharedFolder.OpenCreatedFolder().click();
	  Thread.sleep(1000);
	  assertEquals(sharedFolder.firstRowName().getText().substring(0, 4), "File");
	  
	  }
	
	@Test(priority = 8, groups = { "Functional" }, enabled = false)
	  // @PriorityInClass(1) 
	public void verifyMultipleFilesMovetoSameLevelFolder() throws Exception {
	  setTestDesc("Verify Multiple Files Move to Same Level Folder." ); 
	  sharedFolder.uploadBtn().click();
	  SharedFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\");
	  SharedFolder.uploadFileWithRobot("\"File1.pdf" + "\" \"File1.jpg" + "\" \"File1.png" + "\" \"File1.jpeg" + "\"");
	  Thread.sleep(30000);
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  Thread.sleep(1000);
	  sharedFolder.selectAllCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.selectSecondCheckBox().click();
	  Thread.sleep(1000);	  
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	  sharedFolder.SelectFolderInSameLevel().doubleClick();
	  Thread.sleep(1000);
	  sharedFolder.renameOkBtn().click(); 
	  Thread.sleep(1000);
	  sharedFolder.OpenCreatedFolder().click();
	  Thread.sleep(1000);
	  assertEquals(sharedFolder.firstRowName().getText().substring(0, 4), "File");
	  
	  }
	
	@Test(priority = 9, groups = { "Functional" }, enabled = false)
	  // @PriorityInClass(1) 
	public void verifySingleFolderMoveToSameLevelFolder() throws Exception {
	  setTestDesc("Verify Single Folder Move To Same Level Folder." ); 
	 
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  Thread.sleep(1000);
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest1");
	  Thread.sleep(1000);
	  sharedFolder.selectSinlgeCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	  sharedFolder.SelectFolderInSameLevel().click();
	  Thread.sleep(1000);
	  sharedFolder.renameOkBtn().click(); 
	  Thread.sleep(1000);
	  sharedFolder.OpenCreatedFolder().click();
	  Thread.sleep(1000);
	  assertEquals(sharedFolder.firstRowName().getText(), "AutoTest1");
	  
	  }

	@Test(priority = 10, groups = { "Functional" }, enabled = false)
	  // @PriorityInClass(1) 
	public void verifyMultipleFoldersMoveToSameLevelFolder() throws Exception {
	  setTestDesc("Verify Multiple Folders Move To Same Level Folder" ); 
	 
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  Thread.sleep(1000);
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest1");
	  Thread.sleep(1000);
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest2");
	  Thread.sleep(1000);
	  sharedFolder.selectAllCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.selectSecondCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	  sharedFolder.SelectFolderInSameLevel().click();
	  Thread.sleep(1000);
	  sharedFolder.renameOkBtn().click(); 
	  Thread.sleep(1000);
	  sharedFolder.OpenCreatedFolder().click();
	  Thread.sleep(1000);
	  assertEquals(sharedFolder.firstRowName().getText(), "AutoTest1");
	  
	  }
	

	@Test(priority = 11, groups = { "Functional" }, enabled = false)
	// @PriorityInClass(1)
	public void verifyMultipleFilesFolderMoveToSubFolder() throws Exception {
		setTestDesc("Verify that Admin can move multiple files/folder to sub folders in the root folder.");
		
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("AutoTest");
		Thread.sleep(1000);
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("RootFolder");
		Thread.sleep(1000);
		sharedFolder.firstRowName().click();
		Thread.sleep(1000);
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("AutoTest");
		Thread.sleep(1000);
		sharedFolder.uiControl("breadcrumRoot").click();
		Thread.sleep(1000);
		sharedFolder.uploadBtn().click();
		SharedFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\");
		SharedFolder.uploadFileWithRobot("\"File1.pdf" + "\" \"File1.jpg" + "\" \"File1.png" + "\" \"File1.jpeg" + "\"");
		Thread.sleep(30000);		
		String BeforeDltfileCnt = sharedFolder.getFileCount();
		sharedFolder.selectAllCheckBox().click();
		Thread.sleep(1000);
		sharedFolder.selectSecondCheckBox().click();
		sharedFolder.changeSaveDestinationBtn().click();
		Thread.sleep(1000);
		sharedFolder.SelectFolderInSameLevel().doubleClick();
		Thread.sleep(1000);
		sharedFolder.SelectFolderInSameLevel().doubleClick();
		Thread.sleep(1000);
		sharedFolder.renameOkBtn().click();
		Thread.sleep(1000);
		String AfterDltfileCnt = sharedFolder.getFileCount();
		assertNotEquals(BeforeDltfileCnt, AfterDltfileCnt);

	}
	
	@Test(priority = 12, groups = { "Functional" }, enabled = false)
	// @PriorityInClass(2)
	public void verifyContextMenuFoldersMoveRootfolder() throws Exception {
		setTestDesc("Verify that multiple folders can be moved from subfolders to Root)");
		
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("AutoTest");
		Thread.sleep(1000);
		String BeforeDltfileCnt = sharedFolder.getFileCount();
		Thread.sleep(1000);
		sharedFolder.firstRowName().click();	
		Thread.sleep(1000);
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("AutoTest1");
		Thread.sleep(1000);	
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("SubFolder2");
		Thread.sleep(1000);
		sharedFolder.selectAllCheckBox().click();
		Thread.sleep(1000);		
		sharedFolder.changeSaveDestinationBtn().click();
		Thread.sleep(1000);		
		sharedFolder.ClickUpArrowBtn().click();
		Thread.sleep(1000);
		sharedFolder.renameOkBtn().click();	
		Thread.sleep(1000);
		sharedFolder.uiControl("breadcrumRoot").click();	
		Thread.sleep(1000);
		String AfterDltfileCnt = sharedFolder.getFileCount();
		assertNotEquals(BeforeDltfileCnt, AfterDltfileCnt);
		
	}

	@Test(priority = 13, groups = { "Functional" }, enabled = false)
	// @PriorityInClass(2)
	public void verifyContextMenuFilesMoveRootfolder() throws Exception {
		setTestDesc("Verify that multiple files can be moved from one subfolders to Root)");
		
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("AutoTest");
		Thread.sleep(1000);
		String BeforeDltfileCnt = sharedFolder.getFileCount();
		Thread.sleep(1000);
		sharedFolder.firstRowName().click();	
	
		Thread.sleep(1000);
		sharedFolder.uploadBtn().click();
		SharedFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\");
		SharedFolder.uploadFileWithRobot("\"File1.pdf" + "\" \"File1.jpg" + "\" \"File1.png" + "\" \"File1.jpeg" + "\"");
		Thread.sleep(30000);
				
		sharedFolder.selectAllCheckBox().click();
		Thread.sleep(1000);		
		sharedFolder.changeSaveDestinationBtn().click();
		Thread.sleep(1000);		
		sharedFolder.ClickUpArrowBtn().click();
		Thread.sleep(1000);
		sharedFolder.renameOkBtn().click();	
		Thread.sleep(1000);
		sharedFolder.uiControl("breadcrumRoot").click();	
		Thread.sleep(1000);
		String AfterDltfileCnt = sharedFolder.getFileCount();
		assertNotEquals(BeforeDltfileCnt, AfterDltfileCnt);
		
	}
	
	

	@Test(priority = 14, groups = { "Functional" }, enabled = true)
	  // @PriorityInClass(1) 
	public void verifyPNGFilesMovetoSameLevelFolder5() throws Exception {
	  setTestDesc("Verify admin can Move a File1.png file to sub Folder." ); 
	  sharedFolder.uploadBtn().click();
	  SharedFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\");
	  SharedFolder.uploadFileWithRobot("File1.png");
	  Thread.sleep(15000);
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  Thread.sleep(1000);
	  sharedFolder.selectAllCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.selectSecondCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	  sharedFolder.SelectFolderInSameLevel().doubleClick();
	  Thread.sleep(1000);
	  sharedFolder.renameOkBtn().click(); 
	  Thread.sleep(1000);
	  sharedFolder.OpenCreatedFolder().click();
	  Thread.sleep(1000);
	  assertEquals(sharedFolder.firstRowName().getText(), "File1.png");
	  
	  }
	
	@Test(priority = 15, groups = { "Functional" }, enabled = true)
	  // @PriorityInClass(1) 
	public void verifyJPGFilesMovetoSameLevelFolder5() throws Exception {
	  setTestDesc("Verify admin can Move a File1.jpg file to sub Folder." ); 
	  sharedFolder.uploadBtn().click();
	  SharedFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\");
	  SharedFolder.uploadFileWithRobot("File1.jpg");
	  Thread.sleep(15000);
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  Thread.sleep(1000);
	  sharedFolder.selectAllCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.selectSecondCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	  sharedFolder.SelectFolderInSameLevel().doubleClick();
	  Thread.sleep(1000);
	  sharedFolder.renameOkBtn().click(); 
	  Thread.sleep(1000);
	  sharedFolder.OpenCreatedFolder().click();
	  Thread.sleep(1000);
	  assertEquals(sharedFolder.firstRowName().getText(), "File1.jpg");
	  
	  }

	@Test(priority = 16, groups = { "Functional" }, enabled = true)
	  // @PriorityInClass(1) 
	public void verifyJPEGFilesMovetoSameLevelFolder5() throws Exception {
	  setTestDesc("Verify admin can Move a File1.jpeg file to sub Folder." ); 
	  sharedFolder.uploadBtn().click();
	  SharedFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\");
	  SharedFolder.uploadFileWithRobot("File1.jpeg");
	  Thread.sleep(15000);
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  Thread.sleep(1000);
	  sharedFolder.selectAllCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.selectSecondCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	  sharedFolder.SelectFolderInSameLevel().doubleClick();
	  Thread.sleep(1000);
	  sharedFolder.renameOkBtn().click(); 
	  Thread.sleep(1000);
	  sharedFolder.OpenCreatedFolder().click();
	  Thread.sleep(1000);
	  assertEquals(sharedFolder.firstRowName().getText(), "File1.jpeg");
	  
	  }
	

	@Test(priority = 17, groups = { "Functional" }, enabled = true)
	  // @PriorityInClass(1) 
	public void verifyBMPFilesMovetoSameLevelFolder5() throws Exception {
	  setTestDesc("Verify admin can Move a File.bmp file to sub Folder." ); 
	  sharedFolder.uploadBtn().click();
	  SharedFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\Image type\\");
	  SharedFolder.uploadFileWithRobot("File.BMP");
	  Thread.sleep(15000);
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  Thread.sleep(1000);
	  sharedFolder.selectAllCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.selectSecondCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	  sharedFolder.SelectFolderInSameLevel().doubleClick();
	  Thread.sleep(1000);
	  sharedFolder.renameOkBtn().click(); 
	  Thread.sleep(1000);
	  sharedFolder.OpenCreatedFolder().click();
	  Thread.sleep(1000);
	  assertEquals(sharedFolder.firstRowName().getText(), "File.BMP");
	  
	  }

	@Test(priority = 18, groups = { "Functional" }, enabled = true)
	  // @PriorityInClass(1) 
	public void verifyGIFFilesMovetoSameLevelFolder5() throws Exception {
	  setTestDesc("Verify admin can Move a File.gif file to sub Folder." ); 
	  sharedFolder.uploadBtn().click();
	  SharedFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\Image type\\");
	  SharedFolder.uploadFileWithRobot("File.gif");
	  Thread.sleep(15000);
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  Thread.sleep(1000);
	  sharedFolder.selectAllCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.selectSecondCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	  sharedFolder.SelectFolderInSameLevel().doubleClick();
	  Thread.sleep(1000);
	  sharedFolder.renameOkBtn().click(); 
	  Thread.sleep(1000);
	  sharedFolder.OpenCreatedFolder().click();
	  Thread.sleep(1000);
	  assertEquals(sharedFolder.firstRowName().getText(), "File.gif");
	  
	  }
	

	@Test(priority = 19, groups = { "Functional" }, enabled = true)
	  // @PriorityInClass(1) 
	public void verifyPDFFilesMovetoSameLevelFolder5() throws Exception {
	  setTestDesc("Verify admin can Move a File.pdf file to sub Folder." ); 
	  sharedFolder.uploadBtn().click();
	  SharedFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\Image type\\");
	  SharedFolder.uploadFileWithRobot("File.pdf");
	  Thread.sleep(15000);
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  Thread.sleep(1000);
	  sharedFolder.selectAllCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.selectSecondCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	  sharedFolder.SelectFolderInSameLevel().doubleClick();
	  Thread.sleep(1000);
	  sharedFolder.renameOkBtn().click(); 
	  Thread.sleep(1000);
	  sharedFolder.OpenCreatedFolder().click();
	  Thread.sleep(1000);
	  assertEquals(sharedFolder.firstRowName().getText(), "File.pdf");
	  
	  }

	@Test(priority = 20, groups = { "Functional" }, enabled = true)
	  // @PriorityInClass(1) 
	public void verifyExcelFilesMovetoSameLevelFolder5() throws Exception {
	  setTestDesc("Verify admin can Move a File.xlsx file to sub Folder." ); 
	  sharedFolder.uploadBtn().click();
	  SharedFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\Image type\\");
	  SharedFolder.uploadFileWithRobot("File.xlsx");
	  Thread.sleep(15000);
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  Thread.sleep(1000);
	  sharedFolder.selectAllCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.selectSecondCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	  sharedFolder.SelectFolderInSameLevel().doubleClick();
	  Thread.sleep(1000);
	  sharedFolder.renameOkBtn().click(); 
	  Thread.sleep(1000);
	  sharedFolder.OpenCreatedFolder().click();
	  Thread.sleep(1000);
	  assertEquals(sharedFolder.firstRowName().getText(), "File.xlsx");
	  
	  }
	

	@Test(priority = 21, groups = { "Functional" }, enabled = true)
	  // @PriorityInClass(1) 
	public void verifyTIFFFilesMovetoSameLevelFolder5() throws Exception {
	  setTestDesc("Verify admin can Move a File.tif file to sub Folder." ); 
	  sharedFolder.uploadBtn().click();
	  SharedFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\Image type\\");
	  SharedFolder.uploadFileWithRobot("File.tif");
	  Thread.sleep(15000);
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  Thread.sleep(1000);
	  sharedFolder.selectAllCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.selectSecondCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	  sharedFolder.SelectFolderInSameLevel().doubleClick();
	  Thread.sleep(1000);
	  sharedFolder.renameOkBtn().click(); 
	  Thread.sleep(1000);
	  sharedFolder.OpenCreatedFolder().click();
	  Thread.sleep(1000);
	  assertEquals(sharedFolder.firstRowName().getText(), "File.tif");
	  
	  }

	@Test(priority = 22, groups = { "Functional" }, enabled = true)
	  // @PriorityInClass(1) 
	public void verifyDOCXFilesMovetoSameLevelFolder5() throws Exception {
	  setTestDesc("Verify admin can Move a File.DOCX file to sub Folder." ); 
	  sharedFolder.uploadBtn().click();
	  SharedFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\Text Doc\\");
	  SharedFolder.uploadFileWithRobot("File.docx");
	  Thread.sleep(15000);
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  Thread.sleep(1000);
	  sharedFolder.selectAllCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.selectSecondCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	  sharedFolder.SelectFolderInSameLevel().doubleClick();
	  Thread.sleep(1000);
	  sharedFolder.renameOkBtn().click(); 
	  Thread.sleep(1000);
	  sharedFolder.OpenCreatedFolder().click();
	  Thread.sleep(1000);
	  assertEquals(sharedFolder.firstRowName().getText(), "File.docx");
	  
	  }

	@Test(priority = 23, groups = { "Functional" }, enabled = true)
	  // @PriorityInClass(1) 
	public void verifyODPFilesMovetoSameLevelFolder5() throws Exception {
	  setTestDesc("Verify admin can Move a File.odp file to sub Folder." ); 
	  sharedFolder.uploadBtn().click();
	  SharedFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\Text Doc\\");
	  SharedFolder.uploadFileWithRobot("File.odp");
	  Thread.sleep(15000);
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  Thread.sleep(1000);
	  sharedFolder.selectAllCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.selectSecondCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	  sharedFolder.SelectFolderInSameLevel().doubleClick();
	  Thread.sleep(1000);
	  sharedFolder.renameOkBtn().click(); 
	  Thread.sleep(1000);
	  sharedFolder.OpenCreatedFolder().click();
	  Thread.sleep(1000);
	  assertEquals(sharedFolder.firstRowName().getText(), "File.odp");
	  
	  }
	

	@Test(priority = 24, groups = { "Functional" }, enabled = true)
	  // @PriorityInClass(1) 
	public void verifyODSFilesMovetoSameLevelFolder5() throws Exception {
	  setTestDesc("Verify admin can Move a File.ods file to sub Folder." ); 
	  sharedFolder.uploadBtn().click();
	  SharedFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\Text Doc\\");
	  SharedFolder.uploadFileWithRobot("File.ods");
	  Thread.sleep(15000);
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  Thread.sleep(1000);
	  sharedFolder.selectAllCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.selectSecondCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	  sharedFolder.SelectFolderInSameLevel().doubleClick();
	  Thread.sleep(1000);
	  sharedFolder.renameOkBtn().click(); 
	  Thread.sleep(1000);
	  sharedFolder.OpenCreatedFolder().click();
	  Thread.sleep(1000);
	  assertEquals(sharedFolder.firstRowName().getText(), "File.ods");
	  
	  }

	@Test(priority = 25, groups = { "Functional" }, enabled = true)
	  // @PriorityInClass(1) 
	public void verifyODTFilesMovetoSameLevelFolder5() throws Exception {
	  setTestDesc("Verify admin can Move a File.odt file to sub Folder." ); 
	  sharedFolder.uploadBtn().click();
	  SharedFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\Text Doc\\");
	  SharedFolder.uploadFileWithRobot("File.odt");
	  Thread.sleep(15000);
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  Thread.sleep(1000);
	  sharedFolder.selectAllCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.selectSecondCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	  sharedFolder.SelectFolderInSameLevel().doubleClick();
	  Thread.sleep(1000);
	  sharedFolder.renameOkBtn().click(); 
	  Thread.sleep(1000);
	  sharedFolder.OpenCreatedFolder().click();
	  Thread.sleep(1000);
	  assertEquals(sharedFolder.firstRowName().getText(), "File.odt");
	  
	  }
	
	@Test(priority = 26, groups = { "Functional" }, enabled = true)
	  // @PriorityInClass(1) 
	public void verifyPPTXFilesMovetoSameLevelFolder5() throws Exception {
	  setTestDesc("Verify admin can Move a File.pptx file to sub Folder." ); 
	  sharedFolder.uploadBtn().click();
	  SharedFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\Text Doc\\");
	  SharedFolder.uploadFileWithRobot("File.pptx");
	  Thread.sleep(15000);
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  Thread.sleep(1000);
	  sharedFolder.selectAllCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.selectSecondCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	  sharedFolder.SelectFolderInSameLevel().doubleClick();
	  Thread.sleep(1000);
	  sharedFolder.renameOkBtn().click(); 
	  Thread.sleep(1000);
	  sharedFolder.OpenCreatedFolder().click();
	  Thread.sleep(1000);
	  assertEquals(sharedFolder.firstRowName().getText(), "File.pptx");
	  
	  }

	@Test(priority = 27, groups = { "Functional" }, enabled = true)
	  // @PriorityInClass(1) 
	public void verifyRTFFilesMovetoSameLevelFolder5() throws Exception {
	  setTestDesc("Verify admin can Move a File.rtf file to sub Folder." ); 
	  sharedFolder.uploadBtn().click();
	  SharedFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\Text Doc\\");
	  SharedFolder.uploadFileWithRobot("File.rtf");
	  Thread.sleep(15000);
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  Thread.sleep(1000);
	  sharedFolder.selectAllCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.selectSecondCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	  sharedFolder.SelectFolderInSameLevel().doubleClick();
	  Thread.sleep(1000);
	  sharedFolder.renameOkBtn().click(); 
	  Thread.sleep(1000);
	  sharedFolder.OpenCreatedFolder().click();
	  Thread.sleep(1000);
	  assertEquals(sharedFolder.firstRowName().getText(), "File.rtf");
	  
	  }

	@Test(priority = 27, groups = { "Functional" }, enabled = true)
	  // @PriorityInClass(1) 
	public void verifyTXTFilesMovetoSameLevelFolder5() throws Exception {
	  setTestDesc("Verify admin can Move a File.txt file to sub Folder." ); 
	  sharedFolder.uploadBtn().click();
	  SharedFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\Text Doc\\");
	  SharedFolder.uploadFileWithRobot("File.txt");
	  Thread.sleep(15000);
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  Thread.sleep(1000);
	  sharedFolder.selectAllCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.selectSecondCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	  sharedFolder.SelectFolderInSameLevel().doubleClick();
	  Thread.sleep(1000);
	  sharedFolder.renameOkBtn().click(); 
	  Thread.sleep(1000);
	  sharedFolder.OpenCreatedFolder().click();
	  Thread.sleep(1000);
	  assertEquals(sharedFolder.firstRowName().getText(), "File.txt");
	  
	  }	

	@Test(priority = 28, groups = { "Functional" }, enabled = true)
	  // @PriorityInClass(1) 
	public void verifyFilesAVIMovetoSameLevelFolder5() throws Exception {
	  setTestDesc("Verify admin can Move a File.avi file to sub Folder." ); 
	  sharedFolder.uploadBtn().click();
	  SharedFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\Video type\\");
	  SharedFolder.uploadFileWithRobot("File.avi");
	  Thread.sleep(15000);
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  Thread.sleep(1000);
	  sharedFolder.selectAllCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.selectSecondCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	  sharedFolder.SelectFolderInSameLevel().doubleClick();
	  Thread.sleep(1000);
	  sharedFolder.renameOkBtn().click(); 
	  Thread.sleep(1000);
	  sharedFolder.OpenCreatedFolder().click();
	  Thread.sleep(1000);
	  assertEquals(sharedFolder.firstRowName().getText(), "File.avi");
	  
	  }
	@Test(priority = 29, groups = { "Functional" }, enabled = true)
	  // @PriorityInClass(1) 
	public void verifyFilesFLVMovetoSameLevelFolder5() throws Exception {
	  setTestDesc("Verify admin can Move a File.flv file to sub Folder." ); 
	  sharedFolder.uploadBtn().click();
	  SharedFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\Video type\\");
	  SharedFolder.uploadFileWithRobot("File.flv");
	  Thread.sleep(15000);
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  Thread.sleep(1000);
	  sharedFolder.selectAllCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.selectSecondCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	  sharedFolder.SelectFolderInSameLevel().doubleClick();
	  Thread.sleep(1000);
	  sharedFolder.renameOkBtn().click(); 
	  Thread.sleep(1000);
	  sharedFolder.OpenCreatedFolder().click();
	  Thread.sleep(1000);
	  assertEquals(sharedFolder.firstRowName().getText(), "File.flv");
	  
	  }
	@Test(priority = 30, groups = { "Functional" }, enabled = true)
	  // @PriorityInClass(1) 
	public void verifyFilesMKVMovetoSameLevelFolder5() throws Exception {
	  setTestDesc("Verify admin can Move a File.mkv file to sub Folder." ); 
	  sharedFolder.uploadBtn().click();
	  SharedFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\Video type\\");
	  SharedFolder.uploadFileWithRobot("File.mkv");
	  Thread.sleep(15000);
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  Thread.sleep(1000);
	  sharedFolder.selectAllCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.selectSecondCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	  sharedFolder.SelectFolderInSameLevel().doubleClick();
	  Thread.sleep(1000);
	  sharedFolder.renameOkBtn().click(); 
	  Thread.sleep(1000);
	  sharedFolder.OpenCreatedFolder().click();
	  Thread.sleep(1000);
	  assertEquals(sharedFolder.firstRowName().getText(), "File.mkv");
	  
	  }
	@Test(priority = 31, groups = { "Functional" }, enabled = true)
	  // @PriorityInClass(1) 
	public void verifyFilesMOVMovetoSameLevelFolder5() throws Exception {
	  setTestDesc("Verify admin can Move a File.mov file to sub Folder." ); 
	  sharedFolder.uploadBtn().click();
	  SharedFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\Video type\\");
	  SharedFolder.uploadFileWithRobot("File.mov");
	  Thread.sleep(15000);
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  Thread.sleep(1000);
	  sharedFolder.selectAllCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.selectSecondCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	  sharedFolder.SelectFolderInSameLevel().doubleClick();
	  Thread.sleep(1000);
	  sharedFolder.renameOkBtn().click(); 
	  Thread.sleep(1000);
	  sharedFolder.OpenCreatedFolder().click();
	  Thread.sleep(1000);
	  assertEquals(sharedFolder.firstRowName().getText(), "File.mov");
	  
	  }
	@Test(priority = 32, groups = { "Functional" }, enabled = true)
	  // @PriorityInClass(1) 
	public void verifyFilesMP4MovetoSameLevelFolder5() throws Exception {
	  setTestDesc("Verify admin can Move a File.mp4 file to sub Folder." ); 
	  sharedFolder.uploadBtn().click();
	  SharedFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\Video type\\");
	  SharedFolder.uploadFileWithRobot("File.mp4");
	  Thread.sleep(15000);
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  Thread.sleep(1000);
	  sharedFolder.selectAllCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.selectSecondCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	  sharedFolder.SelectFolderInSameLevel().doubleClick();
	  Thread.sleep(1000);
	  sharedFolder.renameOkBtn().click(); 
	  Thread.sleep(1000);
	  sharedFolder.OpenCreatedFolder().click();
	  Thread.sleep(1000);
	  assertEquals(sharedFolder.firstRowName().getText(), "File.mp4");
	  
	  }
	@Test(priority = 33, groups = { "Functional" }, enabled = true)
	  // @PriorityInClass(1) 
	public void verifyFilesMPEGMovetoSameLevelFolder5() throws Exception {
	  setTestDesc("Verify admin can Move a File.mpeg file to sub Folder." ); 
	  sharedFolder.uploadBtn().click();
	  SharedFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\Video type\\");
	  SharedFolder.uploadFileWithRobot("File.mpeg");
	  Thread.sleep(15000);
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  Thread.sleep(1000);
	  sharedFolder.selectAllCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.selectSecondCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	  sharedFolder.SelectFolderInSameLevel().doubleClick();
	  Thread.sleep(1000);
	  sharedFolder.renameOkBtn().click(); 
	  Thread.sleep(1000);
	  sharedFolder.OpenCreatedFolder().click();
	  Thread.sleep(1000);
	  assertEquals(sharedFolder.firstRowName().getText(), "File.mpeg");
	  
	  }
	@Test(priority = 34, groups = { "Functional" }, enabled = true)
	  // @PriorityInClass(1) 
	public void verifyFilesMPE2GMovetoSameLevelFolder5() throws Exception {
	  setTestDesc("Verify admin can Move a File.mpeg2 file to sub Folder." ); 
	  sharedFolder.uploadBtn().click();
	  SharedFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\Video type\\");
	  SharedFolder.uploadFileWithRobot("File.mpeg2");
	  Thread.sleep(15000);
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  Thread.sleep(1000);
	  sharedFolder.selectAllCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.selectSecondCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	  sharedFolder.SelectFolderInSameLevel().doubleClick();
	  Thread.sleep(1000);
	  sharedFolder.renameOkBtn().click(); 
	  Thread.sleep(1000);
	  sharedFolder.OpenCreatedFolder().click();
	  Thread.sleep(1000);
	  assertEquals(sharedFolder.firstRowName().getText(), "File.mpeg2");
	  
	  }
	@Test(priority = 35, groups = { "Functional" }, enabled = true)
	  // @PriorityInClass(1) 
	public void verifyFilesTSMovetoSameLevelFolder5() throws Exception {
	  setTestDesc("Verify admin can Move a File.ts file to sub Folder." ); 
	  sharedFolder.uploadBtn().click();
	  SharedFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\Video type\\");
	  SharedFolder.uploadFileWithRobot("File.ts");
	  Thread.sleep(15000);
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  Thread.sleep(1000);
	  sharedFolder.selectAllCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.selectSecondCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	  sharedFolder.SelectFolderInSameLevel().doubleClick();
	  Thread.sleep(1000);
	  sharedFolder.renameOkBtn().click(); 
	  Thread.sleep(1000);
	  sharedFolder.OpenCreatedFolder().click();
	  Thread.sleep(1000);
	  assertEquals(sharedFolder.firstRowName().getText(), "File.ts");
	  
	  }
		@Test(priority = 36, groups = { "Functional" }, enabled = true)
	  // @PriorityInClass(1) 
	public void verifyFilesWEBMMovetoSameLevelFolder5() throws Exception {
	  setTestDesc("Verify admin can Move a File.webm file to sub Folder." ); 
	  sharedFolder.uploadBtn().click();
	  SharedFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\Video type\\");
	  SharedFolder.uploadFileWithRobot("File.webm");
	  Thread.sleep(15000);
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  Thread.sleep(1000);
	  sharedFolder.selectAllCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.selectSecondCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	  sharedFolder.SelectFolderInSameLevel().doubleClick();
	  Thread.sleep(1000);
	  sharedFolder.renameOkBtn().click(); 
	  Thread.sleep(1000);
	  sharedFolder.OpenCreatedFolder().click();
	  Thread.sleep(1000);
	  assertEquals(sharedFolder.firstRowName().getText(), "File.webm");
	  
	  }
	@Test(priority = 37, groups = { "Functional" }, enabled = true)
	  // @PriorityInClass(1) 
	public void verifyFilesMWVMovetoSameLevelFolder5() throws Exception {
	  setTestDesc("Verify admin can Move a File.mwv file to sub Folder." ); 
	  sharedFolder.uploadBtn().click();
	  SharedFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\Video type\\");
	  SharedFolder.uploadFileWithRobot("File.mwv");
	  Thread.sleep(15000);
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  Thread.sleep(1000);
	  sharedFolder.selectAllCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.selectSecondCheckBox().click();
	  Thread.sleep(1000);
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	  sharedFolder.SelectFolderInSameLevel().doubleClick();
	  Thread.sleep(1000);
	  sharedFolder.renameOkBtn().click(); 
	  Thread.sleep(1000);
	  sharedFolder.OpenCreatedFolder().click();
	  Thread.sleep(1000);
	  assertEquals(sharedFolder.firstRowName().getText(), "File.mwv");
	  
	  }
	
	
	
	//84 characters 
	@Test(priority = 38, groups = { "Functional" }, enabled = true)
	  // @PriorityInClass(1) 
	public void verifyEnglish84SingleByteFileMovetoRootFolder() throws Exception {
	  setTestDesc("Verify that Move file to root folder when File name length English 84 characters of single byte." ); 
	  
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  Thread.sleep(1000);
	  sharedFolder.OpenCreatedFolder().click();
	  Thread.sleep(1000);
	  String file = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZIJKLMNOPQRSTUVWXYvwxyzABCDEF.log";
	  sharedFolder.uploadBtn().click();
	  SharedFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\shareFolder\\file-84\\");
	  SharedFolder.uploadFileWithRobot(file);
	  Thread.sleep(15000);
	 
	  sharedFolder.selectAllCheckBox().click();
	  Thread.sleep(1000);
	 
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	  
	  sharedFolder.ClickUpArrowBtn().click();
	  Thread.sleep(1000);
	  sharedFolder.renameOkBtn().click(); 
	  Thread.sleep(1000);
	  sharedFolder.uiControl("breadcrumRoot").click();
	  Thread.sleep(1000);
	  assertEquals(sharedFolder.secondRowName().getText(), file);
	  
	  }	
	@Test(priority = 39, groups = { "Functional" }, enabled = true)
	  // @PriorityInClass(1) 
	public void verifyEnglish84MultiByteFileMovetoRootFolder() throws Exception {
	  setTestDesc("Verify that Move file to root folder when File name length English 84 characters of multi byte." ); 
	  
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  Thread.sleep(1000);
	  sharedFolder.OpenCreatedFolder().click();
	  Thread.sleep(1000);
	  String file = "ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺＰＱＲＳＴＵＶＷＸＹＺＴＵＶＷＸＹｖｗｘｙｚＡＢＣＤＥＦ.mkv";
	  sharedFolder.uploadBtn().click();
	  SharedFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\shareFolder\\file-84\\");
	  SharedFolder.uploadFileWithRobot(file);
	  Thread.sleep(15000);
	 
	  sharedFolder.selectAllCheckBox().click();
	  Thread.sleep(1000);
	 
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	  
	  sharedFolder.ClickUpArrowBtn().click();
	  Thread.sleep(1000);
	  sharedFolder.renameOkBtn().click(); 
	  Thread.sleep(1000);
	  sharedFolder.uiControl("breadcrumRoot").click();
	  Thread.sleep(1000);
	  assertEquals(sharedFolder.secondRowName().getText(), file);
	  
	  }	
	

	@Test(priority = 40, groups = { "Functional" }, enabled = true)
	  // @PriorityInClass(1) 
	public void verifyKatagana84SingleByteFileMovetoRootFolder() throws Exception {
	  setTestDesc("Verify that Move file to root folder when File name length Katagana 84 characters of single byte." ); 
	  
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  Thread.sleep(1000);
	  sharedFolder.OpenCreatedFolder().click();
	  Thread.sleep(1000);
	  String file = "ﾀｲﾌﾟ･ｲﾝｸﾞﾘｯｼｭ･ﾜｰﾄﾞｽﾞ･ｲﾝ･ｻﾞｰ･ﾎﾞｯｸｽ･ﾍﾞﾛｰ･ﾌﾟﾚｽ･ｺﾝﾊﾞｰﾄ･ﾀｰ･ｶｰﾀｶｰﾅｰ･ﾀｰ･ｺﾝﾊﾀｲﾌﾟ･ｲﾝｸﾞﾘｯｼ.bmp";
	  sharedFolder.uploadBtn().click();
	  SharedFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\shareFolder\\file-84\\");
	  SharedFolder.uploadFileWithRobot(file);
	  Thread.sleep(15000);
	 
	  sharedFolder.selectAllCheckBox().click();
	  Thread.sleep(1000);
	 
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	  
	  sharedFolder.ClickUpArrowBtn().click();
	  Thread.sleep(1000);
	  sharedFolder.renameOkBtn().click(); 
	  Thread.sleep(1000);
	  sharedFolder.uiControl("breadcrumRoot").click();
	  Thread.sleep(1000);
	  assertEquals(sharedFolder.secondRowName().getText(), file);
	  
	  }	
	
	@Test(priority = 41, groups = { "Functional" }, enabled = true)
	  // @PriorityInClass(1) 
	public void verifyKatagana84MultiByteFileMovetoRootFolder() throws Exception {
	  setTestDesc("Verify that Move file to root folder when File name length Katagana 84 characters of multi byte." ); 
	  
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  Thread.sleep(1000);
	  sharedFolder.OpenCreatedFolder().click();
	  Thread.sleep(1000);
	  String file = "タイプ・イングリッシュ・ワードズ・イン・ザー・ボックス・ベロー・プレス・コンバート・ター・カータカーナー・ター・コンバート・ゼム・インターンバート・ター・カータ.doc";
	  sharedFolder.uploadBtn().click();
	  SharedFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\shareFolder\\file-84\\");
	  SharedFolder.uploadFileWithRobot(file);
	  Thread.sleep(15000);
	 
	  sharedFolder.selectAllCheckBox().click();
	  Thread.sleep(1000);
	 
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	  
	  sharedFolder.ClickUpArrowBtn().click();
	  Thread.sleep(1000);
	  sharedFolder.renameOkBtn().click(); 
	  Thread.sleep(1000);
	  sharedFolder.uiControl("breadcrumRoot").click();
	  Thread.sleep(1000);
	  assertEquals(sharedFolder.secondRowName().getText(), file);
	  
	  }	

	@Test(priority = 42, groups = { "Functional" }, enabled = true)
	  // @PriorityInClass(1) 
	public void verifyKanji84MultiByteFileMovetoRootFolder() throws Exception {
	  setTestDesc("Verify that Move file to root folder when File name length Kanji 84 characters of multi byte." ); 
	  
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  Thread.sleep(1000);
	  sharedFolder.OpenCreatedFolder().click();
	  Thread.sleep(1000);
	  String file = "あぁかさたないぃきしちにあぁかさたないぃきしちにあぁかさたないぃきしちにあぁかさたないぃきしちにあぁかさたないぃきしちにあぁかさたないぃきぁかさたないぃきしち.gif";
	  sharedFolder.uploadBtn().click();
	  SharedFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\shareFolder\\file-84\\");
	  SharedFolder.uploadFileWithRobot(file);
	  Thread.sleep(15000);
	 
	  sharedFolder.selectAllCheckBox().click();
	  Thread.sleep(1000);
	 
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	  
	  sharedFolder.ClickUpArrowBtn().click();
	  Thread.sleep(1000);
	  sharedFolder.renameOkBtn().click(); 
	  Thread.sleep(1000);
	  sharedFolder.uiControl("breadcrumRoot").click();
	  Thread.sleep(1000);
	  assertEquals(sharedFolder.secondRowName().getText(), file);
	  
	  }	
	@Test(priority = 43, groups = { "Functional" }, enabled = true)
	  // @PriorityInClass(1) 
	public void verifyHiragana84MultiByteFileMovetoRootFolder() throws Exception {
	  setTestDesc("Verify that Move file to root folder when File name length Hiragana 84 characters of multi byte." ); 
	  
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  Thread.sleep(1000);
	  sharedFolder.OpenCreatedFolder().click();
	  Thread.sleep(1000);
	  String file = "教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教教育漢字教育漢字教育漢.flv";
	  sharedFolder.uploadBtn().click();
	  SharedFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\shareFolder\\file-84\\");
	  SharedFolder.uploadFileWithRobot(file);
	  Thread.sleep(15000);	 
	  sharedFolder.selectAllCheckBox().click();
	  Thread.sleep(1000);
	 
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	  
	  sharedFolder.ClickUpArrowBtn().click();
	  Thread.sleep(1000);
	  sharedFolder.renameOkBtn().click(); 
	  Thread.sleep(1000);
	  sharedFolder.uiControl("breadcrumRoot").click();
	  Thread.sleep(1000);
	  assertEquals(sharedFolder.secondRowName().getText(), file);
	  
	  }	
	

	@Test(priority = 44, groups = { "Functional" }, enabled = true)
	  // @PriorityInClass(1) 
	public void verifyNumber84SingleByteFileMovetoRootFolder() throws Exception {
	  setTestDesc("Verify that Move file to root folder when File name length Number 84 characters of single byte." ); 
	  
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  Thread.sleep(1000);
	  sharedFolder.OpenCreatedFolder().click();
	  Thread.sleep(1000);
	  String file = "11111111111111111111111111111111111111111111111111111111111111111111111111111111.mov";
	  sharedFolder.uploadBtn().click();
	  SharedFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\shareFolder\\file-84\\");
	  SharedFolder.uploadFileWithRobot(file);
	  Thread.sleep(15000);
	 
	  sharedFolder.selectAllCheckBox().click();
	  Thread.sleep(1000);
	 
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	  
	  sharedFolder.ClickUpArrowBtn().click();
	  Thread.sleep(1000);
	  sharedFolder.renameOkBtn().click(); 
	  Thread.sleep(1000);
	  sharedFolder.uiControl("breadcrumRoot").click();
	  Thread.sleep(1000);
	  assertEquals(sharedFolder.secondRowName().getText(), file);
	  
	  }	
	
	@Test(priority = 45, groups = { "Functional" }, enabled = true)
	  // @PriorityInClass(1) 
	public void verifyNumber84MultiByteFileMovetoRootFolder() throws Exception {
	  setTestDesc("Verify that Move file to root folder when File name length Number 84 characters of multi byte." ); 
	  
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  Thread.sleep(1000);
	  sharedFolder.OpenCreatedFolder().click();
	  Thread.sleep(1000);
	  String file = "１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１.mp4";
	  sharedFolder.uploadBtn().click();
	  SharedFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\shareFolder\\file-84\\");
	  SharedFolder.uploadFileWithRobot(file);
	  Thread.sleep(15000);
	 
	  sharedFolder.selectAllCheckBox().click();
	  Thread.sleep(1000);
	 
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	  
	  sharedFolder.ClickUpArrowBtn().click();
	  Thread.sleep(1000);
	  sharedFolder.renameOkBtn().click(); 
	  Thread.sleep(1000);
	  sharedFolder.uiControl("breadcrumRoot").click();
	  Thread.sleep(1000);
	  assertEquals(sharedFolder.secondRowName().getText(), file);
	  
	  }	

	@Test(priority = 46, groups = { "Functional" }, enabled = true)
	  // @PriorityInClass(1) 
	public void verifySpecial84MultiByteFileMovetoRootFolder() throws Exception {
	  setTestDesc("Verify that Move file to root folder when File name length Special 84 characters of multi byte." ); 
	  
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  Thread.sleep(1000);
	  sharedFolder.OpenCreatedFolder().click();
	  Thread.sleep(1000);
	  String file = "！＠＃＄％＾＆＊（）＿＋＝－｀～［｛］｝￥｜’”；：／？．＞＜，！＠＃＄％＾＆＊（）＿＋＝－｀～［｛］｝￥｜’”；：／？．＞＜，？．＞＜＜～［｛］｝￥｜’”；：.JPG";
	  sharedFolder.uploadBtn().click();
	  SharedFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\shareFolder\\file-84\\");
	  SharedFolder.uploadFileWithRobot(file);
	  Thread.sleep(15000);
	 
	  sharedFolder.selectAllCheckBox().click();
	  Thread.sleep(1000);
	 
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	  
	  sharedFolder.ClickUpArrowBtn().click();
	  Thread.sleep(1000);
	  sharedFolder.renameOkBtn().click(); 
	  Thread.sleep(1000);
	  sharedFolder.uiControl("breadcrumRoot").click();
	  Thread.sleep(1000);
	  assertEquals(sharedFolder.secondRowName().getText(), file);
	  
	  }	

	@Test(priority = 38, groups = { "Functional" }, enabled = true)
	  // @PriorityInClass(1) 
	public void verifyEnglish84SingleByteFolderMovetoRootFolder() throws Exception {
	  setTestDesc("Verify that Move file to root folder when folder name length English 84 characters of single byte." ); 
	  
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  Thread.sleep(1000);
	  sharedFolder.OpenCreatedFolder().click();
	  Thread.sleep(1000);
	  String file = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZIJKLMNOPQRSTUVWXYvwxyzABCDEF.log";
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder(file);
	  Thread.sleep(1000);
	 
	  sharedFolder.selectAllCheckBox().click();
	  Thread.sleep(1000);
	 
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	  
	  sharedFolder.ClickUpArrowBtn().click();
	  Thread.sleep(1000);
	  sharedFolder.renameOkBtn().click(); 
	  Thread.sleep(1000);
	  sharedFolder.uiControl("breadcrumRoot").click();
	  Thread.sleep(1000);
	  assertEquals(sharedFolder.secondRowName().getText(), file);
	  
	  }	
	
	@Test(priority = 39, groups = { "Functional" }, enabled = true)
	  // @PriorityInClass(1) 
	public void verifyEnglish84MultiBytefolderMovetoRootFolder() throws Exception {
	  setTestDesc("Verify that Move file to root folder when folder name length English 84 characters of multi byte." ); 
	  
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  Thread.sleep(1000);
	  sharedFolder.OpenCreatedFolder().click();
	  Thread.sleep(1000);
	  String file = "ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺＰＱＲＳＴＵＶＷＸＹＺＴＵＶＷＸＹｖｗｘｙｚＡＢＣＤＥＦ.mkv";
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder(file);
	  Thread.sleep(1000);
	 
	  sharedFolder.selectAllCheckBox().click();
	  Thread.sleep(1000);
	 
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	  
	  sharedFolder.ClickUpArrowBtn().click();
	  Thread.sleep(1000);
	  sharedFolder.renameOkBtn().click(); 
	  Thread.sleep(1000);
	  sharedFolder.uiControl("breadcrumRoot").click();
	  Thread.sleep(1000);
	  assertEquals(sharedFolder.secondRowName().getText(), file);
	  
	  }	
	
	@Test(priority = 40, groups = { "Functional" }, enabled = true)
	  // @PriorityInClass(1) 
	public void verifyKatagana84SingleBytefolderMovetoRootFolder() throws Exception {
	  setTestDesc("Verify that Move file to root folder when folder name length Katagana 84 characters of single byte." ); 
	  
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  Thread.sleep(1000);
	  sharedFolder.OpenCreatedFolder().click();
	  Thread.sleep(1000);
	  String file = "ﾀｲﾌﾟ･ｲﾝｸﾞﾘｯｼｭ･ﾜｰﾄﾞｽﾞ･ｲﾝ･ｻﾞｰ･ﾎﾞｯｸｽ･ﾍﾞﾛｰ･ﾌﾟﾚｽ･ｺﾝﾊﾞｰﾄ･ﾀｰ･ｶｰﾀｶｰﾅｰ･ﾀｰ･ｺﾝﾊﾀｲﾌﾟ･ｲﾝｸﾞﾘｯｼ.bmp";
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder(file);
	  Thread.sleep(1000);
	 
	  sharedFolder.selectAllCheckBox().click();
	  Thread.sleep(1000);
	 
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	  
	  sharedFolder.ClickUpArrowBtn().click();
	  Thread.sleep(1000);
	  sharedFolder.renameOkBtn().click(); 
	  Thread.sleep(1000);
	  sharedFolder.uiControl("breadcrumRoot").click();
	  Thread.sleep(1000);
	  assertEquals(sharedFolder.secondRowName().getText(), file);
	  
	  }	
	
	@Test(priority = 41, groups = { "Functional" }, enabled = true)
	  // @PriorityInClass(1) 
	public void verifyKatagana84MultiBytefolderMovetoRootFolder() throws Exception {
	  setTestDesc("Verify that Move file to root folder when folder name length Katagana 84 characters of multi byte." ); 
	  
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  Thread.sleep(1000);
	  sharedFolder.OpenCreatedFolder().click();
	  Thread.sleep(1000);
	  String file = "タイプ・イングリッシュ・ワードズ・イン・ザー・ボックス・ベロー・プレス・コンバート・ター・カータカーナー・ター・コンバート・ゼム・インターンバート・ター・カータ.doc";
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder(file);
	  Thread.sleep(1000);
	 
	  sharedFolder.selectAllCheckBox().click();
	  Thread.sleep(1000);
	 
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	  
	  sharedFolder.ClickUpArrowBtn().click();
	  Thread.sleep(1000);
	  sharedFolder.renameOkBtn().click(); 
	  Thread.sleep(1000);
	  sharedFolder.uiControl("breadcrumRoot").click();
	  Thread.sleep(1000);
	  assertEquals(sharedFolder.secondRowName().getText(), file);
	  
	  }	

	@Test(priority = 42, groups = { "Functional" }, enabled = true)
	  // @PriorityInClass(1) 
	public void verifyKanji84MultiBytefolderMovetoRootFolder() throws Exception {
	  setTestDesc("Verify that Move file to root folder when folder name length Kanji 84 characters of multi byte." ); 
	  
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  Thread.sleep(1000);
	  sharedFolder.OpenCreatedFolder().click();
	  Thread.sleep(1000);
	  String file = "あぁかさたないぃきしちにあぁかさたないぃきしちにあぁかさたないぃきしちにあぁかさたないぃきしちにあぁかさたないぃきしちにあぁかさたないぃきぁかさたないぃきしち.gif";
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder(file);
	  Thread.sleep(1000);
	 
	  sharedFolder.selectAllCheckBox().click();
	  Thread.sleep(1000);
	 
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	  
	  sharedFolder.ClickUpArrowBtn().click();
	  Thread.sleep(1000);
	  sharedFolder.renameOkBtn().click(); 
	  Thread.sleep(1000);
	  sharedFolder.uiControl("breadcrumRoot").click();
	  Thread.sleep(1000);
	  assertEquals(sharedFolder.secondRowName().getText(), file);
	  
	  }	
	
	@Test(priority = 43, groups = { "Functional" }, enabled = true)
	  // @PriorityInClass(1) 
	
	
	public void verifyHiragana84MultiBytefolderMovetoRootFolder() throws Exception {
	  setTestDesc("Verify that Move file to root folder when folder name length Hiragana 84 characters of multi byte." ); 
	  
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  Thread.sleep(1000);
	  sharedFolder.OpenCreatedFolder().click();
	  Thread.sleep(1000);
	  String file = "教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教教育漢字教育漢字教育漢.flv";
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder(file);
	  Thread.sleep(1000);
	  sharedFolder.selectAllCheckBox().click();
	  Thread.sleep(1000);
	 
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	  
	  sharedFolder.ClickUpArrowBtn().click();
	  Thread.sleep(1000);
	  sharedFolder.renameOkBtn().click(); 
	  Thread.sleep(1000);
	  sharedFolder.uiControl("breadcrumRoot").click();
	  Thread.sleep(1000);
	  assertEquals(sharedFolder.secondRowName().getText(), file);
	  
	  }	
	
	@Test(priority = 44, groups = { "Functional" }, enabled = true)
	  // @PriorityInClass(1) 
	public void verifyNumber84SingleBytefolderMovetoRootFolder() throws Exception {
	  setTestDesc("Verify that Move file to root folder when folder name length Number 84 characters of single byte." ); 
	  
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  Thread.sleep(1000);
	  sharedFolder.OpenCreatedFolder().click();
	  Thread.sleep(1000);
	  String file = "11111111111111111111111111111111111111111111111111111111111111111111111111111111.mov";
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder(file);
	  Thread.sleep(1000);
	 
	  sharedFolder.selectAllCheckBox().click();
	  Thread.sleep(1000);
	 
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	  
	  sharedFolder.ClickUpArrowBtn().click();
	  Thread.sleep(1000);
	  sharedFolder.renameOkBtn().click(); 
	  Thread.sleep(1000);
	  sharedFolder.uiControl("breadcrumRoot").click();
	  Thread.sleep(1000);
	  assertEquals(sharedFolder.firstRowName().getText(), file);
	  
	  }	
	
	@Test(priority = 45, groups = { "Functional" }, enabled = true)
	  // @PriorityInClass(1) 
	public void verifyNumber84MultiBytefolderMovetoRootFolder() throws Exception {
	  setTestDesc("Verify that Move file to root folder when folder name length Number 84 characters of multi byte." ); 
	  
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  Thread.sleep(1000);
	  sharedFolder.OpenCreatedFolder().click();
	  Thread.sleep(1000);
	  String file = "１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１.mp4";
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder(file);
	  Thread.sleep(1000);
	 
	  sharedFolder.selectAllCheckBox().click();
	  Thread.sleep(1000);
	 
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	  
	  sharedFolder.ClickUpArrowBtn().click();
	  Thread.sleep(1000);
	  sharedFolder.renameOkBtn().click(); 
	  Thread.sleep(1000);
	  sharedFolder.uiControl("breadcrumRoot").click();
	  Thread.sleep(1000);
	  assertEquals(sharedFolder.secondRowName().getText(), file);
	  
	  }	

	@Test(priority = 46, groups = { "Functional" }, enabled = true)
	  // @PriorityInClass(1) 
	public void verifySpecial84MultiBytefolderMovetoRootFolder() throws Exception {
	  setTestDesc("Verify that Move file to root folder when folder name length Special 84 characters of multi byte." ); 
	  
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  Thread.sleep(1000);
	  sharedFolder.OpenCreatedFolder().click();
	  Thread.sleep(1000);
	  String file = "！＠＃＄％＾＆＊（）＿＋＝－｀～［｛］｝￥｜’”；：／？．＞＜，！＠＃＄％＾＆＊（）＿＋＝－｀～［｛］｝￥｜’”；：／？．＞＜，？．＞＜＜～［｛］｝￥｜’”；：.JPG";
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder(file);
	  Thread.sleep(1000);
	 
	  sharedFolder.selectAllCheckBox().click();
	  Thread.sleep(1000);
	 
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	  
	  sharedFolder.ClickUpArrowBtn().click();
	  Thread.sleep(1000);
	  sharedFolder.renameOkBtn().click(); 
	  Thread.sleep(1000);
	  sharedFolder.uiControl("breadcrumRoot").click();
	  Thread.sleep(1000);
	  assertEquals(sharedFolder.secondRowName().getText(), file);
	  
	  }	
	
	
	
	@Test(priority = 47, groups = { "Functional" }, enabled = true)
	  // @PriorityInClass(1) 
	public void VerifySourcefolderIsNotAllowedToSelect() throws Exception {
	  setTestDesc("Verify that Source folder is not allowed to select and Verify that folder selection dialog has no folders to move a file" ); 
	  
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");		
	  Thread.sleep(1000);	
	 
	  sharedFolder.selectAllCheckBox().click();
	  Thread.sleep(1000);
	 
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	
	  assertEquals( sharedFolder.SourceFolderNotDisplayed().getText().trim(),getLocalizedMessage("There are no subfolders."));
	  Thread.sleep(1000);
	  sharedFolder.SaveChangeDestinatioDialogCancelBtn().click(); 
	  Thread.sleep(1000);
	 
	  
	  }	
	  */
	@Test(priority = 48, groups = { "Functional" }, enabled = false)
	  // @PriorityInClass(1) 
	public void VerifyUpperParentAllowedToSelect() throws Exception {
	  setTestDesc("Verify that folder selection dialog is allowed to navigate to upper/parent folder and lower/ sub folders to move afile. Verify that Folder selection dialog is closed after File moved success." ); 
	  
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  Thread.sleep(1000);
	  
	  sharedFolder.OpenCreatedFolder().click();
	  Thread.sleep(1000);	
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("SubFolder");
	  String BeforeDltfileCnt = sharedFolder.getFileCount();
	  Thread.sleep(1000);
	  sharedFolder.OpenCreatedSubFolder().click();
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("SubToSubFolder");		
	  Thread.sleep(1000);
	 
	  sharedFolder.selectAllCheckBox().click();
	  Thread.sleep(1000);
	 
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	  //
	  sharedFolder.ClickParentfolderBtn().click();
	  Thread.sleep(1000);
		/*
		 * sharedFolder.ClickParentFolder().doubleClick(); Thread.sleep(1000);
		 */
	  
	  sharedFolder.renameOkBtn().click(); 
	  Thread.sleep(1000);
	  
	  sharedFolder.uiControl("breadcrumParentSub").click();
	  Thread.sleep(1000);
	  String AfterDltfileCnt = sharedFolder.getFileCount();
	  Thread.sleep(1000);	 
	  assertNotEquals(BeforeDltfileCnt,AfterDltfileCnt);
	 	  
	  }	
	@Test(priority = 49, groups = { "Functional" }, enabled = true)
	  // @PriorityInClass(1) 
	public void Verify() throws Exception {
	  setTestDesc("Verify that File/Folder is deleted in the source location after File moved success.." ); 
	  
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("AutoTest");
	  Thread.sleep(1000);
	  
	  sharedFolder.OpenCreatedFolder().click();
	  Thread.sleep(1000);	
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("SubFolder");
	 
	  Thread.sleep(1000);
	  sharedFolder.OpenCreatedSubFolder().click();
	  sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	  sharedFolderCreation.createFolder("SubToSubFolder");		
	  Thread.sleep(1000);
	  sharedFolder.uploadBtn().click();
	  SharedFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\");
	  SharedFolder.uploadFileWithRobot("File1.png");
	  Thread.sleep(15000);
	  
	  String BeforeDltfileCnt = sharedFolder.getFileCount();
	  sharedFolder.selectAllCheckBox().click();
	  Thread.sleep(1000);
	 
	  sharedFolder.changeSaveDestinationBtn().click();
	  Thread.sleep(1000);
	  //
	  sharedFolder.ClickParentfolderBtn().click();
	  Thread.sleep(1000);
		/*
		 * sharedFolder.ClickParentFolder().doubleClick(); Thread.sleep(1000);
		 */
	  
	  sharedFolder.renameOkBtn().click(); 
	  Thread.sleep(1000);	  
	 
	  String AfterDltfileCnt = sharedFolder.getFileCount();
	  Thread.sleep(1000);	 
	  assertNotEquals(BeforeDltfileCnt,AfterDltfileCnt);
	 	  
	  }	
	
	
	
	
	
	 /*	  
	 @Test(priority = 16, groups = { "Functional" }, enabled = true)
	 * // @PriorityInClass(1) public void verifyFolderMoveToSubFolder() throws
	 * Exception {
	 * setTestDesc("Verify that Admin can move folder to sub folders in the root folder."
	 * );
	 * 
	 * sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
	 * sharedFolderCreation.createFolder("AutoTest1"); Thread.sleep(1000);
	 * sharedFolder.selectSinlgeCheckBox().click(); Thread.sleep(1000);
	 * sharedFolder.changeSaveDestinationBtn().click(); Thread.sleep(1000);
	 * sharedFolder.SelectFolderInSameLevel().click(); Thread.sleep(1000);
	 * sharedFolder.renameOkBtn().click(); Thread.sleep(1000);
	 * sharedFolder.OpenCreatedFolder().click(); Thread.sleep(1000);
	 * assertEquals(sharedFolder.firstRowName().getText().substring(0, 9),
	 * "AutoTest1");
	 * 
	 * }
	 * 
	 * @Test(priority = 17, groups = { "Functional" }, enabled = true)
	 * // @PriorityInClass(1) public void verifyMultipleFileFolderMoveToSubFolder()
	 * throws Exception {
	 * setTestDesc("Verify that Admin can move multiple file/folder to sub folders in the root folder."
	 * ); String BeforeDltfileCnt = sharedFolder.getFileCount();
	 * sharedFolder.selectAllCheckBox().click(); Thread.sleep(1000);
	 * sharedFolder.selectSecondCheckBox().click();
	 * sharedFolder.changeSaveDestinationBtn().click(); Thread.sleep(1000);
	 * sharedFolder.SelectFolderInSameLevel().click(); Thread.sleep(1000);
	 * sharedFolder.renameOkBtn().click(); Thread.sleep(1000); String
	 * AfterDltfileCnt = sharedFolder.getFileCount();
	 * assertNotEquals(BeforeDltfileCnt, AfterDltfileCnt);
	 * 
	 * }
	 * 
	 * @Test(priority = 19,groups = { "Functional" }, enabled = false)
	 * // @PriorityInClass(1) public void
	 * verifyFolderFileFolderMoveToPersonalFolder() throws Exception {
	 * setTestDesc("Verify that Admin can move multiple files/folders from Shared folder to the personal folders."
	 * );
	 * 
	 * // sharedFolder.uploadBtn().click(); //
	 * sharedFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\"); //
	 * sharedFolder.uploadFileWithRobot("\"File1.pdf" + "\" \"File1.jpg"+ "\" //
	 * \"File1.png" + "\" \"File1.jpeg"+ "\""); // Thread.sleep(40000); //
	 * sharedFolderCreation = sharedFolder.gotoSharedFolderCreation(); //
	 * sharedFolderCreation.createFolder("AutoTest1"); String BeforeDltfileCnt =
	 * sharedFolder.getFileCount(); sharedFolder.selectAllCheckBox().click();
	 * Thread.sleep(1000); sharedFolder.changeSaveDestinationBtn().click();
	 * Thread.sleep(1000); sharedFolder.SelectFileMovetoHome().click();
	 * Thread.sleep(1000);
	 * sharedFolder.selectFileMovetoPesonalFolder().doubleClick();
	 * Thread.sleep(1000); sharedFolder.renameOkBtn().click(); Thread.sleep(1000);
	 * 
	 * String AfterDltfileCnt = sharedFolder.getFileCount();
	 * assertNotEquals(BeforeDltfileCnt, AfterDltfileCnt);
	 * 
	 * }
	 * 
	 */
}
