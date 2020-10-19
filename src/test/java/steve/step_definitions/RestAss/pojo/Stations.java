package steve.step_definitions.RestAss.pojo;

public class Stations {
    private String empty_slots;
    private Extras extra;
    private Integer free_bikes;
    private String id;
    private String latitude;
    private String longitude;
    private String name;
    private String timestamp;

    public Stations() {}

    public Stations(String empty_slots, Extras extra, Integer free_bikes, String id, String latitude, String longitude, String name, String timestamp) {
        this.empty_slots = empty_slots;
        this.extra = extra;
        this.free_bikes = free_bikes;
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.timestamp = timestamp;
    }

    public String getEmpty_slots() {
        return empty_slots;
    }

    public void setEmpty_slots(String empty_slots) {
        this.empty_slots = empty_slots;
    }

    public Extras getExtra() {
        return extra;
    }

    public void setExtra(Extras extra) {
        this.extra = extra;
    }

    public Integer getFree_bikes() {
        return free_bikes;
    }

    public void setFree_bikes(Integer free_bikes) {
        this.free_bikes = free_bikes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
