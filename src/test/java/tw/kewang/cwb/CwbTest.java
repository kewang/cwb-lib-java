package tw.kewang.cwb;

import org.junit.Before;
import org.junit.Test;
import tw.kewang.cwb.datalist.FutureWeatherByCity;
import tw.kewang.cwb.datalist.FutureWeatherByTown;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CwbTest {
    @Before
    public void setup() {
        Cwb.init("");
    }

    @Test
    public void testGeocode() {
        assertEquals("6300100", Geocode.find("松山區").getCode());
        assertEquals("通霄鎮", Geocode.find("1000503").getName());
        assertNull(Geocode.find("白宮"));
    }

    @Test
    public void testGetFutureWeatherByCityWithString() {
        FutureWeatherByCity weather = Cwb.getFutureWeatherByCity("彰化縣");

        assertEquals("6500500", weather.getLocationsName());
    }

//    @Test
//    public void testGetFutureWeatherByTownWithGeocode() {
//        FutureWeatherByTown weather = Cwb.getFutureWeatherByTown(Geocode.find("新莊區"));
//
//        assertEquals("6500500", weather.getGeocode());
//    }
//
//    @Test
//    public void testGetFutureWeatherByTownWithString() {
//        FutureWeatherByTown weather = Cwb.getFutureWeatherByTown("新莊區");
//
//        assertEquals("6500500", weather.getGeocode());
//    }
}