package ru.gamble.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sbtqa.tag.pagefactory.Page;
import ru.sbtqa.tag.pagefactory.PageFactory;
import ru.sbtqa.tag.pagefactory.annotations.ActionTitle;
import ru.sbtqa.tag.pagefactory.exceptions.PageException;
import ru.sbtqa.tag.pagefactory.exceptions.PageInitializationException;
import ru.sbtqa.tag.qautils.errors.AutotestError;


public abstract class AbstractClassPage extends Page{
    private static final Logger LOG = LoggerFactory.getLogger(AbstractClassPage.class);


    static WebDriver driver = PageFactory.getDriver();


    public static void pressButton(String param) {
        Page page;
        WebElement button;
        try {
            page = PageFactory.getInstance().getCurrentPage();
            button = page.getElementByTitle(param);
            button.click();
        } catch (PageInitializationException e) {
            e.printStackTrace();
        } catch (PageException e) {
            throw new AutotestError("Ошибка! Не удалось нажать на копку [" + param + "]\n" + e.getMessage());
        }
    }

    @ActionTitle("нажимает кнопку")
    public static void pressButtonAP(String param){
        pressButton(param);
        LOG.info("Нажали на [" + param + "]");
    }


    public void tryingLoadPage(WebElement element, int count, int waitSeconds) {
        WebDriver driver = PageFactory.getWebDriver();
        LOG.info("Ищем элемент [" + element + "] на странице::" + driver.getCurrentUrl());

        for (int j = 0; j < count; j++) {
            try {
                new WebDriverWait(PageFactory.getDriver(), waitSeconds ).until(ExpectedConditions.visibilityOf(element));
                break;
            } catch (Exception e) {
                driver.navigate().refresh();
            }
            if (j >= count - 1) {
                throw new AutotestError("Ошибка! Не нашли элемент после " + j + " попыток перезагрузки страницы");
            }
        }
    }

    @ActionTitle("вводит в поле")
    public void inputInField(String inputField, String text) throws PageException {
        WebElement webElement;
        fillField(inputField, text);
    }



}

