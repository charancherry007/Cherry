package com.CommonUtilities;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utility {

	/**
	 * This class contains all the required methods for automating. It contains
	 * methods to click, enterText, wait until visible, wait until clickable, get
	 * Text, Handling iFrames. All the methods in this class contain common @param which is String
	 * XPath.
	 */

	private WebDriver driver;
	private WebDriverWait wait;

	/** 
	 * Constructor to initialize WebDriver and WebDriverWait
	 * @param driver
	 */
	public void SeleniumUtils(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	/**
	 *  Method to find an Element
	 * @param xpath
	 * type @return element.
	 */
	public WebElement locateElement(String xpath) {
		WebElement element = driver.findElement(By.xpath(xpath));
		return element;
	}

	/**
	 * Method to wait for an element to clickable
	 * @param xpath
	 * Type @return boolean.
	 */
	public WebElement waitUntilElementClickable(String xpath) {
		waitUntilElementVisible(xpath);
		return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
	}

	/**
	 *  Method to wait for an element to be visible
	 * @param xpath
	 * Type @return boolean.
	 */
	public WebElement waitUntilElementVisible(String xpath) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}

	/**
	 *  Method to click an element.
	 * @param xpath String.
	 */
	public void clickOn(String xpath) {
		waitUntilElementClickable(xpath);
		WebElement element = locateElement(xpath);
		element.click();
	}

	/**
	 *  Method to enter text into an input field.
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
	 *  Method to get the text of an element
	 * @param xpath
	 * Method type @return String element text.
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
     * @param identifier 
     * The index (Integer) or name/ID (String) of the iFrame to switch to.
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
     * @param identifier The index (Integer) or title (String) of the tab to switch to.
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
	 *  Method to scroll into View for an element using JavaScript
	 * @param xpath as String
	 */
	public void jsScrollToElement(String xpath) {
		waitUntilElementVisible(xpath);
		WebElement element = locateElement(xpath);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	/**
	 *  Method to click on element using JavaScript
	 * @param xpath as String
	 */
	public void jsClickElement(String xpath) {
		jsScrollToElement(xpath);
		WebElement element = locateElement(xpath);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}
}