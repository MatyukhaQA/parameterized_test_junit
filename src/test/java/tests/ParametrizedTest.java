package tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import tests.data.Language;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class ParametrizedTest extends TestBase{

    static Stream<Arguments> steamShouldDisplayCorrectButtonsTest() {
        return Stream.of(
                Arguments.of(
                        Language.English,
                        List.of("Your Store", "New & Noteworthy", "Categories")),
                Arguments.of(
                        Language.Deutsch,
                        List.of("Ihr Shop", "Neu und nennenswert", "Kategorien"))

        );
    }

    @MethodSource
    @ParameterizedTest(name = "Проверка корректного отображения текста для  языка {0}")
    void steamShouldDisplayCorrectButtonsTest(Language language, List<String> expectedButtons) {
        open("https://store.steampowered.com/");
        $("#language_pulldown").click();
        $(withText(language.name())).click();
        $$(".store_nav_bg span a.pulldown_desktop").shouldHave(texts(expectedButtons));
    }

    @ValueSource(strings = {
            "Cyberpunk", "CS", "half life"
    })
    @ParameterizedTest(name = "Поисковой запрос {0} должен отдавать не пустой список")
    void searchResultsShouldNotBeEmptyTest(String searchQuery) {
        open("https://store.steampowered.com/");
        $("#store_nav_search_term").setValue(searchQuery).pressEnter();
        $$("#search_results a").shouldBe(sizeGreaterThan(0));
    }

    @CsvSource(value = {
            "Dansk , Din butik",
            "Deutsch, Ihr Shop",
            "Nederlands , Jouw winkel"
    })

    @ParameterizedTest(name = "Проверка корректного отображения текста для  языка {0}")
    void correctLanguageShouldDisplayedTest(String language, String expectedResult) {
        open("https://store.steampowered.com/");
        $("#language_pulldown").click();
        $(withText(language)).click();
        $("#store_nav_area").shouldHave(text(expectedResult));
    }

    @EnumSource(Language.class)
    @ParameterizedTest(name = "Проверка корректного отображения кнопки скачать для языка {0}")
    void shouldDisplayedCorrectTextVerifyWithEnumTest(Language language) {
        open("https://store.steampowered.com/");
        $("#language_pulldown").click();
        $(withText(language.name())).click();
        $(".header_installsteam_btn_content").shouldHave(text(language.description));
    }
}
