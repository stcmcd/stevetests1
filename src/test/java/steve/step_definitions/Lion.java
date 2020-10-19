package steve.step_definitions;

import org.junit.Test;

public class Lion implements Animal {

//    public Lion() {
//        System.out.println("Constructor called");
//    }

    public void eat() {
        System.out.println("www");
        // Lion's way to eat
    }

    public void sleep(){
        // Lion's way to sleep
    }

    @Test
    public void main() {
        Lion lion = new Lion();
        lion.eat();
    }
}
