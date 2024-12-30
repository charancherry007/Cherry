package com.applicationName.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.CommonUtilities.Utility;
import com.Xpaths.WebPageNameXpaths;

public class SampleLoginTest {
	private Utility utility;
	private WebPageNameXpaths xpaths;
	private String searchboxXpath;

	@BeforeClass
	public void setUp() {
		utility = new Utility();
		utility.navigateTo("chrome", "https://www.youtube.com");
		searchboxXpath = xpaths.EXAMPLE_LINK.getXpath();
	}

	@SuppressWarnings("static-access")
	@Test
	public void sampleTest(){
		utility.clickAction(searchboxXpath);
		utility.keyboardActions("Automation Tutorial enter");
		utility.waitUntilPageLoaded();
		utility.scrollRecursively(xpaths.EXAMPLE_CONTAINER.getXpath());
		utility.switchToTab(1);
		utility.commonWait(5);
		utility.switchToTab(0);
	}
}