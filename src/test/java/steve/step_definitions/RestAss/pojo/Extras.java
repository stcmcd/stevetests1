package steve.step_definitions.RestAss.pojo;

import java.util.List;

public class Extras {
    private List<String> bike_uids;
    private String number;
    private String uid;
    private String address;
    private int ebikes;
    private int electric_free;
    private int electric_slots;
    private int normal_bikes;
    private int normal_free;
    private int normal_slots;
    private int slots;
    private Boolean has_ebikes;
    private int altitude;
    private int status;
    private String placement;
    private Boolean closed;

    public Extras() {}

    public Extras(List<String> bike_uids, String number, String uid, String address, int ebikes, int electric_free, int electric_slots, int normal_bikes, int normal_free, int normal_slots, int slots, Boolean has_ebikes, int altitude, int status, String placement, Boolean closed) {
        this.bike_uids = bike_uids;
        this.number = number;
        this.uid = uid;
        this.address = address;
        this.ebikes = ebikes;
        this.electric_free = electric_free;
        this.electric_slots = electric_slots;
        this.normal_bikes = normal_bikes;
        this.normal_free = normal_free;
        this.normal_slots = normal_slots;
        this.slots = slots;
        this.has_ebikes = has_ebikes;
        this.altitude = altitude;
        this.status = status;
        this.placement = placement;
        this.closed = closed;
    }

    public List<String> getBike_uids() {
        return bike_uids;
    }

    public void setBike_uids(List<String> bike_uids) {
        this.bike_uids = bike_uids;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getEbikes() {
        return ebikes;
    }

    public void setEbikes(int ebikes) {
        this.ebikes = ebikes;
    }

    public int getElectric_free() {
        return electric_free;
    }

    public void setElectric_free(int electric_free) {
        this.electric_free = electric_free;
    }

    public int getElectric_slots() {
        return electric_slots;
    }

    public void setElectric_slots(int electric_slots) {
        this.electric_slots = electric_slots;
    }

    public int getNormal_bikes() {
        return normal_bikes;
    }

    public void setNormal_bikes(int normal_bikes) {
        this.normal_bikes = normal_bikes;
    }

    public int getNormal_free() {
        return normal_free;
    }

    public void setNormal_free(int normal_free) {
        this.normal_free = normal_free;
    }

    public int getNormal_slots() {
        return normal_slots;
    }

    public void setNormal_slots(int normal_slots) {
        this.normal_slots = normal_slots;
    }

    public int getSlots() {
        return slots;
    }

    public void setSlots(int slots) {
        this.slots = slots;
    }

    public Boolean getHas_ebikes() {
        return has_ebikes;
    }

    public void setHas_ebikes(Boolean has_ebikes) {
        this.has_ebikes = has_ebikes;
    }

    public int getAltitude() {
        return altitude;
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPlacement() {
        return placement;
    }

    public void setPlacement(String placement) {
        this.placement = placement;
    }

    public Boolean getClosed() {
        return closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }
}
