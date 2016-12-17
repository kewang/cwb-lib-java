package tw.kewang.cwb;

import org.apache.commons.lang3.StringUtils;
import tw.kewang.cwb.datalist.Datalist;
import tw.kewang.cwb.datalist.FutureWeatherByCity;
import tw.kewang.cwb.datalist.FutureWeatherByTown;

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

    public static FutureWeatherByTown getFutureWeatherByTown(Geocode geocode) {
        return sender.send(Datalist.FUTURE_WEATHER_BY_TOWN, geocode);
    }

    public static FutureWeatherByTown getFutureWeatherByTown(String data) {
        return getFutureWeatherByTown(Geocode.find(data));
    }

    public static FutureWeatherByCity getFutureWeatherByCity(String data) {
        return sender.sendFutureWeatherByCity(Datalist.FUTURE_WEATHER_BY_CITY, data);
    }
}