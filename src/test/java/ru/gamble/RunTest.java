package ru.gamble;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import io.qameta.allure.Attachment;
import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sbtqa.tag.pagefactory.PageFactory;

@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        glue = {"github_project.stepdefs", "ru.sbtqa.tag.stepdefs.ru"},
        features = {"src/test/resources/features/"},
        plugin = {"io.qameta.allure.cucumber2jvm.AllureCucumber2Jvm","pretty"},
        tags = {"@test"})

public class RunTest {
    private static final Logger LOG = LoggerFactory.getLogger(RunTest.class);

    @Rule
    public TestWatcher watchman = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            screenshot();
        }

        @Attachment(value = "Page screenshot", type = "image/jpeg")
        public byte[] saveScreenshot(byte[] screenShot) {
            return screenShot;
        }

        public void screenshot() {
            if (PageFactory.getWebDriver() == null) {
                LOG.info("Driver for screenshot not found");
            }
        }
    };

    @AfterClass
    public static void afterScenario(){
        PageFactory.dispose();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}




