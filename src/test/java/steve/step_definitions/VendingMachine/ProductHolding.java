package steve.step_definitions.VendingMachine;

import steve.step_definitions.Vending.Item;

import java.util.HashMap;
import java.util.Map;

public class ProductHolding {
    private HashMap<Item, Integer> holding = new HashMap<Item, Integer>();

    public int getQuantity(Item product){
        Integer value = holding.get(product);
        return value == null? 0 : value ;
    }

    public void add(Item product){
        int count = holding.get(product);
        holding.put(product, count+1);
    }

    public void deduct(Item product) {
        if (hasItem(product)) {
            int count = holding.get(product);
            holding.put(product, count - 1);
        }
    }

    public boolean hasItem(Item product){
        return getQuantity(product) > 0;
    }

    public void clear(){
        holding.clear();
    }

    public void put(Item product, int quantity) {
        holding.put(product, quantity);
    }}
