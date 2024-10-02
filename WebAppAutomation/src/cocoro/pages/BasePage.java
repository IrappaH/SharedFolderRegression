package cocoro.pages;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.google.common.base.Enums;

import cocoro.lib.central.COCOROSingleton;
import cocoro.lib.loader.JsonLoader;
import cocoro.lib.loader.Loader;
import cocoro.lib.uiauto.Section;
import cocoro.lib.uiauto.SeleniumUIControl;
import cocoro.lib.uiauto.UIControl;
import cocoro.tabs.TabTypes;


public abstract class BasePage implements Page {
	//private User user;
	private RemoteWebDriver  driver = null;
	private Loader loader = null;
	protected int waitSmall;
	protected int waitMedium;
	protected int waitHigh;

	public BasePage(RemoteWebDriver  driver) throws Exception {
		//this.user = user;
		this.driver = driver;
		this.load();
		waitSmall = COCOROSingleton.INSTANCE.getSmallWaitTime();
		waitMedium = COCOROSingleton.INSTANCE.getMediumWaitTime();
		waitHigh = COCOROSingleton.INSTANCE.getHighWaitTime();
	}

	protected void load() throws Exception {
		//if (Enums.getIfPresent(TabTypes.class, this.getClass().getSimpleName()).isPresent()) {
			loader = new JsonLoader();
			System.out.println(" This is loader object: "+ this.getClass().getSimpleName());
			Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		    String browserName = cap.getBrowserName().toLowerCase();
		    System.out.println("this browser name: "+ browserName);
			// Here name has to be converted to full path of Locator file
			loader.load(System.getProperty("user.dir") + File.separator + ("Locators").toLowerCase() + File.separator
					+ this.getClass().getSimpleName() + ".json", browserName );
		//}
	}

	/*protected User getUser() {
		return this.user;
	}*/
	
	public void refresh() throws Exception {
		getDriver().navigate().refresh();
	}

	protected RemoteWebDriver getDriver() {
		return this.driver;
	}

	public UIControl uiControl(String name) throws Exception {
		return SeleniumUIControl.find(this.driver, this.loader.getElemMap().getInfo(name));
	}

	public List<UIControl> uiControls(String name) throws Exception {
		return SeleniumUIControl.findAll(this.driver, this.loader.getElemMap().getInfo(name));
	}
	
