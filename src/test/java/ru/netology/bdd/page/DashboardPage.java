package ru.netology.bdd.page;

import com.codeborne.selenide.Clipboard;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.netology.bdd.data.DataHelper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.selector.ByDeepShadow.cssSelector;
import static java.lang.reflect.Array.get;

public class DashboardPage {

    private final String balanceStart = ", баланс: ";
    private final String balanceFinish = " р.";
    private final SelenideElement heading = $("[data-test-id='dashboard']");
    private final ElementsCollection cards = $$(".list__item div");



    public DashboardPage() {
        heading.shouldBe(visible);
    }


   public int getCardBalance(DataHelper.CardInfo cardInfo) {
       var testCardNumber = cardInfo.getCardNumber();
       var textLine = cards
               .findBy(text(testCardNumber.substring(15)))
              .getText();
      return extractCardBalance(textLine);
   }



    private int extractCardBalance(String textLine) {
        var start = textLine.indexOf(balanceStart);
        var finish = textLine.indexOf(balanceFinish);
        var value = textLine.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    // button data-test-id='action-deposit'

    public TransferPage selectCardToTransfer(DataHelper.CardInfo cardInfo) {
        var testCardNumber = cardInfo.getCardNumber();
        $(cards.findBy(text(testCardNumber.substring(15))))
                .findElement(By.cssSelector("[data-test-id='action-deposit']"))
                .click();
        return new TransferPage();
    }

}

