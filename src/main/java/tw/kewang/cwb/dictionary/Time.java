package tw.kewang.cwb.dictionary;

import java.util.Date;
import java.util.List;

public class Time {
    private String elementValue;
    private Date startTime;
    private Date endTime;
    private Date dataTime;
    private List<Parameter> parameter;

    public String getElementValue() {
        return elementValue;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public Date getDataTime() {
        return dataTime;
    }

    public List<Parameter> getParameter() {
        return parameter;
    }
}