package com.CommonUtilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Utility {

	/**
	 * This class contains all the required methods for automating. It contains
	 * methods to Specifying browser Navigating to url,click, enterText, wait until
	 * visible, wait until clickable, get Text, Handling iFrames, Storing values and
	 * getting those values. Actions are also used to perform keyboard actions. All
	 * the methods in this class contain common @param which is String XPath.
	 */

	private WebDriver driver;
	private Actions actions;
	private WebDriverWait wait;
	private WebElement element;
	private JavascriptExecutor js;
	private Properties properties;
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
		try {
			driver.get(url);
		} catch (Exception e) {
			driver.navigate().to(url);
		}
		waitUntilPageLoaded();
	}

	/**
	 * Method to find an Element
	 * 
	 * @param xpath type @return element.
	 */
	public WebElement locateElement(String xpath) {
		try {
			waitUntilElementVisible(xpath);
			element = driver.findElement(By.xpath(xpath));
		} catch (NoSuchElementException e) {
			System.out.println("There was no such element found with the given xpath : " + xpath);
		}
		return element;
	}

	/**
	 * Method to wait for an element to clickable
	 * 
	 * @param xpath Type @return boolean.
	 */
	public WebElement waitUntilElementClickable(String xpath) {
		try {
			waitUntilElementVisible(xpath);
		} catch (TimeoutException e) {
			System.out.println("There was no such element found with xpath : " + xpath
					+ " retried for 30 Seconds with 5 Seconds interval");
		}
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
		try {
			waitUntilElementClickable(xpath);
			element = locateElement(xpath);
			element.click();
		} catch (Exception e) {
			if (e instanceof ElementNotInteractableException) {
				jsScrollToElement(xpath);
				element.click();
				System.out.println("The element with xpath : " + xpath
						+ " was out of range. So the element was scrolled and clicked catching ElementNotInteractableException.");
			} else if (e instanceof StaleElementReferenceException) {
				System.out.println("The element with xpath : " + xpath
						+ " is no longer available in the DOM StaleElementReferenceException.");
			} else if (e instanceof ElementClickInterceptedException) {
				System.out.println("The click was interrupted for the element with xpath : " + xpath
						+ " causing ElementClickInterceptedException.");
			} else {
				System.out.println("The Exception " + e + " was not handeled.");
			}
		}
	}

	/**
	 * Method to enter text into an input field.
	 * 
	 * @param xpath String.
	 * @param text  String.
	 */
	public void enterText(String xpath, String text) {
		try {
			waitUntilElementClickable(xpath);
			element = locateElement(xpath);
			element.clear();
			element.sendKeys(text);
		} catch (Exception e) {
			if (e instanceof ElementNotInteractableException) {
				jsScrollToElement(xpath);
				element.clear();
				element.sendKeys(text);
				System.out.println("The element with xpath : " + xpath
						+ " was out of range. So the element was scrolled and clicked catching ElementNotInteractableException.");
			} else if (e instanceof StaleElementReferenceException) {
				System.out.println("The element with xpath : " + xpath
						+ " is no longer available in the DOM StaleElementReferenceException.");
			} else if (e instanceof ElementClickInterceptedException) {
				System.out.println("The click was interrupted for the element with xpath : " + xpath
						+ " causing ElementClickInterceptedException.");
			} else {
				System.out.println("The Exception " + e + " was not handeled.");
			}
		}
	}

	/**
	 * Method to get the text of an element
	 * 
	 * @param xpath Method type @return String element text.
	 */
	public String getText(String xpath) {
		String value = null;
		try {
			waitUntilElementVisible(xpath);
			element = locateElement(xpath);
			value = element.getText();
		} catch (Exception e) {
			if (e instanceof ElementNotInteractableException) {
				jsScrollToElement(xpath);
				value = element.getText();
				System.out.println("The element with xpath : " + xpath
						+ " was out of range. So the element was scrolled and clicked catching ElementNotInteractableException.");
			} else if (e instanceof StaleElementReferenceException) {
				System.out.println("The element with xpath : " + xpath
						+ " is no longer available in the DOM StaleElementReferenceException.");
			} else if (e instanceof ElementClickInterceptedException) {
				System.out.println("The click was interrupted for the element with xpath : " + xpath
						+ " causing ElementClickInterceptedException.");
			} else {
				System.out.println("The Exception " + e + " was not handeled.");
			}
		}
		return value;
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
		element = locateElement(xpath);
		try {
			js.executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (Exception e) {
			if (e instanceof JavascriptException) {
				System.out.println("There was a runtime error occured while performing click.");
			} else {
				System.out.println("Ther was an " + e + " Exception occured.");
			}
		}
	}

	/**
	 * Method to click on element using JavaScript
	 * 
	 * @param xpath as String
	 */
	public void jsClickElement(String xpath) {
		jsScrollToElement(xpath);
		element = locateElement(xpath);
		try {
			js.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			if (e instanceof JavascriptException) {
				System.out.println("There was a runtime error occured while performing click.");
			} else {
				System.out.println("Ther was an " + e + " Exception occured.");
			}
		}
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
			case "ctrl":
				actions.keyDown(Keys.CONTROL).perform();
				break;
			default:
				actions.sendKeys(" " + key).perform();
				break;
			}
		}
	}

	/**
	 * This method will wait until the DOM is loaded completly.
	 */
	public void waitUntilPageLoaded() {
		try {
			js.executeScript("return document.readyState").equals("complete");
		} catch (Exception e) {
			System.out.println("There was an error while waiting for readyState of page");
		}

	}

	/**
	 * Method to scroll a webpage recursively. Used for content that loads new.
	 * Element when we scroll down. Ex: youtube, instagram feed.
	 * 
	 * @param xpath String
	 */
	public void scrollRecursively(String xpath) {
		commonWait(5);
		try {
			List<WebElement> elements = driver.findElements(By.xpath(xpath));
			int i = elements.size();
			System.out.println("Initial size of elements: " + i);
			boolean conditionMet = false;
			while (!conditionMet) {
				if (elements.size() > 0) {
					WebElement lastElement = elements.get(elements.size() - 1);
					actions.moveToElement(lastElement).perform();
					js.executeScript("arguments[0].scrollIntoView(true);", lastElement);
					waitUntilPageLoaded();
					commonWait(5);
					elements = driver.findElements(By.xpath(xpath));
					if (elements.size() == i) {
						conditionMet = true;
					} else {
						i = elements.size();
						System.out.println("Iterated size of elements: " + i);
					}
				} else {
					System.out.println("No elements found with the given xpath.");
					break;
				}
			}
			System.out.println("Final size of elements: " + i);

		} catch (Exception e) {
			if (e instanceof NoSuchElementException) {
				System.out.println("There was no such element found with xpath : " + xpath);
			} else {
				System.out.println("There was an " + e + " Exception occured while scrolling recursively.");
			}
		}
	}

	/**
	 * Method to wait until specific period of time
	 * 
	 * @param seconds int
	 */
	public void commonWait(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * Method will return the specified property/attribute of an element.
	 * 
	 * @param xpath String
	 * 
	 * @return value String
	 */
	public String getProperty(String xpath, String attribute) {
		String value = null;
		WebElement element = locateElement(xpath);
		try {
			value = element.getAttribute(attribute);
		} catch (Exception e) {
			if (e instanceof StaleElementReferenceException) {
				System.out.println("The element with xpath : " + xpath
						+ " is no longer available in the DOM StaleElementReferenceException.");
			} else {
				System.out.println("There was an " + e + " Exception while fetching " + attribute + " value.");
			}
		}
		return value;
	}

	/**
	 * Method to execute javascript on an element.
	 * 
	 * @param xpath String
	 * @param args  String
	 */
	public void executeJS(String xpath, String arguments) {
		element = locateElement(xpath);
		try {
			js.executeScript(arguments, element);
		} catch (Exception e) {
			if (e instanceof JavascriptException) {
				System.out.println("There was a runtime error occured while performing " + arguments
						+ " check the snippet for syntax/errors.");
			} else {
				System.out.println("Ther was an " + e + " Exception occured.");
			}
		}
	}

	/**
	 * Method to close the browser and tabs.
	 */
	public void endSession() {
		if (driver != null) {
			driver.quit();
		}
	}

	/**
	 * Method to set the browser with set of options and navigate to url.
	 * 
	 * @param browserName
	 * @param url
	 * @param options
	 * 
	 */
	public void navigateWithArguments(String browserName, String url, Map<String, Object> options) {
		switch (browserName.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			if (options != null) {
				if (options.containsKey("incognito") && (boolean) options.get("incognito")) {
					chromeOptions.addArguments("--incognito");
				}
				if (options.containsKey("disableCache") && (boolean) options.get("disableCache")) {
					chromeOptions.addArguments("--disable-application-cache");
				}
				if (options.containsKey("headless") && (boolean) options.get("headless")) {
					chromeOptions.addArguments("--headless");
				}
			}
			driver = new ChromeDriver(chromeOptions);
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			EdgeOptions edgeOptions = new EdgeOptions();
			if (options != null) {
				if (options.containsKey("headless") && (boolean) options.get("headless")) {
					edgeOptions.addArguments("--headless");
				}
				if (options.containsKey("disableCache") && (boolean) options.get("disableCache")) {
					edgeOptions.addArguments("--disable-application-cache");
				}
			}
			driver = new EdgeDriver(edgeOptions);
			break;
		case "ie":
			WebDriverManager.iedriver().setup();
			InternetExplorerOptions ieOptions = new InternetExplorerOptions();
			if (options != null) {
				if (options.containsKey("ignoreZoomSetting") && (boolean) options.get("ignoreZoomSetting")) {
					ieOptions.requireWindowFocus();
				}
				if (options.containsKey("enablePersistentHover") && (boolean) options.get("enablePersistentHover")) {
					ieOptions.enablePersistentHovering();
				}
			}
			driver = new InternetExplorerDriver(ieOptions);
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			if (options != null) {
				if (options.containsKey("headless") && (boolean) options.get("headless")) {
					firefoxOptions.addArguments("--headless");
				}
				if (options.containsKey("disableCache") && (boolean) options.get("disableCache")) {
					firefoxOptions.addPreference("browser.cache.disk.enable", false);
					firefoxOptions.addPreference("browser.cache.memory.enable", false);
					firefoxOptions.addPreference("browser.cache.offline.enable", false);
				}
			}
			driver = new FirefoxDriver(firefoxOptions);
			break;
		default:
			throw new IllegalArgumentException("Unsupported browser: " + browserName);
		}
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		this.actions = new Actions(driver);
		this.js = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		try {
			driver.get(url);
		} catch (Exception e) {
			driver.navigate().to(url);
		}
		waitUntilPageLoaded();
	}

	/**
	 * Method to load the property file with path as String.
	 * 
	 * @param propertiesFilePath
	 */
	public void loadPropertyFile(String propertiesFilePath) {
		properties = new Properties();
		try (InputStream input = new FileInputStream(propertiesFilePath)) {
			properties.load(input);
		} catch (IOException e) {
			throw new RuntimeException("Unable to find properties file at given path : " + propertiesFilePath);
		}
	}

	/**
	 * Method will retrive the value from the property file and return its value
	 * based on the data type.
	 * 
	 * @param key
	 * @return
	 */
	public String getPropertyValue(String key) {
		String value = null;
		try {
			value = properties.getProperty(key);
			if (value == null || value.isEmpty()) {
				System.out.println("There is no value assigned for the key: " + key + " in the property file.");
				return null;
			} else {
				return value;
			}
		} catch (Exception e) {
			System.out.println("There is no key: " + key + " in the property file.");
			return null;
		}
	}

	/**
	 * Method to open new tab in the browser.
	 */
	public void openNewTab() {
		try {
			js.executeScript("window.open('', '_blank');");
			commonWait(3);
		} catch (Exception e) {
			System.out.println("There was an error while launching new tab.");
		}
	}

	/**
	 * Method to launch url in active browser.
	 * 
	 * @param url String.
	 */
	public void goTo(String url) {
		try {
			driver.get(url);
		} catch (Exception e) {
			driver.navigate().to(url);
		}
		waitUntilPageLoaded();
	}
}