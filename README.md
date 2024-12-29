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

# In-Pipeline

Future enhancements to the Selenium framework will introduce more robust methods for executing drag and drop actions, streamlining the automation of complex user interactions. These enhancements are designed to significantly boost the functionality and versatility of Selenium, empowering testers to easily implement a wider range of automation scenarios with greater efficiency and precision.
## Contributing

We welcome contributions from the community! If you have suggestions, improvements, or would like to report an issue, please feel free to submit a pull request or open an issue. For any inquiries or further discussions, you can reach us at [chandu.charan007@gmail.com].
