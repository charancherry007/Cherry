package com.CommonUtilities;

import java.text.NumberFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Utility {

	/**
	 * This class contains all the required methods for automating. It contains
	 * methods to Specifying browser Navigating to url,click, enterText, wait until
	 * visible, wait until clickable, get Text, Handling iFrames, Storing values and
	 * getting those values. Actions are also used to perform keyboard actions.
	 * All the methods in this class contain common @param
	 * which is String XPath.
	 */

	private WebDriver driver;
	private Actions actions;
	private WebDriverWait wait;
	private JavascriptExecutor js;
	private Map<String, Object> storeValue = new HashMap<>();

	/**
	 * Method to set the browser and navigate to url.
	 * 
	 * @param browserName
	 * @param url
	 * 
	 */
	public void navigateTo(String browserName, String url) {
		switch (browserName.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		case "ie":
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		default:
			throw new IllegalArgumentException("Unsupported browser: " + browserName);
		}
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		this.actions = new Actions(driver);
		this.js = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		driver.get(url);
		waitUntilPageLoaded();
	}

	/**
	 * Method to find an Element
	 * 
	 * @param xpath type @return element.
	 */
	public WebElement locateElement(String xpath) {
		WebElement element = driver.findElement(By.xpath(xpath));
		return element;
	}

	/**
	 * Method to wait for an element to clickable
	 * 
	 * @param xpath Type @return boolean.
	 */
	public WebElement waitUntilElementClickable(String xpath) {
		waitUntilElementVisible(xpath);
		return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
	}

	/**
	 * Method to wait for an element to be visible
	 * 
	 * @param xpath Type @return boolean.
	 */
	public WebElement waitUntilElementVisible(String xpath) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}

	/**
	 * Method to click an element.
	 * 
	 * @param xpath String.
	 */
	public void clickOn(String xpath) {
		waitUntilElementClickable(xpath);
		WebElement element = locateElement(xpath);
		element.click();
	}

	/**
	 * Method to enter text into an input field.
	 * 
	 * @param xpath String.
	 * @param text  String.
	 */
	public void enterText(String xpath, String text) {
		waitUntilElementClickable(xpath);
		WebElement element = locateElement(xpath);
		element.clear();
		element.sendKeys(text);
	}

	/**
	 * Method to get the text of an element
	 * 
	 * @param xpath Method type @return String element text.
	 */
	public String getText(String xpath) {
		waitUntilElementVisible(xpath);
		WebElement element = locateElement(xpath);
		return element.getText();
	}

	/**
	 * Retrieves all iFrames on the page and prints their indices and names/IDs.
	 */
	public void listIframes() {
		List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
		System.out.println("Found " + iframes.size() + " iframes on the page:");
		for (int i = 0; i < iframes.size(); i++) {
			WebElement iframe = iframes.get(i);
			String name = iframe.getAttribute("name");
			if (name == null || name.isEmpty()) {
				name = iframe.getAttribute("id");
			}
			System.out.println("Index: " + i + ", Name/ID: " + (name != null ? name : "N/A"));
		}
	}

	/**
	 * Switches to an iFrame using either its index or name/ID.
	 * 
	 * @param identifier The index (Integer) or name/ID (String) of the iFrame to
	 *                   switch to.
	 */
	public void switchToIframe(Object identifier) {
		if (identifier instanceof Integer) {
			int index = (Integer) identifier;
			List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
			if (index >= 0 && index < iframes.size()) {
				driver.switchTo().frame(iframes.get(index));
			} else {
				throw new IndexOutOfBoundsException("No iframe found at index: " + index);
			}
		} else if (identifier instanceof String) {
			String name = (String) identifier;
			driver.switchTo().frame(name);
		} else {
			throw new IllegalArgumentException("Identifier must be an Integer (index) or String (name).");
		}
	}

	/**
	 * Switch back to the default content from the iFrame.
	 */
	public void switchToDefault() {
		driver.switchTo().defaultContent();
	}

	/**
	 * Switch to a tab using either its index or title.
	 *
	 * @param identifier The index (Integer) or title (String) of the tab to switch
	 *                   to.
	 */
	public void switchToTab(Object identifier) {
		List<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		if (identifier instanceof Integer) {
			int index = (Integer) identifier;
			if (index >= 0 && index < tabs.size()) {
				driver.switchTo().window(tabs.get(index));
				System.out.println("Switched to tab at index: " + index);
			} else {
				throw new IndexOutOfBoundsException("No tab found at index: " + index);
			}
		} else if (identifier instanceof String) {
			String title = (String) identifier;
			for (String tab : tabs) {
				driver.switchTo().window(tab);
				if (driver.getTitle().equals(title)) {
					System.out.println("Switched to tab with title: " + title);
					return;
				}
			}
			throw new IllegalArgumentException("No tab found with title: " + title);
		} else {
			throw new IllegalArgumentException("Identifier must be an Integer (index) or String (title).");
		}
	}

	/**
	 * Method to scroll into View for an element using JavaScript
	 * 
	 * @param xpath as String
	 */
	public void jsScrollToElement(String xpath) {
		waitUntilElementVisible(xpath);
		WebElement element = locateElement(xpath);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	/**
	 * Method to click on element using JavaScript
	 * 
	 * @param xpath as String
	 */
	public void jsClickElement(String xpath) {
		jsScrollToElement(xpath);
		WebElement element = locateElement(xpath);
		js.executeScript("arguments[0].click();", element);
	}

	/**
	 * Method to store values based on data type We have used US Numbering format
	 * change it as required.
	 * 
	 * @param value      which is the value need to stored.
	 * @param objectName which is decleared as referance name.
	 */
	public void saveValue(String objectName, Object value) {
		String trimmedValue = ((String) value).trim();
		trimmedValue = trimmedValue.replaceAll("[$₹]", "");
		value = trimmedValue;
		if (value instanceof Number) {
			NumberFormat formatNumber = NumberFormat.getInstance(Locale.US);
			value = formatNumber.format(value);
			storeValue.put(objectName, trimmedValue);
		} else if (value instanceof String) {
			storeValue.put(objectName, trimmedValue);
		} else {
			throw new IllegalArgumentException("Unsupported type: " + value.getClass().getName());
		}
	}

	/**
	 * Method to get the stored value with objectName.
	 * 
	 * @param objectName which is a String decleared in the saveValue method.
	 * @return
	 */
	public Object getValue(String objectName) {
		if (storeValue.containsKey(objectName)) {
			System.out.println("The value of " + objectName + " is: " + storeValue.get(objectName));
			return storeValue.get(objectName);
		} else {
			throw new IllegalArgumentException("No value found for the objectName: " + objectName);
		}
	}

	/**
	 * This method will move to the element and perform click action using Actions.
	 * 
	 * @param xpath which is a String.
	 */
	public void clickAction(String xpath) {
		actions.moveToElement(locateElement(xpath)).click().build().perform();
	}

	/**
	 * This method will mimic the keyboard actions.
	 * 
	 * @param keys String
	 */
	public void keyboardActions(String keys) {
		String[] keyArray = keys.split(" ");
		for (String key : keyArray) {
			switch (key.toLowerCase()) {
			case "enter":
				actions.sendKeys(Keys.ENTER).perform();
				break;
			case "tab":
				actions.sendKeys(Keys.TAB).perform();
				break;
			case "backspace":
				actions.sendKeys(Keys.BACK_SPACE).perform();
				break;
			case "ctrl+a":
				actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
				break;
			case "ctrl+c":
				actions.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();
				break;
			case "ctrl+v":
				actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();
				break;
			case "shift":
				actions.keyDown(Keys.SHIFT).perform();
				break;
			case "shift_release":
				actions.keyUp(Keys.SHIFT).perform();
				break;
			default:
				actions.sendKeys(key).perform();
				break;
			}
		}
	}

	/**
	 * This method will wait until the DOM is loaded completly.
	 */
	public Boolean waitUntilPageLoaded() {
		return ((JavascriptExecutor) js).executeScript("return document.readyState").equals("complete");
	}
}