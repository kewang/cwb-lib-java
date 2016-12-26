package tw.kewang.cwb.pretty;

import tw.kewang.cwb.dictionary.Location;
import tw.kewang.cwb.dictionary.Time;
import tw.kewang.cwb.dictionary.WeatherElement;
import tw.kewang.cwb.utils.Constants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FutureWeatherByTown {
    private Location location;
    private Date date;
    private List<PrettyWeatherElement> prettyWeatherElements;

    public FutureWeatherByTown() {
    }

    public FutureWeatherByTown(Location location, Date date) {
        this.location = location;
        this.date = date;

        buildPrettyWeatherElements();
    }

    private void buildPrettyWeatherElements() {
        prettyWeatherElements = new ArrayList<>();

        for (WeatherElement weatherElement : location.getWeatherElement()) {
            Date resultDate = new Date(0);
            Time resultTime = null;

            for (Time time : weatherElement.getTime()) {
                Date startTime = time.getStartTime();
                Date endTime = time.getEndTime();
                Date dataTime = time.getDataTime();

                if (dataTime != null) {
                    if (date.after(dataTime) && dataTime.after(resultDate)) {
                        resultDate = dataTime;
                        resultTime = time;
                    }
                } else if (date.after(startTime) && date.before(endTime)) {
                    PrettyWeatherElement prettyWeatherElement = new PrettyWeatherElement()
                            .setName(weatherElement.getElementName()).setMeasure(weatherElement.getElementMeasure())
                            .setValue(time.getElementValue()).setStartDate(startTime).setEndDate(endTime);

                    prettyWeatherElements.add(prettyWeatherElement);

                    break;
                }
            }

            if (resultTime != null) {
                PrettyWeatherElement prettyWeatherElement = new PrettyWeatherElement()
                        .setName(weatherElement.getElementName()).setMeasure(weatherElement.getElementMeasure())
                        .setValue(resultTime.getElementValue()).setDataDate(resultDate);

                prettyWeatherElements.add(prettyWeatherElement);
            }
        }
    }

    public String getName() {
        if (location == null) {
            return Constants.NOT_FOUND;
        }

        return location.getLocationName();
    }

    public List<PrettyWeatherElement> getWeatherElements() {
        return prettyWeatherElements;
    }

    public String getWeatherDescription() {
        for (PrettyWeatherElement element : prettyWeatherElements) {
            if (element.getName().equals("WeatherDescription")) {
                return element.getValue();
            }
        }

        return Constants.NOT_FOUND;
    }

    public float getPoP() {
        for (PrettyWeatherElement element : prettyWeatherElements) {
            if (element.getName().equals("PoP")) {
                return Float.valueOf(element.getValue()) / 100;
            }
        }

        return Float.MIN_VALUE;
    }

    public float getApparent() {
        for (PrettyWeatherElement element : prettyWeatherElements) {
            if (element.getName().equals("AT")) {
                return Integer.valueOf(element.getValue());
            }
        }

        return Float.MIN_VALUE;
    }

    public float getRH() {
        for (PrettyWeatherElement element : prettyWeatherElements) {
            if (element.getName().equals("RH")) {
                return Float.valueOf(element.getValue()) / 100;
            }
        }

        return Float.MIN_VALUE;
    }

    public float getTemperature() {
        for (PrettyWeatherElement element : prettyWeatherElements) {
            if (element.getName().equals("T")) {
                return Float.valueOf(element.getValue()) / 100;
            }
        }

        return Float.MIN_VALUE;
    }
}