package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import cocoro.lib.uiauto.UIControl;
import cocoro.pages.BasePage;

public class HomePage extends BasePage {

	public HomePage(RemoteWebDriver driver) throws Exception {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public String getShareFolderLbl() throws Exception {
		return this.uiControl("sharedFolderLbl").getText();
	}

	public String getUserLbl() throws Exception {
		return this.uiControl("userLbl").getText().trim();
	}

	public UIControl logoutBtn() throws Exception {
		return this.uiControl("logoutBtn");
	}

	public String getOverviewLbl() throws Exception {
		return this.uiControl("overviewLbl").getText();
	}

	public UIControl homeLink() throws Exception {
		return this.uiControl("homeLink");
	}

	public String getTotalCapacityLbl() throws Exception {
		return this.uiControl("totalCapacityLbl").getText();
	}

	public String getTotalCapacityValue() throws Exception {
		return this.uiControl("totalCapacityValue").getText();
	}

	public String getTotalUsageLbl() throws Exception {
		return this.uiControl("totalUsageLbl").getText();
	}

	public String getTotalUsageValue() throws Exception {
		return this.uiControl("totalUsageValue").getText();
	}

	public String getRemainingCapacityLbl() throws Exception {
		return this.uiControl("remainingCapacityLbl").getText();
	}

	public String getRemainingCapacityValue() throws Exception {
		return this.uiControl("remainingCapacityValue").getText();
	}

	public UIControl graph() throws Exception {
		return this.uiControl("graph");
	}

	public String getTotalUsageValueBelowGraph() throws Exception {
		return this.uiControl("totalUsageValueBelowGraph").getText();
	}

	public String getTotalCapacityValueBelowGraph() throws Exception {
		return this.uiControl("totalCapacityValueBelowGraph").getText();
	}

	public String getYourUsageValue() throws Exception {
		return this.uiControl("yourUsageValue").getText();
	}

	public UIControl historyBtn() throws Exception {
		return this.uiControl("historyBtn");
	}

	public String getDownloadTrafficTotalUsageValue() throws Exception {
		return this.uiControl("downloadTrafficTotalUsageValue").getText();
	}

	public String getRemainingDownloadVolumeValue() throws Exception {
		return this.uiControl("remainingDownloadVolumeValue").getText();
	}

	public String getResetDate() throws Exception {
		return this.uiControl("resetDate").getText();
	}

	public String getFolderLbl() throws Exception {
		return this.uiControl("folderLbl").getText();
	}

	public UIControl hideOtherUserFolderCheckBox() throws Exception {
		return this.uiControl("hideOtherUserFolderCheckBox");
	}

	public UIControl checkTabColumn(String locater, String value) throws Exception {
		List<UIControl> allLocaters = this.uiControls(locater);
		for (UIControl locator : allLocaters) {
			if (locator.getText().trim().equals((value).trim())) {
				return locator;
			}
		}
		return null;
	}

	public PersonalFolder gotoPersonalFolder(String value) throws Exception {
		UIControl control = checkTabColumn("foldersList", value);
		control.click();
		return new PersonalFolder(this.getDriver());
	}

	public SharedFolder gotoSharedFolder() throws Exception {
		this.uiControl("shareFolderLbl").click();
		Thread.sleep(2000);
		return new SharedFolder(this.getDriver());
	}

	public PersonalFolder gotoPersonalFolder() throws Exception {
	//	waitForControlToDisplay();
		this.uiControl("personalFolderLbl").click();
		Thread.sleep(2000);
		return new PersonalFolder(this.getDriver());
	}

	public OtherUserPersonalFolder gotoOtherUserPersonalFolder() throws Exception {
		this.uiControl("otheruserpersonalFolderLbl").click();
		Thread.sleep(2000);
		return new OtherUserPersonalFolder(this.getDriver());
	}

}
