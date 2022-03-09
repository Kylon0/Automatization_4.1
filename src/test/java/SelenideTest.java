import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.threeten.bp.LocalDate;

import com.codeborne.selenide.*;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static java.util.stream.LongStream.range;


public class SelenideTest {
    public LocalDate date = LocalDate.now();
    public String dateFormat = date.plusDays(5).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

    @Test
    void shouldSubmitRequest() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Санкт-Петербург");
        {$("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE);}
        $("[data-test-id=date] input").setValue(String.valueOf(dateFormat));
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+79533556677");
        $("[data-test-id=agreement]").click();
        $(withText("Забронировать")).click();
        $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(14));
        $("[data-test-id=notification]").shouldHave(Condition.text("Встреча успешно забронирована на " + dateFormat));
    }
}
