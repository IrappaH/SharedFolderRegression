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
import pages.PersonalFolder;
import tests.InitialTestSetup;

public class ShareFileInPersonalFolderTest extends InitialTestSetup {
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
		/*
		 * personalFolder = homePage.gotoPersonalFolder();
		 * personalFolder.uploadBtn().click();
		 * PersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\");
		 * PersonalFolder .uploadFileWithRobot("\"File1.pdf" + "\" \"File1.jpg" +
		 * "\" \"File1.png" + "\" \"File1.jpeg" + "\""); Thread.sleep(30000);
		 * personalFolder.uiControl("homeLink").click(); Thread.sleep(2000);
		 */

	}

	@BeforeMethod(alwaysRun = true)
	public void verifyOpenPersonalFolder() throws Exception {
		personalFolder = homePage.gotoPersonalFolder();
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
		personalFolder.uiControl("homeLink").click();
		Thread.sleep(2000);
		// }
	}

	@Test(priority = 1, groups = { "StringVerification", "UI" })
  
  @PriorityInClass(1) public void verifyFileSharedDetails() throws Exception {
  setTestDesc("Verify that Admin can share file from the context menu in the root folder. "
  ); personalFolder.OperationMenu().click(); Thread.sleep(1000);
  personalFolder.fileShare().click(); Thread.sleep(1000); 
  // Share detail  file's header
  // System.out.println("The name: " + //  getLocalizedMessage("SharedfileFilename").trim());
  assertEquals(personalFolder.getSharedFileDetailsHeader().getText().trim(),
  getLocalizedMessage("SharedfileFilename").trim());
  // System.out.println("The  local msssage is:
  //  "+COCOROSingleton.INSTANCE.getLocalizedMessage("SharedfileFilename"));
  
  personalFolder.getSharedFilesCancelButtonLabel().click();
  
  }

	@Test(priority = 2, groups = { "StringVerification", "UI" })

	@PriorityInClass(2)
	public void VerifySharedFilesLabel() throws Exception {
		setTestDesc("verify that 'Share the file.' label is displayed ");

		personalFolder.OperationMenu().click();
		Thread.sleep(1000);
		personalFolder.fileShare().click();
		Thread.sleep(1000);
		assertEquals(personalFolder.getSharedFilesLabel().getText().trim(), getLocalizedMessage("Sharethefile").trim());
		Thread.sleep(1000);
		personalFolder.getSharedFilesCancelButtonLabel().click();

	}

	@Test(priority = 3, groups = { "StringVerification", "UI" })

	@PriorityInClass(3)
	public void VerifySharedFilesStorageLabel() throws Exception {
		setTestDesc(" verify that 'Storage period.' label is displayed ");

		personalFolder.OperationMenu().click();
		Thread.sleep(1000);
		personalFolder.fileShare().click();
		Thread.sleep(1000);
		assertEquals(personalFolder.getSharedFilesStorageLabel().getText().trim(),
				getLocalizedMessage("Storageperiod").trim());

		Thread.sleep(1000);
		personalFolder.getSharedFilesCancelButtonLabel().click();

	}

	@Test(priority = 4, groups = { "StringVerification", "UI" })

	@PriorityInClass(3)
	public void VerifyErrorMessageSharedFilesStorageDayInvalidData() throws Exception {
		setTestDesc(" verify that Error message when user provides invalid input to 'Storage period.' Number of days ");

		personalFolder.OperationMenu().click();
		Thread.sleep(1000);
		personalFolder.fileShare().click();
		Thread.sleep(1000);
		personalFolder.getNumberOfDaysRadioBtn().click(); //
		personalFolder.ClearValues(); //
		personalFolder.getNumberOfDaysFromRadioBtn().clear();
		personalFolder.getNumberOfDaysFromRadioBtn().sendKeys("10");
		assertEquals(personalFolder.getErrorMsgDays().getText(), getLocalizedMessage("getErrorMsgInDays").trim());

		Thread.sleep(1000);
		personalFolder.getSharedFilesCancelButtonLabel().click();

	}

	@Test(priority = 5, groups = { "StringVerification", "UI" })

	@PriorityInClass(3)
	public void VerifyErrorMessageSharedFilesStorageTIMEInvalidData() throws Exception {
		setTestDesc(" verify that Error message when user provides invalid input to 'Storage period.' Times");

		personalFolder.OperationMenu().click();
		Thread.sleep(1000);
		personalFolder.fileShare().click();
		Thread.sleep(1000);
		personalFolder.getNumberOfTimesRadioBtn().click();
		personalFolder.getNumberOfTimesFromRadioBtn().sendKeys("10");
		Thread.sleep(1000);
		assertEquals(personalFolder.getErrorMsgTimes().getText().trim(),
				getLocalizedMessage("getErrorMsgInTime").trim());

		Thread.sleep(1000);
		personalFolder.getSharedFilesCancelButtonLabel().click();

	}

	@Test(priority = 5, groups = { "StringVerification", "UI" }, enabled = false)

	@PriorityInClass(5)
	public void VerifySharedFilesStorageTimeRadioButton() throws Exception {
		setTestDesc("verify that 'Storage period.' Time radio button default value is displayed  ");

		personalFolder.OperationMenu().click();
		Thread.sleep(1000);
		personalFolder.fileShare().click();
		Thread.sleep(1000);
		personalFolder.getNumberOfTimesRadioBtn().click();
		personalFolder.getSharedFilesTimeRadiobtn().sendKeys("10");
		assertEquals(personalFolder.getErrorMessageFromSharedFilesMaximumdownloads().getText().trim(),
				getLocalizedMessage("NumberOfTimesRadio").trim());
		Thread.sleep(1000);
		personalFolder.getSharedFilesCancelButtonLabel().click();
	}

	@Test(priority = 6, groups = { "StringVerification", "UI" }, enabled = false)

	@PriorityInClass(3)
	public void VerifySharedFilesStorageDayRadioButton() throws Exception {
		setTestDesc(" verify that 'Storage period.' Number of days radio button default value is displayed ");

		personalFolder.OperationMenu().click();
		Thread.sleep(1000);
		personalFolder.fileShare().click();
		Thread.sleep(1000);
		personalFolder.getNumberOfDaysRadioBtn().click();

		assertEquals(personalFolder.getNumberOfDaysFromRadioBtn().getText(),
				getLocalizedMessage("NumberOfDaysRadio").trim());
		Thread.sleep(1000);
		personalFolder.getSharedFilesCancelButtonLabel().click();

	}

	@Test(priority = 7, groups = { "StringVerification", "UI" })

	@PriorityInClass(4)
	public void VerifySharedFilesDownloadpasswordLabel() throws Exception {

		setTestDesc(" verify that 'Download password' label is displayed ");

		personalFolder.OperationMenu().click();
		Thread.sleep(1000);
		personalFolder.fileShare().click();
		Thread.sleep(1000);
		assertEquals(personalFolder.getSharedFilesDownloadpasswordLabel().getText().trim(),
				getLocalizedMessage("Downloadpasswordlabel").trim());

		Thread.sleep(1000);
		personalFolder.getSharedFilesCancelButtonLabel().click();

	}

	@Test(priority = 8, groups = { "Functional", "UI" })

	@PriorityInClass(4)
	public void VerifyErrorMessageSharedFilesDownloadpasswordCheckboxselection() throws Exception {

		setTestDesc(" Verify Error Message when user selected Download password Checkbox ");

		personalFolder.OperationMenu().click();
		Thread.sleep(1000);
		personalFolder.fileShare().click();
		Thread.sleep(1000);
		personalFolder.getSharedFilesDownloadpasswordLabel().click();
		Thread.sleep(1000);
		assertEquals(personalFolder.getPasswordErrorMsg().getText().trim(),
				getLocalizedMessage("DownloadpasswordErrormsg").trim());
		Thread.sleep(1000);
		personalFolder.getSharedFilesCancelButtonLabel().click();

	}

	@Test(priority = 9, groups = { "StringVerification", "UI" })

	@PriorityInClass(5)
	public void VerifySharedFilesMaximumdownloadsLabel() throws Exception {
		setTestDesc(" verify that 'Download password' label is displayed ");

		personalFolder.OperationMenu().click();
		Thread.sleep(1000);
		personalFolder.fileShare().click();
		Thread.sleep(1000);
		assertEquals(personalFolder.getSharedFilesMaximumdownloadsLabel().getText().trim(),
				getLocalizedMessage("Maximumdownloads").trim());

		Thread.sleep(1000);
		personalFolder.getSharedFilesCancelButtonLabel().click();
	}

	@Test(priority = 10, groups = { "StringVerification", "UI" }, enabled = false)
	public void VerifySharedFilesMaximumdownloadscountByDefault() throws Exception {
		setTestDesc(" Verify Shared Files Maximum download count By Default ");
		personalFolder.OperationMenu().click();
		Thread.sleep(1000);
		personalFolder.fileShare().click();
		Thread.sleep(1000);
		assertEquals(personalFolder.getSharedFilesMaximumdownloadscountByDefault().getText().trim(),
				getLocalizedMessage("MaximumdownloadscountbyDefault").trim());
		Thread.sleep(1000);
		personalFolder.getSharedFilesCancelButtonLabel().click();
	}

	@Test(priority = 10, groups = { "StringVerification", "UI" }, enabled = true)
	public void VerifyErrorMessageWhenUserProvidesInvalidMaximumdownloadCount() throws Exception {
		setTestDesc(" Verify Error Message When User Provides Invalid Maximum download Count ");
		personalFolder.OperationMenu().click();
		Thread.sleep(1000);
		personalFolder.fileShare().click();
		Thread.sleep(1000);
		personalFolder.getSharedFilesMaximumdownloadscountByDefault().sendKeys("10");
		assertEquals(personalFolder.getErrorMessageFromSharedFilesMaximumdownloads().getText().trim(),
				getLocalizedMessage("getErrorMsgMaximumdownloads").trim());
		Thread.sleep(1000);
		personalFolder.getSharedFilesCancelButtonLabel().click();
	}

	@Test(priority = 11, groups = { "Functional" }, alwaysRun = true)
	public void VerifySharedFilesCancelButtonLabel() throws Exception {
		setTestDesc(" verify that 'Cancel button' label is displayed ");

		personalFolder.OperationMenu().click();
		Thread.sleep(1000);
		personalFolder.fileShare().click();
		Thread.sleep(1000);
		assertEquals(personalFolder.getSharedFilesCancelButtonLabel().getText().trim(),
				getLocalizedMessage("Cancelbtn").trim());
		personalFolder.getSharedFilesCancelButtonLabel().click();
	}

	@Test(priority = 12, groups = { "Functional" }, enabled = true)
	public void VerifySharedFilesShareButtonLabel() throws Exception {
		setTestDesc("Verify that 'Share button' label is displayed ");
		personalFolder.OperationMenu().click();
		Thread.sleep(1000);
		personalFolder.fileShare().click();
		Thread.sleep(1000);
		assertEquals(personalFolder.clickShareBtn().getText().trim(), getLocalizedMessage("sharebtn").trim());
		personalFolder.getSharedFilesCancelButtonLabel().click();
	}

	@Test(priority = 13, groups = { "Functional" }, enabled = true)
	public void verifyFileShare() throws Exception {
		setTestDesc("Verify that Admin can share file from the context menu in the root folder. ");

		personalFolder.OperationMenu().click();
		Thread.sleep(1000);
		personalFolder.fileShare().click();
		Thread.sleep(1000);
		personalFolder.clickShareBtn().click();
		Thread.sleep(4000);
		assertEquals(personalFolder.shareFileHeader().getText().trim(), getLocalizedMessage("TempFileStorage"));
		Thread.sleep(1000);
		personalFolder.shareOkBtn().click();
		Thread.sleep(1000);
	}

	@Test(priority = 14, groups = { "Functional" }, enabled = true)
	public void verifyShareFileOnSuccessMessage() throws Exception {
		setTestDesc("verify Share File OnSuccess Message ");

		personalFolder.OperationMenu().click();
		Thread.sleep(1000);
		personalFolder.fileShare().click();
		Thread.sleep(1000);
		personalFolder.clickShareBtn().click();
		Thread.sleep(2000);

		// to get header from on success message
		assertEquals(personalFolder.shareFileHeader().getText().trim(), getLocalizedMessage("TempFileStorage"));
		personalFolder.shareOkBtn().click();
		Thread.sleep(1000);
	}

	@Test(priority = 15, groups = { "Functional" }, enabled = true) public void
  verifyHeaderShareFileOnSuccessMessage() throws Exception {
  setTestDesc("verify to get header from on success message Share File ");
  
  personalFolder.OperationMenu().click(); Thread.sleep(1000);
  personalFolder.fileShare().click(); Thread.sleep(1000);
  personalFolder.clickShareBtn().click(); Thread.sleep(2000);
  // to get URL  dynamic change
  assertTrue(personalFolder.getURLFromSharedFileOnSuccess().isDisplayed());
  personalFolder.shareOkBtn().click(); Thread.sleep(1000); }

	@Test(priority = 16, groups = { "Functional" }, enabled = true) public void
  verifyToolTipShareFileOnSuccessMessage() throws Exception {
  setTestDesc("verify to get tool tip from on success message Share File ");
  
  personalFolder.OperationMenu().click(); Thread.sleep(1000);
  personalFolder.fileShare().click(); Thread.sleep(1000);
  personalFolder.clickShareBtn().click(); Thread.sleep(2000); 
  // to get tool  tip URL
  boolean displayed =  personalFolder.getURLFromtooltipsSharedFileOnSuccess().isDisplayed();
  assertTrue(displayed); 
  personalFolder.shareOkBtn().click();
  Thread.sleep(1000);
  
  }

	@Test(priority = 17, groups = { "Functional" }, enabled = true) public void
  verifyFilenameShareFileOnSuccessMessage() throws Exception {
  setTestDesc("verify to get file name from on success message Share File ");
  
  personalFolder.OperationMenu().click(); Thread.sleep(1000);
  personalFolder.fileShare().click(); Thread.sleep(1000);
  personalFolder.clickShareBtn().click(); Thread.sleep(2000);
  // to get File  name 
  assertEquals(personalFolder.getFileName().getText().trim(),
  getLocalizedMessage("FilenameONSuccessShareFile"));
  personalFolder.shareOkBtn().click(); Thread.sleep(1000); }

	@Test(priority = 18, groups = { "Functional" }, enabled = true)
	public void verifyExpiredDateShareFileOnSuccessMessage() throws Exception {
		setTestDesc("verify to get Expired date from on success message Share File ");

		personalFolder.OperationMenu().click();
		Thread.sleep(1000);
		personalFolder.fileShare().click();
		Thread.sleep(1000);
		personalFolder.clickShareBtn().click();
		Thread.sleep(2000);

		// to get Expired date dynamic change
		assertEquals(personalFolder.expirationDate().getText().trim(), "保存期限 " + personalFolder.getTime() + " です。");
		personalFolder.shareOkBtn().click();
		Thread.sleep(1000);
	}

	@Test(priority = 19, groups = { "Functional" }, enabled = true) public void
  verifydownloadCountShareFileOnSuccessMessage() throws Exception {
  setTestDesc("verify to get download Count from on success message Share File "
  );
  
  personalFolder.OperationMenu().click(); Thread.sleep(1000);
  personalFolder.fileShare().click(); Thread.sleep(1000);
  personalFolder.clickShareBtn().click(); Thread.sleep(2000);
  // to get  download count
  assertEquals(personalFolder.downloadCountfromOnSuccessMessage().getText().
  trim(), getLocalizedMessage("downloadCount"));
  
  personalFolder.shareOkBtn().click(); Thread.sleep(1000); }

	@Test(priority = 20, groups = { "Functional" }, enabled = true)
	public void verifySharedFileOnSuccessURL() throws Exception {
		setTestDesc("verify Share File OnSuccess Message ");

		String Filename = personalFolder.getFileNamefromRow().getText().trim();
		String fileSize = personalFolder.getFileSize().getText().trim();
		personalFolder.OperationMenu().click();
		Thread.sleep(1000);
		personalFolder.fileShare().click();
		Thread.sleep(1000);
		personalFolder.clickShareBtn().click();
		Thread.sleep(2000);

		// to get URL dynamic change 
		String 	copyURL =personalFolder.getURLFromSharedFileOnSuccess().getText().trim();
		String downloadCount = personalFolder.downloadCountfromOnSuccessMessage().getText().trim(); 
		String fileName = personalFolder.getFileName().getText().trim();

		personalFolder.shareOkBtn().click();
		Thread.sleep(1000);

		personalFolder.OpenNewTab().click();
		getDriver().findElement(By.xpath("//body")).sendKeys(Keys.CONTROL + "+" + 't');
		getDriver().get(copyURL);
		Thread.sleep(1000);
		assertEquals(personalFolder.getFileNameFromFileInfo().getText().trim(), Filename);
		assertEquals(personalFolder.getFileSizeFromFileInfo().getText().trim(), fileSize);
		Thread.sleep(1000);
		personalFolder.GetDownloadText().click();
		Thread.sleep(5000);
		assertEquals(personalFolder.getdownloadCount().getText().trim(), getLocalizedMessage("downloadedOnce"));

		getDriver().navigate().back();

	}

	@Test(priority = 21, groups = { "Functional" }, enabled = true)
	public void verifyShareFileWithValidPassword() throws Exception {
		setTestDesc("verify Share File with password and download file");
		personalFolder.OperationMenu().click();
		Thread.sleep(1000);
		personalFolder.fileShare().click();
		Thread.sleep(2000);
		personalFolder.getSharedFilesDownloadpasswordLabel().click();
		Thread.sleep(1000);
		personalFolder.enterPassword().sendKeys(getLocalizedMessage("downloadPassword"));
		personalFolder.clickShareBtn().click();
		Thread.sleep(2000);
		// to get URL dynamic change
		String copyURL = personalFolder.getURLFromSharedFileOnSuccess().getText().trim();
		String downloadCount = personalFolder.downloadCountfromOnSuccessMessage().getText().trim();
		personalFolder.shareOkBtn().click();
		Thread.sleep(1000);
		personalFolder.OpenNewTab().click();
		getDriver().findElement(By.xpath("//body")).sendKeys(Keys.CONTROL + "+" + 't');
		getDriver().get(copyURL);
		Thread.sleep(1000);

		Thread.sleep(1000);
		personalFolder.GetDownloadText().click();
		Thread.sleep(2000);
		personalFolder.enterPassworToDownload().sendKeys(getLocalizedMessage("downloadPassword"));
		Thread.sleep(2000);
		personalFolder.ClickOKButton().click();
		Thread.sleep(4000);
		assertEquals(personalFolder.getdownloadCount().getText().trim(), getLocalizedMessage("downloadedOnce"));

		getDriver().navigate().back();

	}

	@Test(priority = 22, groups = { "Functional" }, enabled = true)
	public void verifyShareFilePasswordCancel() throws Exception {
		setTestDesc("verify that Share a File with password and click cancel button from download password diloag ");
		personalFolder.OperationMenu().click();
		Thread.sleep(1000);
		personalFolder.fileShare().click();
		Thread.sleep(2000);
		personalFolder.getSharedFilesDownloadpasswordLabel().click();
		Thread.sleep(1000);
		personalFolder.enterPassword().sendKeys(getLocalizedMessage("downloadPassword"));
		personalFolder.clickShareBtn().click();
		Thread.sleep(2000);
		// to get URL dynamic change
		String copyURL = personalFolder.getURLFromSharedFileOnSuccess().getText().trim();
		String downloadCount = personalFolder.downloadCountfromOnSuccessMessage().getText().trim();
		personalFolder.shareOkBtn().click();
		Thread.sleep(1000);
		personalFolder.OpenNewTab().click();
		getDriver().findElement(By.xpath("//body")).sendKeys(Keys.CONTROL + "+" + 't');
		getDriver().get(copyURL);
		Thread.sleep(1000);

		personalFolder.GetDownloadText().click();

		Thread.sleep(2000);

		personalFolder.closePasswordMessagedialog().click();

		getDriver().navigate().back();

	}

	@Test(priority = 23, groups = { "Functional" }, enabled = true)
	public void verifyShareFileWithInvalidPassword() throws Exception {
		setTestDesc(
				"verify Error message that Share a File with password and download the same file with invalid password");
		personalFolder.OperationMenu().click();
		Thread.sleep(1000);
		personalFolder.fileShare().click();
		Thread.sleep(2000);
		personalFolder.getSharedFilesDownloadpasswordLabel().click();
		Thread.sleep(1000);
		personalFolder.enterPassword().sendKeys(getLocalizedMessage("downloadPassword"));
		personalFolder.clickShareBtn().click();
		Thread.sleep(2000);
		// to get URL dynamic change
		String copyURL = personalFolder.getURLFromSharedFileOnSuccess().getText().trim();
		String downloadCount = personalFolder.downloadCountfromOnSuccessMessage().getText().trim();
		personalFolder.shareOkBtn().click();
		Thread.sleep(1000);
		personalFolder.OpenNewTab().click();
		getDriver().findElement(By.xpath("//body")).sendKeys(Keys.CONTROL + "+" + 't');
		getDriver().get(copyURL);
		Thread.sleep(1000);

		personalFolder.GetDownloadText().click();
		Thread.sleep(2000);
		personalFolder.enterPassworToDownload().sendKeys("downloadPassword");
		Thread.sleep(2000);
		personalFolder.ClickOKButton().click();
		Thread.sleep(4000);
		assertEquals(personalFolder.getdownloadErrorMessage().getText().trim(),
				getLocalizedMessage("downloadedErrormessage"));
		Thread.sleep(1000);
		personalFolder.closeErrorMessagedialog().click();
		Thread.sleep(1000);
		personalFolder.closePasswordMessagedialog().click();

		getDriver().navigate().back();

	}

}
