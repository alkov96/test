package ru.gamble.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sbtqa.tag.pagefactory.PageFactory;
import ru.sbtqa.tag.pagefactory.annotations.ActionTitle;
import ru.sbtqa.tag.pagefactory.annotations.PageEntry;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;


@PageEntry(title = "Логин")
public class GitHubLogIn extends AbstractClassPage {

    private static final Logger LOG = LoggerFactory.getLogger(GitHubLogIn.class);
    static WebDriver driver = PageFactory.getDriver();

    @FindBy(xpath = "//input[@name='login']")
    private WebElement login;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement password;

    @FindBy(xpath = "//h1[contains(text(),'Sign in to GitHub')]")
    private WebElement titile;

    @FindBy(xpath = "//input[@name='commit']")
    private WebElement commit;

    public GitHubLogIn(){
        WebDriver driver = PageFactory.getDriver();
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
        tryingLoadPage(titile,10, 5);
    }

    @ActionTitle("логинится с данными")
    public void logIn(String log, String pass){
        fillField(login,log);
        fillField(password, pass);
        commit.click();

    }

}
