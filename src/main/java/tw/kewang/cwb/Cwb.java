package tw.kewang.cwb;

import org.apache.commons.lang3.StringUtils;
import tw.kewang.cwb.datalist.FD0047;
import tw.kewang.cwb.pretty.FutureWeatherByCity;
import tw.kewang.cwb.pretty.FutureWeatherByTown;

public class Cwb {
    private static String apiKey;
    private static CwbSender sender = new CwbSender();
    private static boolean init;

    private Cwb() {
    }

    public static void init(String apiKey) {
        if (StringUtils.isEmpty(apiKey)) {
            init = false;

            throw new IllegalArgumentException("Please indicate apiKey");
        }

        Cwb.apiKey = apiKey;

        init = true;

        Geocode.init();
    }

    public static String getApiKey() {
        return apiKey;
    }

    public static FutureWeatherByCity getFutureWeatherByCity(String data) {
        FD0047.ByCity city = FD0047.ByCity.find(data);

        if (city == null) {
            return new FutureWeatherByCity();
        }

        return getFutureWeatherByCity(city);
    }

    public static FutureWeatherByCity getFutureWeatherByCity(FD0047.ByCity city) {
        return sender.sendFutureWeatherByCity(city);
    }

    public static FutureWeatherByTown getFutureWeatherByTown(String data) {
        Geocode geocode = Geocode.find(data);

        return getFutureWeatherByTown(geocode);
    }

    public static FutureWeatherByTown getFutureWeatherByTown(Geocode geocode) {
        FD0047.ByCity city = FD0047.ByCity.findByTown(geocode);

        if (city == null) {
            return new FutureWeatherByTown();
        }

        return sender.sendFutureWeatherByTown(city, geocode);
    }
}