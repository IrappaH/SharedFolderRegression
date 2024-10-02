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
import pages.SharedFolder;
import tests.InitialTestSetup;

public class ShareFileInSharedFolderTest extends InitialTestSetup {
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

		sharedFolder = homePage.gotoSharedFolder();
		sharedFolder.uploadBtn().click();
		SharedFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\");
		SharedFolder
				.uploadFileWithRobot("\"File1.pdf" + "\" \"File1.jpg" + "\" \"File1.png" + "\" \"File1.jpeg" + "\"");
		Thread.sleep(30000);
		sharedFolder.uiControl("homeLink").click();
		Thread.sleep(2000);

	}

	@BeforeMethod(alwaysRun = true)
	public void verifyOpensharedFolder() throws Exception {
		sharedFolder = homePage.gotoSharedFolder();

	}

	@AfterMethod(alwaysRun = true)
	public void VerifyOpenHomePage() throws Exception {

		sharedFolder.uiControl("homeLink").click();
		Thread.sleep(2000);

	}

	@Test(priority = 1, groups = { "StringVerification", "UI" })

	@PriorityInClass(1)
	public void verifyFileSharedDetails() throws Exception {
		setTestDesc("Verify that Admin can share file from the context menu in the root folder. ");
		sharedFolder.OperationMenu().click();
		Thread.sleep(1000);
		sharedFolder.fileShare().click();
		Thread.sleep(1000);

		assertEquals(sharedFolder.getSharedFileDetailsHeader().getText().trim(),
				getLocalizedMessage("SharedfileFilename").trim());

		sharedFolder.getSharedFilesCancelButtonLabel().click();

	}

	@Test(priority = 2, groups = { "StringVerification", "UI" })

	@PriorityInClass(2)
	public void VerifySharedFilesLabel() throws Exception {
		setTestDesc("verify that 'Share the file.' label is displayed ");

		sharedFolder.OperationMenu().click();
		Thread.sleep(1000);
		sharedFolder.fileShare().click();
		Thread.sleep(1000);
		assertEquals(sharedFolder.getSharedFilesLabel().getText().trim(), getLocalizedMessage("Sharethefile").trim());
		Thread.sleep(1000);
		sharedFolder.getSharedFilesCancelButtonLabel().click();

	}

	@Test(priority = 3, groups = { "StringVerification", "UI" })

	@PriorityInClass(3)
	public void VerifySharedFilesStorageLabel() throws Exception {
		setTestDesc(" verify that 'Storage period.' label is displayed ");

		sharedFolder.OperationMenu().click();
		Thread.sleep(1000);
		sharedFolder.fileShare().click();
		Thread.sleep(1000);
		assertEquals(sharedFolder.getSharedFilesStorageLabel().getText().trim(),
				getLocalizedMessage("Storageperiod").trim());

		Thread.sleep(1000);
		sharedFolder.getSharedFilesCancelButtonLabel().click();

	}

	@Test(priority = 4, groups = { "StringVerification", "UI" })

	@PriorityInClass(3)
	public void VerifyErrorMessageSharedFilesStorageDayInvalidData() throws Exception {
		setTestDesc(" verify that Error message when user provides invalid input to 'Storage period.' Number of days ");

		sharedFolder.OperationMenu().click();
		Thread.sleep(1000);
		sharedFolder.fileShare().click();
		Thread.sleep(1000);
		sharedFolder.getNumberOfDaysRadioBtn().click(); //
		sharedFolder.ClearValues(); //
		sharedFolder.getNumberOfDaysFromRadioBtn().clear();
		sharedFolder.getNumberOfDaysFromRadioBtn().sendKeys("10");
		assertEquals(sharedFolder.getErrorMsgDays().getText(), getLocalizedMessage("getErrorMsgInDays").trim());

		Thread.sleep(1000);
		sharedFolder.getSharedFilesCancelButtonLabel().click();

	}

	@Test(priority = 5, groups = { "StringVerification", "UI" })

	@PriorityInClass(3)
	public void VerifyErrorMessageSharedFilesStorageTIMEInvalidData() throws Exception {
		setTestDesc(" verify that Error message when user provides invalid input to 'Storage period.' Times");
		sharedFolder.OperationMenu().click();
		Thread.sleep(1000);
		sharedFolder.fileShare().click();
		Thread.sleep(1000);
		sharedFolder.getNumberOfTimesRadioBtn().click();
		sharedFolder.getNumberOfTimesFromRadioBtn().sendKeys("10");
		Thread.sleep(1000);
		assertEquals(sharedFolder.getErrorMsgTimes().getText().trim(), getLocalizedMessage("getErrorMsgInTime").trim());

		Thread.sleep(1000);
		sharedFolder.getSharedFilesCancelButtonLabel().click();

	}

	@Test(priority = 5, groups = { "StringVerification", "UI" }, enabled = false)

	@PriorityInClass(5)
	public void VerifySharedFilesStorageTimeRadioButton() throws Exception {
		setTestDesc("verify that 'Storage period.' Time radio button default value is displayed  ");
		sharedFolder.OperationMenu().click();
		Thread.sleep(1000);
		sharedFolder.fileShare().click();
		Thread.sleep(1000);
		sharedFolder.getNumberOfTimesRadioBtn().click();
		sharedFolder.getSharedFilesTimeRadiobtn().sendKeys("10");
		assertEquals(sharedFolder.getErrorMessageFromSharedFilesMaximumdownloads().getText().trim(),
				getLocalizedMessage("NumberOfTimesRadio").trim());
		Thread.sleep(1000);
		sharedFolder.getSharedFilesCancelButtonLabel().click();
	}

	@Test(priority = 6, groups = { "StringVerification", "UI" }, enabled = false)

	@PriorityInClass(3)
	public void VerifySharedFilesStorageDayRadioButton() throws Exception {
		setTestDesc(" verify that 'Storage period.' Number of days radio button default value is displayed ");

		sharedFolder.OperationMenu().click();
		Thread.sleep(1000);
		sharedFolder.fileShare().click();
		Thread.sleep(1000);
		sharedFolder.getNumberOfDaysRadioBtn().click();

		assertEquals(sharedFolder.getNumberOfDaysFromRadioBtn().getText(),
				getLocalizedMessage("NumberOfDaysRadio").trim());
		Thread.sleep(1000);
		sharedFolder.getSharedFilesCancelButtonLabel().click();

	}

	@Test(priority = 7, groups = { "StringVerification", "UI" })

	@PriorityInClass(4)
	public void VerifySharedFilesDownloadpasswordLabel() throws Exception {

		setTestDesc(" verify that 'Download password' label is displayed ");

		sharedFolder.OperationMenu().click();
		Thread.sleep(1000);
		sharedFolder.fileShare().click();
		Thread.sleep(1000);
		assertEquals(sharedFolder.getSharedFilesDownloadpasswordLabel().getText().trim(),
				getLocalizedMessage("Downloadpasswordlabel").trim());

		Thread.sleep(1000);
		sharedFolder.getSharedFilesCancelButtonLabel().click();

	}

	@Test(priority = 8, groups = { "Functional", "UI" })

	@PriorityInClass(4)
	public void VerifyErrorMessageSharedFilesDownloadpasswordCheckboxselection() throws Exception {

		setTestDesc(" Verify Error Message when user selected Download password Checkbox ");

		sharedFolder.OperationMenu().click();
		Thread.sleep(1000);
		sharedFolder.fileShare().click();
		Thread.sleep(1000);
		sharedFolder.getSharedFilesDownloadpasswordLabel().click();
		Thread.sleep(1000);
		assertEquals(sharedFolder.getPasswordErrorMsg().getText().trim(),
				getLocalizedMessage("DownloadpasswordErrormsg").trim());
		Thread.sleep(1000);
		sharedFolder.getSharedFilesCancelButtonLabel().click();

	}

	@Test(priority = 9, groups = { "StringVerification", "UI" })

	@PriorityInClass(5)
	public void VerifySharedFilesMaximumdownloadsLabel() throws Exception {
		setTestDesc(" verify that 'Download password' label is displayed ");

		sharedFolder.OperationMenu().click();
		Thread.sleep(1000);
		sharedFolder.fileShare().click();
		Thread.sleep(1000);
		assertEquals(sharedFolder.getSharedFilesMaximumdownloadsLabel().getText().trim(),
				getLocalizedMessage("Maximumdownloads").trim());

		Thread.sleep(1000);
		sharedFolder.getSharedFilesCancelButtonLabel().click();
	}

	@Test(priority = 10, groups = { "StringVerification", "UI" }, enabled = false)
	public void VerifySharedFilesMaximumdownloadscountByDefault() throws Exception {
		setTestDesc(" Verify Shared Files Maximum download count By Default ");
		sharedFolder.OperationMenu().click();
		Thread.sleep(1000);
		sharedFolder.fileShare().click();
		Thread.sleep(1000);
		assertEquals(sharedFolder.getSharedFilesMaximumdownloadscountByDefault().getText().trim(),
				getLocalizedMessage("MaximumdownloadscountbyDefault").trim());
		Thread.sleep(1000);
		sharedFolder.getSharedFilesCancelButtonLabel().click();
	}

	@Test(priority = 10, groups = { "StringVerification", "UI" }, enabled = true)
	public void VerifyErrorMessageWhenUserProvidesInvalidMaximumdownloadCount() throws Exception {
		setTestDesc(" Verify Error Message When User Provides Invalid Maximum download Count ");
		sharedFolder.OperationMenu().click();
		Thread.sleep(1000);
		sharedFolder.fileShare().click();
		Thread.sleep(1000);
		sharedFolder.getSharedFilesMaximumdownloadscountByDefault().sendKeys("10");
		assertEquals(sharedFolder.getErrorMessageFromSharedFilesMaximumdownloads().getText().trim(),
				getLocalizedMessage("getErrorMsgMaximumdownloads").trim());
		Thread.sleep(1000);
		sharedFolder.getSharedFilesCancelButtonLabel().click();
	}

	@Test(priority = 11, groups = { "Functional" }, alwaysRun = true)
	public void VerifySharedFilesCancelButtonLabel() throws Exception {
		setTestDesc(" verify that 'Cancel button' label is displayed ");

		sharedFolder.OperationMenu().click();
		Thread.sleep(1000);
		sharedFolder.fileShare().click();
		Thread.sleep(1000);
		assertEquals(sharedFolder.getSharedFilesCancelButtonLabel().getText().trim(),
				getLocalizedMessage("Cancelbtn").trim());
		sharedFolder.getSharedFilesCancelButtonLabel().click();
	}

	@Test(priority = 12, groups = { "Functional" }, enabled = true)
	public void VerifySharedFilesShareButtonLabel() throws Exception {
		setTestDesc("Verify that 'Share button' label is displayed ");
		sharedFolder.OperationMenu().click();
		Thread.sleep(1000);
		sharedFolder.fileShare().click();
		Thread.sleep(1000);
		assertEquals(sharedFolder.clickShareBtn().getText().trim(), getLocalizedMessage("sharebtn").trim());
		sharedFolder.getSharedFilesCancelButtonLabel().click();
	}

	@Test(priority = 13, groups = { "Functional" }, enabled = true)
	public void verifyFileShare() throws Exception {
		setTestDesc("Verify that Admin can share file from the context menu in the root folder. ");

		sharedFolder.OperationMenu().click();
		Thread.sleep(1000);
		sharedFolder.fileShare().click();
		Thread.sleep(1000);
		sharedFolder.clickShareBtn().click();
		Thread.sleep(4000);
		assertEquals(sharedFolder.shareFileHeader().getText().trim(), getLocalizedMessage("TempFileStorage"));
		Thread.sleep(1000);
		sharedFolder.shareOkBtn().click();
		Thread.sleep(1000);
	}

	@Test(priority = 14, groups = { "Functional" }, enabled = true)
	public void verifyShareFileOnSuccessMessage() throws Exception {
		setTestDesc("verify Share File OnSuccess Message ");

		sharedFolder.OperationMenu().click();
		Thread.sleep(1000);
		sharedFolder.fileShare().click();
		Thread.sleep(1000);
		sharedFolder.clickShareBtn().click();
		Thread.sleep(2000);
		assertEquals(sharedFolder.shareFileHeader().getText().trim(), getLocalizedMessage("TempFileStorage"));
		sharedFolder.shareOkBtn().click();
		Thread.sleep(1000);
	}

	@Test(priority = 15, groups = { "Functional" }, enabled = true)
	public void verifyHeaderShareFileOnSuccessMessage() throws Exception {
		setTestDesc("verify to get header from on success message Share File ");

		sharedFolder.OperationMenu().click();
		Thread.sleep(1000);
		sharedFolder.fileShare().click();
		Thread.sleep(1000);
		sharedFolder.clickShareBtn().click();
		Thread.sleep(2000);
		assertTrue(sharedFolder.getURLFromSharedFileOnSuccess().isDisplayed());
		sharedFolder.shareOkBtn().click();
		Thread.sleep(1000);
	}

	@Test(priority = 16, groups = { "Functional" }, enabled = true)
	public void verifyToolTipShareFileOnSuccessMessage() throws Exception {
		setTestDesc("verify to get tool tip from on success message Share File ");

		sharedFolder.OperationMenu().click();
		Thread.sleep(1000);
		sharedFolder.fileShare().click();
		Thread.sleep(1000);
		sharedFolder.clickShareBtn().click();
		Thread.sleep(2000);		
		boolean displayed = sharedFolder.getURLFromtooltipsSharedFileOnSuccess().isDisplayed();
		assertTrue(displayed);
		sharedFolder.shareOkBtn().click();
		Thread.sleep(1000);

	}

	@Test(priority = 17, groups = { "Functional" }, enabled = true)
	public void verifyFilenameShareFileOnSuccessMessage() throws Exception {
		setTestDesc("verify to get file name from on success message Share File ");

		sharedFolder.OperationMenu().click();
		Thread.sleep(1000);
		sharedFolder.fileShare().click();
		Thread.sleep(1000);
		sharedFolder.clickShareBtn().click();
		Thread.sleep(2000);		
		assertEquals(sharedFolder.getFileName().getText().trim(), getLocalizedMessage("FilenameONSuccessShareFile"));
		sharedFolder.shareOkBtn().click();
		Thread.sleep(1000);
	}

	@Test(priority = 18, groups = { "Functional" }, enabled = true)
	public void verifyExpiredDateShareFileOnSuccessMessage() throws Exception {
		setTestDesc("verify to get Expired date from on success message Share File ");

		sharedFolder.OperationMenu().click();
		Thread.sleep(1000);
		sharedFolder.fileShare().click();
		Thread.sleep(1000);
		sharedFolder.clickShareBtn().click();
		Thread.sleep(2000);

		assertEquals(sharedFolder.expirationDate().getText().trim(), "保存期限 " + sharedFolder.getTime() + " です。");
		sharedFolder.shareOkBtn().click();
		Thread.sleep(1000);
	}

	@Test(priority = 19, groups = { "Functional" }, enabled = true)
	public void verifydownloadCountShareFileOnSuccessMessage() throws Exception {
		setTestDesc("verify to get download Count from on success message Share File ");

		sharedFolder.OperationMenu().click();
		Thread.sleep(1000);
		sharedFolder.fileShare().click();
		Thread.sleep(1000);
		sharedFolder.clickShareBtn().click();
		Thread.sleep(2000);
		// to get download count
		assertEquals(sharedFolder.downloadCountfromOnSuccessMessage().getText().trim(),
				getLocalizedMessage("downloadCount"));

		sharedFolder.shareOkBtn().click();
		Thread.sleep(1000);
	}

	@Test(priority = 20, groups = { "Functional" }, enabled = true)
	public void verifySharedFileOnSuccessURL() throws Exception {
		setTestDesc("verify Share File OnSuccess Message ");

		String Filename = sharedFolder.getFileNamefromRow().getText().trim();
		String fileSize = sharedFolder.getFileSize().getText().trim();
		sharedFolder.OperationMenu().click();
		Thread.sleep(1000);
		sharedFolder.fileShare().click();
		Thread.sleep(1000);
		sharedFolder.clickShareBtn().click();
		Thread.sleep(2000);
		
		String copyURL = sharedFolder.getURLFromSharedFileOnSuccess().getText().trim();
		String downloadCount = sharedFolder.downloadCountfromOnSuccessMessage().getText().trim();
		String fileName = sharedFolder.getFileName().getText().trim();

		sharedFolder.shareOkBtn().click();
		Thread.sleep(1000);

		sharedFolder.OpenNewTab().click();
		getDriver().findElement(By.xpath("//body")).sendKeys(Keys.CONTROL + "+" + 't');
		getDriver().get(copyURL);
		Thread.sleep(1000);
		assertEquals(sharedFolder.getFileNameFromFileInfo().getText().trim(), Filename);
		assertEquals(sharedFolder.getFileSizeFromFileInfo().getText().trim(), fileSize);
		Thread.sleep(1000);
		sharedFolder.GetDownloadText().click();
		Thread.sleep(5000);
		assertEquals(sharedFolder.getdownloadCount().getText().trim(), getLocalizedMessage("downloadedOnce"));

		getDriver().navigate().back();

	}

	@Test(priority = 21, groups = { "Functional" }, enabled = true)
	public void verifyShareFileWithValidPassword() throws Exception {
		setTestDesc("verify Share File with password and download file");
		sharedFolder.OperationMenu().click();
		Thread.sleep(1000);
		sharedFolder.fileShare().click();
		Thread.sleep(2000);
		sharedFolder.getSharedFilesDownloadpasswordLabel().click();
		Thread.sleep(1000);
		sharedFolder.enterPassword().sendKeys(getLocalizedMessage("downloadPassword"));
		sharedFolder.clickShareBtn().click();
		Thread.sleep(2000);
		String copyURL = sharedFolder.getURLFromSharedFileOnSuccess().getText().trim();
		String downloadCount = sharedFolder.downloadCountfromOnSuccessMessage().getText().trim();
		sharedFolder.shareOkBtn().click();
		Thread.sleep(1000);
		sharedFolder.OpenNewTab().click();
		///getDriver().findElement(By.xpath("//body")).sendKeys(Keys.CONTROL + "+" + 't');
		getDriver().get(copyURL);
		Thread.sleep(1000);

		Thread.sleep(1000);
		sharedFolder.GetDownloadText().click();
		Thread.sleep(2000);
		sharedFolder.enterPassworToDownload().sendKeys(getLocalizedMessage("downloadPassword"));
		Thread.sleep(2000);
		sharedFolder.ClickOKButton().click();
		Thread.sleep(4000);
		assertEquals(sharedFolder.getdownloadCount().getText().trim(), getLocalizedMessage("downloadedOnce"));

		getDriver().navigate().back();

	}

	@Test(priority = 22, groups = { "Functional" }, enabled = true)
	public void verifyShareFilePasswordCancel() throws Exception {
		setTestDesc("verify that Share a File with password and click cancel button from download password diloag ");
		sharedFolder.OperationMenu().click();
		Thread.sleep(1000);
		sharedFolder.fileShare().click();
		Thread.sleep(2000);
		sharedFolder.getSharedFilesDownloadpasswordLabel().click();
		Thread.sleep(1000);
		sharedFolder.enterPassword().sendKeys(getLocalizedMessage("downloadPassword"));
		sharedFolder.clickShareBtn().click();
		Thread.sleep(2000);	
		String copyURL = sharedFolder.getURLFromSharedFileOnSuccess().getText().trim();
		String downloadCount = sharedFolder.downloadCountfromOnSuccessMessage().getText().trim();
		sharedFolder.shareOkBtn().click();
		Thread.sleep(1000);
		sharedFolder.OpenNewTab().click();
		getDriver().findElement(By.xpath("//body")).sendKeys(Keys.CONTROL + "+" + 't');
		getDriver().get(copyURL);
		Thread.sleep(1000);

		sharedFolder.GetDownloadText().click();

		Thread.sleep(2000);

		sharedFolder.closePasswordMessagedialog().click();

		getDriver().navigate().back();

	}

	@Test(priority = 23, groups = { "Functional" }, enabled = true)
	public void verifyShareFileWithInvalidPassword() throws Exception {
		setTestDesc(
				"verify Error message that Share a File with password and download the same file with invalid password");
		sharedFolder.OperationMenu().click();
		Thread.sleep(1000);
		sharedFolder.fileShare().click();
		Thread.sleep(2000);
		sharedFolder.getSharedFilesDownloadpasswordLabel().click();
		Thread.sleep(1000);
		sharedFolder.enterPassword().sendKeys(getLocalizedMessage("downloadPassword"));
		sharedFolder.clickShareBtn().click();
		Thread.sleep(2000);		
		String copyURL = sharedFolder.getURLFromSharedFileOnSuccess().getText().trim();
		String downloadCount = sharedFolder.downloadCountfromOnSuccessMessage().getText().trim();
		sharedFolder.shareOkBtn().click();
		Thread.sleep(1000);
		sharedFolder.OpenNewTab().click();
		getDriver().findElement(By.xpath("//body")).sendKeys(Keys.CONTROL + "+" + 't');
		getDriver().get(copyURL);
		Thread.sleep(1000);

		sharedFolder.GetDownloadText().click();
		Thread.sleep(2000);
		sharedFolder.enterPassworToDownload().sendKeys("downloadPassword");
		Thread.sleep(2000);
		sharedFolder.ClickOKButton().click();
		Thread.sleep(4000);
		assertEquals(sharedFolder.getdownloadErrorMessage().getText().trim(),
				getLocalizedMessage("downloadedErrormessage"));
		Thread.sleep(1000);
		sharedFolder.closeErrorMessagedialog().click();
		Thread.sleep(1000);
		sharedFolder.closePasswordMessagedialog().click();

		getDriver().navigate().back();

	}

}
