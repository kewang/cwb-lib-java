package tw.kewang.cwb;

import org.junit.Before;
import org.junit.Test;
import tw.kewang.cwb.datalist.FD0047;
import tw.kewang.cwb.pretty.FutureWeatherByCity;
import tw.kewang.cwb.pretty.FutureWeatherByTown;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CwbTest {
    @Before
    public void setup() {
        Cwb.init("CWB_APIKEY");
    }

    @Test
    public void testGeocode() {
        assertEquals("6300100", Geocode.find("松山區").getCode());
        assertEquals("通霄鎮", Geocode.find("1000503").getName());
        assertNull(Geocode.find("白宮"));
    }

    @Test
    public void testGetFutureWeatherByCity() {
        FutureWeatherByCity weather1 = Cwb.getFutureWeatherByCity(FD0047.ByCity.CHAIYI_COUNTY);
        FutureWeatherByCity weather2 = Cwb.getFutureWeatherByCity(FD0047.ByCity.TAIPEI);

        assertEquals("嘉義縣", weather1.getCity());
        assertEquals("臺北市", weather2.getCity());
    }

    @Test
    public void testGetFutureWeatherByCityWithString() {
        FutureWeatherByCity weather1 = Cwb.getFutureWeatherByCity("彰化縣");
        FutureWeatherByCity weather2 = Cwb.getFutureWeatherByCity("049");
        FutureWeatherByCity weather3 = Cwb.getFutureWeatherByCity("猜猜看");
        FutureWeatherByCity weather4 = Cwb.getFutureWeatherByCity("台東縣");
        FutureWeatherByCity weather5 = Cwb.getFutureWeatherByCity("臺東縣");

        assertEquals("彰化縣", weather1.getCity());
        assertEquals("基隆市", weather2.getCity());
        assertEquals("找不到資料", weather3.getCity());
        assertEquals("臺東縣", weather4.getCity());
        assertEquals("臺東縣", weather5.getCity());
    }

    @Test
    public void testGetFutureWeatherByTown() {
        FutureWeatherByTown weather1 = Cwb.getFutureWeatherByTown("新莊區");

        assertEquals("新莊區", weather1.getTown());
    }

    @Test
    public void testGetFutureWeatherByTownWithGeocode() {
        FutureWeatherByTown weather1 = Cwb.getFutureWeatherByTown(Geocode.find("新莊區"));

        assertEquals("新莊區", weather1.getTown());
    }
}