package org.example.base.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.Map;

import static com.codeborne.selenide.Selenide.*;

public class BaseTest {

    protected WebDriver driver;

    // Headless is opt-in for local runs (real browser stays visible by default)
    // but turns on automatically in CI: GitHub Actions runners have no display
    // server, so a headed launch would just hang/fail there. Either the
    // "selenide.headless" system property (explicit override, also honoured by
    // Selenide's own auto-created driver via Configuration) or the "CI" env var
    // GitHub Actions sets on every run enables it.
    private static final boolean HEADLESS = Boolean.parseBoolean(
            System.getProperty("selenide.headless", System.getenv("CI") != null ? "true" : "false")
    );

    @BeforeMethod
    public void setUp() {
        System.out.println(">>> BaseTest setUp started (headless=" + HEADLESS + ")");

        WebDriverManager.chromedriver().setup();

        String userDataDir = System.getProperty("java.io.tmpdir") + "/selenide-profile-" + System.currentTimeMillis();

        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--user-data-dir=" + userDataDir,
                "--disable-extensions",
                "--disable-popup-blocking",
                "--no-first-run",
                "--no-default-browser-check",
                "--disable-infobars",
                "--incognito",
                "--disable-save-password-bubble"
        );

        if (HEADLESS) {
            options.addArguments(
                    "--headless=new",
                    "--disable-gpu",
                    "--no-sandbox",
                    "--disable-dev-shm-usage",
                    "--window-size=1920,1080"
            );
        }

        options.setExperimentalOption("prefs", Map.of(
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false
        ));

        driver = new ChromeDriver(options);
        WebDriverRunner.setWebDriver(driver);

        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 5000;

        open("https://www.saucedemo.com/");
    }

    @AfterMethod
    public void tearDown() {
        closeWebDriver();
    }
}