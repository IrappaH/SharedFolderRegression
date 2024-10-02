package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import cocoro.enums.ConfigType;
import cocoro.lib.uiauto.UIControl;
import cocoro.pages.BasePage;



public class LoginPage extends BasePage{

	public LoginPage( RemoteWebDriver  driver) throws Exception {
		super(driver);
		// TODO Auto-generated constructor stub
		
	}
	
	public String getLoginLbl() throws Exception {
		return this.uiControl("loginLbl").getText().trim();
	}
	
	public String getUserNameLbl() throws Exception {
		return this.uiControl("userNameLbl").getText();
	}
	
	public UIControl userNameTxtbox() throws Exception {
		return this.uiControl("userNameTxtBox");
	}
	
	public String getPasswordLbl() throws Exception {
		return this.uiControl("passwordLbl").getText();
	}
	
	public UIControl passwordTxtbox() throws Exception {
		return this.uiControl("passwordTxtbox");
	}
	
	public String getUserNameTxtboxValue() throws Exception {
		return this.uiControl("userNameTxtBox").getAttribute("value");
	}
	
	public String getPasswordTxtboxValue() throws Exception {
		return this.uiControl("passwordTxtbox").getAttribute("value");
	}
	
	public UIControl loginBtn() throws Exception {
		return this.uiControl("loginBtn");
	}
	
	public String getLoginBtnLbl() throws Exception {
		return this.uiControl("loginBtn").getAttribute("value");
	}
	
	public String getErrorTxt() throws Exception {
		return this.uiControl("errorMsg").getText();
	}
	
	public HomePage login() throws Exception {
		this.uiControl("LoginBtn").click();
		return new HomePage(this.getDriver());
	}

}
