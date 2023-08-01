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
