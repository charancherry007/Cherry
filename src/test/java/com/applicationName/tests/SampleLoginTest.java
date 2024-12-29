package com.applicationName.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.CommonUtilities.Utility;
import com.Xpaths.WebPageNameXpaths;

public class SampleLoginTest {
	private Utility utility;

	@BeforeClass
	public void setUp() {
		utility = new Utility();
		utility.navigateTo("chrome", "https://www.facebook.com");
	}

	@Test
	public void sampleTest() {
		utility.clickOn(WebPageNameXpaths.EXAMPLE_BUTTON.getXpath());
		utility.saveValue("Number", "$3");
		utility.getValue("Number");
	}
}