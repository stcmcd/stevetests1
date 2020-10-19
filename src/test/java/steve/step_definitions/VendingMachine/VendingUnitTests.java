package steve.step_definitions.VendingMachine;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import steve.step_definitions.Vending.*;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VendingUnitTests {
    private VendingMachine vendingMachine;

    @Test
    public void testBuyItemWithExactPrice() {
        VendingMachine vendingMachine = new VendingMachine();

        long price = vendingMachine.selectItemAndGetPrice(Item.COKE);

        vendingMachine.enterCoins(CoinAmount.QUARTER);

        Bucket<Item, List<CoinAmount>> bucket = vendingMachine.collectItemAndChange();
        Item item = bucket.getFirst();
        List<CoinAmount> change = bucket.getSecond();

        //should be Coke
        assertEquals(Item.COKE, item);
        //there should not be any change
        assertTrue(change.isEmpty());
    }

    @Test
    public void testBuyItemWithMorePrice(){
        VendingMachine vendingMachine = new VendingMachine();
        long price = vendingMachine.selectItemAndGetPrice(Item.SODA);
        assertEquals(Item.SODA.getPrice(), price);

        vendingMachine.enterCoins(CoinAmount.QUARTER);
        vendingMachine.enterCoins(CoinAmount.QUARTER);

        Bucket<Item, List<CoinAmount>> bucket = vendingMachine.collectItemAndChange();
        Item item = bucket.getFirst();
        List<CoinAmount> change = bucket.getSecond();

        //should be Coke
        assertEquals(Item.SODA, item);
        //there should not be any change
        assertTrue(!change.isEmpty());
        //comparing change
        assertEquals(50 - Item.SODA.getPrice(), getTotal(change));

    }


    @Test
    public void testRefund(){
        VendingMachine vendingMachine = new VendingMachine();
        long price = vendingMachine.selectItemAndGetPrice(Item.PEPSI);
        assertEquals(Item.PEPSI.getPrice(), price);
        vendingMachine.enterCoins(CoinAmount.DIME);
        vendingMachine.enterCoins(CoinAmount.NICKLE);
        vendingMachine.enterCoins(CoinAmount.PENNY);
        vendingMachine.enterCoins(CoinAmount.QUARTER);

        assertEquals(41, getTotal(vendingMachine.refund()));
    }

    private long getTotal(List<CoinAmount> change){
        long total = 0;
        for(CoinAmount c : change){
            total = total + c.getPennies();
        }
        return total;
    }
}
