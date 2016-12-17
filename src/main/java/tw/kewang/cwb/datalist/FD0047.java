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
        CHANGHUA("017", "彰化縣");

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