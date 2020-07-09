import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
public class TravelResultPage {

    public void setSortingByLowestPrice() {
        $(By.xpath("//div[2]/span/span/div/div/div[2]/span")).click();
        sleep(500);
        $(By.xpath("//div[2]/div[3]/span/span")).click(); // by the lowest price
        sleep(4000);
    }

    public void printHotels() {
        ElementsCollection links = $$(".PVOOXe");
        System.out.println("Hotels: \n" +
                $("#hotelName1").getText() + "\n" + links.get(0).getAttribute("href") + "\n" +
                $("#hotelName2").getText() + "\n" + links.get(1).getAttribute("href") + "\n" +
                $("#hotelName3").getText() + "\n" + links.get(2).getAttribute("href"));
    }

    public void setPrice(int price) {
        int thresholdPrice = price/2;
        SelenideElement handle = $(".JZoRwf.vF4Dne.fIEIff").$(".HPkm2e.R4GWv");

        actions()
                .clickAndHold(handle)
                .moveByOffset(thresholdPrice,0)
                .release(handle)
                .perform();
        sleep(3000);
    }

    public void chooseAmenities() {
        $(By.xpath("//*[@id=\"yDmH0d\"]/c-wiz[2]/div/div[2]/div/c-wiz/" +
                "div/div[1]/div[2]/div[2]/div[9]/span/div[2]/span")).click();
        sleep(1000);

        $(By.xpath("//div[9]/div[2]/div[2]/div/div/div/div")).click(); // wifi
        sleep(1000);

        $(By.xpath("//div[9]/div[2]/div[2]/div/div/div[2]/div")).click(); // gym
        sleep(1000);

        $(By.xpath("//div[2]/div/div/div[11]")).click(); // spa
        sleep(1000);

        $(By.xpath("//div[9]/div/div[3]/div[2]/div/span")).click();
        sleep(2000);
    }

    public void collectHotelsReviews(int minReviewsAmount) {
        ArrayList<String> fixedList = new ArrayList<String>();
        ArrayList<String> reviewsList = new ArrayList<String>();
        ElementsCollection reviews = $$(Selectors.withText("opinii"));
        for (int i = 0; i < 5; i++) reviewsList.add(reviews.get(i).toString());
        for (String s : reviewsList) {
            String rev = s.substring(34, 38);
            fixedList.add(rev);
        }
    }

    public void setAdditionalThings(int price, int minReviewAmount, ArrayList<String> options) {
        setPrice(price);
        chooseAmenities();
        //chooseAmenities3();
        setSortingByLowestPrice();
        collectHotelsReviews(minReviewAmount);
        printHotels();
    }

      /*
    public void chooseAmenities2(ArrayList<String> options) {
        $(By.xpath("//*[@id=\"yDmH0d\"]/c-wiz[2]/div/div[2]/div/c-wiz/" +
                "div/div[1]/div[2]/div[2]/div[9]/span/div[2]/span")).click();
        //ElementsCollection amenities = $$(".CzQnRc");
        ElementsCollection amenities = $$(".rCL7Nc.pJYzRb.Eu5ZOc");
        for (int i = 0; i < options.size(); i++) {
            SelenideElement element = amenities.find(Condition.exactValue(options.get(i)));
            element.click();
        }
    }
     */

}

