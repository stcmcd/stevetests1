package steve.step_definitions.Vending;

import java.util.*;

class Machine
{
    public static void main(String args[])
    {
        Sample sp = new Sample();
        Scanner sc = new Scanner(System.in);
        System.out.println("\t\t\tWelcome to Vending Machine.");
        System.out.println("Put coins in the denomination of: 1,5,10,25,50,100,200");
        int coins = sc.nextInt();
        sp.input(coins);
        System.out.println("Select one of the Items: \n");
        System.out.println("1.CANDY(10)");
        System.out.println("2.SNACK(50)");
        System.out.println("3.NUTS(90)");
        System.out.println("4.COKE(25)");
        System.out.println("5.PEPSI(35)");
        System.out.println("6.SODA(45)");
        System.out.println("7.CANCEL");
        System.out.println("8.RESET");
        int choice = sc.nextInt();
        sp.select(choice);

    }
}
