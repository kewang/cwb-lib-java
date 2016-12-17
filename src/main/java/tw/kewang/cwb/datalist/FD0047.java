package tw.kewang.cwb.datalist;

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
        YILAN("001", "宜蘭縣"),
        TAOYUAN("005", "桃園市"),
        HSINCHU_COUNTY("009", "新竹縣"),
        MIAOLI("013", "苗栗縣"),
        CHANGHUA("017", "彰化縣"),
        NANTOU("021", "南投縣"),
        YUNLIN("025", "雲林縣"),
        CHAIYI_COUNTY("029", "嘉義縣"),
        PINGTUNG("033", "屏東縣"),
        TAITUNG("037", "臺東縣"),
        HUALIEN("041", "花蓮縣"),
        PENGHU("045", "澎湖縣"),
        KEELUNG("049", "基隆市"),
        HSINCHU_CITY("053", "新竹市"),
        CHAIYI_CITY("057", "嘉義市"),
        TAIPEI("061", "臺北市"),
        KAOSHIUNG("065", "高雄市"),
        NEWTAIPEI("069", "新北市"),
        TAICHUNG("073", "臺中市"),
        TAINAN("077", "臺南市"),
        LIENCHIANG("081", "連江縣"),
        KINMEN("085", "金門縣");

        private final String dataId;
        private final String city;

        ByCity(String dataId, String city) {
            this.dataId = dataId;
            this.city = city;
        }

        public String getDataId() {
            return dataId;
        }

        public static ByCity find(String data) {
            for (ByCity e : values()) {
                if (e.dataId.equalsIgnoreCase(data) || e.city.equalsIgnoreCase(data)) {
                    return e;
                }
            }

            return null;
        }
    }
}