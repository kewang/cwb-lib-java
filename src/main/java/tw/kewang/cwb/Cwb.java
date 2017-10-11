package tw.kewang.cwb;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tw.kewang.cwb.datalist.FD0047;
import tw.kewang.cwb.pretty.FutureWeatherByCity;
import tw.kewang.cwb.pretty.FutureWeatherByTown;
import tw.kewang.cwb.utils.Constants;

import java.util.Date;

public class Cwb {
    private static final Logger LOG = LoggerFactory.getLogger(Cwb.class);

    private static String apiKey;
    private static CwbSender sender;
    private static boolean init;

    private static final Cwb instance = new Cwb();

    private Cwb() {
    }

    public static Cwb getInstance() {
        return instance;
    }

    public Cwb init(String apiKey) {
        if (StringUtils.isEmpty(apiKey)) {
            init = false;

            throw new IllegalArgumentException("Please indicate apiKey");
        }

        Cwb.apiKey = apiKey;

        init = true;

        Geocode.init();

        sender = new CwbSender(instance);

        return instance;
    }

    public String getApiKey() {
        return apiKey;
    }

    /**
     * 取得該城市的未來天氣
     *
     * @param data
     * @return
     */
    public FutureWeatherByCity getFutureWeatherByCity(String data) {
        FD0047.ByCity city = FD0047.ByCity.find(data);

        if (city == null) {
            return new FutureWeatherByCity();
        }

        return getFutureWeatherByCity(city);
    }

    /**
     * 取得該城市的未來天氣
     *
     * @param city
     * @return
     */
    public FutureWeatherByCity getFutureWeatherByCity(FD0047.ByCity city) {
        return sender.sendFutureWeatherByCity(city);
    }

    /**
     * 取得該鄉鎮的未來天氣
     *
     * @param data
     * @param afterHours
     * @return
     */
    public FutureWeatherByTown getFutureWeatherByTown(String data, float afterHours) {
        Geocode geocode = Geocode.find(data);

        if (geocode == null) {
            return new FutureWeatherByTown();
        }

        Date date = new Date(System.currentTimeMillis() + (long) (Constants.ONE_HOUR * afterHours));

        LOG.debug("getFutureWeatherByTown: {}, {}", geocode.toString(), date.toString());

        return getFutureWeatherByTown(geocode, date);
    }

    /**
     * 取得該鄉鎮的未來天氣
     *
     * @param geocode
     * @param afterHours
     * @return
     */
    public FutureWeatherByTown getFutureWeatherByTown(Geocode geocode, float afterHours) {
        Date date = new Date(System.currentTimeMillis() + (long) (Constants.ONE_HOUR * afterHours));

        LOG.debug("getFutureWeatherByTown: {}, {}", geocode, date.toString());

        return getFutureWeatherByTown(geocode, date);
    }

    /**
     * 取得該鄉鎮的未來天氣
     *
     * @param geocode
     * @param date
     * @return
     */
    public FutureWeatherByTown getFutureWeatherByTown(Geocode geocode, Date date) {
        FD0047.ByCity city = FD0047.ByCity.findByTown(geocode);

        if (city == null) {
            return new FutureWeatherByTown();
        }

        LOG.debug("getFutureWeatherByTown: {}, {}", geocode.toString(), date.toString());

        return sender.sendFutureWeatherByTown(city, geocode, date);
    }
}