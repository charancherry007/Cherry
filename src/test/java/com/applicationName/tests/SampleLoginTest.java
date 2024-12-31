package com.applicationName.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.CommonUtilities.Utility;
import com.Xpaths.WebPageNameXpaths;

public class SampleLoginTest {
	private Utility utility;
	private WebPageNameXpaths xpaths;
	private String searchboxXpath;
	
	@SuppressWarnings("static-access")
	@BeforeClass
	public void setUp() {
		utility = new Utility();
		//utility.navigateTo("chrome", "https://www.youtube.com");
		searchboxXpath = xpaths.EXAMPLE_LINK.getXpath();
	}
	@SuppressWarnings("static-access")
	@Test
	public void sampleTest(){
		
		Map<String, Object> options = new HashMap<>();
		options.put("incognito", true);
		
		/*
		 * The below are some of the browser option arguments which can be passed for navigateWithArguments.
		 * options.put("disableCache", true); options.put("headless", true);
		 * options.put("ignoreZoomSetting", true);
		 * options.put("requireWindowFocus",true);
		 */
		utility.navigateWithArguments("chrome", "https://www.youtube.com",options);
		
		utility.clickAction(searchboxXpath);
		utility.keyboardActions("Automation Tutorial enter");
		utility.waitUntilPageLoaded();
		utility.scrollRecursively(xpaths.EXAMPLE_CONTAINER.getXpath());
		utility.switchToTab(1);
		utility.commonWait(5);
		utility.switchToTab(0);
		utility.endSession();
	}
}