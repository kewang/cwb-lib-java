package tw.kewang.cwb;

import org.junit.Before;
import org.junit.Test;

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
}