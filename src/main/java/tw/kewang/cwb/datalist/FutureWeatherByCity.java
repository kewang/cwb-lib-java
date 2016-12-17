package tw.kewang.cwb.datalist;

import tw.kewang.cwb.dictionary.Locations;

public class FutureWeatherByCity extends Locations {
    public enum ByCity {
        CHANGHUA("017", "彰化縣");

        private final String dataid;
        private final String city;

        ByCity(String dataid, String city) {
            this.dataid = dataid;
            this.city = city;
        }

        public String getDataid() {
            return dataid;
        }

        public String getCity() {
            return city;
        }

        public static ByCity find(String data) {
            for (ByCity e : values()) {
                if (e.city.equalsIgnoreCase(data) || e.dataid.equalsIgnoreCase(data)) {
                    return e;
                }
            }

            return null;
        }
    }
}