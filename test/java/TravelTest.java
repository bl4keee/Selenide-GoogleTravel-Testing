import org.junit.jupiter.api.*;
import java.time.LocalDate;
import java.util.ArrayList;

import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selenide.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TravelTest {

    private final String localization = "London";
    private final int guests = 3;
    private final LocalDate startDate = LocalDate.of(2020, 8,22);
    private final LocalDate endDate = LocalDate.of(2020, 8, 26);
    private final int minPrice = 400;
    private final int minReviewsAmount = 500;
    ArrayList<String> options = new ArrayList<String>();

    @BeforeAll
    public static void run() {
        startMaximized = true;
    }

    @AfterEach
    public void betweenTests() {
        holdBrowserOpen = true;
    }

    @Test
    @Order(1)
    void submitForm() {
        TravelPage travelPage = open("https://www.google.com/travel", TravelPage.class);
        sleep(1500);
        travelPage.fillBasicForm(localization, startDate, endDate, guests);
    }

    @Test
    @Order(2)
    void filterForm() {
        TravelResultPage travelResultPage = open("https://www.google.com/travel/hotels?ictx=3&g2lb" +
                "=202158%2C2502548%2C4258168%2C4260007%2C4270442%2C4274032%2C4305595%2C4306835%2C4317915%2C4328159%2C" +
                "4364504%2C4366684%2C4367953%2C4371335%2C4381263%2C4386654%2C4386665%2C4395590%2C4398672%2C4401005%2C4" +
                "401769%2C4402492&hl=pl&gl=PL&hrf=CgUI3gIQACIDUExOKhYKBwjkDxAHGBYSBwjkDxAHGBoYBCABsAEAWAFoAXICCAOaAS8SB" +
                "kxvbmRvbholMHg0N2Q4YTAwYmFmMjFkZTc1OjB4NTI5NjNhNWFkZGQ1MmE5OaoBCgoCCCESAghbGAGqAQoKAgguEgIIDBgB&rp=SAI" +
                "&destination=London&ap=MABoAA",TravelResultPage.class);
        sleep(2000);
        travelResultPage.setAdditionalThings(minPrice, minReviewsAmount, options);
        sleep(2000);
    }

    @AfterAll
    public static void cleanUp() {
        clearBrowserCookies();
        closeWindow();
    }
}
