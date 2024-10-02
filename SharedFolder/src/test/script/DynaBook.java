package test.script;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DynaBook {

	public static void main(String[] args) {
		System.out.println("Start");
		 // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();
        
        // Add 7 days to the current date
        LocalDateTime futureDateTime = currentDateTime.plusDays(7);
        
        // Round up the time to the next 30 minutes or the next hour
        LocalDateTime roundedFutureDateTime = roundUpToNext30OrHour(futureDateTime);
        
        // Format the output for readability
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        
        System.out.println("Current Date and Time: " + currentDateTime.format(formatter));
        System.out.println("Date and Time After 7 Days (Rounded Up): " + roundedFutureDateTime.format(formatter));
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
