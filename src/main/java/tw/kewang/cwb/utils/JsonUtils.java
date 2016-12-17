package tw.kewang.cwb.utils;

import com.google.gson.Gson;
import tw.kewang.cwb.datalist.FutureWeatherByCity;
import tw.kewang.cwb.datalist.FutureWeatherByTown;

public class JsonUtils {
    public static final Gson GSON = new Gson();

    public static FutureWeatherByTown fromJson(String string) {
        return GSON.fromJson(string, FutureWeatherByTown.class);
    }

    public static FutureWeatherByCity fromJsonByFutureWeatherByCity(String string) {
        return GSON.fromJson(string, FutureWeatherByCity.class);
    }
}
