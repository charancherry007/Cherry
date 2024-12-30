package com.Xpaths;

public enum WebPageNameXpaths {

	/**
	 * Declare all the XPaths of all the elements in this class. Rename the class
	 * name (WebPageName) with WebPage Name of your desired.
	 */

	EXAMPLE_BUTTON("//button[@id='loginbutton']"),
	EXAMPLE_DIV("//div[@id='example']"),
	EXAMPLE_INPUT("//input[@name='exampleInput']"),
	EXAMPLE_LINK("//yt-searchbox[@role='search']"),
	EXAMPLE_CONTAINER("//div[@class='yt-lockup-view-model-wiz__metadata']");

	private final String xpath;

	WebPageNameXpaths(String xpath) {
		this.xpath = xpath;
	}

	public String getXpath() {
		return xpath;
	}
}
