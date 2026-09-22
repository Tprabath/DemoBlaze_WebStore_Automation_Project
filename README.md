# DemoBlaze WebStore Automation Project

----

## About
This project is a Selenium WebDriver automation framework developed for automating the DemoBlaze web application.

### Tech Stack
- Java
- Selenium WebDriver
- TestNG
- Maven build system

### Features

- Custom findElement() wrapper methods
- Explicit wait abstractions
- Centralized locators
- Reusable cart-row iteration methods
- Browser configurable execution

> ## Requirements
1. JDK (25 or later),
   - Java 25.0.2 2026-01-20 LTS,
   - Java™ SE Runtime Environment (build 25.0.2+10-LTS-69),
   - Java HotSpot™ 64-Bit Server VM (build 25.0.2+10-LTS-69, mixed mode, sharing)
   - Java Compiler v25.0.2
2. Maven 3 (v3.9.16) or 3.9+,
3. Selenium v4.48.0,
4. TestNG v7.9.0
5. IntelliJ IDEA v2026.2.1 or 2026.2+

> ## Setup
### 1. Clone the Repo
```Bash
git clone  https://github.com/Tprabath/DemoBlaze_WebStore_Automation_Project.git
```

### 2. Import/Open the Project in Your IDE
```text
Interlij IDEA -> file -> open -> select folder
VS Code -> file -> open folder -> select folder
```
### 3. Sync the Maven Project

---

> ### Execution
- Modify the WEB_ELEMENT_LOCATORS or EXPECTED_VALUES in DemoBlazeTests.java as required.
- In IntelliJ IDEA, just simple click Run Test button on left side of editor window. 
- You can now observe the execution logs in the console and watch the browser automation run.

> [!TIP]
>  You can change what webDriver want to use in BaseTest.java class,
> ``` java
> protected static void setup(
>            String url, boolean maximizeBrowser){
>       setup(new ChromeDriver(), url, maximizeBrowser);
>   }
> ```
> 
>  or use overloaded method of `setup()` on BaseTest.java Class. under `@BeforeMethod` annotation like that,
> ```java
> @BeforeMethod
> public void beforeTest(){
>        setup(new EdgeDriver(), true);
> }
> ```

> [!NOTE]
>  This project is licensed under the MIT License. You are free to reuse and modify the BaseTest.java class in your own projects.
