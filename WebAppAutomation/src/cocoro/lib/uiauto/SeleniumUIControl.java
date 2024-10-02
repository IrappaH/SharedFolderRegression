package cocoro.lib.uiauto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.isomorphic.webdriver.ByScLocator;

public class SeleniumUIControl implements UIControl {

	private WebElement uiElement = null;
	private WebDriver driver = null;
	Actions actions = null;
	private Map<String, String> idMap;

	public SeleniumUIControl(WebElement uiElement, WebDriver driver, Map<String, String> idMap) {
		this.uiElement = uiElement;
		this.driver = driver;
		this.idMap = idMap;
		actions = new Actions(driver);
	}
	
	public static UIControl find(WebDriver driver, Map<String, String> idMap) throws Exception {
		return new SeleniumUIControl(identify(driver, idMap).get(0), driver, idMap);
	}

	public static UIControl find(WebElement rootElem, Map<String, String> idMap) throws Exception {
		return new SeleniumUIControl(identifyByParent(rootElem, idMap).get(0), null, idMap);
	}

	public static List<UIControl> findAll(WebDriver driver, Map<String, String> idMap) throws Exception {
		return convertToUIControls(identify(driver, idMap), driver, idMap);
	}

	public static List<UIControl> findAll(WebElement rootElem, Map<String, String> idMap) throws Exception {
		return convertToUIControls(identifyByParent(rootElem, idMap), null, idMap);
	}

	// Identification of controls
	private static List<WebElement> identifyByParent(SearchContext finder, Map<String, String> idMap) throws Exception {
		long start = System.currentTimeMillis() / 1000;
		long current = start;
		List<WebElement> locatorElement = null;
		while (current - start < 60) {
			if (idMap.containsKey("css")) {
				locatorElement = finder.findElements(By.cssSelector(idMap.get("css")));
			} else if (idMap.containsKey("sclocator")) {
				locatorElement = finder.findElements(ByScLocator.scLocator(idMap.get("sclocator")));
			} else if (idMap.containsKey("id")) {
				locatorElement = finder.findElements(By.id(idMap.get("id")));
			} else if (idMap.containsKey("xpath")) {
				locatorElement = finder.findElements(By.xpath(idMap.get("xpath")));
			} else if (idMap.containsKey("name")) {
				locatorElement = finder.findElements(By.name(idMap.get("name")));
			}
			if ((locatorElement != null) && (locatorElement.size() != 0)) {
				break;
			} else {
				Thread.sleep(500);
				current = System.currentTimeMillis() / 1000;
			}
		}

		if ((locatorElement != null) && (locatorElement.size() != 0)) {
			return locatorElement;
		} else {
			throw new Exception(String.format("Not able to identify element for id map: %s", idMap));
		}

		// Implement the Logic to identify using eleminfo
		// this.element = After Finding
	}

