package tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import tests.data.Language;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import tests.pages.MainPage;
import tests.pages.helpers.CapitalizedString;

import java.util.List;
import java.util.stream.Stream;


public class ParametrizedTest extends TestBase{

    MainPage mainpage = new MainPage();

    static Stream<Arguments> steamShouldDisplayCorrectButtonsTest() {
        return Stream.of(
                Arguments.of(
                        "English",
                        List.of("Your Store", "New & Noteworthy", "Categories")),
                Arguments.of(
                        "Deutsch",
                        List.of("Ihr Shop", "Neu und nennenswert", "Kategorien"))
        );
    }
    @MethodSource
    @ParameterizedTest(name = "Проверка корректного отображения текста для  языка {0}")
    void steamShouldDisplayCorrectButtonsTest(String language, List<String> expectedButtons) {
        mainpage.openPage()
                .openLanguageDropDown()
                .chooseLanguage(language)
                .checkLanguageOfButtons(expectedButtons);
    }

    @ValueSource(strings = {
            "Cyberpunk", "CS", "half life"
    })
    @ParameterizedTest(name = "Поисковой запрос {0} должен отдавать не пустой список")
    void searchResultsShouldNotBeEmptyTest(String searchQuery) {
        mainpage.openPage()
                .sendingSearchRequest(searchQuery)
                .checkSearchResult();
    }

    @CsvSource(value = {
            "Dansk , Din butik",
            "Deutsch, Ihr Shop",
            "Nederlands , Jouw winkel"
    })
    @ParameterizedTest(name = "Проверка корректного отображения текста для  языка {0}")
    void correctLanguageShouldDisplayedTest(String language, String expectedResult) {
        mainpage.openPage()
                .openLanguageDropDown()
                .chooseLanguage(language)
                .checkLanguageOfShopButton(expectedResult);
    }

    @EnumSource(Language.class)
    @ParameterizedTest(name = "Проверка корректного отображения кнопки скачать для языка {0}")
    void shouldDisplayedCorrectTextVerifyWithEnumTest(Language language) {
        String capitalizedLanguageName = new CapitalizedString().capitalizedString(language.name());
        mainpage.openPage()
                .openLanguageDropDown()
                .chooseLanguage(capitalizedLanguageName)
                .checkLanguageOfInstallButton(language.description);
    }
}
