package test.script;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import cocoro.lib.uiauto.DriverFactory;
import pages.HomePage;
import pages.LoginPage;
import pages.OtherUserPersonalFolder;
import pages.PersonalFolder;
import pages.PersonalFolderDelete;
import tests.InitialTestSetup;

public class OtherPersonalFolderUploadFileToRootFolderTest extends InitialTestSetup {
	
	private OtherUserPersonalFolder otherpersonalFolder = null;
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
		otherpersonalFolder = homePage.gotoOtherUserPersonalFolder();
		
		Thread.sleep(2000);
		System.out.println("This is NoData from App: " + otherpersonalFolder.uiControl("noData").getText());
		System.out.println("This is NoData from Expected: " + getLocalizedMessage("noFilesInTable").trim());
		if (otherpersonalFolder.uiControl("noData").getText().trim().equals(getLocalizedMessage("noFilesInTable").trim())) {
			System.out.println("No folders are available");
		} else {
			otherpersonalFolder.selectAllCheckBox().click();
			PersonalFolderDelete deleteFolder = otherpersonalFolder.gotoPersonalFolderDelete();
			deleteFolder.okBtn().click();
			Thread.sleep(2000);
			assertEquals(otherpersonalFolder.uiControl("noData").getText(), getLocalizedMessage("noFilesInTable").trim());
		}

	}

	@AfterMethod(alwaysRun = true)
	public void deleteAllFolders() throws Exception {
		System.out.println("This is Expected result: " + getLocalizedMessage("noFilesInTable").trim());

		if ((otherpersonalFolder.uiControl("noData").getText().trim())
				.equals(getLocalizedMessage("noFilesInTable").trim())) {
			System.out.println("After Method: No data");
			otherpersonalFolder.uiControl("homeLink").click();
			Thread.sleep(2000);
		} else {
			otherpersonalFolder.selectAllCheckBox().click();
			PersonalFolderDelete deleteFolder = otherpersonalFolder.gotoPersonalFolderDelete();
			deleteFolder.okBtn().click();
			Thread.sleep(2000);

			otherpersonalFolder.uiControl("homeLink").click();
			Thread.sleep(2000);
		}
	}

	@Test(priority = 1, groups = { "Functional" }, enabled = true)
	public void verifyUploadButtonIsDisplayedInRootFolder() throws Exception {
		setTestDesc("Verify that Upload button is displayed for the root folder");
		assertTrue(otherpersonalFolder.uploadBtn().isDisplayed());

	}

	@Test(priority = 2, groups = { "Functional" }, enabled = false)
	// @PriorityInClass(2)
	public void verifyUploadButtonToolTipsIsDisplayed() throws Exception {
		setTestDesc("Verify that upload button's tool tip is displayed when mouse over on the Upload button");
		Actions actions = new Actions(getDriver());
		actions.moveToElement((WebElement) otherpersonalFolder.uploadBtn()).build().perform();
		// assertEquals(personalFolder.tooltips().getText(), "upload");

	}
	
	// Image files
		@Test(priority = 2, groups = { "Functional" }, enabled = true)
		public void verifyPDFFileUpload() throws Exception {
			setTestDesc("Verify that Admin can Upload a file 'File.pdf' to root folder.");
			otherpersonalFolder.uploadBtn().click();
			PersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\Image type\\");
			PersonalFolder.uploadFileWithRobot("File.pdf");
			Thread.sleep(8000);
			// personalFolder.gotoSearchPersonalFolder("Single.pdf");
			assertEquals(otherpersonalFolder.firstRowName().getText(), "File.pdf");

		}

	// Image files
	@Test(priority = 3, groups = { "Functional" }, enabled = true)
	public void verifyBMPFileUpload() throws Exception {
		setTestDesc("Verify that Admin can Upload a file 'File.BMP' to root folder.");
		otherpersonalFolder.uploadBtn().click();
		PersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\Image type\\");
		PersonalFolder.uploadFileWithRobot("File.BMP");
		Thread.sleep(8000);
		// personalFolder.gotoSearchPersonalFolder("Single.pdf");
		assertEquals(otherpersonalFolder.firstRowName().getText(), "File.BMP");

	}

	@Test(priority = 4, groups = { "Functional" }, enabled = true)
	public void verifyGIFFileUpload() throws Exception {
		setTestDesc("Verify that Admin can Upload a file 'File.gif' to root folder.");
		otherpersonalFolder.uploadBtn().click();
		otherpersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\Image type\\");
		otherpersonalFolder.uploadFileWithRobot("File.gif");
		Thread.sleep(8000);
		assertEquals(otherpersonalFolder.firstRowName().getText(), "File.gif");
	}

	@Test(priority = 5, groups = { "Functional" }, enabled = true)
	public void verifyJPEGFileUpload() throws Exception {
		setTestDesc("Verify that Admin can Upload a file 'File.jpeg' to root folder.");
		otherpersonalFolder.uploadBtn().click();
		otherpersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\Image type\\");
		otherpersonalFolder.uploadFileWithRobot("File.jpeg");
		Thread.sleep(8000);
		assertEquals(otherpersonalFolder.firstRowName().getText(), "File.jpeg");
	}

	@Test(priority = 5, groups = { "Functional" }, enabled = true)
	public void verifyJPGFileUpload() throws Exception {
		setTestDesc("Verify that Admin can Upload a file 'File.jpg' to root folder.");
		otherpersonalFolder.uploadBtn().click();
		otherpersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\Image type\\");
		otherpersonalFolder.uploadFileWithRobot("File.jpg");
		Thread.sleep(8000);
		assertEquals(otherpersonalFolder.firstRowName().getText(), "File.jpg");
	}

	@Test(priority = 6, groups = { "Functional" }, enabled = true)
	public void verifyPNGFileUpload() throws Exception {
		setTestDesc("Verify that Admin can Upload a file 'File.png' to root folder.");
		otherpersonalFolder.uploadBtn().click();
		otherpersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\Image type\\");
		otherpersonalFolder.uploadFileWithRobot("File.png");
		Thread.sleep(8000);
		assertEquals(otherpersonalFolder.firstRowName().getText(), "File.png");
	}

	@Test(priority = 7, groups = { "Functional" }, enabled = true)
	public void verifyTIFFileUpload() throws Exception {
		setTestDesc("Verify that Admin can Upload a file 'File.tif' to root folder.");
		otherpersonalFolder.uploadBtn().click();
		otherpersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\Image type\\");
		otherpersonalFolder.uploadFileWithRobot("File.tif");
		Thread.sleep(8000);
		assertEquals(otherpersonalFolder.firstRowName().getText(), "File.tif");
	}

	// Text files
	@Test(priority = 8, groups = { "Functional" }, enabled = true)
	public void verifyExcelFileUpload() throws Exception {
		setTestDesc("Verify that Admin can Upload a file 'File.xlsx' to root folder.");
		otherpersonalFolder.uploadBtn().click();
		otherpersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\Text Doc\\");
		otherpersonalFolder.uploadFileWithRobot("File.xlsx");
		Thread.sleep(8000);
		assertEquals(otherpersonalFolder.firstRowName().getText(), "File.xlsx");
	}

	@Test(priority = 9, groups = { "Functional" }, enabled = true)
	public void verifyDOCXFileUpload() throws Exception {
		setTestDesc("Verify that Admin can Upload a file 'File.docx' to root folder.");
		otherpersonalFolder.uploadBtn().click();
		otherpersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\Text Doc\\");
		otherpersonalFolder.uploadFileWithRobot("File.docx");
		Thread.sleep(8000);
		assertEquals(otherpersonalFolder.firstRowName().getText(), "File.docx");
	}

	@Test(priority = 10, groups = { "Functional" }, enabled = true)
	public void verifyODPFileUpload() throws Exception {
		setTestDesc("Verify that Admin can Upload a file 'File.odp' to root folder.");
		otherpersonalFolder.uploadBtn().click();
		otherpersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\Text Doc\\");
		otherpersonalFolder.uploadFileWithRobot("File.odp");
		Thread.sleep(8000);
		assertEquals(otherpersonalFolder.firstRowName().getText(), "File.odp");
	}

	@Test(priority = 11, groups = { "Functional" }, enabled = true)
	public void verifyODSFileUpload() throws Exception {
		setTestDesc("Verify that Admin can Upload a file 'File.ods' to root folder.");
		otherpersonalFolder.uploadBtn().click();
		otherpersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\Text Doc\\");
		otherpersonalFolder.uploadFileWithRobot("File.ods");
		Thread.sleep(8000);
		assertEquals(otherpersonalFolder.firstRowName().getText(), "File.ods");
	}

	@Test(priority = 12, groups = { "Functional" }, enabled = true)
	public void verifyODTFileUpload() throws Exception {
		setTestDesc("Verify that Admin can Upload a file 'File.odt' to root folder.");
		otherpersonalFolder.uploadBtn().click();
		otherpersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\Text Doc\\");
		otherpersonalFolder.uploadFileWithRobot("File.odt");
		Thread.sleep(8000);
		assertEquals(otherpersonalFolder.firstRowName().getText(), "File.odt");
	}

	@Test(priority = 13, groups = { "Functional" }, enabled = true)
	public void verifyPPTXFileUpload() throws Exception {
		setTestDesc("Verify that Admin can Upload a file 'File.pptx' to root folder.");
		otherpersonalFolder.uploadBtn().click();
		otherpersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\Text Doc\\");
		otherpersonalFolder.uploadFileWithRobot("File.pptx");
		Thread.sleep(8000);
		assertEquals(otherpersonalFolder.firstRowName().getText(), "File.pptx");
	}

	@Test(priority = 14, groups = { "Functional" }, enabled = true)
	public void verifyRTFFileUpload() throws Exception {
		setTestDesc("Verify that Admin can Upload a file 'File.rtf' to root folder.");
		otherpersonalFolder.uploadBtn().click();
		otherpersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\Text Doc\\");
		otherpersonalFolder.uploadFileWithRobot("File.rtf");
		Thread.sleep(8000);
		assertEquals(otherpersonalFolder.firstRowName().getText(), "File.rtf");
	}

	@Test(priority = 15, groups = { "Functional" }, enabled = true)
	public void verifyTXTFileUpload() throws Exception {
		setTestDesc("Verify that Admin can Upload a file 'File.txt' to root folder.");
		otherpersonalFolder.uploadBtn().click();
		otherpersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\Text Doc\\");
		otherpersonalFolder.uploadFileWithRobot("File.txt");
		Thread.sleep(8000);
		assertEquals(otherpersonalFolder.firstRowName().getText(), "File.txt");
	}

	@Test(priority = 15, groups = { "Functional" }, enabled = true)
	public void verifyXPSFileUpload() throws Exception {
		setTestDesc("Verify that Admin can Upload a file 'File.xps' to root folder.");
		otherpersonalFolder.uploadBtn().click();
		otherpersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\Text Doc\\");
		otherpersonalFolder.uploadFileWithRobot("File.xps");
		Thread.sleep(8000);
		assertEquals(otherpersonalFolder.firstRowName().getText(), "File.xps");
	}

