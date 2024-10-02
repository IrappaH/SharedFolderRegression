package cocoro.tests;

import java.io.File;

import javax.swing.UIClientPropertyKey;

import cocoro.lib.loader.JsonLoader;
import cocoro.lib.uiauto.ElementMap;
import cocoro.lib.uiauto.UIControl;

public class TestExecutor {
	private static final String HomePageTabIcon1 = null;

	//private static String dir = System.getProperty("user.dir");
	
	public static void main(String[] args) throws Exception {	
		JsonLoader loader= new JsonLoader();
		loader.load("./Locators/HomePage.json");
		
							
	/*	
		HomePage home = new HomePage(DriverFactory.createDriver(c);
		UIControl gTreeRoot = home.uiControl("GroupTreeRoot");
		Section groupTree = home.section("GroupTree", gTreeRoot);
		home.uiControl("refreshButton").click();
		groupTree.uiControl("plusButton").click();*/
		//home.section("GroupTree").uiControl("plusButton").click();
	}

}
