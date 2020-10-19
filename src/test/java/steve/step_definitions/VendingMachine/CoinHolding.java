package steve.step_definitions.VendingMachine;

import java.util.HashMap;
import java.util.Map;

public class CoinHolding {
    private HashMap<CoinAmount, Integer> holding = new HashMap<CoinAmount, Integer>();

    public int getQuantity(CoinAmount coin){
        Integer value = holding.get(coin);
        return value == null? 0 : value ;
    }

    public void add(CoinAmount coin){
        int count = holding.get(coin);
        holding.put(coin, count+1);
    }

    public void deduct(CoinAmount coin) {
        if (hasItem(coin)) {
            int count = holding.get(coin);
            holding.put(coin, count - 1);
        }
    }

    public boolean hasItem(CoinAmount coin){
        return getQuantity(coin) > 0;
    }

    public void clear(){
        holding.clear();
    }

    public void put(CoinAmount coin, int quantity) {
        holding.put(coin, quantity);
    }

}
