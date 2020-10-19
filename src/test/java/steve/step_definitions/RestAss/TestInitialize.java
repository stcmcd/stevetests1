package steve.step_definitions.RestAss;

import cucumber.api.java.Before;
import steve.RestAssuredExtension;

public class TestInitialize {
    @Before

    public void TestSetup() {
        RestAssuredExtension restAssuredExtension  = new RestAssuredExtension();
    }



}
