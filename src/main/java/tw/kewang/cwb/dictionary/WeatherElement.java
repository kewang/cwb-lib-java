package tw.kewang.cwb.dictionary;

import java.util.List;

public class WeatherElement {
    private String elementName;
    private String elementMeasure;
    private List<Time> time;

    public String getElementName() {
        return elementName;
    }

    public String getElementMeasure() {
        return elementMeasure;
    }

    public List<Time> getTime() {
        return time;
    }
}