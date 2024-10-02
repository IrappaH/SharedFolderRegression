package test.script;

import static org.testng.Assert.assertEquals;
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
import pages.PersonalFolderDelete;
import pages.SharedFolder;
import pages.SharedFolderCreation;
import pages.SharedFolderDelete;
import tests.Autoutil;
import tests.InitialTestSetup;

public class CreateFolderInShareRootFolder extends InitialTestSetup implements Autoutil {

	private SharedFolder sharedFolder = null;
	private SharedFolderCreation sharedFolderCreation = null;
	private HomePage homePage = null;

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

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(1)
	public void verifyFolderIsCreated() throws Exception {
		setTestDesc(
				"Verify that Admin can create folder in the root folder.Folder should be create in the root folder");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(Autoutil.File84[0]);
		assertEquals(sharedFolder.firstRowName().getText(), Autoutil.File84[0]);

	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(2)
	public void verifySpecialCharFolderIsCreated() throws Exception {
		setTestDesc(
				"Verify that Admin can create folder in the root folder.Folder should be create in the root folder");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(Autoutil.File84[1]);
		assertEquals(sharedFolder.firstRowName().getText(), Autoutil.File84[1]);

	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(3)
	public void verify84LengthEngCharFolderIsCreatedSingleByte() throws Exception {
		setTestDesc(
				"Verify that Creating Folder name length English 84 characters of single byte in the Root folder.Folder should be created ");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(Autoutil.File84[2]);
		assertEquals(sharedFolder.firstRowName().getText(), Autoutil.File84[2]);
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(4)
	public void verify84LengthEngCharFolderIsCreatedMultiByte() throws Exception {
		setTestDesc(
				"Verify that Creating Folder name length English 84 characters of multi byte in the Root folder.Folder should be created ");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(Autoutil.File84[3]);
		assertEquals(sharedFolder.firstRowName().getText(), Autoutil.File84[3]);
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(5)
	public void verify84LengthKataganaCharFolderIsCreatedSingleByte() throws Exception {
		setTestDesc(
				"Verify that Creating Folder name length Katagana 84 characters of single byte in the Root folder.Folder should be created ");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(Autoutil.File84[4]);
		assertEquals(sharedFolder.firstRowName().getText(), Autoutil.File84[4]);
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(6)
	public void verify84LengthKataganaCharFolderIsCreatedMultiByte() throws Exception {
		setTestDesc(
				"Verify that Creating Folder name length Katagana 84 characters of multi byte in the Root folder.Folder should be created ");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(Autoutil.File84[5]);
		assertEquals(sharedFolder.firstRowName().getText(), Autoutil.File84[5]);
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(7)
	public void verify84LengthHiraganaCharFolderIsCreatedMultiByte() throws Exception {
		setTestDesc(
				"Verify that Creating Folder name length Hiragana 84 characters of multi byte in the Root folder.Folder should be created ");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(Autoutil.File84[6]);
		assertEquals(sharedFolder.firstRowName().getText(), Autoutil.File84[6]);
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(8)
	public void verify84LengthKanjiCharFolderIsCreatedMultiByte() throws Exception {
		setTestDesc(
				"Verify that Creating Folder name length Kanji 84 characters of multi byte in the Root folder.Folder should be created ");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(Autoutil.File84[7]);
		assertEquals(sharedFolder.firstRowName().getText(), Autoutil.File84[7]);
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)

	@PriorityInClass(9)
	public void verify84LengthSymbolsCharFolderIsCreatedMultiByte() throws Exception {
		setTestDesc(
				"Verify that Creating Folder name length Symbols 84 characters of multi byte in the Root folder.Folder should be created ");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(Autoutil.File84[8]);
		assertEquals(sharedFolder.firstRowName().getText(), Autoutil.File84[8]);
	}

	// Folder name length 85 characters

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(10)
	public void verify85LengthEngCharFolderIsCreatedSingleByte() throws Exception {
		setTestDesc(
				"Verify that Creating Folder name length English 85 characters of single byte in the Root folder.Folder should be created ");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(Autoutil.File85[0]);
		assertEquals(sharedFolder.ErrorfolderNamelegnttoolong().getText(), getLocalizedMessage("FolderCreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, enabled = true)
	@PriorityInClass(11)
	public void verify85LengthEngCharFolderIsCreatedMultiByte() throws Exception {
		setTestDesc(
				"Verify that Creating Folder name length English 85 characters of multi byte in the Root folder.Folder should be created ");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(Autoutil.File85[1]);
		assertEquals(sharedFolder.ErrorfolderNamelegnttoolong().getText(), getLocalizedMessage("FolderCreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, enabled = true)
	@PriorityInClass(12)
	public void verify85LengthKataganaCharFolderIsCreatedSingleByte() throws Exception {
		setTestDesc(
				"Verify that Creating Folder name length Katagana 85 characters of single byte in the Root folder.Folder should be created ");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(Autoutil.File85[2]);
		assertEquals(sharedFolder.ErrorfolderNamelegnttoolong().getText(), getLocalizedMessage("FolderCreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, enabled = true)
	@PriorityInClass(13)
	public void verify85LengthKataganaCharFolderIsCreatedMultiByte() throws Exception {
		setTestDesc(
				"Verify that Creating Folder name length Katagana 85 characters of multi byte in the Root folder.Folder should be created ");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(Autoutil.File85[3]);
		assertEquals(sharedFolder.ErrorfolderNamelegnttoolong().getText(), getLocalizedMessage("FolderCreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, enabled = true)
	@PriorityInClass(14)
	public void verify85LengthHiraganaCharFolderIsCreatedMultiByte() throws Exception {
		setTestDesc(
				"Verify that Creating Folder name length Hiragana 85 characters of multi byte in the Root folder.Folder should be created ");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(Autoutil.File85[4]);
		assertEquals(sharedFolder.ErrorfolderNamelegnttoolong().getText(), getLocalizedMessage("FolderCreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, enabled = true)
	@PriorityInClass(15)
	public void verify85LengthKanjiCharFolderIsCreatedMultiByte() throws Exception {
		setTestDesc(
				"Verify that Creating Folder name length Kanji 85 characters of multi byte in the Root folder.Folder should be created ");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(Autoutil.File85[5]);
		assertEquals(sharedFolder.ErrorfolderNamelegnttoolong().getText(), getLocalizedMessage("FolderCreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}

	@Test(groups = { "CreateFolder" }, enabled = true)
	@PriorityInClass(16)
	public void verify85LengthSymbolsCharFolderIsCreatedMultiByte() throws Exception {
		setTestDesc(
				"Verify that Creating Folder name length Symbols 85 characters of multi byte in the Root folder.Folder should be created ");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(Autoutil.File85[6]);
		assertEquals(sharedFolder.ErrorfolderNamelegnttoolong().getText(), getLocalizedMessage("FolderCreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
	}
	
	//Folder creation using Unsupported Characters
	
	
	

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(17)
	public void verifyErrorMsgIsDisplayedForBackSlash() throws Exception {
		setTestDesc(
				"Verify that unsupported characters to create folder in root folder '\' (half-width backslash).Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("\\");
		assertEquals(sharedFolder.errorMsginvalid().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true, enabled = true)
	@PriorityInClass(18)
	public void verifyErrorMsgIsDisplayedForFontSlash() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder '/'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("/\"/");
		assertEquals(sharedFolder.errorMsginvalid().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(19)
	public void verifyErrorMsgIsDisplayedForColon() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder ':'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(":");
		assertEquals(sharedFolder.errorMsginvalid().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(20)
	public void verifyErrorMsgIsDisplayedForMultiplication() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder '*'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("*");
		assertEquals(sharedFolder.errorMsginvalid().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(21)
	public void verifyErrorMsgIsDisplayedForQuestionMark() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder '?'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("?");
		assertEquals(sharedFolder.errorMsginvalid().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true, enabled = true)
	@PriorityInClass(22)
	public void verifyErrorMsgIsDisplayedForDoubleQuotes() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'DoubleQuotes'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("/\"/");
		assertEquals(sharedFolder.errorMsginvalid().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(23)
	public void verifyErrorMsgIsDisplayedForLessThan() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'LessThan'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("<");
		assertEquals(sharedFolder.errorMsginvalid().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(24)
	public void verifyErrorMsgIsDisplayedForGreaterThan() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'GreaterThan'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(">");
		assertEquals(sharedFolder.errorMsginvalid().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(25)
	public void verifyErrorMsgIsDisplayedForPipe() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder '|'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("|");
		assertEquals(sharedFolder.errorMsginvalid().getText(), getLocalizedMessage("CreateErrorMgs"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(26)
	public void verifyErrorMsgIsDisplayedForremote_access_backup() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder '.remote_access_backup'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(".remote_access_backup");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
	
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(27)
	public void verifyErrorMsgIsDisplayedForREMOTE_ACCESS_BACKUP() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder '.REMOTE_ACCESS_BACKUP'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(".REMOTE_ACCESS_BACKUP");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(28)
	public void verifyErrorMsgIsDisplayedFortrashbox() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'trashbox'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("trashbox");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(29)
	public void verifyErrorMsgIsDisplayedFortrashBox() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'trashBox'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("trashBox");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(30)
	public void verifyErrorMsgIsDisplayedForTRASHBOX() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'TRASHBOX'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("TRASHBOX");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(31)
	public void verifyErrorMsgIsDisplayedForsnapshot() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder '.snapshot'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(".snapshot");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(32)
	public void verifyErrorMsgIsDisplayedForSNAPSHOT() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder '.SNAPSHOT'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(".SNAPSHOT");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(33)
	public void verifyErrorMsgIsDisplayedForsnapshots() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder '.snapshots'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(".snapshots");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(34)
	public void verifyErrorMsgIsDisplayedForSNAPSHOTS() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder '.SNAPSHOTS'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(".SNAPSHOTS");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(35)
	public void verifyErrorMsgIsDisplayedForSnapshot() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder '.Snapshot'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(".Snapshot");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(36)
	public void verifyErrorMsgIsDisplayedForCON() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'CON'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("CON");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(37)
	public void verifyErrorMsgIsDisplayedForPRN() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'PRN'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("PRN");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(38)
	public void verifyErrorMsgIsDisplayedForAUX() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'AUX'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("AUX");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(39)
	public void verifyErrorMsgIsDisplayedForNUL() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'NUL'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("NUL");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(40)
	public void verifyErrorMsgIsDisplayedForCLOCK$() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'CLOCK$'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("CLOCK$");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(41)
	public void verifyErrorMsgIsDisplayedForCOM0() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'COM0'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("COM0");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(42)
	public void verifyErrorMsgIsDisplayedForCOM1() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'COM1'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("COM1");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}
	
	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(43)
	public void verifyErrorMsgIsDisplayedForCOM2() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'COM2'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("COM2");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(44)
	public void verifyErrorMsgIsDisplayedForCOM3() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'COM3'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("COM3");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(45)
	public void verifyErrorMsgIsDisplayedForCOM4() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'COM4'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("COM4");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(46)
	public void verifyErrorMsgIsDisplayedForCOM5() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'COM5'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("COM5");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(47)
	public void verifyErrorMsgIsDisplayedForCOM6() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'COM6'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("COM6");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(48)
	public void verifyErrorMsgIsDisplayedForCOM7() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'COM7'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("COM7");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(49)
	public void verifyErrorMsgIsDisplayedForCOM8() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'COM8'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("COM8");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(50)
	public void verifyErrorMsgIsDisplayedForCOM9() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'COM9'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("COM9");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(51)
	public void verifyErrorMsgIsDisplayedForLPT0() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'LPT0'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("LPT0");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(52)
	public void verifyErrorMsgIsDisplayedForLPT1() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'LPT1'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("LPT1");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(53)
	public void verifyErrorMsgIsDisplayedForLPT2() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'LPT2'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("LPT2");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(54)
	public void verifyErrorMsgIsDisplayedForLPT3() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'LPT3'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("LPT3");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(55)
	public void verifyErrorMsgIsDisplayedForLPT4() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'LPT4'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("LPT4");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(56)
	public void verifyErrorMsgIsDisplayedForLPT5() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'LPT5'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("LPT5");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(57)
	public void verifyErrorMsgIsDisplayedForLPT6() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'LPT6'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("LPT6");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(58)
	public void verifyErrorMsgIsDisplayedForLPT7() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'LPT7'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("LPT7");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(59)
	public void verifyErrorMsgIsDisplayedForLPT8() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'LPT8'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("LPT8");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(60)
	public void verifyErrorMsgIsDisplayedForLPT9() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'LPT9'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("LPT9");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(61)
	public void verifyErrorMsgIsDisplayedForcon() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'con'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("con");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(62)
	public void verifyErrorMsgIsDisplayedForprn() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'prn'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("prn");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(63)
	public void verifyErrorMsgIsDisplayedForaux() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'aux'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("aux");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(64)
	public void verifyErrorMsgIsDisplayedFornul() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'nul'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("nul");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(65)
	public void verifyErrorMsgIsDisplayedForclock$() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'clock$'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("clock$");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(66)
	public void verifyErrorMsgIsDisplayedForcom0() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'com0'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("com0");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(67)
	public void verifyErrorMsgIsDisplayedForcom1() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'com1'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("com1");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(68)
	public void verifyErrorMsgIsDisplayedForcom2() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'com2'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("com2");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(69)
	public void verifyErrorMsgIsDisplayedForcom3() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'com3'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("com3");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(70)
	public void verifyErrorMsgIsDisplayedForcom4() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'com4'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("com4");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(71)
	public void verifyErrorMsgIsDisplayedForcom5() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'com5'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("com5");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(72)
	public void verifyErrorMsgIsDisplayedForcom6() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'com6'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("com6");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(73)
	public void verifyErrorMsgIsDisplayedForcom7() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'com7'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("com7");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(74)
	public void verifyErrorMsgIsDisplayedForcom8() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'com8'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("com8");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(75)
	public void verifyErrorMsgIsDisplayedForcom9() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'com9'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("com9");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(76)
	public void verifyErrorMsgIsDisplayedForlpt0() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'lpt0'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("lpt0");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(77)
	public void verifyErrorMsgIsDisplayedForlpt1() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'lpt1'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("lpt1");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(78)
	public void verifyErrorMsgIsDisplayedForlpt2() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'lpt2'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("lpt2");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(79)
	public void verifyErrorMsgIsDisplayedForlpt3() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'lpt3'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("lpt3");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(80)
	public void verifyErrorMsgIsDisplayedForlpt4() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'lpt4'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("lpt4");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(81)
	public void verifyErrorMsgIsDisplayedForlpt5() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'lpt5'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("lpt5");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(82)
	public void verifyErrorMsgIsDisplayedForlpt6() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'lpt6'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("lpt6");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(83)
	public void verifyErrorMsgIsDisplayedForlpt7() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'lpt7'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("lpt7");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(84)
	public void verifyErrorMsgIsDisplayedForlpt8() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'lpt8'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("lpt8");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(85)
	public void verifyErrorMsgIsDisplayedForlpt9() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'lpt9'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("lpt9");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(86)
	public void verifyErrorMsgIsDisplayedForNameEndindWithDot() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'Name ending with '.'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("Test.pdf.");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(87)
	public void verifyErrorMsgIsDisplayedForNameEndingWithHalfWidthSpace() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'Name ending with half-width space'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder("Test.pdf ");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(88)
	public void verifyErrorMsgIsDisplayedForNameStartingWithHalfWidthSpace() throws Exception {
		setTestDesc("Verify that unsupported characters to create folder in root folder 'Name starting with half-width space'.Error message should be display.");
		sharedFolderCreation = sharedFolder.gotoSharedFolderCreation();
		sharedFolderCreation.createFolder(" Test.pdf");
		assertEquals(sharedFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		sharedFolder.errorMsgOkBtn().click();
		
	}
	
	
	
	
	

}