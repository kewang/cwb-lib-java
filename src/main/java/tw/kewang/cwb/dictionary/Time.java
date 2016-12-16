package tw.kewang.cwb.dictionary;

import java.util.List;

public class Time {
    private String elementValue;
    private long startTime;
    private long endTime;
    private long dataTime;
    private List<Parameter> parameter;

    public String getElementValue() {
        return elementValue;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public long getDataTime() {
        return dataTime;
    }

    public List<Parameter> getParameter() {
        return parameter;
    }
}