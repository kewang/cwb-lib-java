package tw.kewang.cwb;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tw.kewang.cwb.datalist.FD0047;
import tw.kewang.cwb.dictionary.Location;
import tw.kewang.cwb.pretty.FutureWeatherByCity;
import tw.kewang.cwb.pretty.FutureWeatherByTown;
import tw.kewang.cwb.utils.JsonUtils;

import java.io.IOException;

public class CwbSender {
    private static final Logger LOG = LoggerFactory.getLogger(CwbSender.class);

    private OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new TokenInterceptor()).build();

    public FutureWeatherByCity sendFutureWeatherByCity(FD0047.ByCity city) {
        Request req = new Request.Builder().url("http://opendata.cwb.gov.tw/api/v1/rest/datastore/F-D0047-" + city.getDataId()).build();

        try {
            Response res = client.newCall(req).execute();

            String body = res.body().string();

            FD0047 rawData = JsonUtils.fromJson(body, FD0047.class);

            return new FutureWeatherByCity(rawData);
        } catch (IOException e) {
            LOG.error("Caught Exception: ", e);

            return new FutureWeatherByCity();
        }
    }

    public FutureWeatherByTown sendFutureWeatherByTown(FD0047.ByCity city, Geocode geocode) {
        Request req = new Request.Builder().url("http://opendata.cwb.gov.tw/api/v1/rest/datastore/F-D0047-" + city.getDataId()).build();

        try {
            Response res = client.newCall(req).execute();

            String body = res.body().string();

            FD0047 rawData = JsonUtils.fromJson(body, FD0047.class);

            for (Location location : rawData.getRecords().getLocations().get(0).getLocation()) {
                if (location.getGeocode().equalsIgnoreCase(geocode.getCode())) {
                    return new FutureWeatherByTown(location);
                }
            }

            return new FutureWeatherByTown();
        } catch (IOException e) {
            LOG.error("Caught Exception: ", e);

            return new FutureWeatherByTown();
        }
    }
}