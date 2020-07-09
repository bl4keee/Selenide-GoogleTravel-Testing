import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.*;

public class TravelPage {

    SelenideElement searchBar = $(By.xpath("//*[@id=\"yDmH0d\"]/c-wiz[2]/div/div[2]/div/c-wiz/div[1]/" +
                                "div[1]/div[2]/div[1]/c-wiz/div/div[1]/div[2]/div[1]/div/div[1]/input"));

    public void search() {
        searchBar.pressEnter();
        sleep(5000);
    }

    public void acceptPolicy() {
        $(".gb_Xd.gb_wd").click();
        sleep(1000);
    }

    public void enterLocalization(String localization) {
        searchBar.setValue(localization);
        sleep(1000);
    }

    public void goToHotels() {
        $(By.xpath("//div[4]/a/button/i")).click(); // hotel icon
        sleep(3000);
    }

        public void selectGuestsAmount(int guests) {
            $(".DPvwYc.mZOHec.zi2Q5b").click(); // arrow down
            sleep(1000);
            $$(byAttribute("role", "option"))
                    .get(guests)
                    .click();
            sleep(3000);
        }

    public void selectDate(LocalDate startDate, LocalDate endDate) {
        $(".p0RA.ogfYpf.Py5Hke").click(); // open calendar
        $$(".lkvzbb.KQqAEc")
                .find(Condition.text(String.valueOf(startDate.getDayOfMonth())))
                .click();
        sleep(500);
        $$(".lkvzbb.KQqAEc")
                .find(Condition.text(String.valueOf(endDate.getDayOfMonth())))
                .click();
        sleep(500);
        $(".VfPpkd-Jh9lGc").click(); // close calendar
        sleep(1000);
    }

    public void fillBasicForm(String localization, LocalDate startDate, LocalDate endDate, int guests) {
        acceptPolicy();
        goToHotels();
        enterLocalization(localization);
        selectDate(startDate, endDate);
        selectGuestsAmount(guests);
        search();
    }
}
