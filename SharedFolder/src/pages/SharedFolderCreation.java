package pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import cocoro.lib.uiauto.UIControl;
import cocoro.pages.BasePage;

public class SharedFolderCreation extends BasePage{

	public SharedFolderCreation(RemoteWebDriver driver) throws Exception {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public String getCreateNewFolderLbl() throws Exception {
		return this.uiControl("createNewFolderLbl").getText().trim();
	}
	
	public UIControl createNewFolderTxtBox() throws Exception {
		return this.uiControl("createNewFolderTxtBox");
	}
	
	public String getCreateNewFolderTxtBox() throws Exception {
		return this.uiControl("createNewFolderTxtBox").getAttribute("value");
	}
	
	public UIControl cancelBtn() throws Exception {
		return this.uiControl("cancelBtn");
	}
	
	public UIControl okBtn() throws Exception {
		return this.uiControl("okBtn");
	}

	
	public void createFolder(String value) throws Exception {
		createNewFolderTxtBox().sendKeys(value);
		okBtn().click();
		Thread.sleep(2000);
	}
}
