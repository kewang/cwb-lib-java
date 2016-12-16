package tw.kewang.cwb.dictionary;

import java.util.List;

public class Location {
    private String lat;
    private String lon;
    private String locationName;
    private String geocode;
    private List<WeatherElement> weatherElement;

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

    public String getLocationName() {
        return locationName;
    }

    public String getGeocode() {
        return geocode;
    }

    public List<WeatherElement> getWeatherElement() {
        return weatherElement;
    }
}