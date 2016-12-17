package tw.kewang.cwb.pretty;

import tw.kewang.cwb.dictionary.Location;

public class FutureWeatherByTown {
    private Location location;

    public FutureWeatherByTown() {
    }

    public FutureWeatherByTown(Location location) {
        this.location = location;
    }

    public String getTown() {
        return location.getLocationName();
    }
}
