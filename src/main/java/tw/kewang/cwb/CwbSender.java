package tw.kewang.cwb;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tw.kewang.cwb.datalist.FD0047;
import tw.kewang.cwb.dictionary.Location;
import tw.kewang.cwb.pretty.FutureWeatherByCity;
import tw.kewang.cwb.pretty.FutureWeatherByTown;
import tw.kewang.cwb.utils.JsonUtils;

import java.io.IOException;
import java.net.Proxy;
import java.util.Date;

public class CwbSender {
    private static final Logger LOG = LoggerFactory.getLogger(CwbSender.class);
    private static final String ERROR_PREFIX = "Caught Exception: ";
    private static final String URL_FD0047 = "http://opendata.cwb.gov.tw/api/v1/rest/datastore/F-D0047-";

    private OkHttpClient client;

    public CwbSender(Cwb cwb, Proxy proxy) {
        this.client = new OkHttpClient.Builder().proxy(proxy).addInterceptor(new TokenInterceptor(cwb)).build();
    }

    public FutureWeatherByCity sendFutureWeatherByCity(FD0047.ByCity city) {
        Request req = new Request.Builder().url(URL_FD0047 + city.getDataId()).build();

        try {
            FD0047 rawData = buildFD0047RawData(req);

            return new FutureWeatherByCity(rawData);
        } catch (IOException e) {
            LOG.error(ERROR_PREFIX, e);

            return new FutureWeatherByCity();
        }
    }

    public FutureWeatherByTown sendFutureWeatherByTown(FD0047.ByCity city, Geocode geocode, Date date) {
        Request req = new Request.Builder().url(URL_FD0047 + city.getDataId()).build();

        try {
            FD0047 rawData = buildFD0047RawData(req);

            for (Location location : rawData.getRecords().getLocations().get(0).getLocation()) {
                if (location.getGeocode().equalsIgnoreCase(geocode.getCode())) {
                    return new FutureWeatherByTown(location, date);
                }
            }

            return new FutureWeatherByTown();
        } catch (IOException e) {
            LOG.error(ERROR_PREFIX, e);

            return new FutureWeatherByTown();
        }
    }

    private FD0047 buildFD0047RawData(Request req) throws IOException {
        String body = client.newCall(req).execute().body().string();

        return JsonUtils.fromJson(body, FD0047.class);
    }
}