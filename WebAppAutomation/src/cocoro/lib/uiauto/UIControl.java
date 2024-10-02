package cocoro.lib.uiauto;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public interface UIControl {
	void click() throws Exception;
	void clickAction();
	void sendKeys(String value) throws Exception;
	boolean exists() throws Exception;
	String getText() throws Exception;
	WebElement getRawWebElement();
	boolean isEnabled();
	boolean isSelected();
	boolean isDisplayed() throws Exception;
	String getAttribute(String attributeName) throws Exception;
	void mouseOver() throws Exception;
	void clear() throws Exception;
	void doubleClick();
	void scrollIntoView() throws Exception;
	void sendKeys(Keys keys);
	void dragAndDrop(UIControl sourceLocator) throws Exception;
	void refresh();
	void waitForControlToDisappear();
	
}
