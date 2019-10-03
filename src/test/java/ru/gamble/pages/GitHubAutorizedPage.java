package ru.gamble.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.sbtqa.tag.pagefactory.PageFactory;
import ru.sbtqa.tag.pagefactory.annotations.ActionTitle;
import ru.sbtqa.tag.pagefactory.annotations.ElementTitle;
import ru.sbtqa.tag.pagefactory.annotations.PageEntry;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

@PageEntry(title = "Авторизованная страница")

public class GitHubAutorizedPage extends AbstractClassPage {

    @FindBy(xpath = "//header[@class='Header js-details-container Details flex-wrap flex-lg-nowrap p-responsive']")
    private WebElement titile;

    @FindBy(xpath = "//summary[@class='Header-link']")
    private WebElement icon_of_user;

    @ElementTitle("Кнопка выбора параметров")
    @FindBy(xpath = "//div[@class='Header-item position-relative mr-0 d-none d-lg-flex']")
    private WebElement items;

    public GitHubAutorizedPage() {
        WebDriver driver = PageFactory.getDriver();
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
        tryingLoadPage(titile, 10, 5);
    }


    @ActionTitle("проверяет, что пользователь залогиген под своей учёткой")
    public void checkUserLogInCorrectly(String user) {
        String user_on_page = driver.findElement(By.xpath("//img[@class='avatar']")).getAttribute("alt");
        Assert.assertTrue("Залогинились под юзером " + user_on_page + " вместо " + user + "", user.equals(user_on_page));
    }

    @ActionTitle("выбирает из списка опций")
    public void chooseOption(String option){
        driver.findElement(By.xpath("//details-menu[@class='dropdown-menu dropdown-menu-sw mt-2']/a[text()='"+option+"']")).click();
    }

}

