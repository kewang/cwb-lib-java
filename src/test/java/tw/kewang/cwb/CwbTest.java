package tw.kewang.cwb;

import org.junit.Before;
import org.junit.Test;
import tw.kewang.cwb.datalist.FD0047;
import tw.kewang.cwb.pretty.FutureWeatherByCity;
import tw.kewang.cwb.pretty.FutureWeatherByTown;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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

        assertEquals("嘉義縣", weather1.getName());
        assertEquals("臺北市", weather2.getName());
    }

    @Test
    public void testGetFutureWeatherByCityWithString() {
        FutureWeatherByCity weather1 = Cwb.getFutureWeatherByCity("彰化縣");
        FutureWeatherByCity weather2 = Cwb.getFutureWeatherByCity("049");
        FutureWeatherByCity weather3 = Cwb.getFutureWeatherByCity("猜猜看");
        FutureWeatherByCity weather4 = Cwb.getFutureWeatherByCity("台東縣");
        FutureWeatherByCity weather5 = Cwb.getFutureWeatherByCity("臺東縣");

        assertEquals("彰化縣", weather1.getName());
        assertEquals("基隆市", weather2.getName());
        assertEquals("找不到資料", weather3.getName());
        assertEquals("臺東縣", weather4.getName());
        assertEquals("臺東縣", weather5.getName());
    }

    @Test
    public void testGetFutureWeatherByTown() {
        FutureWeatherByTown weather1 = Cwb.getFutureWeatherByTown("新莊區", System.currentTimeMillis() + 86400 * 1000 * 1);
        FutureWeatherByTown weather2 = Cwb.getFutureWeatherByTown("猜猜看", System.currentTimeMillis() + 86400 * 1000 * 1);

        assertEquals("新莊區", weather1.getName());
        assertNotNull(weather1.getWeatherElements());
        assertEquals("找不到資料", weather2.getName());
    }

    @Test
    public void testGetFutureWeatherByTownWithGeocode() {
        FutureWeatherByTown weather1 = Cwb.getFutureWeatherByTown(Geocode.find("新莊區"), System.currentTimeMillis() + 86400 * 1000 * 1);
        FutureWeatherByTown weather2 = Cwb.getFutureWeatherByTown(Geocode.find("猜猜看"), System.currentTimeMillis() + 86400 * 1000 * 1);

        assertEquals("新莊區", weather1.getName());
        assertEquals("找不到資料", weather2.getName());
    }

    @Test
    public void testGetFutureWeatherByTownWithWeatherElements() {
        FutureWeatherByTown weather1 = Cwb.getFutureWeatherByTown("新莊區", System.currentTimeMillis() + 86400 * 1000 * 1);

        System.out.println(weather1.getWeatherDescription());
        System.out.println(weather1.getApparent());
        System.out.println(weather1.getTemperature());
        System.out.println(weather1.getPoP());
        System.out.println(weather1.getRH());

        assertNotNull(weather1.getWeatherDescription());
        assertNotNull(weather1.getApparent());
        assertNotNull(weather1.getTemperature());
        assertNotNull(weather1.getPoP());
        assertNotNull(weather1.getRH());
    }
}