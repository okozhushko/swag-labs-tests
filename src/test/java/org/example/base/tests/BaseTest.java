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

    // Flip this to switch the local default between headed and headless.
    private static final boolean LOCAL_DEFAULT_HEADLESS = false;

    private static final boolean HEADLESS = resolveHeadless();

    // Resolution order: an explicit "-Dselenide.headless=..." always wins
    // (also honoured by Selenide's own auto-created driver via Configuration);
    // otherwise CI runs headless automatically (GitHub Actions runners have no
    // display server, so a headed launch would just hang/fail there); otherwise
    // LOCAL_DEFAULT_HEADLESS above applies.
    private static boolean resolveHeadless() {
        String override = System.getProperty("selenide.headless");
        if (override != null) {
            return Boolean.parseBoolean(override);
        }
        if (System.getenv("CI") != null) {
            return true;
        }
        return LOCAL_DEFAULT_HEADLESS;
    }

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