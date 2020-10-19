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
    static int aggregateTotal = 0;

    @Given("^i want to buy a \"([^\"]*)\"$")
    public static void iWantToBuyADrink(String drink) {
        switch (drink) {
            case "Coke" :
                price += 25;
                break;
            case "Pepsi" :
                price += 35;
                break;
            case "Soda" :
                price += 45;
                break;
        }
    }

    @And("^i enter the following coins$")
    public static void iEnterTheFollowingCoins(DataTable coinsInserted) {
        List<Map<String, String>> table = coinsInserted.asMaps(String.class, String.class);

        for (Map<String, String> rows : table) {
            for (Map.Entry<String, String> cols : rows.entrySet()) {
                switch (cols.getValue()) {
                    case "Penny" :
                        coinTotal += 1;
                        break;
                    case "Nickle" :
                        coinTotal += 5;
                        break;
                    case "Dime" :
                        coinTotal += 10;
                        break;
                    case "Quarter" :
                        coinTotal += 25;
                        break;
                }
            }
        }
    }

    @Then("^i  receive a refund of (\\d+)$")
    public void iReceiveARefundOf(int refund) {
        Assert.assertEquals(refund, coinTotal - price);
        aggregateTotal += price;
        coinTotal = 0;
        price = 0;
    }

    @Then("^supplier resets machine and takes accumulated cash of (\\d+)$")
    public void supplierEmptiesMachineOf(int accumulatedCash) {
        Assert.assertEquals(accumulatedCash, aggregateTotal);
        aggregateTotal = 0;
    }
}
