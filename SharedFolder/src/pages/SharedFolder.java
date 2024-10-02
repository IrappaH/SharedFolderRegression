package pages;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.RemoteWebDriver;
import cocoro.lib.uiauto.UIControl;
import cocoro.pages.BasePage;

public class SharedFolder extends BasePage{

	public SharedFolder(RemoteWebDriver  driver) throws Exception {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	public UIControl getSharedFileDetailsHeader() throws Exception {
		return this.uiControl("getSharedFileDetailsHeader");
	}
	
	public UIControl getSharedFilesLabel() throws Exception {
		return this.uiControl("getSharedFilesLabel");
	}
	
	
	public UIControl getSharedFilesStorageLabel() throws Exception {
		return this.uiControl("getSharedFilesStorageLabel");
	}
	
	public UIControl getSharedFilesDownloadpasswordLabel() throws Exception {
		return this.uiControl("getSharedFilesDownloadpasswordLabel");
	}
	
	public UIControl getSharedFilesCancelButtonLabel() throws Exception {
		return this.uiControl("getSharedFilesCancelButtonLabel");
	}
	

	public UIControl getSharedFilesMaximumdownloadsLabel() throws Exception {
		return this.uiControl("getSharedFilesMaximumdownloadsLabel");
	}
	public UIControl renameInput() throws Exception {
		return this.uiControl("renameInput");
	}
	
	public UIControl getPasswordErrorMsg() throws Exception {
		return this.uiControl("getPasswordErrorMsg");
	}
	
	public void ClearValues() throws Exception
	{
		getNumberOfDaysFromRadioBtn().sendKeys(Keys.BACK_SPACE);
	}
	public UIControl getNumberOfDaysFromRadioBtn() throws Exception {
		return this.uiControl("getNumberOfDaysFromRadioBtn");
	}
	
	
	public UIControl getErrorMsgDays() throws Exception {
		return this.uiControl("getErrorMsgDays");
	}
	
	public UIControl getErrorMsgTimes() throws Exception {
		return this.uiControl("getErrorMsgTimes");
	}
	
	public UIControl getNumberOfTimesRadioBtn() throws Exception {
		return this.uiControl("getNumberOfTimesRadioBtn");
	}
	
	public UIControl getSharedFilesTimeRadiobtn() throws Exception {
		return this.uiControl("getSharedFilesTimeRadiobtn");
	}
	
	
	public UIControl getSharedFilesMaximumdownloadscountByDefault() throws Exception {
		return this.uiControl("getSharedFilesMaximumdownloadscountByDefault");
	}
	
	
	public UIControl getURLFromSharedFileOnSuccess() throws Exception {
		return this.uiControl("getURLFromSharedFileOnSuccess");
	}
	
	public UIControl getFileNamefromRow() throws Exception {
		return this.uiControl("getFileNamefromRow");
	}
	
	public UIControl getFileName() throws Exception {
		return this.uiControl("getFileName");
	}
	
	public UIControl GetDownloadText() throws Exception {
		return this.uiControl("GetDownloadText");
	}
	public UIControl OpenNewTab() throws Exception {
		return this.uiControl("OpenNewTab");
	}
	
	public UIControl enterPassworToDownload() throws Exception {
		return this.uiControl("enterPassworToDownload");
	}
	
	public UIControl ClickOKButton() throws Exception {
		return this.uiControl("ClickOKButton");
	}
	
	public UIControl enterPassword() throws Exception {
		return this.uiControl("enterPassword");
	}
	public UIControl getdownloadCount() throws Exception {
		return this.uiControl("getdownloadCount");
	}
	public UIControl getFileSizeFromFileInfo() throws Exception {
		return this.uiControl("getFileSizeFromFileInfo");
	}
	public UIControl getFileNameFromFileInfo() throws Exception {
		return this.uiControl("getFileNameFromFileInfo");
	}
	public UIControl getFileSize() throws Exception {
		return this.uiControl("getFileSize");
	}
	public UIControl getURLFromtooltipsSharedFileOnSuccess() throws Exception {
		return this.uiControl("getURLFromtooltipsSharedFileOnSuccess");
	}
	public UIControl downloadCountfromOnSuccessMessage() throws Exception {
		return this.uiControl("downloadCountfromOnSuccessMessage");
	}
	
	public UIControl expirationDate() throws Exception {
		return this.uiControl("expirationDate");
	}
	public UIControl getNumberOfTimesFromRadioBtn() throws Exception {
		return this.uiControl("getNumberOfTimesFromRadioBtn");
	}

	public UIControl getNumberOfDaysRadioBtn() throws Exception {
		return this.uiControl("getNumberOfDaysRadioBtn");
	}
	public UIControl getErrorMessageFromSharedFilesMaximumdownloads() throws Exception {
		return this.uiControl("getErrorMessageFromSharedFilesMaximumdownloads");
	}
	public UIControl getdownloadErrorMessage() throws Exception {
		return this.uiControl("getdownloadErrorMessage");
	}
	
	public UIControl closeErrorMessagedialog() throws Exception {
		return this.uiControl("closeErrorMessagedialog");
	}
	
	public UIControl closePasswordMessagedialog() throws Exception {
		return this.uiControl("closePasswordMessagedialog");
	}
	
	public UIControl searchTxtBox() throws Exception {
		return this.uiControl("searchTxtBox");
	}
	
	public String getSearchTxtBoxValue() throws Exception {
		return this.uiControl("searchTxtBox").getAttribute("value");
	}
	
	public UIControl searchButton() throws Exception {
		return this.uiControl("searchButton");
	}
	
	public UIControl homeLink() throws Exception {
		return this.uiControl("homeLink");
	}
	
	public String getSaredFolderName() throws Exception {
		return this.uiControl("saredFolderName").getText().trim();
	}
	public UIControl selectSinlgeCheckBox() throws Exception {
		return this.uiControl("selectSingleCheckBox");
	}
	
	public UIControl downloadBtn() throws Exception {
		return this.uiControl("downloadBtn");
	}
	
	public UIControl changeSaveDestinationBtn() throws Exception {
		return this.uiControl("changeSaveDestinationBtn");
	}
	
	public UIControl deleteBtn() throws Exception {
		return this.uiControl("deleteBtn");
	}
	
	public UIControl uploadBtn() throws Exception {
		return this.uiControl("uploadBtn");
	}
	
	public UIControl createNewFolderBtn() throws Exception {
		return this.uiControl("createNewFolderBtn");
	}
	
	public UIControl topDropDownBtn() throws Exception {
		return this.uiControl("topDropDownBtn");
	}
	
	public List getTopDropDownList() throws Exception {
		return this.uiControls("topDropDownList");
	}
	
	public String getTopPagination() throws Exception {
		return this.uiControl("topPagination").getText();
	}
	
	public UIControl topStatingPageBtn() throws Exception {
		return this.uiControl("topStatingPageBtn");
	}
	
	public UIControl topDeforePageBtn() throws Exception {
		return this.uiControl("topDeforePageBtn");
	}
	
	public UIControl topEndingPageBtn() throws Exception {
		return this.uiControl("topEndingPageBtn");
	}
	
	public UIControl topAfterPageBtn() throws Exception {
		return this.uiControl("topAfterPageBtn");
	}
	
	public UIControl selectAllCheckBox() throws Exception {
		return this.uiControl("selectAllCheckBox");
	}
	
	public List graph() throws Exception {
		return this.uiControls("headerList");
	}
	
	public List checkBoxList() throws Exception {
		return this.uiControls("checkBoxList");
	}
	
	public List nameList() throws Exception {
		return this.uiControls("nameList");
	}
	
	public List sizeList() throws Exception {
		return this.uiControls("sizeList");
	}
	
	public List updatedDateAndtimeList() throws Exception {
		return this.uiControls("updatedDateAndtimeList");
	}
	
	public List menuButtonList() throws Exception {
		return this.uiControls("menuButtonList");
	}
	
	public UIControl firstRowMenuBtn() throws Exception {
		return this.uiControl("firstRowMenuBtn");
	}
	
	public List rowMenuList() throws Exception {
		return this.uiControls("rowMenuList");
	}
	
	public UIControl footerDropDownBtn() throws Exception {
		return this.uiControl("footerDropDownBtn");
	}
	
	public List footerDropDownList() throws Exception {
		return this.uiControls("footerDropDownList");
	}
	
	public String getFooterPagination() throws Exception {
		return this.uiControl("footerPagination").getText();
	}
	
	public UIControl footerStatingPageBtn() throws Exception {
		return this.uiControl("footerStatingPageBtn");
	}
	
	public UIControl footerDeforePageBtn() throws Exception {
		return this.uiControl("footerDeforePageBtn");
	}
	
	public UIControl footerEndingPageBtn() throws Exception {
		return this.uiControl("footerEndingPageBtn");
	}
	
	public UIControl footerAfterPageBtn() throws Exception {
		return this.uiControl("footerAfterPageBtn");
	}
	public String getFileCount() throws Exception {
		return this.uiControl("fileCount").getText().trim();
	}
	public UIControl OperationMenu() throws Exception {
		return this.uiControl("operationMenu");
	}
	public UIControl deleteBtnfromContext() throws Exception {
		return this.uiControl("deletefrmContext");
	}
	public UIControl deleteMsgOkBtn() throws Exception {
		return this.uiControl("deleteMsgOkBtn");
	}
	public UIControl downloadFromContextBtn() throws Exception {
		return this.uiControl("downloadFrmContextBtn");
	}
	public UIControl renameFromContextBtn() throws Exception {
		return this.uiControl("renameFromContextBtn");
	}
	public UIControl renameOkBtn() throws Exception {
		return this.uiControl("renameOkBtn");
	}
	public UIControl openFilePreview() throws Exception {
		return this.uiControl("openFilePreview");
	}
	public UIControl selectSecondCheckBox() throws Exception {
		return this.uiControl("selectSecondCheckBox");
	}
	
	public UIControl selectSecondCheckBoxfile() throws Exception {
		return this.uiControl("selectSecondCheckBoxfile");
	}

	public UIControl SelectFileMovetoHome() throws Exception {
		return this.uiControl("selectFileMovetoHome");
	}
	
	
	public UIControl selectFileMovetoPesonalFolder() throws Exception {
		return this.uiControl("selectFileMovetoPesonalFolder");
	}
	

	public UIControl shareOkBtn() throws Exception {
		return this.uiControl("shareOkBtn");
	}
	
	public UIControl fileShare() throws Exception {
		return this.uiControl("fileShare");
	}
	
	public UIControl shareFileHeader() throws Exception {
		return this.uiControl("shareFileHeader");
	}
	
	public UIControl clickShareBtn() throws Exception {
		return this.uiControl("clickShareBtn");
	}
	
	
	public UIControl ClickUpArrowBtn() throws Exception {
		return this.uiControl("ClickUpArrowBtn");
	}
	
	public UIControl OpenCreatedSubFolder() throws Exception {
		return this.uiControl("OpenCreatedSubFolder");
	}

	public UIControl OpenCreatedFolder() throws Exception {
		return this.uiControl("OpenCreatedFolder");
	}
	
	public UIControl SelectFolderInSubLevel() throws Exception {
		return this.uiControl("SelectFolderInSubLevel");
	}
	
	
	public UIControl SelectFolderInSameLevel() throws Exception {
		return this.uiControl("SelectFolderInTheSameLevel");
	}
	
	public UIControl ErrorfolderNamelegnttoolong() throws Exception {
		return this.uiControl("folderNamelegnttoolong");
	}
	
	public UIControl errorMsginvalid() throws Exception {
		return this.uiControl("errorMsginvalid");
	}

	
	public UIControl fileClose() throws Exception {
		return this.uiControl("fileClose");
	}
	public UIControl changeSaveDestinationBtnfrmContext() throws Exception {
		return this.uiControl("changeSaveDestinationBtnFrmContext");
	}
	
	
	/*
	 * public UIControl renameInput() throws Exception { return
	 * this.uiControl("renameInput"); }
	 */
	
	public SharedFolderSearch gotoSearchSharedFolder(String value) throws Exception {
		this.uiControl("searchTxtBox").sendKeys(value);;
		this.uiControl("searchButton").click();
		Thread.sleep(2000);
		return new SharedFolderSearch(this.getDriver());
	}
	
	public SharedFolderCreation gotoSharedFolderCreation() throws Exception {
		this.uiControl("createNewFolderBtn").click();
		return new SharedFolderCreation(this.getDriver());
	}
	

	public SharedFolderRename gotoSharedFolderRename() throws Exception {
		this.uiControl("folderOperationMenu").click();
		return new SharedFolderRename(this.getDriver());
	}
	
	public SharedFolderRename gotoSharedFileRename() throws Exception {
		this.uiControl("fileOperationMenu").click();
		return new SharedFolderRename(this.getDriver());
	}
	
	public SharedFolderChangeSaveDestination gotoSharedFolderChangeSaveDestination() throws Exception {
		this.uiControl("changeSaveDestinationBtn").click();
		return new SharedFolderChangeSaveDestination(this.getDriver());
	}
	
	public SharedFolderDelete gotoSharedFolderDelete() throws Exception {
		this.uiControl("deleteBtn").click();
		Thread.sleep(2000);
		return new SharedFolderDelete(this.getDriver());
	}
	
	public UIControl SourceFolderNotDisplayed() throws Exception {
		return this.uiControl("SourceFolderNotDisplayed");
}
	
	
	public UIControl ClickParentFolder() throws Exception {
		return this.uiControl("ClickParentFolder");
}
	
	public UIControl ClickParentfolderBtn() throws Exception {
		return this.uiControl("ClickParentfolderBtn");
}
	
	public UIControl SaveChangeDestinatioDialogCancelBtn() throws Exception {
		return this.uiControl("SaveChangeDestinatioDialog");
}
	
	public UIControl secondRowName() throws Exception {
		return this.uiControl("secondRowName");
}

	public UIControl firstRowName() throws Exception {
		return this.uiControl("firstRowName");
}
	public static void uploadFileWithRobot(String imagePath) throws InterruptedException {
		Thread.sleep(3000);
		StringSelection stringSelection = new StringSelection(imagePath);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		robot.delay(250);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.delay(150);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	public UIControl getRootfolder() throws Exception {
		return this.uiControl("rootFoldername");
	}

	public UIControl getAppBuildVersion() throws Exception {
		return this.uiControl("appVersion");
		
	}
	public UIControl errorMsgOkBtn() throws Exception {
		return this.uiControl("errorOKbtn");
	}

	
	public UIControl errorMsg() throws Exception {
		return this.uiControl("errorMsgunsupport");
	}

	public UIControl getFilepathTooLongErrorMsg() throws Exception {
		
		return this.uiControl("getFilepathTooLongErrorMsg");
	}

	
	
	

	

	
	


}
