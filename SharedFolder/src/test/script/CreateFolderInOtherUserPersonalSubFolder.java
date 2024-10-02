package test.script;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import org.hamcrest.generator.qdox.tools.QDoxTester.Reporter;
import org.openqa.selenium.By;
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
import tests.InitialTestSetup;

public class CreateFolderInOtherUserPersonalSubFolder extends InitialTestSetup {

	private PersonalFolderCreation personalFolderCreation = null;
	private PersonalFolder personalFolder = null;
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
		personalFolder = homePage.gotoPersonalFolder();
		Thread.sleep(2000);
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("AutoTest1");
	}

	@BeforeMethod(alwaysRun = true)
	public void verifyFolderIsEmpty() throws Exception {
		
		personalFolder.uiControl("openRootFolder").click();
		Thread.sleep(8000);
		System.out.println("This is NoData from App: " + personalFolder.uiControl("noData").getText());
		System.out.println("This is NoData from Expected: " + getLocalizedMessage("noFilesInTable").trim());
		if (personalFolder.uiControl("noData").getText().trim().equals(getLocalizedMessage("noFilesInTable").trim())) {
			System.out.println("No folders are available");
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
			personalFolder.uiControl("breadcrumRoot").click();
			//getDriver().findElement(By.xpath("//A[@data-v-24bb6368=''][text()='ホーム']")).click();
			Thread.sleep(2000);
		} else {
			personalFolder.selectAllCheckBox().click();
			PersonalFolderDelete deleteFolder = personalFolder.gotoPersonalFolderDelete();
			deleteFolder.okBtn().click();
			Thread.sleep(2000);
			// assertEquals(personalFolder.uiControl("noData").getText(),
			// getLocalizedMessage("noData"));
			personalFolder.uiControl("breadcrumRoot").click();
			//getDriver().findElement(By.xpath("//A[@data-v-24bb6368=''][text()='ホーム']")).click();
			//this.getDriver().navigate().refresh();
			Thread.sleep(2000);
		}
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(1)
	public void verifyFolderIsCreated() throws Exception {
		setTestDesc(
				"Verify that Admin can create folder in the Sub folder.Folder should be create in the Sub folder");

		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("AutoTest");
		assertEquals(personalFolder.firstRowName().getText(), "AutoTest");
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(2)
	public void verifySpecialCharFolderIsCreated() throws Exception {
		setTestDesc(
				"Verify that Creating Folder name can be Special characters in the Sub folder.Folder should be created ");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("@#$");
		assertEquals(personalFolder.firstRowName().getText(), "@#$");
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(3)
	public void verify84LengthEngCharFolderIsCreatedSingleByte() throws Exception {
		setTestDesc(
				"Verify that Creating Folder name length English 84 characters of single byte in the Sub folder.Folder should be created ");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation
				.createFolder("ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZAXYZAB");
		assertEquals(personalFolder.firstRowName().getText(),
				"ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZAXYZAB");
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(4)
	public void verify84LengthEngCharFolderIsCreatedMultiByte() throws Exception {
		setTestDesc(
				"Verify that Creating Folder name length English 84 characters of multi byte in the Sub folder.Folder should be created ");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation
				.createFolder("ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺＰＱＲＳＴＵＶＷＸＹＺＴＵＶＷＸＹＴＵＶＷＸＹＺＰＱＲＳＰＱＲＳ");
		assertEquals(personalFolder.firstRowName().getText(),
				"ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺＰＱＲＳＴＵＶＷＸＹＺＴＵＶＷＸＹＴＵＶＷＸＹＺＰＱＲＳＰＱＲＳ");
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(5)
	public void verify84LengthKatakanaCharFolderIsCreatedSingleByte() throws Exception {
		setTestDesc(
				"Verify that Creating Folder name length Katakana 84 characters of single byte in the Sub folder.Folder should be created ");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation
				.createFolder("ｶｻﾀﾅﾊﾏﾔﾗﾜｱｶｻﾀﾅﾊﾏﾔﾗﾜｱｶｻﾀﾅﾊﾏﾔﾗﾜｱｶｻﾀﾅﾊﾏﾔﾗﾜｱｶｻﾀﾅﾊﾏﾔﾗﾜｱｶｻﾀﾅﾊﾏﾔﾗﾜﾗﾊﾏﾔﾗﾜﾗﾜｱｶﾊﾏﾔﾗﾜﾗｻﾀﾅﾊﾊﾀﾅﾊﾊ");
		assertEquals(personalFolder.firstRowName().getText(),
				"ｶｻﾀﾅﾊﾏﾔﾗﾜｱｶｻﾀﾅﾊﾏﾔﾗﾜｱｶｻﾀﾅﾊﾏﾔﾗﾜｱｶｻﾀﾅﾊﾏﾔﾗﾜｱｶｻﾀﾅﾊﾏﾔﾗﾜｱｶｻﾀﾅﾊﾏﾔﾗﾜﾗﾊﾏﾔﾗﾜﾗﾜｱｶﾊﾏﾔﾗﾜﾗｻﾀﾅﾊﾊﾀﾅﾊﾊ");
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(6)
	public void verify84LengthKatakanaCharFolderIsCreatedMultiByte() throws Exception {
		setTestDesc(
				"Verify that Creating Folder name length Katakana 84 characters of multi byte in the Sub folder.Folder should be created ");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation
				.createFolder("アカサタナハマヤラワアカサタナハマヤラワアカサタナハマヤラワアカサタナハマヤラワアカサタナハマヤラワアカサタナハマヤヤラワアカサタナハマヤラワアカサタナハマヤヤハマヤヤ");
		assertEquals(personalFolder.firstRowName().getText(),
				"アカサタナハマヤラワアカサタナハマヤラワアカサタナハマヤラワアカサタナハマヤラワアカサタナハマヤラワアカサタナハマヤヤラワアカサタナハマヤラワアカサタナハマヤヤハマヤヤ");
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true, enabled = false)
	@PriorityInClass(7)
	public void verify84LengthHiraganaCharFolderIsCreatedSingleByte() throws Exception {
		setTestDesc(
				"Verify that Creating Folder name length Hiragana 84 characters of single byte in the Sub folder.Folder should be created ");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation
				.createFolder("あぁかさたないぃきしちにあぁかさたないぃきしちにあぁかさたないぃきしちにあぁかぁかさたないぃきしちにあぁかさたないぃきしちにあさたないぃきしちにあぁかさたないぃたないぃ");
		assertEquals(personalFolder.firstRowName().getText(),
				"あぁかさたないぃきしちにあぁかさたないぃきしちにあぁかさたないぃきしちにあぁかぁかさたないぃきしちにあぁかさたないぃきしちにあさたないぃきしちにあぁかさたないぃたないぃ");
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(8)
	public void verify84LengthHiraganaCharFolderIsCreatedMultiByte() throws Exception {
		setTestDesc(
				"Verify that Creating Folder name length Hiragana 84 characters of multi byte in the Sub folder.Folder should be created ");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation
				.createFolder("あぁかさたないぃきしちにあぁかさたないぃきしちにあぁかさたないぃきしちにあぁかぁかさたないぃきしちにあぁかさたないぃきしちにあさたないぃきしちにあぁかさたないぃたないぃ");
		assertEquals(personalFolder.firstRowName().getText(),
				"あぁかさたないぃきしちにあぁかさたないぃきしちにあぁかさたないぃきしちにあぁかぁかさたないぃきしちにあぁかさたないぃきしちにあさたないぃきしちにあぁかさたないぃたないぃ");
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(9)
	public void verify84LengthKanjiCharFolderIsCreatedSingleByte() throws Exception {
		setTestDesc(
				"Verify that Creating Folder name length Kanji 84 characters of single byte in the Sub folder.Folder should be created ");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation
				.createFolder("教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教教育漢字教育漢字教育漢字教育漢");
		assertEquals(personalFolder.firstRowName().getText(),
				"教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教教育漢字教育漢字教育漢字教育漢");
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(10)
	public void verify84LengthKanjiCharFolderIsCreatedMultiByte() throws Exception {
		setTestDesc(
				"Verify that Creating Folder name length Kanji 84 characters of multi byte in the Sub folder.Folder should be created ");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation
				.createFolder("教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教教育漢字教育漢字教育漢字教育漢");
		assertEquals(personalFolder.firstRowName().getText(),
				"教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教教育漢字教育漢字教育漢字教育漢");
	}
	
	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(11)
	public void verifyErrorMsgIsDisplayedForBackSlash() throws Exception {
		setTestDesc(
				"Verify that below all unsupported characters to create folder in Sub folder '\' (half-width backslash).Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("\\");
		assertEquals(personalFolder.errorMsginvalid().getText(), getLocalizedMessage("CreateErrorMgs"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true, enabled = true)
	@PriorityInClass(12)
	public void verifyErrorMsgIsDisplayedForFontSlash() throws Exception {
		setTestDesc("'/'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("/\"/");
		assertEquals(personalFolder.errorMsginvalid().getText(), getLocalizedMessage("CreateErrorMgs"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(13)
	public void verifyErrorMsgIsDisplayedForColon() throws Exception {
		setTestDesc("':'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder(":");
		assertEquals(personalFolder.errorMsginvalid().getText(), getLocalizedMessage("CreateErrorMgs"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(14)
	public void verifyErrorMsgIsDisplayedForMultiplication() throws Exception {
		setTestDesc("'*'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("*");
		assertEquals(personalFolder.errorMsginvalid().getText(), getLocalizedMessage("CreateErrorMgs"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(15)
	public void verifyErrorMsgIsDisplayedForQuestionMark() throws Exception {
		setTestDesc("'?'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("?");
		assertEquals(personalFolder.errorMsginvalid().getText(), getLocalizedMessage("CreateErrorMgs"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true, enabled = true)
	@PriorityInClass(16)
	public void verifyErrorMsgIsDisplayedForDoubleQuotes() throws Exception {
		setTestDesc("'DoubleQuotes'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("/\"/");
		assertEquals(personalFolder.errorMsginvalid().getText(), getLocalizedMessage("CreateErrorMgs"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(17)
	public void verifyErrorMsgIsDisplayedForLessThan() throws Exception {
		setTestDesc("'LessThan'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("<");
		assertEquals(personalFolder.errorMsginvalid().getText(), getLocalizedMessage("CreateErrorMgs"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(18)
	public void verifyErrorMsgIsDisplayedForGreaterThan() throws Exception {
		setTestDesc("'GreaterThan'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder(">");
		assertEquals(personalFolder.errorMsginvalid().getText(), getLocalizedMessage("CreateErrorMgs"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(19)
	public void verifyErrorMsgIsDisplayedForPipe() throws Exception {
		setTestDesc("'|'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("|");
		assertEquals(personalFolder.errorMsginvalid().getText(), getLocalizedMessage("CreateErrorMgs"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(20)
	public void verifyErrorMsgIsDisplayedForremote_access_backup() throws Exception {
		setTestDesc("'.remote_access_backup'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder(".remote_access_backup");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
	
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(21)
	public void verifyErrorMsgIsDisplayedForREMOTE_ACCESS_BACKUP() throws Exception {
		setTestDesc("'.REMOTE_ACCESS_BACKUP'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder(".REMOTE_ACCESS_BACKUP");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(22)
	public void verifyErrorMsgIsDisplayedFortrashbox() throws Exception {
		setTestDesc("'trashbox'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("trashbox");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(23)
	public void verifyErrorMsgIsDisplayedFortrashBox() throws Exception {
		setTestDesc("'trashBox'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("trashBox");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(24)
	public void verifyErrorMsgIsDisplayedForTRASHBOX() throws Exception {
		setTestDesc("'TRASHBOX'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("TRASHBOX");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(25)
	public void verifyErrorMsgIsDisplayedForsnapshot() throws Exception {
		setTestDesc("'.snapshot'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder(".snapshot");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(26)
	public void verifyErrorMsgIsDisplayedForSNAPSHOT() throws Exception {
		setTestDesc("'.SNAPSHOT'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder(".SNAPSHOT");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(27)
	public void verifyErrorMsgIsDisplayedForsnapshots() throws Exception {
		setTestDesc("'.snapshots'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder(".snapshots");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(28)
	public void verifyErrorMsgIsDisplayedForSNAPSHOTS() throws Exception {
		setTestDesc("'.SNAPSHOTS'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder(".SNAPSHOTS");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(29)
	public void verifyErrorMsgIsDisplayedForSnapshot() throws Exception {
		setTestDesc("'.Snapshot'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder(".Snapshot");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(30)
	public void verifyErrorMsgIsDisplayedForCON() throws Exception {
		setTestDesc("'CON'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("CON");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(31)
	public void verifyErrorMsgIsDisplayedForPRN() throws Exception {
		setTestDesc("'PRN'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("PRN");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(32)
	public void verifyErrorMsgIsDisplayedForAUX() throws Exception {
		setTestDesc("'AUX'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("AUX");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(33)
	public void verifyErrorMsgIsDisplayedForNUL() throws Exception {
		setTestDesc("'NUL'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("NUL");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(34)
	public void verifyErrorMsgIsDisplayedForCLOCK$() throws Exception {
		setTestDesc("'CLOCK$'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("CLOCK$");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(35)
	public void verifyErrorMsgIsDisplayedForCOM0() throws Exception {
		setTestDesc("'COM0'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("COM0");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(36)
	public void verifyErrorMsgIsDisplayedForCOM1() throws Exception {
		setTestDesc("'COM1'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("COM1");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}
	
	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(37)
	public void verifyErrorMsgIsDisplayedForCOM2() throws Exception {
		setTestDesc("'COM2'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("COM2");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(38)
	public void verifyErrorMsgIsDisplayedForCOM3() throws Exception {
		setTestDesc("'COM3'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("COM3");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(39)
	public void verifyErrorMsgIsDisplayedForCOM4() throws Exception {
		setTestDesc("'COM4'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("COM4");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(40)
	public void verifyErrorMsgIsDisplayedForCOM5() throws Exception {
		setTestDesc("'COM5'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("COM5");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(41)
	public void verifyErrorMsgIsDisplayedForCOM6() throws Exception {
		setTestDesc("'COM6'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("COM6");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(42)
	public void verifyErrorMsgIsDisplayedForCOM7() throws Exception {
		setTestDesc("'COM7'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("COM7");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(43)
	public void verifyErrorMsgIsDisplayedForCOM8() throws Exception {
		setTestDesc("'COM8'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("COM8");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(44)
	public void verifyErrorMsgIsDisplayedForCOM9() throws Exception {
		setTestDesc("'COM9'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("COM9");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(45)
	public void verifyErrorMsgIsDisplayedForLPT0() throws Exception {
		setTestDesc("'LPT0'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("LPT0");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(46)
	public void verifyErrorMsgIsDisplayedForLPT1() throws Exception {
		setTestDesc("'LPT1'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("LPT1");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(47)
	public void verifyErrorMsgIsDisplayedForLPT2() throws Exception {
		setTestDesc("'LPT2'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("LPT2");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(48)
	public void verifyErrorMsgIsDisplayedForLPT3() throws Exception {
		setTestDesc("'LPT3'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("LPT3");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(49)
	public void verifyErrorMsgIsDisplayedForLPT4() throws Exception {
		setTestDesc("'LPT4'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("LPT4");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(50)
	public void verifyErrorMsgIsDisplayedForLPT5() throws Exception {
		setTestDesc("'LPT5'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("LPT5");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(51)
	public void verifyErrorMsgIsDisplayedForLPT6() throws Exception {
		setTestDesc("'LPT6'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("LPT6");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(52)
	public void verifyErrorMsgIsDisplayedForLPT7() throws Exception {
		setTestDesc("'LPT7'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("LPT7");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(53)
	public void verifyErrorMsgIsDisplayedForLPT8() throws Exception {
		setTestDesc("'LPT8'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("LPT8");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(54)
	public void verifyErrorMsgIsDisplayedForLPT9() throws Exception {
		setTestDesc("'LPT9'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("LPT9");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(55)
	public void verifyErrorMsgIsDisplayedForcon() throws Exception {
		setTestDesc("'con'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("con");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(56)
	public void verifyErrorMsgIsDisplayedForprn() throws Exception {
		setTestDesc("'prn'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("prn");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(57)
	public void verifyErrorMsgIsDisplayedForaux() throws Exception {
		setTestDesc("'aux'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("aux");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(58)
	public void verifyErrorMsgIsDisplayedFornul() throws Exception {
		setTestDesc("'nul'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("nul");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(59)
	public void verifyErrorMsgIsDisplayedForclock$() throws Exception {
		setTestDesc("'clock$'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("clock$");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(60)
	public void verifyErrorMsgIsDisplayedForcom0() throws Exception {
		setTestDesc("'com0'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("com0");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(61)
	public void verifyErrorMsgIsDisplayedForcom1() throws Exception {
		setTestDesc("'com1'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("com1");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(62)
	public void verifyErrorMsgIsDisplayedForcom2() throws Exception {
		setTestDesc("'com2'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("com2");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(63)
	public void verifyErrorMsgIsDisplayedForcom3() throws Exception {
		setTestDesc("'com3'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("com3");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(64)
	public void verifyErrorMsgIsDisplayedForcom4() throws Exception {
		setTestDesc("'com4'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("com4");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(65)
	public void verifyErrorMsgIsDisplayedForcom5() throws Exception {
		setTestDesc("'com5'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("com5");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(66)
	public void verifyErrorMsgIsDisplayedForcom6() throws Exception {
		setTestDesc("'com6'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("com6");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(67)
	public void verifyErrorMsgIsDisplayedForcom7() throws Exception {
		setTestDesc("'com7'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("com7");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(68)
	public void verifyErrorMsgIsDisplayedForcom8() throws Exception {
		setTestDesc("'com8'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("com8");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(69)
	public void verifyErrorMsgIsDisplayedForcom9() throws Exception {
		setTestDesc("'com9'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("com9");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(70)
	public void verifyErrorMsgIsDisplayedForlpt0() throws Exception {
		setTestDesc("'lpt0'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("lpt0");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(71)
	public void verifyErrorMsgIsDisplayedForlpt1() throws Exception {
		setTestDesc("'lpt1'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("lpt1");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(72)
	public void verifyErrorMsgIsDisplayedForlpt2() throws Exception {
		setTestDesc("'lpt2'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("lpt2");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(73)
	public void verifyErrorMsgIsDisplayedForlpt3() throws Exception {
		setTestDesc("'lpt3'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("lpt3");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(74)
	public void verifyErrorMsgIsDisplayedForlpt4() throws Exception {
		setTestDesc("'lpt4'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("lpt4");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(75)
	public void verifyErrorMsgIsDisplayedForlpt5() throws Exception {
		setTestDesc("'lpt5'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("lpt5");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(76)
	public void verifyErrorMsgIsDisplayedForlpt6() throws Exception {
		setTestDesc("'lpt6'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("lpt6");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(77)
	public void verifyErrorMsgIsDisplayedForlpt7() throws Exception {
		setTestDesc("'lpt7'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("lpt7");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(78)
	public void verifyErrorMsgIsDisplayedForlpt8() throws Exception {
		setTestDesc("'lpt8'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("lpt8");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(79)
	public void verifyErrorMsgIsDisplayedForlpt9() throws Exception {
		setTestDesc("'lpt9'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("lpt9");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(80)
	public void verifyErrorMsgIsDisplayedForNameEndindWithDot() throws Exception {
		setTestDesc("'Name ending with '.'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("Test.pdf.");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(81)
	public void verifyErrorMsgIsDisplayedForNameEndingWithHalfWidthSpace() throws Exception {
		setTestDesc("'Name ending with half-width space'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder("Test.pdf ");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

	@Test(groups = { "CreateFolder" }, alwaysRun = true)
	@PriorityInClass(82)
	public void verifyErrorMsgIsDisplayedForNameStartingWithHalfWidthSpace() throws Exception {
		setTestDesc("'lpt2'.Error message should be display.");
		personalFolderCreation = personalFolder.gotoPersonalFolderCreation();
		personalFolderCreation.createFolder(" Test.pdf");
		assertEquals(personalFolder.errorMsg().getText(), getLocalizedMessage("CreateErrorMgsWith406"));
		personalFolder.errorMsgOkBtn().click();
		
	}

}