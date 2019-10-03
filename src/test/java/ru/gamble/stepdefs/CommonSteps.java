package ru.gamble.stepdefs;

import cucumber.api.DataTable;
import cucumber.api.java.ru.Когда;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sbtqa.tag.datajack.Stash;
import ru.sbtqa.tag.pagefactory.PageFactory;
import ru.sbtqa.tag.stepdefs.GenericStepDefs;

import java.util.Map;
import java.util.Random;


public class CommonSteps extends GenericStepDefs {


    private static final Logger LOG = LoggerFactory.getLogger(CommonSteps.class);
    static WebDriver driver = PageFactory.getDriver();




    @Когда("^сохраняем в память$")
    public static void saveValueToKey(DataTable dataTable) {
        String key, value;
        Map<String,String> table = dataTable.asMap(String.class,String.class);
        for (Map.Entry<String,String> entry:table.entrySet()) {

            key = entry.getKey();
            value = entry.getValue();

            StringBuilder result = new StringBuilder();
            int count = Integer.valueOf(value.replace("randomNumber", "").trim());
            for (int i = 0; i < count; i++) {
                result.append((char) ('1' + new Random().nextInt(8)));
            }
            value = result.toString();
            Stash.put(key, value);
            LOG.info("key::[" + key + "] value::[" + value + "]");
        }

    }

    @Когда("^переходит на страницу '(.+)'$")
    public static void goToMainPage(String siteUrl) {
        driver.get(siteUrl);
        LOG.info("Перешли на страницу [" + siteUrl + "]");
    }


    @Когда("^проверка ответа API из \"([^\"]*)\":$")
    public void checkresponceAPI22(String keyStash, DataTable dataTable) {
        Api.checkResponse(keyStash,dataTable);
    }


    private String collectQueryString(String path) {
        String requestFull = "";
        LOG.info("Собираем строку запроса.");
        requestFull =  "https://api.github.com/" + path;
        LOG.info("requestFull [" + requestFull + "]");
        return requestFull;
    }

    @Когда("^запрос к API \"([^\"]*)\" и сохраняем в \"([^\"]*)\":$")
    public void requestToAPI(String path, String keyStash, DataTable dataTable) {
        String fullPath = collectQueryString(path);
        Response response = Api.requestAndResponse("POST",fullPath,dataTable);
        Stash.put(keyStash, response);
    }

}