	private static List<WebElement> identify(WebDriver driver, Map<String, String> idMap) throws Exception {

		/*Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
*/
		WebDriverWait wait = new WebDriverWait(driver, 30);
			   
		long start = System.currentTimeMillis() / 1000;
		long current = start;
		List<WebElement> locatorElement = null;
		while (current - start < 60) {
			if (idMap.containsKey("css")) {				
				locatorElement = wait.
						until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(idMap.get("css"))));
			} else if (idMap.containsKey("sclocator")) {
				locatorElement = wait.until(ExpectedConditions
						.presenceOfAllElementsLocatedBy(ByScLocator.scLocator(idMap.get("sclocator"))));
			} else if (idMap.containsKey("id")) {
				locatorElement = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(idMap.get("id"))));
			} else if (idMap.containsKey("xpath")) {
				locatorElement = wait
						.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(idMap.get("xpath"))));
			} else if (idMap.containsKey("name")) {
				locatorElement = wait
						.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.name(idMap.get("name"))));
			}

			if ((locatorElement != null) && (locatorElement.size() != 0)) {
				break;
			} else {
				Thread.sleep(500);
				current = System.currentTimeMillis() / 1000;
			}
		}

		if ((locatorElement != null) && (locatorElement.size() != 0)) {
			return locatorElement;
		} else {
			throw new Exception(String.format("Not able to identify element for id map: %s", idMap));
		}
	}

	// Implement the Logic to identify using eleminfo
	// this.element = After Finding

	private static List<UIControl> convertToUIControls(List<WebElement> elements, WebDriver driver,
			Map<String, String> idMap) {
		List<UIControl> out = new ArrayList<UIControl>();
		for (WebElement element : elements) {
			out.add(new SeleniumUIControl(element, driver, idMap));
		}

		return out;
	}

	@Override
	public void click() throws Exception {
		int attempts = 0;
		while (attempts < 3) {
			try {
		 this.uiElement.click();
		 break;
			} catch (StaleElementReferenceException e) {
				attempts++;
				this.uiElement = identify(driver, idMap).get(0);
			}catch (WebDriverException e) {
				attempts++;
				this.uiElement = identify(driver, idMap).get(0);
			}
		}
		if(attempts==3) {
		throw new Exception("Failed to retrieve attibute post stale element exception and 2 retries.");
		}
	}
	
	@Override
	public void clickAction() {
		new WebDriverWait(driver, 20).ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.elementToBeClickable(this.uiElement));
		if (this.uiElement.isDisplayed() && this.uiElement.isEnabled()) {
			Actions actions = new Actions(driver);
			actions.moveToElement(uiElement);
			actions.click(uiElement);
			Action action = actions.build();
			action.perform();
	//		JavascriptExecutor executor = (JavascriptExecutor)driver;
	//		executor.executeScript("arguments[0].click();", uiElement);
		}
	}

	@Override
	public void sendKeys(String value) throws Exception {
		int attempts = 0;
		while (attempts < 3) {
			try {
				this.uiElement.sendKeys(value);
				Thread.sleep(2000);
				break;
			} catch (StaleElementReferenceException e) {
				attempts++;
				this.uiElement = identify(driver, idMap).get(0);
			}
		}
		if(attempts==3) {
		throw new Exception("Failed to retrieve attibute post stale element exception and 2 retries.");
		}
	}

	@Override
	public void sendKeys(Keys keys) {
		
		Actions actn = new Actions(driver);
        actn.sendKeys(keys).build().perform();
	}

	public void dragAndDrop(UIControl sourceLocator) throws Exception 
	{
		int attempts = 0;
		while (attempts < 2) {
			try {
		Actions action = new Actions(driver);
		action.clickAndHold(sourceLocator.getRawWebElement());
		action.moveToElement(this.uiElement);
		action.release(sourceLocator.getRawWebElement());
		action.build().perform();
		break;
//		action.dragAndDrop(sourceLocator.getRawWebElement(), this.uiElement).build().perform();
			} catch (StaleElementReferenceException e) {
				attempts++;
				this.uiElement = identify(driver, idMap).get(0);
			}
		}
		if(attempts==3) {
		throw new Exception("Failed to retrieve attibute post stale element exception and 2 retries.");
		}
	}
	
	@Override
	public boolean exists() throws Exception {
		int attempts = 0;
		while (attempts < 3) {
			try {
		return this.uiElement.isDisplayed();} catch (StaleElementReferenceException e) {
			attempts++;
			this.uiElement = identify(driver, idMap).get(0);
		}
	}
	throw new Exception("Failed to retrieve attibute post stale element exception and 2 retries.");
	}

	@Override
	public WebElement getRawWebElement() {
		return this.uiElement;
	}

	@Override
	public String getText() throws Exception {
		int attempts = 0;
		while (attempts < 3) {
			try {
		return this.uiElement.getText();
			} catch (StaleElementReferenceException e) {
				attempts++;
				this.uiElement = identify(driver, idMap).get(0);
			}
		}
		throw new Exception("Failed to retrieve attibute post stale element exception and 2 retries.");
	}

	@Override
	public boolean isEnabled() {
		return this.uiElement.isEnabled();
	}
	
	@Override
	public boolean isSelected() {
		return this.uiElement.isSelected();
	}

	@Override
	public boolean isDisplayed() throws Exception {
		int attempts = 0;
		while (attempts < 2) {
			try {
		return this.uiElement.isDisplayed();} catch (StaleElementReferenceException e) {
			attempts++;
			this.uiElement = identify(driver, idMap).get(0);
			}
		}
		throw new Exception("Failed to retrieve attibute post stale element exception and 2 retries.");
	}

	@Override
	public String getAttribute(String attributeName) throws Exception {	
		int attempts = 0;
		while (attempts < 2) {
			try {
				return this.uiElement.getAttribute(attributeName);
			} catch (StaleElementReferenceException e) {
				attempts++;
				this.uiElement = identify(driver, idMap).get(0);
			}
		}
		throw new Exception("Failed to retrieve attibute post stale element exception and 2 retries.");
	}

	@Override
	public void clear() throws Exception {
		int attempts = 0;
		while (attempts < 2) {
			try {
				this.uiElement.clear();
				break;
			} catch (StaleElementReferenceException e) {
				attempts++;
				this.uiElement = identify(driver, idMap).get(0);
			}
		}
		if (attempts == 3) {
			throw new Exception("Failed to retrieve attibute post stale element exception and 2 retries.");
		}
	}

	@Override
	public void mouseOver() throws Exception {
		int attempts = 0;
		while (attempts < 2) {
			try {
				actions.moveToElement(this.uiElement).build().perform();
				break;
			} catch (StaleElementReferenceException e) {
				attempts++;
				this.uiElement = identify(driver, idMap).get(0);
			}
		}
		if(attempts==3) {
		throw new Exception("Failed to retrieve attibute post stale element exception and 2 retries.");
		}
	}

	@Override
	public void doubleClick() {
		actions.doubleClick((this.uiElement)).build().perform();
	}
	
	@Override
	public void scrollIntoView() throws Exception {		
		int attempts = 0;
		while (attempts < 3) {
			try {
				((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
						this.uiElement);
		 break;
			} catch (StaleElementReferenceException e) {
				attempts++;
				this.uiElement = identify(driver, idMap).get(0);
			}
		}
		if(attempts==3) {
		throw new Exception("Failed to retrieve attibute post stale element exception and 2 retries.");
		}
//		
//		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
//				this.uiElement);
	}
	
	@Override
	public void refresh() {
		this.driver.navigate().refresh();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void waitForControlToDisappear() {
		FluentWait wait = new FluentWait(driver);
		wait.withTimeout(120, TimeUnit.SECONDS).pollingEvery(2, TimeUnit.SECONDS).ignoring(NullPointerException.class)
				.ignoring(TimeoutException.class).until(ExpectedConditions.invisibilityOf(this.uiElement));
	}
	
	}
