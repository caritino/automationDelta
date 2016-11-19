package trainingSelenium;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.JavascriptExecutor;

public class Ejercicio5 {
	static int convertToInt(String s){
		if(s.equals(" ")){
			return 0;
		}else{
			return Integer.parseInt(s);
		}	
	}
	
	public static void main(String []args) throws InterruptedException{
// --- 	GOING INTO DELTA --- //
		System.setProperty("webdriver.chrome.driver", 
				"C:\\Users\\araba\\Downloads\\Hexaware\\Selenium\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
				
		String url = "http://es.delta.com/?lang=es&loc=us";
		driver.get(url);
		Actions builder = new Actions(driver);
		
		Thread.sleep(3000L);
		
		driver.findElement(By.xpath("//*[@id=\"srcCityLookup\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"country_tab\"]")).click();
		
		Thread.sleep(1000L);
		
		WebElement countryDeparture = driver.findElement(By.xpath("//*[@id=\"country_ul\"]/li[110]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", countryDeparture);
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id=\"country_ul\"]/li[110]")).click();
		
		Thread.sleep(1000L);
		
		WebElement airportDeparture = driver.findElement(By.xpath("//*[@id=\"airport_ul\"]/li[31]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", airportDeparture);
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id=\"airport_ul\"]/li[31]")).click();
		
		Thread.sleep(1000L);
		
		driver.findElement(By.xpath("//*[@id=\"destCityLookup\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"us_canada_tab\"]")).click();
		
		Thread.sleep(1000L);
		
		WebElement countryDestination = driver.findElement(By.xpath("//*[@id=\"stateProvince_ul\"]/li[12]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", countryDestination);
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id=\"stateProvince_ul\"]/li[12]")).click();
		Thread.sleep(1000L);
		driver.findElement(By.xpath("//*[@id=\"stateProvinceAirport_ul\"]/li[2]")).click();
		
		Thread.sleep(1000L);
		
		driver.findElement(By.xpath("//*[@id=\"depDateCalIcon\"]")).click();
		
		Calendar cal = new GregorianCalendar();
		java.util.Date date= new Date();
		cal.setTime(date);
		
		Date dateFromToday = cal.getTime();
		int dayFromToday = cal.get(Calendar.DAY_OF_MONTH);
		String monthFromToday= new SimpleDateFormat("MMM", new Locale("ES","ES")).format(cal.getTime());
		System.out.println(dateFromToday);
		System.out.println(dayFromToday);
		System.out.println(monthFromToday);
		
		cal.add(Calendar.DATE, 7);
		Date departureDate = cal.getTime();
		int departureDay = cal.get(Calendar.DAY_OF_MONTH);
		String departureMonth = new SimpleDateFormat("MMM", new Locale("ES","ES")).format(cal.getTime());
		System.out.println(departureDate);
		System.out.println(departureDay);
		System.out.println(departureMonth);
		
		cal.add(Calendar.DATE, 14);
		Date destinationDate = cal.getTime();
		int destinationDay = cal.get(Calendar.DAY_OF_MONTH);
		String destinationMonth =new SimpleDateFormat("MMM", new Locale("ES","ES")).format(cal.getTime());
		System.out.println(destinationDate);
		System.out.println(destinationDay);
		System.out.println(destinationMonth);
		
		Calendar monthPlusOne = Calendar.getInstance(); 
		monthPlusOne.add(Calendar.MONTH, 1);
		String getMonthPlusOne = new SimpleDateFormat("MMM", new Locale("ES","ES")).format(monthPlusOne.getTime());
		System.out.println("Month + 1: " + getMonthPlusOne);
		
		String departureMonth1 = driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[3]/div/div/span[1]")).getText();
		String departureMonth2 = driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[4]/div/div/span[1]")).getText();
		
		if(departureMonth1.toLowerCase().contains(monthFromToday.toLowerCase()) && departureMonth2.toLowerCase().contains(getMonthPlusOne.toLowerCase())){
			System.out.println("The months displayed in the web page are the same as the month from today and the next one");
		} else {
			driver.close();
		}
		
		WebElement departureCalendar1 = driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[3]/table/tbody"));
		WebElement departureCalendar2 = driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[4]/table/tbody"));
		List<WebElement> dayDepartureCalendar1 = departureCalendar1.findElements(By.tagName("td"));
		List<WebElement> dayDepartureCalendar2 = departureCalendar2.findElements(By.tagName("td"));

		if(departureMonth1.toLowerCase().contains(departureMonth.toLowerCase())){
			for(WebElement departureElement : dayDepartureCalendar1){
				if(departureDay == convertToInt(departureElement.getText())){
					System.out.println(departureElement.getText());
					departureElement.click();
					break;
				}
			}
		}else{
			for(WebElement departureElement : dayDepartureCalendar2){
				if(departureDay == convertToInt(departureElement.getText())){
					System.out.println(departureElement.getText());
					departureElement.click();
					break;
				}
			}
		}
		
		Thread.sleep(2000L);
				
		String destinationMonth1 = driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[3]/div/div/span[1]")).getText();
		String destinationMonth2 = driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[4]/div/div/span[1]")).getText();
		
		WebElement destinationCalendar1 = driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[3]/table/tbody"));
		WebElement destinationCalendar2 = driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[4]/table/tbody"));
		List<WebElement> dayDestinationCalendar1 = destinationCalendar1.findElements(By.tagName("td"));
		List<WebElement> dayDestinationCalendar2 = destinationCalendar2.findElements(By.tagName("td"));
		
		if(destinationMonth1.toLowerCase().contains(destinationMonth.toLowerCase())){
			for(WebElement destinationElement : dayDestinationCalendar1){
				if(destinationDay == convertToInt(destinationElement.getText())){
					System.out.println(destinationElement.getText());
					destinationElement.click();
					break;
				}
			}
		}else{
			for(WebElement destinationElement : dayDestinationCalendar2){
				if(destinationDay == convertToInt(destinationElement.getText())){
					System.out.println(destinationElement.getText());
					destinationElement.click();
					break;
				}
			}
		}
		
		Thread.sleep(2000L);
		driver.findElement(By.xpath("//*[@id=\"flexDaysBtn\"]/span")).click();
		
		Thread.sleep(1000L);
		driver.findElement(By.xpath("//*[@id=\"findFlightsSubmit\"]")).click();
		
		Thread.sleep(10000L);
			
		WebElement lookForLower = driver.findElement(By.xpath("//*[@id=\"_flexDateCalendarHolder\"]/table/tbody"));
		List<WebElement> listLookForLower = lookForLower.findElements(By.tagName("td"));
		
		for(WebElement lookForLowerElement : listLookForLower){
			if(lookForLowerElement.getText().contains("TARIFA M\u00c1S BAJA")){	
				System.out.println(lookForLowerElement.getText());
				lookForLowerElement.click();
				break;
			}		
		}
		
		Thread.sleep(2000L);
		
		WebElement lookForOutbound = driver.findElement(By.xpath("/html/body"));
		List<WebElement> listLookForOutbound = lookForOutbound.findElements(By.tagName("div"));
		
		for(WebElement lookForOutboundElement : listLookForOutbound){
			if(lookForOutbound.getText().contains("Outbound") && lookForOutbound.getText().contains("MTY") && lookForOutbound.getText().contains("ATL")){	
				System.out.println("Correct message");
				break;
			}
		}
		
	}
}
