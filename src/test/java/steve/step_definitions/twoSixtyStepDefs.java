package steve.step_definitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import steve.pages.TwoSixtyPage;
import cucumber.api.DataTable;
import cucumber.api.java.en.When;

import java.util.List;
import java.util.Map;

public class twoSixtyStepDefs extends BaseStepDefinition {
    @Autowired
    private TwoSixtyPage twoSixtyPage;

    @Given("^I navigate to the apple store$")
    public void navigate_to_apple_store() throws Throwable {
        String demoURL = twoSixtyPage.getDemoUrl();
        twoSixtyPage.navigateURL(demoURL);
    }

    @When("^I choose a MacBook Pro with the following features and accessories$")
    public void i_choose_features_and_accessories(DataTable featuresAccessories) {
        twoSixtyPage.selectProduct().click();
        twoSixtyPage.selectModel().click();
        twoSixtyPage.buy().click();

        List<Map<String, String>> table = featuresAccessories.asMaps(String.class, String.class);
//        table.get(1).get("Option") Colour
//        table.get(0).get("Option") Screen

        for (Map<String, String> row : table) {
            for (Map.Entry<String, String> col : row.entrySet()) {
                System.out.println(col.getValue());
                System.out.println(col.getKey());

            }
        }

        for (int index = 0; index < featuresAccessories.asMaps(String.class, String.class).size(); index++) {
                Map<String, String> map = featuresAccessories.asMaps(String.class, String.class).get(index);

            if (map.get("Option").equals("Screen")) {
                switch (map.get("Specification")) {
                    case "15’’":
                        twoSixtyPage.selectSize().click();
                        break;
                }
            } else if (map.get("Option").equals("Colour")) {
                switch (map.get("Specification")) {
                    case "Silver":
                        twoSixtyPage.selectColour().click();
                        twoSixtyPage.selectButton().click();
                        break;
                }
            } else if (map.get("Option").equals("Processor")) {
                switch (map.get("Specification")) {
                    case "2.9 GHz":
                        twoSixtyPage.selectProcessor().click();
                        break;
                }
            } else if (map.get("Option").equals("Memory RAM")) {
                switch (map.get("Specification")) {
                    case "16 GB":
                        twoSixtyPage.selectMemory().click();
                        break;
                }
            } else if (map.get("Option").equals("Software")) {
                switch (map.get("Specification")) {
                    case "Logic Pro X":
                        twoSixtyPage.selectSoftware().click();
                        twoSixtyPage.addToCart1().click();
                        break;
                }
            } else if (map.get("Option").equals("Display adapter")) {
                switch (map.get("Specification")) {
                    case "USB-C to USB Adapter":
                        twoSixtyPage.displayAdapter().click();
                        break;
                }
            }
        }
    }

    @Then("^I can place an order for it$")
    public void i_can_place_an_order_for_it() {
    }

    @Given("^when I choose the following items:$")
    public void i_choose_following_items(DataTable items) {
// put in asserts
    }

    @Then("^I proceed to the checkout$")
    public void i_proceed_to_checkout() {
    }

    @Then("^a total price of £(.*) will be displayed$")
// put in asserts
    public void price_is_displayed(String totalPrice) throws Throwable {
    }

    @Then("^£(.*) will be listed for VAT$")
    public void vat_is_displayed(String vat) throws Throwable {
// put in asserts
    }

    @Given("^Order Management System login page is displayed$")
    public void orderManagementSystemLoginPageIsDisplayed() {
        
    }

    @When("^Fund Manager logs in$")
    public void fundManagerLogsIn() {
        
    }

    @Then("^\"([^\"]*)\" login page is displayed$")
    public void loginPageIsDisplayed(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^Fund Manager selects \"([^\"]*)\" – \"([^\"]*)\" in Order Type dialog$")
    public void fundManagerSelectsInOrderTypeDialog(String arg0, String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^System displays Single Order – Future screen$")
    public void systemDisplaysSingleOrderFutureScreen() {
    }

    @And("^Enter order details \"([^\"]*)\"$")
    public void enterOrderDetails(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}