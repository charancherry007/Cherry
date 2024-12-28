package com.Xpaths;

public enum WebPageNameXpaths {

	/**
	 * Declare all the XPaths of all the elements in this class. Rename the class
	 * name (WebPageName) with WebPage Name of your desired.
	 */

	EXAMPLE_BUTTON("//button[@id='exampleButton']"), EXAMPLE_DIV("//div[@id='example']"),
	EXAMPLE_INPUT("//input[@name='exampleInput']");

	private final String xpath;

	WebPageNameXpaths(String xpath) {
		this.xpath = xpath;
	}

	public String getXpath() {
		return xpath;
	}
}
