package steve.step_definitions.RestAss.pojo;

public class IndustryIdentidiers {
    String type;
    String Identifier;

    public IndustryIdentidiers() {}

    public IndustryIdentidiers(String type, String identifier) {
        this.type = type;
        Identifier = identifier;
    }

    public String getType() {
        return type;
    }

    public String getIdentifier() {
        return Identifier;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setIdentifier(String identifier) {
        Identifier = identifier;
    }

}
