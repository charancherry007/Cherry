# Key Word Driven Automation Framework for Web Application's.

This project offers a comprehensive solution for automating web interactions through Selenium WebDriver. The Utility class is designed to streamline automation tasks by providing a robust set of methods for managing browsers, interacting with web elements, and efficiently storing and retrieving data. By utilizing this utility class, developers can significantly reduce the effort required to write lengthy and repetitive code, allowing them to focus on higher-level test logic and improving overall productivity in automated testing workflows.

## Features

- **Browser Management**: Seamlessly switch between multiple browsers, including Chrome, Firefox, Edge, and Internet Explorer, with minimal configuration, allowing for versatile testing across different environments.
- **Element Interaction**: A comprehensive set of methods that facilitate clicking, entering text, and retrieving information from web elements, enabling smooth and efficient interactions with the user interface.
- **Wait Conditions**: Robust built-in methods that intelligently manage wait times, ensuring elements are fully visible or clickable before interactions, thereby enhancing the reliability and stability of automated tests.
- **iFrame Handling**: Effortlessly switch between iFrames and the main content, allowing for seamless navigation and interaction within complex web applications that utilize nested frames.
- **Tab Management**: Conveniently switch between browser tabs using either their index or title, enabling efficient navigation and interaction within multi-tabbed web applications.
- **JavaScript Execution**: Leverage JavaScript to scroll to specific elements and perform clicks, providing greater control over interactions and ensuring reliable execution in scenarios where traditional methods may fall short.
- **Value Storage**: Efficiently store and retrieve values with built-in support for formatting, allowing for seamless data management and ensuring consistency across automated tests.

## Prerequisites

- Java Development Kit (JDK) 17 or higher
- Maven
- An IDE (e.g., IntelliJ IDEA, Eclipse)

## Installation

1. **Clone the repository**
2. **Import the project using pom.xml or run mvn clean install**

## Methods Overview

- **navigateTo(String browserName, String url)**: Instantly launches the specified browser and navigates to the provided URL, streamlining the setup process for automated tests and ensuring a quick start to your web interactions.

      Utility.navigateTo("chrome","www.example.com");
  
- **locateElement(String xpath)**: Accurately locates a web element using the specified XPath, enabling precise interactions and ensuring that your automation scripts target the correct elements on the page.
- **waitUntilElementClickable(String xpath)**: Monitors the specified element until it becomes clickable, ensuring that interactions occur only when the element is ready, thereby enhancing the stability and reliability of your automated tests.
- **clickOn(String xpath)**: Executes a click action on the specified element, ensuring that the interaction is performed only when the element is ready, thereby promoting smoother and more reliable automation workflows.
- **enterText(String xpath, String text)**: Inputs the specified text into the targeted input field, ensuring that the action is performed accurately and efficiently, which is essential for simulating user interactions in automated tests.
- **getText(String xpath)**: Extracts and returns the text content of the specified element, enabling effective validation and verification of displayed information within automated tests.
- **listIframes()**: Compiles and returns a list of all iFrames present on the current page, facilitating easy navigation and interaction within complex web structures that utilize multiple frames.
- **switchToIframe(Object identifier)**: Switches context to the specified iframe using either its index or name, allowing for seamless interaction with nested content and enhancing the efficiency of automated testing in complex web applications.
- **switchToDefault()**: Switches back to the default content.
- **switchToTab(Object identifier)**: Switches to the specified browser tab using either its index or title, enabling efficient navigation and interaction within multi-tabbed environments, and streamlining the testing process.
- **jsScrollToElement(String xpath)**: Utilizes JavaScript to smoothly scroll to the specified element, ensuring it is brought into view for interaction, which enhances the reliability of automated tests in dynamic web environments.
- **jsClickElement(String xpath)**: Executes a click action on the specified element using JavaScript, ensuring reliable interaction even in cases where traditional click methods may fail, thereby enhancing the robustness of automated tests.
- **saveValue(String objectName, Object value)**: Stores a value under a specified reference name, enabling easy retrieval and management of data throughout your automated tests, and facilitating better organization and clarity in your test scripts.
- **getValue(String objectName)**: Retrieves the stored value associated with the specified reference name, allowing for efficient data access and manipulation within your automated tests, and promoting better test organization and clarity.
- **clickAction(String xpath)**: Utilizes the Actions class in Selenium to perform click action.
- **keyboardActions(String keys)**: Method takes a string of space-separated key commands and performs corresponding keyboard actions using Selenium's Actions class. It supports various keys such as "Enter," "Tab," "Backspace," and combinations like "Ctrl+A," "Ctrl+C," and "Ctrl+V," as well as handling Shift key presses. For any unrecognized keys, it sends them as regular keystrokes.
Example: Enter **Automation Tutorial** as text and press **ENTER** key.

      Utility.keyboardActions("Automation Tutorial enter");

- **waitUntilPageLoaded()**: Checks if the web page has fully loaded by executing a JavaScript command that returns the document's readiness state.
- **scrollRecursively(String xpath)**: Method scrolls through a webpage to load additional elements matching a specified XPath. It initially retrieves the elements and enters a loop where it scrolls to the last element, waits for the page to load, and checks if new elements have been loaded. The process continues until no new elements are found or an exception occurs. It also handles exceptions, specifically logging if no elements are found or if any other error occurs during the scrolling process.
- **commonWait(int seconds)**: It pauses the execution of the current thread for a specified number of seconds by using Thread.sleep(). It takes an integer parameter representing the duration in seconds, converts it to milliseconds, and handles any InterruptedException by restoring the thread's interrupted status.The below Example will wait for 5 Seconds.

      Utility.commonWait(5);

- **getProperty(String xpath, String attribute)**: Retrieves the value of a specified attribute from a web element identified by the given XPath.
- **executeJS(String xpath, String arguments)**: Executes a JavaScript snippet on a web element identified by the given XPath.
- **endSession**: Simple but more reusable in Automation. A method will close the Tab's / Browser window.
- **navigateWithArguments**: The navigateWithArguments method is a versatile and flexible way to initialize a WebDriver for different browsers, allowing for various configurations through a simple options map. This design makes it easy to adapt the method for different testing scenarios and browser requirements. Find the below Example for declaration and usage of this method.

      Map<String, Object> options = new HashMap<>();
      options.put("incognito", true);
      options.put("disableCache", true);
      options.put("headless", true); // For headless mode
      options.put("ignoreZoomSetting", true); // For Internet Explorer
      options.put("requireWindowFocus", true); // For Internet Explorer
      Utility.navigateWithArguments("chrome", "https://www.example.com", options);
    

# In-Pipeline

Future enhancements to the Selenium framework will introduce more robust methods for executing drag and drop actions, streamlining the automation of complex user interactions. These enhancements are designed to significantly boost the functionality and versatility of Selenium, empowering testers to easily implement a wider range of automation scenarios with greater efficiency and precision.
## Contributing

We welcome contributions from the community! If you have suggestions, improvements, or would like to report an issue, please feel free to submit a pull request or open an issue. For any inquiries or further discussions, you can reach us at [chandu.charan007@gmail.com].