	public boolean isControlPresent(String name) {
		try {
			this.uiControl(name);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public Section section(String name) throws Exception {
		return new Section(name, this);
	}

	public Loader getLoader() {
		return loader;
	}

	/*protected List<String> getLocalizedMessage(String... ids) {
		return COCOROSingleton.INSTANCE.getLocalizedMessage(ids);
	}*/

	protected String getLocalizedMessage(String id) {
		return COCOROSingleton.INSTANCE.getLocalizedMessage(id);
	}

	public void navigateTo(String URL) {
		this.driver.get(URL);
	}

	public void quit() {
		driver.quit();
	}

	// Window related code can be used.//Like Switch To window
	public boolean checkWindowExist(String windowTitle, String moduleName) {
		List<String> winHandles = null;
		boolean successFlag = false;
		try {
			String originalWin = this.driver.getWindowHandle();
			long start = System.currentTimeMillis() / 1000;
			long current = start;
			while (current - start < 60) {
				winHandles = new ArrayList<String>(this.driver.getWindowHandles());
				for (int i = 0; i < winHandles.size(); i++) {
					this.driver.switchTo().window(winHandles.get(i));
					String Title = this.driver.getTitle();
					String currentWindowURL = this.driver.getCurrentUrl();
					if (Title.equalsIgnoreCase(windowTitle) && currentWindowURL.contains(moduleName)) {
						this.driver.close();
						successFlag = true;
						break;
					}
				}
				if (successFlag) {
					break;

				} else {
					Thread.sleep(500);
					current = System.currentTimeMillis() / 1000;
				}
			}
			this.driver.switchTo().window(originalWin);
			return successFlag;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean switchWindow(String windowTitle, String moduleName) {
		List<String> winHandles = null;
		boolean successFlag = false;
		try {
			long start = System.currentTimeMillis() / 1000;
			long current = start;
			while (current - start < 60) {
				winHandles = new ArrayList<String>(this.driver.getWindowHandles());
				for (int i = 0; i < winHandles.size(); i++) {
					this.driver.switchTo().window(winHandles.get(i));
					String Title = this.driver.getTitle();
					String currentWindowURL = this.driver.getCurrentUrl();
					if (Title.equalsIgnoreCase(windowTitle) && currentWindowURL.contains(moduleName)) {
						successFlag = true;
						break;
					}
				}
				if (successFlag) {
					break;

				} else {
					Thread.sleep(500);
					current = System.currentTimeMillis() / 1000;
				}
			}
			return successFlag;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean waitForControlToDisplay(String ctrlToCheck) throws Exception {
		long start = System.currentTimeMillis() / 1000;
		long current = start;

		while (current - start < 60) {
			List<UIControl> ctl = this.uiControls(ctrlToCheck);
			if (ctl.size()>0) {				
				Thread.sleep(1000);
				return true;
			} else {
				Thread.sleep(500);
				current = System.currentTimeMillis() / 1000;
			}
		}
		return false;
	}
	
	
	public boolean cntrlNotDisplyed(String cntrlToCheck) throws Exception {
		try {
			this.uiControl(cntrlToCheck).isDisplayed();
			return false;
		} catch (Exception e) {
			return true;
		}
	}
	
	public String changeDateFormat(String oldFormt , String newFormt , String dateString) throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat(oldFormt);
		Date d = sdf.parse(dateString);
		sdf.applyPattern(newFormt);
		return( sdf.format(d));
	}
	
	public boolean waitForControlNotToDisplay(String ctrlToCheck) throws Exception {
		long start = System.currentTimeMillis() / 1000;
		long current = start;
		boolean isCtrlExit = false;
		
		while (current - start < 120) {
			try {
				List<UIControl> ctl = this.uiControls(ctrlToCheck);
				if (ctl.size() < 1) {
					Thread.sleep(1000);
					isCtrlExit = true;
					break;					
				} else {
					Thread.sleep(500);
					current = System.currentTimeMillis() / 1000;					
				}				
			} 
			catch(TimeoutException e) {				
			}
			
		}
		return isCtrlExit;
	}
	
	
	public boolean waitForControlToDisplay(String ctrlToCheck , String value) throws Exception {
		long start = System.currentTimeMillis() / 1000;
		long current = start;

		while (current - start < 60) {
		
			if (this.uiControl(ctrlToCheck).getText().equals(getLocalizedMessage(value))) {				
				Thread.sleep(1000);
				return true;
			} else {
				Thread.sleep(500);
				current = System.currentTimeMillis() / 1000;
			}
		}
		return false;
	}
	
	public String getTime()
	{
		LocalDateTime currentDateTime = LocalDateTime.now();
        
        // Add 7 days to the current date
        LocalDateTime futureDateTime = currentDateTime.plusDays(7);
        
        // Round up the time to the next 30 minutes or the next hour
        LocalDateTime roundedFutureDateTime = roundUpToNext30OrHour(futureDateTime);
        
        // Format the output for readability
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        
        System.out.println("Current Date and Time: " + currentDateTime.format(formatter));
        System.out.println("Date and Time After 7 Days (Rounded Up): " + roundedFutureDateTime.format(formatter));
		return roundedFutureDateTime.format(formatter);
    }
    
    // Method to round the time up to the next 30 minutes or hour
    public static LocalDateTime roundUpToNext30OrHour(LocalDateTime dateTime) {
        int minutes = dateTime.getMinute();
        int hours = dateTime.getHour();
        
        // If minutes are greater than 0 but less than or equal to 30, round up to 30 minutes
        if (minutes > 0 && minutes <= 30) {
            minutes = 30;
        } 
        // If minutes are greater than 30, round up to the next hour
        else if (minutes > 30) {
            minutes = 0;
            hours = (hours + 1) % 24;  // Handle wrap-around at 24 hours
        } else {
            minutes = 0; // If it's exactly the hour (00), leave it as is
        }
        
        // Return the new rounded date and time
        return LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(), dateTime.getDayOfMonth(), hours, minutes);
    }
	
	
}

