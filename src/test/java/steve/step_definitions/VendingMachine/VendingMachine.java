package steve.step_definitions.VendingMachine;

import steve.step_definitions.Vending.*;
import steve.step_definitions.VendingMachine.CoinAmount;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VendingMachine {
    private CoinHolding coinHolding = new CoinHolding();
    private ProductHolding productHolding = new ProductHolding();
    private long totalSales;
    private Item currentItem;
    private long currentBalance;

    public VendingMachine(){
        initialize();
    }

    private void initialize(){
        for(CoinAmount c : CoinAmount.values()){
            coinHolding.put(c, 5);
        }

        for(Item i : Item.values()){
            productHolding.put(i, 5);
        }

    }

    public long selectItemAndGetPrice(Item item) {
            currentItem = item;
            return currentItem.getPrice();
    }

    public void enterCoins(CoinAmount coin) {
        currentBalance = currentBalance + CoinAmount.QUARTER.getPennies();
        coinHolding.add(coin);
    }

    public Bucket<Item, List<CoinAmount>> collectItemAndChange() {
        Item item = collectItem();
        totalSales = totalSales + currentItem.getPrice();

        List<CoinAmount> change = collectChange();

        return new Bucket<Item, List<CoinAmount>>(item, change);
    }

    private Item collectItem() {
        if(isFullPaid()){
            if(hasSufficientChange()){
                productHolding.deduct(currentItem);
                return currentItem;
            }
        }
        long remainingBalance = currentItem.getPrice() - currentBalance;
        return currentItem;
    }

    private List<CoinAmount> collectChange() {
        long changeAmount = currentBalance - currentItem.getPrice();
        List<CoinAmount> change = getChange(changeAmount);
        updateCashInventory(change);
        currentBalance = 0;
        currentItem = null;
        return change;
    }

    public List<CoinAmount> refund(){
        List<CoinAmount> refund = getChange(currentBalance);
        updateCashInventory(refund);
        currentBalance = 0;
        currentItem = null;
        return refund;
    }


    private boolean isFullPaid() {
        if(currentBalance >= currentItem.getPrice()){
            return true;
        }
        return false;
    }


    private List<CoinAmount> getChange(long amount) {
        List<CoinAmount> changes = Collections.EMPTY_LIST;

        if(amount > 0){
            changes = new ArrayList<CoinAmount>();
            long balance = amount;
            while(balance > 0){
                if(balance >= CoinAmount.QUARTER.getPennies()){
                    changes.add(CoinAmount.QUARTER);
                    balance = balance - CoinAmount.QUARTER.getPennies();
                    continue;

                }else if(balance >= CoinAmount.DIME.getPennies()) {
                    changes.add(CoinAmount.DIME);
                    balance = balance - CoinAmount.DIME.getPennies();
                    continue;

                }else if(balance >= CoinAmount.NICKLE.getPennies()) {
                    changes.add(CoinAmount.NICKLE);
                    balance = balance - CoinAmount.NICKLE.getPennies();
                    continue;

                }else if(balance >= CoinAmount.PENNY.getPennies()) {
                    changes.add(CoinAmount.PENNY);
                    balance = balance - CoinAmount.PENNY.getPennies();
                    continue;

                }else{
                    System.out.println("NotSufficientChange,Please try another product");
                }
            }
        }

        return changes;
    }

    public void reset(){
        coinHolding.clear();
        productHolding.clear();
        totalSales = 0;
        currentItem = null;
        currentBalance = 0;
    }

    private boolean hasSufficientChange(){
        return hasSufficientChangeForAmount(currentBalance - currentItem.getPrice());
    }

    private boolean hasSufficientChangeForAmount(long amount){
        boolean hasChange = true;
        try{
            getChange(amount);
        }catch(NotSufficientChangeException nsce){
            return hasChange = false;
        }

        return hasChange;
    }

    private void updateCashInventory(List<CoinAmount> change) {
        for(CoinAmount c : change){
            coinHolding.deduct(c);
        }
    }

    public long getTotalSales(){
        return totalSales;
    }
}
