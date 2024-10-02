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
import tests.FileUpload;
import tests.InitialTestSetup;

public class SharedFolderSmokeTest extends InitialTestSetup {
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
		loginPage.userNameTxtbox().sendKeys("test5.cocoro@gmail.com");
		loginPage.passwordTxtbox().sendKeys("Demo88Demo");
		homePage = loginPage.login();
		Thread.sleep(5000);

	}

	/*
	 * @BeforeClass(alwaysRun = true) public void ReportProperties(ITestContext
	 * context) throws Exception {
	 * 
	 * Properties properties = new Properties();
	 * 
	 * // Set the properties key-value pairs properties.setProperty("browser",
	 * "chrome"); properties.setProperty("port", "6235");
	 * properties.setProperty("url", "https://m7.networkprint.jp/storage/shr/");
	 * properties.setProperty("wait.implicit", "45");
	 * properties.setProperty("wait.explicit", "90");
	 * properties.setProperty("wait.small", "1000");
	 * properties.setProperty("wait.medium", "3000");
	 * properties.setProperty("wait.high", "10000");
	 * properties.setProperty("language", "Japanese");
	 * properties.setProperty("Execution.env", "windows 11");
	 * properties.setProperty("Execution.engr", "Irappa Hukkeri");
	 * properties.setProperty("Execution.build", "Ver.2.8.0-5");
	 * 
	 * // Specify the path where the properties file will be saved try
	 * (FileOutputStream outputStream = new
	 * FileOutputStream("./properties/chrome/config1.properties")) { // Write the
	 * properties to the file properties.store(outputStream,
	 * "Application Configuration");
	 * 
	 * System.out.println("Properties file created successfully."); } catch
	 * (IOException e) { e.printStackTrace(); }
	 * 
	 * 
	 * }
	 */

	@BeforeMethod(alwaysRun = true)
	public void verifyFolderIsEmpty() throws Exception {
		sharedFolder = homePage.gotoSharedFolder();

		Thread.sleep(2000);
		// System.out.println("This is NoData from App: " +
		// personalFolder.uiControl("noData").getText());
		// System.out.println("This is NoData from Expected: " +
		// getLocalizedMessage("noFilesInTable").trim());
		// if
		// (personalFolder.uiControl("noData").getText().trim().equals(getLocalizedMessage("noFilesInTable").trim()))
		// {
		// System.out.println("I'm Before() - No folders are found..!");
		// } else {
		// personalFolder.selectAllCheckBox().click();
		// PersonalFolderDelete deleteFolder =
		// personalFolder.gotoPersonalFolderDelete();
		// deleteFolder.okBtn().click();
		/// Thread.sleep(2000);
		// System.out.println("I'm Before() Else part - No folders are found..!");
		// assertEquals(personalFolder.uiControl("noData").getText(),
		// getLocalizedMessage("noFilesInTable").trim());
		// }
	}

	@AfterMethod(alwaysRun = true)
	public void deleteAllFolders() throws Exception {
		System.out.println("This is Expected result: " + getLocalizedMessage("noFilesInTable").trim());
		

		sharedFolder.uiControl("homeLink").click();
		
		Thread.sleep(2000);
		// }
	}
	/*
	 * @Test(groups = { "Functional" }, enabled = true) // @PriorityInClass(1)
	 * public void verifyAppBuildVersion() throws Exception {
	 * setTestDesc("Verify that App version"); String AppBuildVersion =
	 * sharedFolder.getAppBuildVersion().getText();
	 * System.out.println("the app version is " + AppBuildVersion);
	 * assertTrue(sharedFolder.getAppBuildVersion().isDisplayed()); }
	 */

	@Test(priority = 1, groups = { "Functional" }, enabled = true)
	// @PriorityInClass(1)
	public void verifyFolderIsCreated() throws Exception {
		setTestDesc(
				"Verify that Admin can create folder in the root folder.Folder should be created in the root folder");

		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("AutoTest");
		assertEquals(sharedFolder.firstRowName().getText(), "AutoTest");

	}

	@Test(priority = 2, groups = { "Functional" }, enabled = true)
	// @PriorityInClass(2)
	public void verifySingleFileUpload() throws Exception {
		setTestDesc("Verify that Admin can Upload a file to root folder.");
		sharedFolder.uploadBtn().click();
		Thread.sleep(2000);
		sharedFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\");
		sharedFolder.uploadFileWithRobot("Single.pdf");
		Thread.sleep(8000);
		sharedFolder.gotoSearchSharedFolder("Single.pdf");
		assertEquals(sharedFolder.firstRowName().getText(), "Single.pdf");

	}

	@Test(priority = 3, groups = { "Functional" }, enabled = true)
	@PriorityInClass(1)
	public void verifyMultiFileUpload() throws Exception {
		setTestDesc("Verify that Admin can Upload multi files to root folder.");
		sharedFolder.uploadBtn().click();
		sharedFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\");
		sharedFolder
				.uploadFileWithRobot("\"File1.pdf" + "\" \"File1.jpg" + "\" \"File1.png" + "\" \"File1.jpeg" + "\"");
		Thread.sleep(30000);
		sharedFolder.gotoSearchSharedFolder("File1.pdf");
		assertEquals(sharedFolder.firstRowName().getText(), "File1.pdf");
	}

	@Test(priority = 4, groups = { "Functional" }, enabled = true)
	// @PriorityInClass(3)
	public void verifySingleFolderDownload() throws Exception {
		setTestDesc(
				"Verify that Admin can download sinlge folder/file from the root folder.file/folder should be available in the root folder");

		sharedFolder.selectSinlgeCheckBox().click();
		Thread.sleep(3000);
		sharedFolder.downloadBtn().click();
		Thread.sleep(3000);

	}

	@Test(priority = 5, groups = { "Functional" }, enabled = true)
	@PriorityInClass(3)
	public void verifyMultiFolderDownload() throws Exception {
		setTestDesc(
				"Verify that Admin can download multiple folder/file from the root folder.Folder should be available in the root folder");
		Thread.sleep(3000);
		sharedFolder.selectAllCheckBox().click();
		Thread.sleep(3000);
		sharedFolder.downloadBtn().click();
		Thread.sleep(8000);

	}

	@Test(priority = 6, groups = { "Functional" }, enabled = true)
	// @PriorityInClass(3)
	public void verifyFolderSearch() throws Exception {
		setTestDesc(
				"Verify that Admin can search folder/file from the root folder.Folder should be available in the root folder");
		String fineName = "File1.png";
		SharedFolderSearch gotoSearchSharedFolder = sharedFolder.gotoSearchSharedFolder(fineName);
		String searchResultStr = gotoSearchSharedFolder.getSearchResultStr();
		Thread.sleep(1000);
		assertEquals(searchResultStr, "検索結果");
		assertEquals(sharedFolder.firstRowName().getText(), fineName);

	}

	@Test(priority = 7, groups = { "Functional" }, enabled = true)
	@PriorityInClass(3)
	public void verifySearchPageReturnButton() throws Exception {
		setTestDesc(
				"Verify that Admin can search folder/file from the root folder and click on Return button to home page");
		String fineName = "File1.png";
		SharedFolderSearch gotoSearchSharedFolder = sharedFolder.gotoSearchSharedFolder(fineName);
		Thread.sleep(1000);
		gotoSearchSharedFolder.returnBtn().click();
		Thread.sleep(1000);
		assertEquals(sharedFolder.getRootfolder().getText(), "共有フォルダー");

	}


	@Test(priority = 8, groups = { "Functional" }, enabled = true)
	@PriorityInClass(1)
	public void verifyContextMenuDownload() throws Exception {
		setTestDesc("Verify that Admin can download folder/file from the context menu in the root folder");
		sharedFolder.OperationMenu().click();
		Thread.sleep(2000);
		sharedFolder.downloadFromContextBtn().click();
		Thread.sleep(1000);
		boolean contextMenu = sharedFolder.OperationMenu().isDisplayed();
		assertTrue(contextMenu);

	}

	@Test(priority = 9, groups = { "Functional" }, enabled = true)
	// @PriorityInClass(2)
	public void verifyContextMenuRename() throws Exception {
		setTestDesc("Verify that Admin can Rename folder/file from the context menu in the root folder");

		sharedFolder.OperationMenu().click();
		Thread.sleep(2000);
		sharedFolder.renameFromContextBtn().click();
		Thread.sleep(1000);
		sharedFolder.renameInput().sendKeys("Rename");
		Thread.sleep(1000);
		sharedFolder.renameOkBtn().click();
		Thread.sleep(1000);
		sharedFolder.gotoSearchSharedFolder("Rename");
		assertEquals(sharedFolder.firstRowName().getText().substring(0, 6), "Rename");

	}

	@Test(priority = 10, groups = { "Functional" }, enabled = true)
	// @PriorityInClass(2)
	public void verifyContextMenuFilePreview() throws Exception {
		setTestDesc("Verify that Admin can open file preview from the context menu in the root folder");

		sharedFolder.OperationMenu().click();
		Thread.sleep(1000);
		sharedFolder.openFilePreview().click();
		Thread.sleep(1000);
		assertTrue(sharedFolder.fileClose().exists());
		sharedFolder.fileClose().click();
	}

	@Test(priority = 11, groups = { "Functional" }, enabled = true)
	// @PriorityInClass(2)
	public void verifyContextMenuFileMove() throws Exception {
		setTestDesc(
				"Verify that Admin can move file from the context menu in the root folder. Files or folders should move to selected folder ");
		// personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		// personalFolderCreation.createFolder("AutoTest");
		Thread.sleep(1000);
		// personalFolder.firstRowName()
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

	@Test(priority = 12, groups = { "Functional" }, enabled = true)
	// @PriorityInClass(2)
	public void verifyContextMenuFileShare() throws Exception {
		setTestDesc("Verify that Admin can share file from the context menu in the root folder. ");
		sharedFolder.OperationMenu().click();
		Thread.sleep(1000);
		sharedFolder.fileShare().click();
		Thread.sleep(1000);
		sharedFolder.clickShareBtn().click();
		Thread.sleep(1000);
		assertEquals(sharedFolder.shareFileHeader().getText(), "ファイルの一時保存先");
		Thread.sleep(1000);
		sharedFolder.shareOkBtn().click();
		Thread.sleep(1000);
	}

	@Test(priority = 13, groups = { "Functional" }, enabled = true)
//	@PriorityInClass(4)
	public void verifySingleFileFolderDelete() throws Exception {
		setTestDesc(
				"Verify that Admin can delete folder/file from the root folder.Folder should be available in the root folder");
		String BeforeDltfileCnt = sharedFolder.getFileCount();
		sharedFolder.selectSinlgeCheckBox().click();
		SharedFolderDelete deleteFolder = sharedFolder.gotoSharedFolderDelete();
		deleteFolder.okBtn().click();
		Thread.sleep(4000);
		String AfterDltfileCnt = sharedFolder.getFileCount();
		assertNotEquals(BeforeDltfileCnt, AfterDltfileCnt);

	}

	@Test(priority = 14, groups = { "Functional" }, enabled = true)
	@PriorityInClass(3)
	public void verifyContextMenuDelete() throws Exception {
		setTestDesc("Verify that Admin can delete folder/file from the context menu in the root folder");
		String BeforeDltfileCnt = sharedFolder.getFileCount();
		sharedFolder.OperationMenu().click();
		Thread.sleep(2000);
		sharedFolder.deleteBtnfromContext().click();
		Thread.sleep(2000);
		sharedFolder.deleteMsgOkBtn().click();
		Thread.sleep(2000);
		String AfterDltfileCnt = sharedFolder.getFileCount();
		assertNotEquals(BeforeDltfileCnt, AfterDltfileCnt);

	}

	@Test(priority = 14, groups = { "Functional" }, enabled = true)
//	@PriorityInClass(4)
	public void verifyMultiFileFolderDelete() throws Exception {
		setTestDesc(
				"Verify that Admin can delete folder/file from the root folder.Folder should be available in the root folder");
		String BeforeDltfileCnt = sharedFolder.getFileCount();
		sharedFolder.selectAllCheckBox().click();
		SharedFolderDelete deleteFolder = sharedFolder.gotoSharedFolderDelete();
		deleteFolder.okBtn().click();
		Thread.sleep(4000);
		String AfterDltfileCnt = sharedFolder.getFileCount();
		assertNotEquals(BeforeDltfileCnt, AfterDltfileCnt);


	}

	@Test(priority = 15, groups = { "Functional" }, enabled = true)
	// @PriorityInClass(1)
	public void verifyFolderFileMove() throws Exception {
		setTestDesc("Verify that Admin can move file to sub folders in the root folder.");
		sharedFolder.uploadBtn().click();
		sharedFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\");
		sharedFolder.uploadFileWithRobot("\"File1.pdf" + "\" \"File1.jpg" + "\" \"File1.png" + "\" \"File1.jpeg" + "\"");
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

	@Test(priority = 16, groups = { "Functional" }, enabled = true)
	// @PriorityInClass(1)
	public void verifyFolderMoveToSubFolder() throws Exception {
		setTestDesc("Verify that Admin can move folder to sub folders in the root folder.");

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
		assertEquals(sharedFolder.firstRowName().getText().substring(0, 9), "AutoTest1");

	}

	@Test(priority = 17, groups = { "Functional" }, enabled = true)
	// @PriorityInClass(1)
	public void verifyMultipleFileFolderMoveToSubFolder() throws Exception {
		setTestDesc("Verify that Admin can move multiple file/folder to sub folders in the root folder.");
		String BeforeDltfileCnt = sharedFolder.getFileCount();
		sharedFolder.selectAllCheckBox().click();
		Thread.sleep(1000);
		sharedFolder.selectSecondCheckBox().click();
		sharedFolder.changeSaveDestinationBtn().click();
		Thread.sleep(1000);
		sharedFolder.SelectFolderInSameLevel().click();
		Thread.sleep(1000);
		sharedFolder.renameOkBtn().click();
		Thread.sleep(1000);
		String AfterDltfileCnt = sharedFolder.getFileCount();
		assertNotEquals(BeforeDltfileCnt, AfterDltfileCnt);

	}
	
	@Test(priority = 18, groups = { "Functional" }, enabled = true)
//	@PriorityInClass(4)
	public void verifyFolderDelete() throws Exception {
		setTestDesc(
				"Verify that Admin can delete folder which files and folders from the root folder.Folder should be available in the root folder");
		String BeforeDltfileCnt = sharedFolder.getFileCount();
		sharedFolder.selectAllCheckBox().click();
		SharedFolderDelete deleteFolder = sharedFolder.gotoSharedFolderDelete();
		deleteFolder.okBtn().click();
		Thread.sleep(4000);
		String AfterDltfileCnt = sharedFolder.getFileCount();
		assertNotEquals(BeforeDltfileCnt, AfterDltfileCnt);

	}

	@Test(priority = 19,groups = { "Functional" }, enabled = false)
	// @PriorityInClass(1)
	public void verifyFolderFileFolderMoveToPersonalFolder() throws Exception {
		setTestDesc("Verify that Admin can move multiple files/folders from Shared folder to the personal folders.");

		// sharedFolder.uploadBtn().click();
		// sharedFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\");
		// sharedFolder.uploadFileWithRobot("\"File1.pdf" + "\" \"File1.jpg"+ "\"
		// \"File1.png" + "\" \"File1.jpeg"+ "\"");
		// Thread.sleep(40000);
		// sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		// sharedFolderCreation.createFolder("AutoTest1");
		String BeforeDltfileCnt = sharedFolder.getFileCount();
		sharedFolder.selectAllCheckBox().click();
		Thread.sleep(1000);
		sharedFolder.changeSaveDestinationBtn().click();
		Thread.sleep(1000);
		sharedFolder.SelectFileMovetoHome().click();
		Thread.sleep(1000);
		sharedFolder.selectFileMovetoPesonalFolder().doubleClick();
		Thread.sleep(1000);
		sharedFolder.renameOkBtn().click();
		Thread.sleep(1000);

		String AfterDltfileCnt = sharedFolder.getFileCount();
		assertNotEquals(BeforeDltfileCnt, AfterDltfileCnt);

	}
	

}