//Video type
	@Test(priority = 16, groups = { "Functional" }, enabled = true)
	public void verifyAVIFileUpload() throws Exception {
		setTestDesc("Verify that Admin can Upload a file 'File.avi' to root folder.");
		otherpersonalFolder.uploadBtn().click();
		otherpersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\Video type\\");
		otherpersonalFolder.uploadFileWithRobot("File.avi");
		Thread.sleep(8000);
		assertEquals(otherpersonalFolder.firstRowName().getText(), "File.avi");
	}

	@Test(priority = 17, groups = { "Functional" }, enabled = true)
	public void verifyFLVFileUpload() throws Exception {
		setTestDesc("Verify that Admin can Upload a file 'File.flv' to root folder.");
		otherpersonalFolder.uploadBtn().click();
		otherpersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\Video type\\");
		otherpersonalFolder.uploadFileWithRobot("File.flv");
		Thread.sleep(8000);
		assertEquals(otherpersonalFolder.firstRowName().getText(), "File.flv");
	}

	@Test(priority = 18, groups = { "Functional" }, enabled = true)
	public void verifyMKVFileUpload() throws Exception {
		setTestDesc("Verify that Admin can Upload a file 'File.mkv' to root folder.");
		otherpersonalFolder.uploadBtn().click();
		otherpersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\Video type\\");
		otherpersonalFolder.uploadFileWithRobot("File.mkv");
		Thread.sleep(8000);
		assertEquals(otherpersonalFolder.firstRowName().getText(), "File.mkv");
	}

	@Test(priority = 19, groups = { "Functional" }, enabled = true)
	public void verifyMOVFileUpload() throws Exception {
		setTestDesc("Verify that Admin can Upload a file 'File.mov' to root folder.");
		otherpersonalFolder.uploadBtn().click();
		otherpersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\Video type\\");
		otherpersonalFolder.uploadFileWithRobot("File.mov");
		Thread.sleep(8000);
		assertEquals(otherpersonalFolder.firstRowName().getText(), "File.mov");
	}

	@Test(priority = 20, groups = { "Functional" }, enabled = true)
	public void verifyMP4FileUpload() throws Exception {
		setTestDesc("Verify that Admin can Upload a file 'File.mp4' to root folder.");
		otherpersonalFolder.uploadBtn().click();
		otherpersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\Video type\\");
		otherpersonalFolder.uploadFileWithRobot("File.mp4");
		Thread.sleep(8000);
		assertEquals(otherpersonalFolder.firstRowName().getText(), "File.mp4");
	}

	@Test(priority = 21, groups = { "Functional" }, enabled = true)
	public void verifyMPEGFileUpload() throws Exception {
		setTestDesc("Verify that Admin can Upload a file 'File.mpeg' to root folder.");
		otherpersonalFolder.uploadBtn().click();
		otherpersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\Video type\\");
		otherpersonalFolder.uploadFileWithRobot("File.mpeg");
		Thread.sleep(8000);
		assertEquals(otherpersonalFolder.firstRowName().getText(), "File.mpeg");
	}

	@Test(priority = 22, groups = { "Functional" }, enabled = true)
	public void verifyMPEG2FileUpload() throws Exception {
		setTestDesc("Verify that Admin can Upload a file 'File.mpeg2' to root folder.");
		otherpersonalFolder.uploadBtn().click();
		otherpersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\Video type\\");
		otherpersonalFolder.uploadFileWithRobot("File.mpeg2");
		Thread.sleep(8000);
		assertEquals(otherpersonalFolder.firstRowName().getText(), "File.mpeg2");
	}

	@Test(priority = 23, groups = { "Functional" }, enabled = true)
	public void verifyTSFileUpload() throws Exception {
		setTestDesc("Verify that Admin can Upload a file 'File.ts' to root folder.");
		otherpersonalFolder.uploadBtn().click();
		otherpersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\Video type\\");
		otherpersonalFolder.uploadFileWithRobot("File.ts");
		Thread.sleep(8000);
		assertEquals(otherpersonalFolder.firstRowName().getText(), "File.ts");
	}

	@Test(priority = 24, groups = { "Functional" }, enabled = true)
	public void verifyWEBMFileUpload() throws Exception {
		setTestDesc("Verify that Admin can Upload a file 'File.webm' to root folder.");
		otherpersonalFolder.uploadBtn().click();
		otherpersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\Video type\\");
		otherpersonalFolder.uploadFileWithRobot("File.webm");
		Thread.sleep(8000);
		assertEquals(otherpersonalFolder.firstRowName().getText(), "File.webm");
	}

	@Test(priority = 25, groups = { "Functional" }, enabled = true)
	public void verifyWMVFileUpload() throws Exception {
		setTestDesc("Verify that Admin can Upload a file 'File.wmv' to root folder.");
		otherpersonalFolder.uploadBtn().click();
		otherpersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\Video type\\");
		otherpersonalFolder.uploadFileWithRobot("File.wmv");
		Thread.sleep(8000);
		assertEquals(otherpersonalFolder.firstRowName().getText(), "File.wmv");
	}
	@Test(priority = 26, groups = { "Functional" }, enabled = true)
	public void verifyFileUploadDisplayedInGrid() throws Exception {
		setTestDesc("Verify that Uploaded file in the root folder displayed ");
		otherpersonalFolder.uploadBtn().click();
		otherpersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\Video type\\");
		otherpersonalFolder.uploadFileWithRobot("File.wmv");
		Thread.sleep(8000);
		assertTrue(otherpersonalFolder.firstRowName().isDisplayed());
	}
	
	// Single width and multi byte 84 chars
	//Numbers
	@Test(priority = 27, groups = { "Functional" }, enabled = true)
	public void verifyFileUploadSingleByteNumbers() throws Exception {
		setTestDesc("Verify that Upload Single Byte file-84 Number's file in the root folder displayed ");
		otherpersonalFolder.uploadBtn().click();
		otherpersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\shareFolder\\file-84\\");
		otherpersonalFolder.uploadFileWithRobot("11111111111111111111111111111111111111111111111111111111111111111111111111111111.mov");
		Thread.sleep(8000);
		assertEquals(otherpersonalFolder.firstRowName().getText(), "11111111111111111111111111111111111111111111111111111111111111111111111111111111.mov");
	}
	
	@Test(priority = 28, groups = { "Functional" }, enabled = true)
	public void verifyFileUploadMultyByteNumbers() throws Exception {
		setTestDesc("Verify that Upload Multi Byte file-84 Number's file in the root folder displayed ");
		otherpersonalFolder.uploadBtn().click();
		otherpersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\shareFolder\\file-84\\");
		otherpersonalFolder.uploadFileWithRobot("１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１.mp4");
		Thread.sleep(8000);
		assertEquals(otherpersonalFolder.firstRowName().getText(), "１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１.mp4");
	}
	
	//alphabets
	@Test(priority = 29, groups = { "Functional" }, enabled = true)
	public void verifyFileUploadSingleByteEnglishCharacters() throws Exception {
		setTestDesc("Verify that Upload Single Byte  English 84 Character's file in the root folder displayed ");
		otherpersonalFolder.uploadBtn().click();
		otherpersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\shareFolder\\file-84\\");
		otherpersonalFolder.uploadFileWithRobot("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZIJKLMNOPQRSTUVWXYvwxyzABCDEF.log");
		Thread.sleep(8000);
		assertEquals(otherpersonalFolder.firstRowName().getText(), "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZIJKLMNOPQRSTUVWXYvwxyzABCDEF.log");
	}
	
	@Test(priority = 30, groups = { "Functional" }, enabled = true)
	public void verifyFileUploadMultyByteEnglishCharacters() throws Exception {
		setTestDesc("Verify that Upload Multi Byte Byte English 84 Character's  file in the root folder displayed ");
		otherpersonalFolder.uploadBtn().click();
		otherpersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\shareFolder\\file-84\\");
		otherpersonalFolder.uploadFileWithRobot("ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺＰＱＲＳＴＵＶＷＸＹＺＴＵＶＷＸＹｖｗｘｙｚＡＢＣＤＥＦ.mkv");
		Thread.sleep(8000);
		assertEquals(otherpersonalFolder.firstRowName().getText(), "ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺＰＱＲＳＴＵＶＷＸＹＺＴＵＶＷＸＹｖｗｘｙｚＡＢＣＤＥＦ.mkv");
	}
	// Katagana		
		@Test(priority = 29, groups = { "Functional" }, enabled = true)
		public void verifyFileUploadSingleByteKataganaCharacters() throws Exception {
			setTestDesc("Verify that Upload Single Byte Katagana 84 Character's file in the root folder displayed ");
			otherpersonalFolder.uploadBtn().click();
			otherpersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\shareFolder\\file-84\\");
			otherpersonalFolder.uploadFileWithRobot("ﾀｲﾌﾟ･ｲﾝｸﾞﾘｯｼｭ･ﾜｰﾄﾞｽﾞ･ｲﾝ･ｻﾞｰ･ﾎﾞｯｸｽ･ﾍﾞﾛｰ･ﾌﾟﾚｽ･ｺﾝﾊﾞｰﾄ･ﾀｰ･ｶｰﾀｶｰﾅｰ･ﾀｰ･ｺﾝﾊﾀｲﾌﾟ･ｲﾝｸﾞﾘｯｼ.bmp");
			Thread.sleep(8000);
			assertEquals(otherpersonalFolder.firstRowName().getText(), "ﾀｲﾌﾟ･ｲﾝｸﾞﾘｯｼｭ･ﾜｰﾄﾞｽﾞ･ｲﾝ･ｻﾞｰ･ﾎﾞｯｸｽ･ﾍﾞﾛｰ･ﾌﾟﾚｽ･ｺﾝﾊﾞｰﾄ･ﾀｰ･ｶｰﾀｶｰﾅｰ･ﾀｰ･ｺﾝﾊﾀｲﾌﾟ･ｲﾝｸﾞﾘｯｼ.bmp");
		}
		
		@Test(priority = 30, groups = { "Functional" }, enabled = true)
		public void verifyFileUploadMultyByteKataganaCharacters() throws Exception {
			setTestDesc("Verify that Upload Multi Byte Byte Katagana 84 Character's  file in the root folder displayed ");
			otherpersonalFolder.uploadBtn().click();
			otherpersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\shareFolder\\file-84\\");
			otherpersonalFolder.uploadFileWithRobot("タイプ・イングリッシュ・ワードズ・イン・ザー・ボックス・ベロー・プレス・コンバート・ター・カータカーナー・ター・コンバート・ゼム・インターンバート・ター・カータ.doc");
			Thread.sleep(8000);
			assertEquals(otherpersonalFolder.firstRowName().getText(), "タイプ・イングリッシュ・ワードズ・イン・ザー・ボックス・ベロー・プレス・コンバート・ター・カータカーナー・ター・コンバート・ゼム・インターンバート・ター・カータ.doc");
		}
	
     //Kanji
		@Test(priority = 31, groups = { "Functional" }, enabled = true)
		public void verifyFileUploadMultyByteKanjiCharacters() throws Exception {
			setTestDesc("Verify that Upload Multi Byte Byte Kanji 84 Character's  file in the root folder displayed ");
			otherpersonalFolder.uploadBtn().click();
			otherpersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\shareFolder\\file-84\\");
			otherpersonalFolder.uploadFileWithRobot("あぁかさたないぃきしちにあぁかさたないぃきしちにあぁかさたないぃきしちにあぁかさたないぃきしちにあぁかさたないぃきしちにあぁかさたないぃきぁかさたないぃきしち.gif");
			Thread.sleep(8000);
			assertEquals(otherpersonalFolder.firstRowName().getText(), "あぁかさたないぃきしちにあぁかさたないぃきしちにあぁかさたないぃきしちにあぁかさたないぃきしちにあぁかさたないぃきしちにあぁかさたないぃきぁかさたないぃきしち.gif");
		}
		
		 //Hiragana
		@Test(priority = 32, groups = { "Functional" }, enabled = true)
		public void verifyFileUploadMultyByteHiraganaCharacters() throws Exception {
			setTestDesc("Verify that Upload Multi Byte Byte 84 Hiragana Character's  file in the root folder displayed ");
			otherpersonalFolder.uploadBtn().click();
			otherpersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\shareFolder\\file-84\\");
			otherpersonalFolder.uploadFileWithRobot("教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教教育漢字教育漢字教育漢.flv");
			Thread.sleep(8000);
			assertEquals(otherpersonalFolder.firstRowName().getText(), "教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教教育漢字教育漢字教育漢.flv");
		}
		
		 //Special char
		@Test(priority = 33, groups = { "Functional" }, enabled = true)
		public void verifyFileUploadMultyByteSpecialCharacters() throws Exception {
			setTestDesc("Verify that Upload Multi Byte Byte 84 Special Character's  file in the root folder displayed ");
			otherpersonalFolder.uploadBtn().click();
			OtherUserPersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\shareFolder\\file-84\\");
			OtherUserPersonalFolder.uploadFileWithRobot("！＠＃＄％＾＆＊（）＿＋＝－｀～［｛］｝￥｜’”；：／？．＞＜，！＠＃＄％＾＆＊（）＿＋＝－｀～［｛］｝￥｜’”；：／？．＞＜，？．＞＜＜～［｛］｝￥｜’”；：.JPG");
			Thread.sleep(8000);
			assertEquals(otherpersonalFolder.firstRowName().getText(), "！＠＃＄％＾＆＊（）＿＋＝－｀～［｛］｝￥｜’”；：／？．＞＜，！＠＃＄％＾＆＊（）＿＋＝－｀～［｛］｝￥｜’”；：／？．＞＜，？．＞＜＜～［｛］｝￥｜’”；：.JPG");
		}
	
		//85 chars		
		//Numbers
		@Test(priority = 34, groups = { "Functional" }, enabled = true)
		public void verifyFileUploadSingleByte85Numbers() throws Exception {
			setTestDesc("Verify that Error message when Upload Single Byte 85 Number's file in the root folder displayed ");
			otherpersonalFolder.uploadBtn().click();
			otherpersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\shareFolder\\files-85\\");
			otherpersonalFolder.uploadFileWithRobot("1111111111111111111111111111111111111111111111111111111111111111111111111111111111.mov");
			Thread.sleep(6000);					
			assertEquals(otherpersonalFolder.getFilepathTooLongErrorMsg().getText().trim(), getLocalizedMessage("fileNamepathTooLong").trim());
			otherpersonalFolder.errorMsgOkBtn().click();
			Thread.sleep(1000);	
		}
		
		@Test(priority = 35, groups = { "Functional" }, enabled = true)
		public void verifyFileUploadMultyByte85Numbers() throws Exception {
			setTestDesc("Verify that Error message when Upload Multi Byte 85 Number's file in the root folder displayed");
			otherpersonalFolder.uploadBtn().click();
			otherpersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\shareFolder\\files-85\\");
			otherpersonalFolder.uploadFileWithRobot("１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１１.mp4");
			Thread.sleep(6000);					
			assertEquals(otherpersonalFolder.getFilepathTooLongErrorMsg().getText().trim(), getLocalizedMessage("fileNamepathTooLong").trim());
			otherpersonalFolder.errorMsgOkBtn().click();
			Thread.sleep(1000);	
		}
		
		//alphabets
		@Test(priority = 36, groups = { "Functional" }, enabled = true)
		public void verifyFileUploadSingleByte85EnglishCharacters() throws Exception {
			setTestDesc("Verify that Error message when Upload single Byte 85 English characters file in the root folder displayed");
			otherpersonalFolder.uploadBtn().click();
			otherpersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\shareFolder\\files-85\\");
			otherpersonalFolder.uploadFileWithRobot("aabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZIJKLMNOPQRSTUVWXYvwxyzABCDEF.log");
			Thread.sleep(6000);					
			assertEquals(otherpersonalFolder.getFilepathTooLongErrorMsg().getText().trim(), getLocalizedMessage("fileNamepathTooLong").trim());
			otherpersonalFolder.errorMsgOkBtn().click();
			Thread.sleep(1000);	
		}
		
		@Test(priority = 37, groups = { "Functional" }, enabled = true)
		public void verifyFileUploadMultyByte85EnglishCharacters() throws Exception {
			setTestDesc("Verify that Error message when Upload Multi Byte 85 English characters file in the root folder displayed");
			otherpersonalFolder.uploadBtn().click();
			otherpersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\shareFolder\\files-85\\");
			otherpersonalFolder.uploadFileWithRobot("aaａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺＰＱＲＳＴＵＶＷＸＹＺＴＵＶＷＸＹｖｗｘｙｚＡＢＣＤＥＦ.mkv");
			Thread.sleep(6000);					
			assertEquals(otherpersonalFolder.getFilepathTooLongErrorMsg().getText().trim(), getLocalizedMessage("fileNamepathTooLong").trim());
			otherpersonalFolder.errorMsgOkBtn().click();
			Thread.sleep(1000);	
		}
		
		// Katagana		
			@Test(priority = 38, groups = { "Functional" }, enabled = true)
			public void verifyFileUploadSingleByte85KataganaCharacters() throws Exception {
				setTestDesc("Verify that Error message when Upload single Byte 85 Katagana characters file in the root folder displayed");
				otherpersonalFolder.uploadBtn().click();
				otherpersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\shareFolder\\files-85\\");
				otherpersonalFolder.uploadFileWithRobot("ﾀﾀｲﾌﾟ･ｲﾝｸﾞﾘｯｼｭ･ﾜｰﾄﾞｽﾞ･ｲﾝ･ｻﾞｰ･ﾎﾞｯｸｽ･ﾍﾞﾛｰ･ﾌﾟﾚｽ･ｺﾝﾊﾞｰﾄ･ﾀｰ･ｶｰﾀｶｰﾅｰ･ﾀｰ･ｺﾝﾊﾀｲﾌﾟ･ｲﾝｸﾞﾘｯｼ.bmp");
				Thread.sleep(6000);					
				assertEquals(otherpersonalFolder.getFilepathTooLongErrorMsg().getText().trim(), getLocalizedMessage("fileNamepathTooLong").trim());
				otherpersonalFolder.errorMsgOkBtn().click();
				Thread.sleep(1000);	
			}
			
			@Test(priority = 39, groups = { "Functional" }, enabled = true)
			public void verifyFileUploadMultyByte85KataganaCharacters() throws Exception {
				setTestDesc("Verify that Error message when Upload Multi Byte 85 Katagana characters file in the root folder displayed ");
				otherpersonalFolder.uploadBtn().click();
				otherpersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\shareFolder\\files-85\\");
				otherpersonalFolder.uploadFileWithRobot("タタタイプ・イングリッシュ・ワードズ・イン・ザー・ボックス・ベロー・プレス・コンバート・ター・カータカーナー・ター・コンバート・ゼム・インターンバート・ター・カータ.doc");
				Thread.sleep(6000);					
				assertEquals(otherpersonalFolder.getFilepathTooLongErrorMsg().getText().trim(), getLocalizedMessage("fileNamepathTooLong").trim());
				otherpersonalFolder.errorMsgOkBtn().click();
				Thread.sleep(1000);	
			}
		
	     //Kanji
			@Test(priority = 40, groups = { "Functional" }, enabled = true)
			public void verifyFileUploadMultyByte85KanjiCharacters() throws Exception {
				setTestDesc("Verify that Error message when Upload Multi Byte 85 Kanji characters file in the root folder displayed");
				otherpersonalFolder.uploadBtn().click();
				otherpersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\shareFolder\\files-85\\");
				otherpersonalFolder.uploadFileWithRobot("あああぁかさたないぃきしちにあぁかさたないぃきしちにあぁかさたないぃきしちにあぁかさたないぃきしちにあぁかさたないぃきしちにあぁかさたないぃきぁかさたないぃきしち.gif");
				Thread.sleep(6000);					
				assertEquals(otherpersonalFolder.getFilepathTooLongErrorMsg().getText().trim(), getLocalizedMessage("fileNamepathTooLong").trim());
				otherpersonalFolder.errorMsgOkBtn().click();
				Thread.sleep(1000);	
			}
			
			 //Hiragana
			@Test(priority = 41, groups = { "Functional" }, enabled = true)
			public void verifyFileUploadMultyByte85HiraganaCharacters() throws Exception {
				setTestDesc("Verify that Error message when Upload Multi Byte 85 Hiragana characters file in the root folder displayed ");
				otherpersonalFolder.uploadBtn().click();
				otherpersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\shareFolder\\files-85\\");
				otherpersonalFolder.uploadFileWithRobot("教教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教育漢字教教育漢字教育漢字教育漢.flv");
				Thread.sleep(6000);					
				assertEquals(otherpersonalFolder.getFilepathTooLongErrorMsg().getText().trim(), getLocalizedMessage("fileNamepathTooLong").trim());
				otherpersonalFolder.errorMsgOkBtn().click();
				Thread.sleep(1000);	
			}
			
			 //Special char
			@Test(priority = 42, groups = { "Functional" }, enabled = true)
			public void verifyFileUploadMultyByte85SpecialCharacters() throws Exception {
				setTestDesc("Verify that Error message when Upload Multi Byte 85 Special characters file in the root folder displayed");
				otherpersonalFolder.uploadBtn().click();
				otherpersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\shareFolder\\files-85\\");
				otherpersonalFolder.uploadFileWithRobot("！！＠＃＄％＾＆＊（）＿＋＝－｀～［｛］｝￥｜’”；：／？．＞＜，！＠＃＄％＾＆＊（）＿＋＝－｀～［｛］｝￥｜’”；：／？．＞＜，？．＞＜＜～［｛］｝￥｜’”；：.JPG");
				Thread.sleep(6000);					
				assertEquals(otherpersonalFolder.getFilepathTooLongErrorMsg().getText().trim(), getLocalizedMessage("fileNamepathTooLong").trim());
				otherpersonalFolder.errorMsgOkBtn().click();
				Thread.sleep(1000);	
			}
			
			 //Password protected char
			@Test(priority = 43, groups = { "Functional" }, enabled = true)
			public void verifyFileUploadPasswordProtected() throws Exception {
				setTestDesc("Verify that Error message when Upload Password protected char file in the root folder displayed");
				otherpersonalFolder.uploadBtn().click();
				otherpersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\shareFolder\\files-85\\");
				otherpersonalFolder.uploadFileWithRobot("Password.pdf");
				Thread.sleep(8000);					
				assertEquals(otherpersonalFolder.firstRowName().getText().trim(), "Password.pdf");
				Thread.sleep(1000);	
				
			}

		
}
