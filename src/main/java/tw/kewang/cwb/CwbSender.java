package tw.kewang.cwb;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tw.kewang.cwb.datalist.Datalist;
import tw.kewang.cwb.datalist.FutureWeatherByCity;
import tw.kewang.cwb.datalist.FutureWeatherByCity.ByCity;
import tw.kewang.cwb.datalist.FutureWeatherByTown;
import tw.kewang.cwb.utils.JsonUtils;

import java.io.IOException;

public class CwbSender {
    private static final Logger LOG = LoggerFactory.getLogger(CwbSender.class);

    private OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new TokenInterceptor()).build();

    public FutureWeatherByTown send(Datalist datalist, Geocode geocode) {
        Request req = new Request.Builder().url("http://opendata.cwb.gov.tw/api/v1/rest/dataset/F-D0047-091").build();

        try {
            Response res = client.newCall(req).execute();

            return JsonUtils.fromJson(res.body().string());
        } catch (IOException e) {
            LOG.error("Caught Exception: ", e);

            return new FutureWeatherByTown();
        }
    }

    public FutureWeatherByCity sendFutureWeatherByCity(Datalist datalist, String data) {
        ByCity city = ByCity.find(data);

        Request req = new Request.Builder().url("http://opendata.cwb.gov.tw/api/v1/rest/dataset/F-D0047-" + city.getDataid()).build();

        try {
            Response res = client.newCall(req).execute();

            return JsonUtils.fromJsonByFutureWeatherByCity(res.body().string());
        } catch (IOException e) {
            LOG.error("Caught Exception: ", e);

            return new FutureWeatherByCity();
        }
    }
}
