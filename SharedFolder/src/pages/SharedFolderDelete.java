package pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import cocoro.lib.uiauto.UIControl;
import cocoro.pages.BasePage;

public class SharedFolderDelete extends BasePage{

	public SharedFolderDelete(RemoteWebDriver driver) throws Exception {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public String getDeleteFolderFileLbl() throws Exception {
		return this.uiControl("deleteFolderFileLbl").getText().trim();
	}
	
	public String getIsItOkLbl() throws Exception {
		return this.uiControl("isItOkLbl").getText().trim();
	}
	
	public String getItTakesSeveralMinLbl() throws Exception {
		return this.uiControl("itTakesSeveralMinLbl").getText().trim();
	}
	
	public UIControl cancelBtn() throws Exception {
		return this.uiControl("cancelBtn");
	}
	
	public UIControl okBtn() throws Exception {
		return this.uiControl("okBtn");
	}

}
