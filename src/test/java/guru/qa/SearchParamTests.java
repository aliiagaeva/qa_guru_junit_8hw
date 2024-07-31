package guru.qa;


import SearchPage.SearchPage;
import guru.qa.data.HeaderSearchContainer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;


public class SearchParamTests {

    SearchPage searchPage = new SearchPage();

    @ValueSource(strings = {
            "java", "javascript", "junit"
    })
    @ParameterizedTest
    @DisplayName("По запросу {0} есть товары в Wildberries ")
    void searchResultsProductNotBeEmpty(String searchQuery) {
        searchPage.openPage()
                .searchWB(searchQuery);
    }


    @CsvFileSource(resources = "/searchResultsProduct.csv")
    @ParameterizedTest
    @DisplayName("По запросу {0} в Wildberries отображаются название запроса {1} и товары")
    void searchResultsProductWith(String searchQuery, String expectedBrandName) {
        searchPage.openPage()
                .searchWB(searchQuery)
                .brandName(expectedBrandName);
    }

    @EnumSource(HeaderSearchContainer.class)
    @ParameterizedTest
    void checkSearch(HeaderSearchContainer headerSearchContainer) {
        searchPage.openPage();
        SearchPage.searchInput.setValue(headerSearchContainer.name()).pressEnter();
        SearchPage.searchingTitleResults.shouldHave(text(headerSearchContainer.description));
    }

    static Stream<Arguments> checkSearchTitleAndProductName() {
        return Stream.of(
                Arguments.of(HeaderSearchContainer.java, "Вильямс"),
                Arguments.of(HeaderSearchContainer.javascript, "Эксмо")
        );
    }

    @MethodSource
    @ParameterizedTest
    void checkSearchTitleAndProductName(HeaderSearchContainer headerSearchContainer, String expectedProductName) {
        searchPage.openPage();
        SearchPage.searchInput.setValue(headerSearchContainer.name()).pressEnter();
        SearchPage.productCardBrand.shouldHave(text(expectedProductName));
    }
}
