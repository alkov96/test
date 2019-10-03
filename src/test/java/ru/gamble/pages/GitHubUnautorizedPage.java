package ru.gamble.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.sbtqa.tag.pagefactory.PageFactory;
import ru.sbtqa.tag.pagefactory.annotations.ElementTitle;
import ru.sbtqa.tag.pagefactory.annotations.PageEntry;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

@PageEntry(title = "Главная неавторизованная Git")

public class GitHubUnautorizedPage extends AbstractClassPage {

    static WebDriver driver = PageFactory.getDriver();

    @ElementTitle("Sign In - Логин")
    @FindBy(xpath = "//a[@class='HeaderMenu-link no-underline mr-3']")
    public WebElement sing_up;

    @FindBy(xpath = "//h1[contains(text(),'Built for developers')]")
    private WebElement titile;

    public GitHubUnautorizedPage(){
        WebDriver driver = PageFactory.getDriver();
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
        tryingLoadPage(titile,10, 5);
    }


}
