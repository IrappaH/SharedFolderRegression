package test.script;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
import pages.PersonalFolder;
import tests.InitialTestSetup;

public class ShareFileInOtherUserPersonalFolderTest extends InitialTestSetup {
	private OtherUserPersonalFolder otherUserpersonalFolder = null;
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
		
		otherUserpersonalFolder = homePage.gotoOtherUserPersonalFolder();
		otherUserpersonalFolder.uploadBtn().click();
		  PersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\");
		  PersonalFolder .uploadFileWithRobot("\"File1.pdf" + "\" \"File1.jpg" +
		  "\" \"File1.png" + "\" \"File1.jpeg" + "\""); Thread.sleep(30000);
		  otherUserpersonalFolder.uiControl("homeLink").click(); Thread.sleep(2000);
		 

	}

	@BeforeMethod(alwaysRun = true)
	public void verifyOpenPersonalFolder() throws Exception {
		otherUserpersonalFolder = homePage.gotoOtherUserPersonalFolder();
		/*
		 * Thread.sleep(2000); System.out.println("This is NoData from App: " +
		 * personalFolder.uiControl("noData").getText());
		 * System.out.println("This is NoData from Expected: " +
		 * getLocalizedMessage("noFilesInTable").trim()); if
		 * (personalFolder.uiControl("noData").getText().trim().equals(
		 * getLocalizedMessage("noFilesInTable").trim())) {
		 * System.out.println("No folders are available"); } else {
		 * personalFolder.selectAllCheckBox().click(); PersonalFolderDelete deleteFolder
		 * = personalFolder.gotoPersonalFolderDelete(); deleteFolder.okBtn().click();
		 * Thread.sleep(2000);
		 * assertEquals(personalFolder.uiControl("noData").getText(),
		 * getLocalizedMessage("noFilesInTable").trim()); }
		 */

	}

	@AfterMethod(alwaysRun = true)
	public void VerifyOpenHomePage() throws Exception {
		/*
		 * System.out.println("This is Expected result: " +
		 * getLocalizedMessage("noFilesInTable").trim());
		 * 
		 * if ((personalFolder.uiControl("noData").getText().trim())
		 * .equals(getLocalizedMessage("noFilesInTable").trim())) {
		 * System.out.println("After Method: No data");
		 * personalFolder.uiControl("homeLink").click(); Thread.sleep(2000); } else {
		 * personalFolder.selectAllCheckBox().click(); PersonalFolderDelete deleteFolder
		 * = personalFolder.gotoPersonalFolderDelete(); deleteFolder.okBtn().click();
		 * Thread.sleep(2000);
		 */
		otherUserpersonalFolder.uiControl("homeLink").click();
		Thread.sleep(2000);
		// }
	}

	@Test(priority = 1, groups = { "StringVerification", "UI" })
	@PriorityInClass(1)
	public void verifyFileSharedDetails() throws Exception {
		setTestDesc("Verify that Admin can share file from the context menu in the root folder. ");
		otherUserpersonalFolder.OperationMenu().click();
		Thread.sleep(1000);
		otherUserpersonalFolder.fileShare().click();
		Thread.sleep(1000);
		// Share detail file's header
		assertEquals(otherUserpersonalFolder.getSharedFileDetailsHeader().getText().trim(),
				getLocalizedMessage("SharedfileFilename").trim());

		otherUserpersonalFolder.getSharedFilesCancelButtonLabel().click();

	}

	@Test(priority = 2, groups = { "StringVerification", "UI" })

	@PriorityInClass(2)
	public void VerifySharedFilesLabel() throws Exception {
		setTestDesc("verify that 'Share the file.' label is displayed ");

		otherUserpersonalFolder.OperationMenu().click();
		Thread.sleep(1000);
		otherUserpersonalFolder.fileShare().click();
		Thread.sleep(1000);
		assertEquals(otherUserpersonalFolder.getSharedFilesLabel().getText().trim(), getLocalizedMessage("Sharethefile").trim());
		Thread.sleep(1000);
		otherUserpersonalFolder.getSharedFilesCancelButtonLabel().click();

	}

	@Test(priority = 3, groups = { "StringVerification", "UI" })

	@PriorityInClass(3)
	public void VerifySharedFilesStorageLabel() throws Exception {
		setTestDesc(" verify that 'Storage period.' label is displayed ");

		otherUserpersonalFolder.OperationMenu().click();
		Thread.sleep(1000);
		otherUserpersonalFolder.fileShare().click();
		Thread.sleep(1000);
		assertEquals(otherUserpersonalFolder.getSharedFilesStorageLabel().getText().trim(),
				getLocalizedMessage("Storageperiod").trim());

		Thread.sleep(1000);
		otherUserpersonalFolder.getSharedFilesCancelButtonLabel().click();

	}

	@Test(priority = 4, groups = { "StringVerification", "UI" })

	@PriorityInClass(3)
	public void VerifyErrorMessageSharedFilesStorageDayInvalidData() throws Exception {
		setTestDesc(" verify that Error message when user provides invalid input to 'Storage period.' Number of days ");

		otherUserpersonalFolder.OperationMenu().click();
		Thread.sleep(1000);
		otherUserpersonalFolder.fileShare().click();
		Thread.sleep(1000);
		otherUserpersonalFolder.getNumberOfDaysRadioBtn().click(); //
		otherUserpersonalFolder.ClearValues(); //
		otherUserpersonalFolder.getNumberOfDaysFromRadioBtn().clear();
		otherUserpersonalFolder.getNumberOfDaysFromRadioBtn().sendKeys("10");
		assertEquals(otherUserpersonalFolder.getErrorMsgDays().getText(), getLocalizedMessage("getErrorMsgInDays").trim());

		Thread.sleep(1000);
		otherUserpersonalFolder.getSharedFilesCancelButtonLabel().click();

	}

	@Test(priority = 5, groups = { "StringVerification", "UI" })

	@PriorityInClass(3)
	public void VerifyErrorMessageSharedFilesStorageTIMEInvalidData() throws Exception {
		setTestDesc(" verify that Error message when user provides invalid input to 'Storage period.' Times");

		otherUserpersonalFolder.OperationMenu().click();
		Thread.sleep(1000);
		otherUserpersonalFolder.fileShare().click();
		Thread.sleep(1000);
		otherUserpersonalFolder.getNumberOfTimesRadioBtn().click();
		otherUserpersonalFolder.getNumberOfTimesFromRadioBtn().sendKeys("10");
		Thread.sleep(1000);
		assertEquals(otherUserpersonalFolder.getErrorMsgTimes().getText().trim(),
				getLocalizedMessage("getErrorMsgInTime").trim());

		Thread.sleep(1000);
		otherUserpersonalFolder.getSharedFilesCancelButtonLabel().click();

	}

	@Test(priority = 5, groups = { "StringVerification", "UI" }, enabled = false)

	@PriorityInClass(5)
	public void VerifySharedFilesStorageTimeRadioButton() throws Exception {
		setTestDesc("verify that 'Storage period.' Time radio button default value is displayed  ");

		otherUserpersonalFolder.OperationMenu().click();
		Thread.sleep(1000);
		otherUserpersonalFolder.fileShare().click();
		Thread.sleep(1000);
		otherUserpersonalFolder.getNumberOfTimesRadioBtn().click();
		otherUserpersonalFolder.getSharedFilesTimeRadiobtn().sendKeys("10");
		assertEquals(otherUserpersonalFolder.getErrorMessageFromSharedFilesMaximumdownloads().getText().trim(),
				getLocalizedMessage("NumberOfTimesRadio").trim());
		Thread.sleep(1000);
		otherUserpersonalFolder.getSharedFilesCancelButtonLabel().click();
	}

	@Test(priority = 6, groups = { "StringVerification", "UI" }, enabled = false)

	@PriorityInClass(3)
	public void VerifySharedFilesStorageDayRadioButton() throws Exception {
		setTestDesc(" verify that 'Storage period.' Number of days radio button default value is displayed ");

		otherUserpersonalFolder.OperationMenu().click();
		Thread.sleep(1000);
		otherUserpersonalFolder.fileShare().click();
		Thread.sleep(1000);
		otherUserpersonalFolder.getNumberOfDaysRadioBtn().click();

		assertEquals(otherUserpersonalFolder.getNumberOfDaysFromRadioBtn().getText(),
				getLocalizedMessage("NumberOfDaysRadio").trim());
		Thread.sleep(1000);
		otherUserpersonalFolder.getSharedFilesCancelButtonLabel().click();

	}

	@Test(priority = 7, groups = { "StringVerification", "UI" })

	@PriorityInClass(4)
	public void VerifySharedFilesDownloadpasswordLabel() throws Exception {

		setTestDesc(" verify that 'Download password' label is displayed ");

		otherUserpersonalFolder.OperationMenu().click();
		Thread.sleep(1000);
		otherUserpersonalFolder.fileShare().click();
		Thread.sleep(1000);
		assertEquals(otherUserpersonalFolder.getSharedFilesDownloadpasswordLabel().getText().trim(),
				getLocalizedMessage("Downloadpasswordlabel").trim());

		Thread.sleep(1000);
		otherUserpersonalFolder.getSharedFilesCancelButtonLabel().click();

	}

	@Test(priority = 8, groups = { "Functional", "UI" })

	@PriorityInClass(4)
	public void VerifyErrorMessageSharedFilesDownloadpasswordCheckboxselection() throws Exception {

		setTestDesc(" Verify Error Message when user selected Download password Checkbox ");

		otherUserpersonalFolder.OperationMenu().click();
		Thread.sleep(1000);
		otherUserpersonalFolder.fileShare().click();
		Thread.sleep(1000);
		otherUserpersonalFolder.getSharedFilesDownloadpasswordLabel().click();
		Thread.sleep(1000);
		assertEquals(otherUserpersonalFolder.getPasswordErrorMsg().getText().trim(),
				getLocalizedMessage("DownloadpasswordErrormsg").trim());
		Thread.sleep(1000);
		otherUserpersonalFolder.getSharedFilesCancelButtonLabel().click();

	}

	@Test(priority = 9, groups = { "StringVerification", "UI" })

	@PriorityInClass(5)
	public void VerifySharedFilesMaximumdownloadsLabel() throws Exception {
		setTestDesc(" verify that 'Download password' label is displayed ");

		otherUserpersonalFolder.OperationMenu().click();
		Thread.sleep(1000);
		otherUserpersonalFolder.fileShare().click();
		Thread.sleep(1000);
		assertEquals(otherUserpersonalFolder.getSharedFilesMaximumdownloadsLabel().getText().trim(),
				getLocalizedMessage("Maximumdownloads").trim());

		Thread.sleep(1000);
		otherUserpersonalFolder.getSharedFilesCancelButtonLabel().click();
	}

	@Test(priority = 10, groups = { "StringVerification", "UI" }, enabled = false)
	public void VerifySharedFilesMaximumdownloadscountByDefault() throws Exception {
		setTestDesc(" Verify Shared Files Maximum download count By Default ");
		otherUserpersonalFolder.OperationMenu().click();
		Thread.sleep(1000);
		otherUserpersonalFolder.fileShare().click();
		Thread.sleep(1000);
		assertEquals(otherUserpersonalFolder.getSharedFilesMaximumdownloadscountByDefault().getText().trim(),
				getLocalizedMessage("MaximumdownloadscountbyDefault").trim());
		Thread.sleep(1000);
		otherUserpersonalFolder.getSharedFilesCancelButtonLabel().click();
	}

	@Test(priority = 10, groups = { "StringVerification", "UI" }, enabled = true)
	public void VerifyErrorMessageWhenUserProvidesInvalidMaximumdownloadCount() throws Exception {
		setTestDesc(" Verify Error Message When User Provides Invalid Maximum download Count ");
		otherUserpersonalFolder.OperationMenu().click();
		Thread.sleep(1000);
		otherUserpersonalFolder.fileShare().click();
		Thread.sleep(1000);
		otherUserpersonalFolder.getSharedFilesMaximumdownloadscountByDefault().sendKeys("10");
		assertEquals(otherUserpersonalFolder.getErrorMessageFromSharedFilesMaximumdownloads().getText().trim(),
				getLocalizedMessage("getErrorMsgMaximumdownloads").trim());
		Thread.sleep(1000);
		otherUserpersonalFolder.getSharedFilesCancelButtonLabel().click();
	}

	@Test(priority = 11, groups = { "Functional" }, alwaysRun = true)
	public void VerifySharedFilesCancelButtonLabel() throws Exception {
		setTestDesc(" verify that 'Cancel button' label is displayed ");

		otherUserpersonalFolder.OperationMenu().click();
		Thread.sleep(1000);
		otherUserpersonalFolder.fileShare().click();
		Thread.sleep(1000);
		assertEquals(otherUserpersonalFolder.getSharedFilesCancelButtonLabel().getText().trim(),
				getLocalizedMessage("Cancelbtn").trim());
		otherUserpersonalFolder.getSharedFilesCancelButtonLabel().click();
	}

	@Test(priority = 12, groups = { "Functional" }, enabled = true)
	public void VerifySharedFilesShareButtonLabel() throws Exception {
		setTestDesc("Verify that 'Share button' label is displayed ");
		otherUserpersonalFolder.OperationMenu().click();
		Thread.sleep(1000);
		otherUserpersonalFolder.fileShare().click();
		Thread.sleep(1000);
		assertEquals(otherUserpersonalFolder.clickShareBtn().getText().trim(), getLocalizedMessage("sharebtn").trim());
		otherUserpersonalFolder.getSharedFilesCancelButtonLabel().click();
	}

	@Test(priority = 13, groups = { "Functional" }, enabled = true)
	public void verifyFileShare() throws Exception {
		setTestDesc("Verify that Admin can share file from the context menu in the root folder. ");

		otherUserpersonalFolder.OperationMenu().click();
		Thread.sleep(1000);
		otherUserpersonalFolder.fileShare().click();
		Thread.sleep(1000);
		otherUserpersonalFolder.clickShareBtn().click();
		Thread.sleep(4000);
		assertEquals(otherUserpersonalFolder.shareFileHeader().getText().trim(), getLocalizedMessage("TempFileStorage"));
		Thread.sleep(1000);
		otherUserpersonalFolder.shareOkBtn().click();
		Thread.sleep(1000);
	}

	@Test(priority = 14, groups = { "Functional" }, enabled = true)
	public void verifyShareFileOnSuccessMessage() throws Exception {
		setTestDesc("verify Share File OnSuccess Message ");

		otherUserpersonalFolder.OperationMenu().click();
		Thread.sleep(1000);
		otherUserpersonalFolder.fileShare().click();
		Thread.sleep(1000);
		otherUserpersonalFolder.clickShareBtn().click();
		Thread.sleep(2000);

		// to get header from on success message
		assertEquals(otherUserpersonalFolder.shareFileHeader().getText().trim(), getLocalizedMessage("TempFileStorage"));
		otherUserpersonalFolder.shareOkBtn().click();
		Thread.sleep(1000);
	}

	@Test(priority = 15, groups = { "Functional" }, enabled = true) public void
  verifyHeaderShareFileOnSuccessMessage() throws Exception {
  setTestDesc("verify to get header from on success message Share File ");
  
  otherUserpersonalFolder.OperationMenu().click(); Thread.sleep(1000);
  otherUserpersonalFolder.fileShare().click(); Thread.sleep(1000);
  otherUserpersonalFolder.clickShareBtn().click(); Thread.sleep(2000);
  // to get URL  dynamic change
  assertTrue(otherUserpersonalFolder.getURLFromSharedFileOnSuccess().isDisplayed());
  otherUserpersonalFolder.shareOkBtn().click(); Thread.sleep(1000); }

	@Test(priority = 16, groups = { "Functional" }, enabled = true) public void
  verifyToolTipShareFileOnSuccessMessage() throws Exception {
  setTestDesc("verify to get tool tip from on success message Share File ");
  
  otherUserpersonalFolder.OperationMenu().click(); Thread.sleep(1000);
  otherUserpersonalFolder.fileShare().click(); Thread.sleep(1000);
  otherUserpersonalFolder.clickShareBtn().click(); Thread.sleep(2000); 
  // to get tool  tip URL
  boolean displayed =  otherUserpersonalFolder.getURLFromtooltipsSharedFileOnSuccess().isDisplayed();
  assertTrue(displayed); 
  otherUserpersonalFolder.shareOkBtn().click();
  Thread.sleep(1000);
  
  }

	@Test(priority = 17, groups = { "Functional" }, enabled = true) public void
  verifyFilenameShareFileOnSuccessMessage() throws Exception {
  setTestDesc("verify to get file name from on success message Share File ");
  
  otherUserpersonalFolder.OperationMenu().click(); Thread.sleep(1000);
  otherUserpersonalFolder.fileShare().click(); Thread.sleep(1000);
  otherUserpersonalFolder.clickShareBtn().click(); Thread.sleep(2000);
 
  assertEquals(otherUserpersonalFolder.getFileName().getText().trim(),
  getLocalizedMessage("FilenameONSuccessShareFile"));
  otherUserpersonalFolder.shareOkBtn().click(); Thread.sleep(1000); }

	@Test(priority = 18, groups = { "Functional" }, enabled = true)
	public void verifyExpiredDateShareFileOnSuccessMessage() throws Exception {
		setTestDesc("verify to get Expired date from on success message Share File ");

		otherUserpersonalFolder.OperationMenu().click();
		Thread.sleep(1000);
		otherUserpersonalFolder.fileShare().click();
		Thread.sleep(1000);
		otherUserpersonalFolder.clickShareBtn().click();
		Thread.sleep(2000);

		
		assertEquals(otherUserpersonalFolder.expirationDate().getText().trim(), "保存期限 " + otherUserpersonalFolder.getTime() + " です。");
		otherUserpersonalFolder.shareOkBtn().click();
		Thread.sleep(1000);
	}

	@Test(priority = 19, groups = { "Functional" }, enabled = true) public void
  verifydownloadCountShareFileOnSuccessMessage() throws Exception {
  setTestDesc("verify to get download Count from on success message Share File "
  );
  
  otherUserpersonalFolder.OperationMenu().click(); Thread.sleep(1000);
  otherUserpersonalFolder.fileShare().click(); Thread.sleep(1000);
  otherUserpersonalFolder.clickShareBtn().click(); Thread.sleep(2000);
  // to get  download count
  assertEquals(otherUserpersonalFolder.downloadCountfromOnSuccessMessage().getText().
  trim(), getLocalizedMessage("downloadCount"));
  
  otherUserpersonalFolder.shareOkBtn().click(); Thread.sleep(1000); }

	@Test(priority = 20, groups = { "Functional" }, enabled = true)
	public void verifySharedFileOnSuccessURL() throws Exception {
		setTestDesc("verify Share File OnSuccess Message ");

		String Filename = otherUserpersonalFolder.getFileNamefromRow().getText().trim();
		String fileSize = otherUserpersonalFolder.getFileSize().getText().trim();
		otherUserpersonalFolder.OperationMenu().click();
		Thread.sleep(1000);
		otherUserpersonalFolder.fileShare().click();
		Thread.sleep(1000);
		otherUserpersonalFolder.clickShareBtn().click();
		Thread.sleep(2000);

		// to get URL dynamic change 
		String 	copyURL =otherUserpersonalFolder.getURLFromSharedFileOnSuccess().getText().trim();
		String downloadCount = otherUserpersonalFolder.downloadCountfromOnSuccessMessage().getText().trim(); 
		String fileName = otherUserpersonalFolder.getFileName().getText().trim();

		otherUserpersonalFolder.shareOkBtn().click();
		Thread.sleep(1000);

		otherUserpersonalFolder.OpenNewTab().click();
		getDriver().findElement(By.xpath("//body")).sendKeys(Keys.CONTROL + "+" + 't');
		getDriver().get(copyURL);
		Thread.sleep(1000);
		assertEquals(otherUserpersonalFolder.getFileNameFromFileInfo().getText().trim(), Filename);
		assertEquals(otherUserpersonalFolder.getFileSizeFromFileInfo().getText().trim(), fileSize);
		Thread.sleep(1000);
		otherUserpersonalFolder.GetDownloadText().click();
		Thread.sleep(5000);
		assertEquals(otherUserpersonalFolder.getdownloadCount().getText().trim(), getLocalizedMessage("downloadedOnce"));

		getDriver().navigate().back();

	}

	@Test(priority = 21, groups = { "Functional" }, enabled = true)
	public void verifyShareFileWithValidPassword() throws Exception {
		setTestDesc("verify Share File with password and download file");
		otherUserpersonalFolder.OperationMenu().click();
		Thread.sleep(1000);
		otherUserpersonalFolder.fileShare().click();
		Thread.sleep(2000);
		otherUserpersonalFolder.getSharedFilesDownloadpasswordLabel().click();
		Thread.sleep(1000);
		otherUserpersonalFolder.enterPassword().sendKeys(getLocalizedMessage("downloadPassword"));
		otherUserpersonalFolder.clickShareBtn().click();
		Thread.sleep(2000);
		// to get URL dynamic change
		String copyURL = otherUserpersonalFolder.getURLFromSharedFileOnSuccess().getText().trim();
		String downloadCount = otherUserpersonalFolder.downloadCountfromOnSuccessMessage().getText().trim();
		otherUserpersonalFolder.shareOkBtn().click();
		Thread.sleep(1000);
		otherUserpersonalFolder.OpenNewTab().click();
		getDriver().findElement(By.xpath("//body")).sendKeys(Keys.CONTROL + "+" + 't');
		getDriver().get(copyURL);
		Thread.sleep(1000);

		Thread.sleep(1000);
		otherUserpersonalFolder.GetDownloadText().click();
		Thread.sleep(2000);
		otherUserpersonalFolder.enterPassworToDownload().sendKeys(getLocalizedMessage("downloadPassword"));
		Thread.sleep(2000);
		otherUserpersonalFolder.ClickOKButton().click();
		Thread.sleep(4000);
		assertEquals(otherUserpersonalFolder.getdownloadCount().getText().trim(), getLocalizedMessage("downloadedOnce"));

		getDriver().navigate().back();

	}

	@Test(priority = 22, groups = { "Functional" }, enabled = true)
	public void verifyShareFilePasswordCancel() throws Exception {
		setTestDesc("verify that Share a File with password and click cancel button from download password diloag ");
		otherUserpersonalFolder.OperationMenu().click();
		Thread.sleep(1000);
		otherUserpersonalFolder.fileShare().click();
		Thread.sleep(2000);
		otherUserpersonalFolder.getSharedFilesDownloadpasswordLabel().click();
		Thread.sleep(1000);
		otherUserpersonalFolder.enterPassword().sendKeys(getLocalizedMessage("downloadPassword"));
		otherUserpersonalFolder.clickShareBtn().click();
		Thread.sleep(2000);
		// to get URL dynamic change
		String copyURL = otherUserpersonalFolder.getURLFromSharedFileOnSuccess().getText().trim();
		String downloadCount = otherUserpersonalFolder.downloadCountfromOnSuccessMessage().getText().trim();
		otherUserpersonalFolder.shareOkBtn().click();
		Thread.sleep(1000);
		otherUserpersonalFolder.OpenNewTab().click();
		getDriver().findElement(By.xpath("//body")).sendKeys(Keys.CONTROL + "+" + 't');
		getDriver().get(copyURL);
		Thread.sleep(1000);

		otherUserpersonalFolder.GetDownloadText().click();

		Thread.sleep(2000);

		otherUserpersonalFolder.closePasswordMessagedialog().click();

		getDriver().navigate().back();

	}

	@Test(priority = 23, groups = { "Functional" }, enabled = true)
	public void verifyShareFileWithInvalidPassword() throws Exception {
		setTestDesc(
				"verify Error message that Share a File with password and download the same file with invalid password");
		otherUserpersonalFolder.OperationMenu().click();
		Thread.sleep(1000);
		otherUserpersonalFolder.fileShare().click();
		Thread.sleep(2000);
		otherUserpersonalFolder.getSharedFilesDownloadpasswordLabel().click();
		Thread.sleep(1000);
		otherUserpersonalFolder.enterPassword().sendKeys(getLocalizedMessage("downloadPassword"));
		otherUserpersonalFolder.clickShareBtn().click();
		Thread.sleep(2000);
		// to get URL dynamic change
		String copyURL = otherUserpersonalFolder.getURLFromSharedFileOnSuccess().getText().trim();
		String downloadCount = otherUserpersonalFolder.downloadCountfromOnSuccessMessage().getText().trim();
		otherUserpersonalFolder.shareOkBtn().click();
		Thread.sleep(1000);
		otherUserpersonalFolder.OpenNewTab().click();
		getDriver().findElement(By.xpath("//body")).sendKeys(Keys.CONTROL + "+" + 't');
		getDriver().get(copyURL);
		Thread.sleep(1000);

		otherUserpersonalFolder.GetDownloadText().click();
		Thread.sleep(2000);
		otherUserpersonalFolder.enterPassworToDownload().sendKeys("downloadPassword");
		Thread.sleep(2000);
		otherUserpersonalFolder.ClickOKButton().click();
		Thread.sleep(4000);
		assertEquals(otherUserpersonalFolder.getdownloadErrorMessage().getText().trim(),
				getLocalizedMessage("downloadedErrormessage"));
		Thread.sleep(1000);
		otherUserpersonalFolder.closeErrorMessagedialog().click();
		Thread.sleep(1000);
		otherUserpersonalFolder.closePasswordMessagedialog().click();

		getDriver().navigate().back();

	}

}
