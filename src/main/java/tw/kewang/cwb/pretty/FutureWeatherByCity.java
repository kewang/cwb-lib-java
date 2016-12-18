package tw.kewang.cwb.pretty;

import tw.kewang.cwb.datalist.FD0047;
import tw.kewang.cwb.utils.Constants;

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

    public String getName() {
        if (rawData == null) {
            return Constants.NOT_FOUND;
        }

        return rawData.getRecords().getLocations().get(0).getLocationsName();
    }
}