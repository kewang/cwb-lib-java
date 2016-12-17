package tw.kewang.cwb.datalist;

import tw.kewang.cwb.Geocode;
import tw.kewang.cwb.dictionary.Locations;

import java.util.List;

public class FD0047 {
    private Records records;
    private Result result;
    private boolean success;

    public Records getRecords() {
        return records;
    }

    public Result getResult() {
        return result;
    }

    public boolean isSuccess() {
        return success;
    }

    public static class Records {
        private String contentDescription;
        private List<Locations> locations;

        public String getContentDescription() {
            return contentDescription;
        }

        public List<Locations> getLocations() {
            return locations;
        }
    }

    public static class Result {
        private List<Fields> fields;
        private String resource_id;

        public List<Fields> getFields() {
            return fields;
        }

        public String getResource_id() {
            return resource_id;
        }

        public static class Fields {
            private String id;
            private String type;
        }
    }

    public enum ByCity {
        YILAN("001", "10002", "宜蘭縣"),
        TAOYUAN("005", "680", "桃園市"),
        HSINCHU_COUNTY("009", "10004", "新竹縣"),
        MIAOLI("013", "10005", "苗栗縣"),
        CHANGHUA("017", "10007", "彰化縣"),
        NANTOU("021", "10008", "南投縣"),
        YUNLIN("025", "10009", "雲林縣"),
        CHAIYI_COUNTY("029", "10010", "嘉義縣"),
        PINGTUNG("033", "10013", "屏東縣"),
        TAITUNG("037", "10014", "臺東縣", "台東縣"),
        HUALIEN("041", "10015", "花蓮縣"),
        PENGHU("045", "10016", "澎湖縣"),
        KEELUNG("049", "10017", "基隆市"),
        HSINCHU_CITY("053", "10018", "新竹市"),
        CHAIYI_CITY("057", "10020", "嘉義市"),
        TAIPEI("061", "630", "臺北市", "台北市"),
        KAOSHIUNG("065", "640", "高雄市"),
        NEWTAIPEI("069", "650", "新北市"),
        TAICHUNG("073", "660", "臺中市", "台中市"),
        TAINAN("077", "670", "臺南市", "台南市"),
        LIENCHIANG("081", "09007", "連江縣"),
        KINMEN("085", "09020", "金門縣");

        private final String dataId;
        private final String geocodePrefix;
        private final String[] cities;

        ByCity(String dataId, String geocodePrefix, String city) {
            this.dataId = dataId;
            this.geocodePrefix = geocodePrefix;
            this.cities = new String[]{city};
        }

        ByCity(String dataId, String geocodePrefix, String... cities) {
            this.dataId = dataId;
            this.geocodePrefix = geocodePrefix;
            this.cities = cities;
        }

        public String getDataId() {
            return dataId;
        }

        public String getGeocodePrefix() {
            return geocodePrefix;
        }

        public static ByCity find(String data) {
            for (ByCity e : values()) {
                if (e.dataId.equalsIgnoreCase(data)) {
                    return e;
                }

                for (String city : e.cities) {
                    if (city.equalsIgnoreCase(data)) {
                        return e;
                    }
                }
            }

            return null;
        }

        public static ByCity findByTown(Geocode geocode) {
            if (geocode == null) {
                return null;
            }

            String code = geocode.getCode();

            for (ByCity e : values()) {
                if (code.startsWith(e.geocodePrefix)) {
                    return e;
                }
            }

            return null;
        }
    }
}