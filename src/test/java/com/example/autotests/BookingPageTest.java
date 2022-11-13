package com.example.autotests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import dev.failsafe.internal.util.Assert;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
public class BookingPageTest {
    private final BookingPage bookingPage = new BookingPage();

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        open(bookingPage.URL);
    }

    @Test
    public void WhenMinAndMaxPriceIs0_NoItemsShouldBeDisplayedInProductGrid() {
        bookingPage.accessoriesReference.click();
        bookingPage.minPriceInput.setValue("0");
        bookingPage.maxPriceInput.setValue("0");
        bookingPage.minPriceInput.pressTab();
        Assert.isTrue(!bookingPage.productGrid.find("div").isDisplayed(), "Product grid is not empty");
    }

    @Test
    public void WhenPressLamaboxesButton_HeaderShouldHaveTextLamaboxes() {
        bookingPage.hotelsBox.click();
        Assert.isTrue(bookingPage.header.getText().equals("Ламабоксы"), "Header text is not equal to 'Ламабоксы'");
    }

    @Test void WhenGoToJojoFranciseLink_HeaderShoudHaveTextJojonoKimyounaBouken(){
        bookingPage.SearchByName.click();
        bookingPage.SearchByName.hover();
        bookingPage.GogoLink.click();
        Assert.isTrue(bookingPage.header.getText().equals("Jojo no Kimyou na Bouken"), "Header text is not equal to 'Jojo no Kimyou na Bouken'");

    }

}
