package pages;

import java.util.List;

import org.openqa.selenium.remote.RemoteWebDriver;

import cocoro.lib.uiauto.UIControl;
import cocoro.pages.BasePage;

public class SharedFolderSearch extends BasePage{

	public SharedFolderSearch(RemoteWebDriver driver) throws Exception {
		super(driver);
		// TODO Auto-generated constructor stub
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
	
	public UIControl returnBtn() throws Exception {
		return this.uiControl("returnBtn");
	}
	
	public UIControl homeLink() throws Exception {
		return this.uiControl("homeLink");
	}
	
	public String getSearchResultStr() throws Exception {
		return this.uiControl("searchResultStr").getText().trim();
	}
	
	public UIControl downloadBtn() throws Exception {
		return this.uiControl("downloadBtn");
	}
	
	public UIControl deleteBtn() throws Exception {
		return this.uiControl("deleteBtn");
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
}
