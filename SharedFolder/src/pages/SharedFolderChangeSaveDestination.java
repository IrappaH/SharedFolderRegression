package pages;

import java.util.List;

import org.openqa.selenium.remote.RemoteWebDriver;

import cocoro.lib.uiauto.UIControl;
import cocoro.pages.BasePage;

public class SharedFolderChangeSaveDestination extends BasePage{

	public SharedFolderChangeSaveDestination(RemoteWebDriver driver) throws Exception {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public String getChangeSaveDestinationLbl() throws Exception {
		return this.uiControl("changeSaveDestinationLbl").getText().trim();
	}
	
	public UIControl homeBtn() throws Exception {
		return this.uiControl("homeBtn");
	}
	
	public String getDestinationName() throws Exception {
		return this.uiControl("destinationName").getText().trim();
	}
	
	public List getFolderList() throws Exception {
		return this.uiControls("folderList");
	}
	
	public UIControl cancelBtn() throws Exception {
		return this.uiControl("cancelBtn");
	}
	
	public UIControl okBtn() throws Exception {
		return this.uiControl("okBtn");
	}

	
}
