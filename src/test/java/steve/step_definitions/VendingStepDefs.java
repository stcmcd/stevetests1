package steve.step_definitions;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;

import java.util.*;

public class VendingStepDefs {
    static int price = 0;
    static int coinTotal = 0;

    @Given("^i want to buy a \"([^\"]*)\"$")
    public static void iWantToBuyADrink(String drink) throws Throwable {
        switch (drink) {
            case "Coke" :
                price = 25;
                break;
            case "Pepsi" :
                price = 35;
                break;
            case "Soda" :
                price = 45;
                break;
        }
    }

    @And("^i enter the following coins$")
    public static void iEnterTheFollowingCoins(DataTable coinsInserted) throws Throwable {
        coinTotal = 0;
        List<Map<String, String>> table = coinsInserted.asMaps(String.class, String.class);

        for (Map<String, String> rows : table) {
            for (Map.Entry<String, String> cols : rows.entrySet()) {
                switch (cols.getValue()) {
                    case "Penny" :
                        coinTotal = coinTotal + 1;
                        break;
                    case "Nickle" :
                        coinTotal = coinTotal + 5;
                        break;
                    case "Dime" :
                        coinTotal = coinTotal + 10;
                        break;
                    case "Quarter" :
                        coinTotal = coinTotal + 25;
                        break;
                }
            }
        }
    }

    @Then("^i  receive a refund of (\\d+)$")
    public void iReceiveARefundOf(int refund) {
        Assert.assertEquals(refund, coinTotal - price);
    }
}
