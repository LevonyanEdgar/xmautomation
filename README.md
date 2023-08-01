Web Automation
=======================


 1.clone from `https://github.com/LevonyanEdgar/xmautomation`

 2.Install dependencies
   `mvn clean install -DskipTests`


### Run on intellij

 1. Install lombok plugin
 2. Enable Annotation processor

### Run from terminal
- mvn failsafe:integration-test

### Run locally

- If there is no system variables for our parameters, suit is working based on configs from config.properties file
- to run allure report locally `allure serve allure-results/`
- screenshots of failing tests `target/screenshots`



### Parameters
- RESOLUTION  set  resolution  options  MAXIMUM, RESOLUTION_1024x768, RESOLUTION_800x600;
- SELENIUM_URL - Application base url
- SELENIUM_BROWSER - Browser type (currently possible only chrome)
- SELENIUM_RETRY - retry count in case of test fail
- IS_SELENIUM_BROWSER_HEADLESS - is the browser headless
- IS_SELENIUM_REMOTE_DRIVER - run on local or on selenoid (Selenoid UI : `ex http://localhost:8080/#/`)


