package steve.step_definitions.RestAss.pojo;

import java.util.List;

public class Networks {
    private List<String> company;
    private String href;
    private String id;
    private Locations location;
    private String name;
    private String source;
    private String gbfs_href;
    private Licenses license;
    private List<Stations> stations;

    public Networks() {}

    public Networks(List<String> company, String href, String id, Locations location, String name, String source, String gbfs_href, Licenses license, List<Stations> stations) {
        this.company = company;
        this.href = href;
        this.id = id;
        this.location = location;
        this.name = name;
        this.source = source;
        this.gbfs_href = gbfs_href;
        this.license = license;
        this.stations = stations;
    }

    public List<String> getCompany() {
        return company;
    }

    public void setCompany(List<String> company) {
        this.company = company;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Locations getLocation() {
        return location;
    }

    public void setLocation(Locations location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getGbfs_href() {
        return gbfs_href;
    }

    public void setGbfs_href(String gbfs_href) {
        this.gbfs_href = gbfs_href;
    }

    public Licenses getLicense() {
        return license;
    }

    public void setLicense(Licenses license) {
        this.license = license;
    }

    public List<Stations> getStations() {
        return stations;
    }

    public void setStations(List<Stations> stations) {
        this.stations = stations;
    }
}
