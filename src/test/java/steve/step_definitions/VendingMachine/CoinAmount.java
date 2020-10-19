package steve.step_definitions.VendingMachine;

public enum CoinAmount {
    PENNY(1), NICKLE(5), DIME(10), QUARTER(25);

    private int pennies;

    private CoinAmount(int pennies){
        this.pennies = pennies;
    }

    public int getPennies(){
        return pennies;
    }
}
