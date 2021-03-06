package tw.kewang.cwb;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Geocode {
    private static final Logger LOG = LoggerFactory.getLogger(Geocode.class);

    private static HashMap<String, Geocode> MAPS = new HashMap<>();

    private static boolean isInit = false;

    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return code + ": " + name;
    }

    public static Geocode find(String data) {
        if (!isInit) {
            LOG.error("Uninitialized");

            return null;
        }

        Geocode result = MAPS.get(data);

        // find by code
        if (result != null) {
            return result;
        }

        // find by name
        for (Geocode geocode : MAPS.values()) {
            if (geocode.name.equalsIgnoreCase(data)) {
                return geocode;
            }
        }

        // not found
        return null;
    }

    public static void init() {
        if (isInit) {
            return;
        }

        List<String> lines = new ArrayList<>();

        try {
            ClassLoader classLoader = Geocode.class.getClassLoader();

            lines = IOUtils.readLines(classLoader.getResourceAsStream("geocode.txt"), Charset.defaultCharset());
        } catch (IOException e) {
            LOG.error("Caught Exception: ", e);
        }

        for (String line : lines) {
            String[] parts = line.split(",");

            Geocode geocode = new Geocode();

            geocode.code = parts[0];
            geocode.name = parts[1];

            MAPS.put(parts[0], geocode);
        }

        isInit = true;
    }
}