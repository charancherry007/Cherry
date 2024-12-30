package com.applicationName.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.CommonUtilities.Utility;
import com.Xpaths.WebPageNameXpaths;

public class SampleLoginTest {
	private Utility utility;
	private WebPageNameXpaths xpaths;

	@BeforeClass
	public void setUp() {
		utility = new Utility();
		utility.navigateTo("chrome", "https://www.youtube.com");
	}

	@SuppressWarnings("static-access")
	@Test
	public void sampleTest() {
		utility.clickOn(xpaths.EXAMPLE_LINK.getXpath());
		utility.keyboardActions("Automation Tutorial enter");
		utility.waitUntilPageLoaded();
		utility.scrollRecursively(xpaths.EXAMPLE_CONTAINER.getXpath());
	}
}