package tw.kewang.cwb.pretty;

import java.util.Date;

public class PrettyWeatherElement {
    private String name;
    private String measure;
    private String value;
    private Date startDate;
    private Date endDate;
    private Date dataDate;

    public String getName() {
        return name;
    }

    public String getMeasure() {
        return measure;
    }

    public String getValue() {
        return value;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Date getDataDate() {
        return dataDate;
    }

    public PrettyWeatherElement setName(String name) {
        this.name = name;

        return this;
    }

    public PrettyWeatherElement setMeasure(String measure) {
        this.measure = measure;

        return this;
    }

    public PrettyWeatherElement setValue(String value) {
        this.value = value;

        return this;
    }

    public PrettyWeatherElement setStartDate(Date startDate) {
        this.startDate = startDate;

        return this;
    }

    public PrettyWeatherElement setEndDate(Date endDate) {
        this.endDate = endDate;

        return this;
    }

    public PrettyWeatherElement setDataDate(Date dataDate) {
        this.dataDate = dataDate;

        return this;
    }
}