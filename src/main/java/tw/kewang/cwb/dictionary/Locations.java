package tw.kewang.cwb.dictionary;

import java.util.List;

public class Locations {
    private String dataid;
    private String datasetDescription;
    private List<Location> location;
    private String locationsName;

    public String getDataid() {
        return dataid;
    }

    public String getDatasetDescription() {
        return datasetDescription;
    }

    public List<Location> getLocation() {
        return location;
    }

    public String getLocationsName() {
        return locationsName;
    }
}