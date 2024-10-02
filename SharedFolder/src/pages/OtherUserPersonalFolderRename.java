package pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import cocoro.lib.uiauto.UIControl;
import cocoro.pages.BasePage;

public class OtherUserPersonalFolderRename extends BasePage {

	public OtherUserPersonalFolderRename(RemoteWebDriver driver) throws Exception {
		super(driver);	
	}
	

	public UIControl folderOperationMenu() throws Exception {
		return this.uiControl("folderOperationMenu");
	}

	public UIControl folderRenamebtn() throws Exception {
		return this.uiControl("folderRenamebtn");
	}

	public UIControl renameFolderTxtBox() throws Exception {
		return this.uiControl("renameFolderTxtBox");
	}
	
	public UIControl renameInput() throws Exception {
		return this.uiControl("renameInput");
	}
	
	public UIControl okBtn() throws Exception {
		return this.uiControl("okBtn");
	}
	
	
	public UIControl errorOKbtn() throws Exception {
		return this.uiControl("errorOKbtn");
	}
	
	public UIControl getErrorMsg() throws Exception {
		return this.uiControl("getErrMsg");
		
		
	}
	public UIControl getUnsupportErrMsg() throws Exception {
		return this.uiControl("getUnsupportErrMsg");
		
		
	}
	
	public Boolean IsDisplayedrenameOption() throws Exception {
		boolean displayed = folderRenamebtn().isDisplayed();
		Thread.sleep(1000);
		return displayed;
		
		
	}
	
	public void renameFolder(String value) throws Exception {
		folderRenamebtn().click();
		Thread.sleep(1000);
		renameInput().sendKeys(value);
		Thread.sleep(1000);
		okBtn().click();		
		Thread.sleep(1000);
		
	}
	
	public void renameFile(String value) throws Exception {
		folderRenamebtn().click();
		Thread.sleep(1000);
		renameInput().sendKeys(value);
		Thread.sleep(1000);
		okBtn().click();		
		Thread.sleep(1000);
		
	}
	
	public String renameFolderInvalid(String value) throws Exception {
		folderRenamebtn().click();
		Thread.sleep(1000);
		renameInput().sendKeys(value);
		Thread.sleep(1000);
		okBtn().click();		
		Thread.sleep(1000);
		String text = getUnsupportErrMsg().getText();
		return text;
	}
	
	


	public void ErrorOKBtn() throws Exception {
		errorOKbtn().click();
		Thread.sleep(1000);
	}

	public String renameFolderpathIsTooLong(String value) throws Exception {
		folderRenamebtn().click();
		Thread.sleep(1000);
		renameInput().sendKeys(value);
		Thread.sleep(1000);
		okBtn().click();		
		Thread.sleep(1000);
		String text = getErrorMsg().getText();
		return text;
		
	}

}
