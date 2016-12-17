package tw.kewang.cwb.datalist;

import tw.kewang.cwb.dictionary.Locations;

import java.util.List;

public class FD0047 {
    private Records records;
    private Result result;
    private boolean success;

    public static class Records {
        private String contentDescription;
        private Locations locations;
    }

    public static class Result {
        private List<Fields> fields;
        private String resource_id;

        public static class Fields {
            private String id;
            private String type;
        }
    }
}
