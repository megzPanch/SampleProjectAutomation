$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/resources/features/Regression.feature");
formatter.feature({
  "name": "Selenium Regression Flow",
  "description": "",
  "keyword": "Feature",
  "tags": [
    {
      "name": "@Test"
    }
  ]
});
formatter.scenarioOutline({
  "name": "verify new browser window",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "user launch the application \u003cURL\u003e",
  "keyword": "Given "
});
formatter.step({
  "name": "user validate new browser window button",
  "keyword": "And "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "URL"
      ]
    },
    {
      "cells": [
        "url"
      ]
    }
  ]
});
formatter.background({
  "name": "",
  "description": "",
  "keyword": "Background"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "A workbook named \"TESTDATA\" and sheet named \"Result\" is read",
  "keyword": "Given "
});
formatter.match({
  "location": "DefaultStepDefinition.a_workbook_named_and_sheet_named_is_read(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "verify new browser window",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@Test"
    }
  ]
});
formatter.step({
  "name": "user launch the application url",
  "keyword": "Given "
});
formatter.match({
  "location": "LoginStepDef.user_launch_the_application(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "user validate new browser window button",
  "keyword": "And "
});
formatter.match({
  "location": "LoginStepDef.validate_new_browser_window_button()"
});
formatter.result({
  "status": "passed"
});
formatter.background({
  "name": "",
  "description": "",
  "keyword": "Background"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "A workbook named \"TESTDATA\" and sheet named \"Result\" is read",
  "keyword": "Given "
});
formatter.match({
  "location": "DefaultStepDefinition.a_workbook_named_and_sheet_named_is_read(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "verify new browser tab",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@Test"
    }
  ]
});
formatter.step({
  "name": "user validate new browser tab button",
  "keyword": "Then "
});
formatter.match({
  "location": "LoginStepDef.user_validate_new_browser_tab_button()"
});
formatter.result({
  "status": "passed"
});
formatter.background({
  "name": "",
  "description": "",
  "keyword": "Background"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "A workbook named \"TESTDATA\" and sheet named \"Result\" is read",
  "keyword": "Given "
});
formatter.match({
  "location": "DefaultStepDefinition.a_workbook_named_and_sheet_named_is_read(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "verify alert button",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@Test"
    }
  ]
});
formatter.step({
  "name": "user validate alert",
  "keyword": "Then "
});
formatter.match({
  "location": "LoginStepDef.user_validate_alert()"
});
formatter.result({
  "error_message": "org.openqa.selenium.NoAlertPresentException: no such alert\n  (Session info: chrome\u003d107.0.5304.62)\nBuild info: version: \u00273.141.59\u0027, revision: \u0027e82be7d358\u0027, time: \u00272018-11-14T08:17:03\u0027\nSystem info: host: \u0027PANCHMEGALA\u0027, ip: \u0027192.168.0.113\u0027, os.name: \u0027Windows 10\u0027, os.arch: \u0027amd64\u0027, os.version: \u002710.0\u0027, java.version: \u00271.8.0_202\u0027\nDriver info: org.openqa.selenium.chrome.ChromeDriver\nCapabilities {acceptInsecureCerts: false, browserName: chrome, browserVersion: 107.0.5304.62, chrome: {chromedriverVersion: 107.0.5304.62 (1eec40d3a576..., userDataDir: C:\\Users\\panch\\AppData\\Loca...}, goog:chromeOptions: {debuggerAddress: localhost:60539}, javascriptEnabled: true, networkConnectionEnabled: false, pageLoadStrategy: normal, platform: WINDOWS, platformName: WINDOWS, proxy: Proxy(), setWindowRect: true, strictFileInteractability: false, timeouts: {implicit: 0, pageLoad: 300000, script: 30000}, unhandledPromptBehavior: dismiss and notify, webauthn:extension:credBlob: true, webauthn:extension:largeBlob: true, webauthn:virtualAuthenticators: true}\nSession ID: 6f34ea981d86e6dba9f5c8acf87f2a41\r\n\tat sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)\r\n\tat sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)\r\n\tat sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)\r\n\tat java.lang.reflect.Constructor.newInstance(Constructor.java:423)\r\n\tat org.openqa.selenium.remote.http.W3CHttpResponseCodec.createException(W3CHttpResponseCodec.java:187)\r\n\tat org.openqa.selenium.remote.http.W3CHttpResponseCodec.decode(W3CHttpResponseCodec.java:122)\r\n\tat org.openqa.selenium.remote.http.W3CHttpResponseCodec.decode(W3CHttpResponseCodec.java:49)\r\n\tat org.openqa.selenium.remote.HttpCommandExecutor.execute(HttpCommandExecutor.java:158)\r\n\tat org.openqa.selenium.remote.service.DriverCommandExecutor.execute(DriverCommandExecutor.java:83)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.execute(RemoteWebDriver.java:552)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.execute(RemoteWebDriver.java:609)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver$RemoteTargetLocator.alert(RemoteWebDriver.java:932)\r\n\tat com.companyName.Testone.Pages.LoginPage.clickonAlert(LoginPage.java:34)\r\n\tat com.companyName.Testone.StepDef.LoginStepDef.user_validate_alert(LoginStepDef.java:28)\r\n\tat ✽.user validate alert(src/test/resources/features/Regression.feature:19)\r\n",
  "status": "failed"
});
formatter.scenarioOutline({
  "name": "verify feedback form",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "user validate feedback form with \u003cName\u003e \u003cEmail\u003e \u003cPhone\u003e \u003cCountry\u003e \u003cCompany\u003e \u003cMesseage\u003e",
  "keyword": "Then "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "Name",
        "Email",
        "Phone",
        "Country",
        "Company",
        "Messeage"
      ]
    },
    {
      "cells": [
        "Megz",
        "megan@gmail.co",
        "1234567890",
        "India",
        "XYZ",
        "Testing"
      ]
    }
  ]
});
formatter.background({
  "name": "",
  "description": "",
  "keyword": "Background"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "A workbook named \"TESTDATA\" and sheet named \"Result\" is read",
  "keyword": "Given "
});
formatter.match({
  "location": "DefaultStepDefinition.a_workbook_named_and_sheet_named_is_read(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "verify feedback form",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@Test"
    }
  ]
});
formatter.step({
  "name": "user validate feedback form with Megz megan@gmail.co 1234567890 India XYZ Testing",
  "keyword": "Then "
});
formatter.match({
  "location": "LoginStepDef.user_validate_feedback_form(String,String,String,String,String,String)"
});
formatter.result({
  "status": "passed"
});
});