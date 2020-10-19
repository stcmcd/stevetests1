package steve.step_definitions.RestAss.pojo;

import java.util.List;

public class Bikes {
    private Networks network;

    public Bikes() {
//        this.networks = networks;
    }

    public Bikes(Networks network) {
        this.network = network;
    }

    public Networks getNetwork() {
        return network;
    }

    public void setNetworks(Networks network) {
        this.network = network;
    }
}
