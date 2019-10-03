package ru.gamble.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.sbtqa.tag.datajack.Stash;
import ru.sbtqa.tag.pagefactory.PageFactory;
import ru.sbtqa.tag.pagefactory.annotations.ActionTitle;
import ru.sbtqa.tag.pagefactory.annotations.PageEntry;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

@PageEntry(title = "Странциа с репозиториями")

public class RepositoriesPage extends AbstractClassPage {

    @FindBy(xpath = "//a[@aria-current='page']")
    private WebElement titile;

    public RepositoriesPage(){
        WebDriver driver = PageFactory.getDriver();
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
        tryingLoadPage(titile,10, 5);
    }


    @ActionTitle("проверяет, создан ли репозиторий")
    public void isReposCreated(String key){
        String repo_we_create = Stash.getValue(key);
        String repo_on_page = driver.findElement(By.xpath("//a[@itemprop='name codeRepository']")).getText();
        Assert.assertTrue("Не нашли репозитория, удовлетворяющего параметрам. Нашли "+repo_on_page+" вместо "+repo_we_create+"", repo_we_create.contains(repo_on_page));

    }

//    @ActionTitle("удалим репозиторий")
//    public void deleteRepo(String repoKey){
//        String repo_we_create = Stash.getValue(repoKey);
//        driver.findElement(By.xpath("//a[@itemprop='name codeRepository' and contains(text(),'"+repo_we_create+"')]")).click();
//        driver.findElements(By.xpath("//svg[@class='js-selected-navigation-item reponav-item']")).get(5).click(); // это путь до settings. я, конечно, извиняюсь за это, но тут по-другому не напишешь
//        driver.findElement(By.xpath("//summary[@class='btn btn-danger boxed-action' and contains(text(),'Delete this repository')]")).click();
//        driver.findElement(By.xpath("//input[@name='verify']")).sendKeys(repo_we_create);
//        driver.findElement(By.xpath("//button[@type='submit']")).click();
//
//    }
}
