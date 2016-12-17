package tw.kewang.cwb.pretty;

import tw.kewang.cwb.datalist.FD0047;

public class FutureWeatherByCity {
    private FD0047 rawData;

    public FutureWeatherByCity() {
    }

    public FutureWeatherByCity(FD0047 rawData) {
        this.rawData = rawData;
    }

    public FD0047 getRawData() {
        return rawData;
    }

    public String getCity() {
        return rawData.getRecords().getLocations().get(0).getLocationsName();
    }
}