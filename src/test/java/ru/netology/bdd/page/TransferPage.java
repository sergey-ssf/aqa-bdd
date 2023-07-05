package ru.netology.bdd.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.bdd.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    private final SelenideElement heading = $("[data-test-id='dashboard']");
    private final SelenideElement amountInputNew = $("[data-test-id='amount'] input");
    private final SelenideElement fromInput = $("[data-test-id='from'] input");
    private final SelenideElement transferButton = $("[data-test-id='action-transfer']");
    private final SelenideElement errorMessage = $("[data-test-id='error-notification'] div.notification__content");

    public TransferPage() {
        final SelenideElement addHeading = heading.sibling(1);
        addHeading.shouldBe(visible);
    }

    public void makeTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {

        amountInputNew.setValue(amountToTransfer);
        fromInput.setValue(cardInfo.getCardNumber());
        transferButton.click();
    }

    public DashboardPage makeValidTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        makeTransfer(amountToTransfer, cardInfo);
        return new DashboardPage();
    }

    public DashboardPage makeInvalidTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        makeTransfer(amountToTransfer, cardInfo);
        return new DashboardPage();
    }

    // expectedMessage в нашем случае = Ошибка! Произошла ошибка
    public void findErrorMessage(String expectedMessage) {
        errorMessage.shouldHave(exactText(expectedMessage), Duration.ofSeconds(15)).shouldBe(visible);

    }






}
