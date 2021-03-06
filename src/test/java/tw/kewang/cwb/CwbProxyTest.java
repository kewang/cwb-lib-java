package tw.kewang.cwb;

import org.junit.Before;
import org.junit.Test;
import tw.kewang.cwb.datalist.FD0047;
import tw.kewang.cwb.pretty.FutureWeatherByCity;
import tw.kewang.cwb.pretty.FutureWeatherByTown;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;

import static org.junit.Assert.*;

public class CwbProxyTest {
    private Cwb cwb;

    @Before
    public void setup() {
        cwb = Cwb.getInstance().proxy(new Proxy(Type.HTTP, new InetSocketAddress("", 0))).init(System.getenv("CWB_APIKEY"));
    }

    @Test
    public void testGeocode() {
        assertEquals("6300100", Geocode.find("松山區").getCode());
        assertEquals("通霄鎮", Geocode.find("1000503").getName());
        assertNull(Geocode.find("白宮"));
    }

    @Test
    public void testGetFutureWeatherByCity() {
        FutureWeatherByCity weather1 = cwb.getFutureWeatherByCity(FD0047.ByCity.CHAIYI_COUNTY);
        FutureWeatherByCity weather2 = cwb.getFutureWeatherByCity(FD0047.ByCity.TAIPEI);

        assertEquals("嘉義縣", weather1.getName());
        assertEquals("臺北市", weather2.getName());
    }

    @Test
    public void testGetFutureWeatherByCityWithString() {
        FutureWeatherByCity weather1 = cwb.getFutureWeatherByCity("彰化縣");
        FutureWeatherByCity weather2 = cwb.getFutureWeatherByCity("049");
        FutureWeatherByCity weather3 = cwb.getFutureWeatherByCity("猜猜看");
        FutureWeatherByCity weather4 = cwb.getFutureWeatherByCity("台東縣");
        FutureWeatherByCity weather5 = cwb.getFutureWeatherByCity("臺東縣");

        assertEquals("彰化縣", weather1.getName());
        assertEquals("基隆市", weather2.getName());
        assertEquals("找不到資料", weather3.getName());
        assertEquals("臺東縣", weather4.getName());
        assertEquals("臺東縣", weather5.getName());
    }

    @Test
    public void testGetFutureWeatherByTown() {
        FutureWeatherByTown weather1 = cwb.getFutureWeatherByTown("新莊區", 24);
        FutureWeatherByTown weather2 = cwb.getFutureWeatherByTown("猜猜看", 24);

        assertEquals("新莊區", weather1.getName());
        assertNotNull(weather1.getWeatherElements());
        assertEquals("找不到資料", weather2.getName());
    }

    @Test
    public void testGetFutureWeatherByTownWithGeocode() {
        FutureWeatherByTown weather1 = cwb.getFutureWeatherByTown(Geocode.find("新莊區"), 24);
        FutureWeatherByTown weather2 = cwb.getFutureWeatherByTown(Geocode.find("猜猜看"), 24);

        assertEquals("新莊區", weather1.getName());
        assertEquals("找不到資料", weather2.getName());
    }

    @Test
    public void testGetFutureWeatherByTownWithWeatherElements() {
        FutureWeatherByTown weather1 = cwb.getFutureWeatherByTown("新莊區", 2);

        System.out.println(weather1.getDescription().getStartDate());
        System.out.println(weather1.getDescription().getEndDate());
        System.out.println(weather1.getDescription().getDetail());
        System.out.println(weather1.getDescription().getShort());
        System.out.println(weather1.getComfortable().getDataDate());
        System.out.println(weather1.getComfortable().getDescription());
        System.out.println(weather1.getComfortable().getValue());
        System.out.println(weather1.getApparent());
        System.out.println(weather1.getTemperature());
        System.out.println(weather1.getPoP());
        System.out.println(weather1.getRH());
        System.out.println(weather1.getWind().getDataDate());
        System.out.println(weather1.getWind().getDirectionDetail());
        System.out.println(weather1.getWind().getDirectionShort());
        System.out.println(weather1.getWind().getScale());
        System.out.println(weather1.getWind().getSpeed());

        assertNotNull(weather1.getDescription().getStartDate());
        assertNotNull(weather1.getDescription().getEndDate());
        assertNotNull(weather1.getDescription().getDetail());
        assertNotNull(weather1.getDescription().getShort());
        assertNotNull(weather1.getComfortable().getDataDate());
        assertNotNull(weather1.getComfortable().getDescription());
        assertNotNull(weather1.getComfortable().getValue());
        assertNotNull(weather1.getApparent());
        assertNotNull(weather1.getTemperature());
        assertNotNull(weather1.getPoP());
        assertNotNull(weather1.getRH());
        assertNotNull(weather1.getWind().getDataDate());
        assertNotNull(weather1.getWind().getDirectionDetail());
        assertNotNull(weather1.getWind().getDirectionShort());
        assertNotNull(weather1.getWind().getScale());
        assertNotNull(weather1.getWind().getSpeed());
    }

    @Test
    public void testGetFutureWeatherByTownWithWeatherElementsExceed() {
        FutureWeatherByTown weather1 = cwb.getFutureWeatherByTown("新莊區", 10);

        System.out.println(weather1.getDescription().getStartDate());
        System.out.println(weather1.getDescription().getEndDate());
        System.out.println(weather1.getDescription().getDetail());
        System.out.println(weather1.getDescription().getShort());
        System.out.println(weather1.getComfortable().getDataDate());
        System.out.println(weather1.getComfortable().getDescription());
        System.out.println(weather1.getComfortable().getValue());
        System.out.println(weather1.getApparent());
        System.out.println(weather1.getTemperature());
        System.out.println(weather1.getPoP());
        System.out.println(weather1.getRH());
        System.out.println(weather1.getWind().getDataDate());
        System.out.println(weather1.getWind().getDirectionDetail());
        System.out.println(weather1.getWind().getDirectionShort());
        System.out.println(weather1.getWind().getScale());
        System.out.println(weather1.getWind().getSpeed());

        assertNotNull(weather1.getDescription().getStartDate());
        assertNotNull(weather1.getDescription().getEndDate());
        assertNotNull(weather1.getDescription().getDetail());
        assertNotNull(weather1.getDescription().getShort());
        assertNotNull(weather1.getComfortable().getDataDate());
        assertNotNull(weather1.getComfortable().getDescription());
        assertNotNull(weather1.getComfortable().getValue());
        assertNotNull(weather1.getApparent());
        assertNotNull(weather1.getTemperature());
        assertNotNull(weather1.getPoP());
        assertNotNull(weather1.getRH());
        assertNotNull(weather1.getWind().getDataDate());
        assertNotNull(weather1.getWind().getDirectionDetail());
        assertNotNull(weather1.getWind().getDirectionShort());
        assertNotNull(weather1.getWind().getScale());
        assertNotNull(weather1.getWind().getSpeed());
    }
}