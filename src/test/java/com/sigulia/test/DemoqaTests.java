package com.sigulia.test;
import com.codeborne.selenide.Configuration;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static com.sigulia.utils.GenerateFakerData.generatedString;
import static com.sigulia.utils.GenerateFakerData.generatedInt;
import static java.lang.String.format;


public class DemoqaTests {


    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
    }

    @BeforeEach
    void openPage() {
        open("https://demoqa.com/automation-practice-form");
    }


    String first_name = generatedString(),
            last_name = generatedString(),
            user_email = generatedString() + "@gmail.com";

    String expectedFullName = format("" + first_name + " " + last_name + "");

    @Test
    void submitWithoutValue() {
        $("#submit").click();
        $(".modal-content").shouldNotBe(visible);
    }

    @Test
    void fillFormsWithObligatoryFields() {
        //String first_name = generatedString();
        //String last_name = generatedString();
        //String user_email = generatedString() + "@gmail.com";
        String mobile_number = generatedInt();
        $("#firstName").setValue(first_name);
        $("#lastName").setValue(last_name);
        $("#userEmail").setValue(user_email);
        $("#userNumber").setValue(mobile_number);
        $("#genterWrapper .custom-control-label").click();
        String gender = $("#genterWrapper .custom-control-label").getText();
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").find(byText("1999")).click();
        $(".react-datepicker__month .react-datepicker__day").click();
        String date_input = $("#dateOfBirthInput").getValue();
        String subjects = "English";
        $("#subjectsInput").sendKeys("eng");
        $(".subjects-auto-complete__option").click();
        $("#hobbiesWrapper .custom-control").click();
        $("#uploadPicture").uploadFromClasspath("cute_cat.jpg");
        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Delhi")).click();
        $("#submit").click();
        $(".modal-content").shouldBe(visible);
        $(byXpath("//td[contains(.,'Student Name')]/following-sibling::td")).shouldHave(text(expectedFullName));
        $(byXpath("//td[contains(.,'Student Email')]/following-sibling::td")).shouldHave(text(user_email));
        $(byXpath("//td[contains(.,'Gender')]/following-sibling::td")).shouldHave(text(gender));
        $(byXpath("//td[contains(.,'Mobile')]/following-sibling::td")).shouldHave(text(mobile_number));
        $("#closeLargeModal").click();
    }


}