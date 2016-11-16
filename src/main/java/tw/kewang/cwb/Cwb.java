package tw.kewang.cwb;

import org.apache.commons.lang3.StringUtils;

public class Cwb {
    private static String apiKey;
    private static boolean init;

    private Cwb() {
    }

    public static void init(String apiKey) {
        if (StringUtils.isEmpty(apiKey)) {
            init = false;

            throw new IllegalArgumentException("Please indicate apiKey");
        }

        Cwb.apiKey = apiKey;

        init = true;
    }

    public static String getApiKey() {
        return apiKey;
    }

    // TODO: query data
    public static void query() {
        return;
    }
}