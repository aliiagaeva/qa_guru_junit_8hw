package SearchPage;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class SearchPage {
    public static final SelenideElement searchInput = $("#searchInput");
    public final SelenideElement searchingCountResults = $(".searching-results__count");
    public static final SelenideElement productCardBrand = $(".product-card__brand");
    public static final SelenideElement searchingTitleResults = $(".searching-results__title");


    public SearchPage openPage() {
        open("https://www.wildberries.ru/");
        return this;
    }
    public SearchPage searchWB(String searchInputWB){
        searchInput.setValue(searchInputWB).pressEnter();
        searchingCountResults.shouldBe(visible);
        return this;
    }
    public SearchPage brandName(String cardBrandName){
        sleep(5000);
        productCardBrand.shouldHave(text(cardBrandName));
        return this;
    }

}
