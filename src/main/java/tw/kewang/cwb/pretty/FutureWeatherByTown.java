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

    public FutureWeatherByTown() {
    }

    public FutureWeatherByTown(Location location) {
        this.location = location;
    }

    public String getName() {
        if (location == null) {
            return Constants.NOT_FOUND;
        }

        return location.getLocationName();
    }

    public List<PrettyWeatherElement> getWeatherElementsByTime(Date date) {
        List<PrettyWeatherElement> prettyWeatherElements = new ArrayList<>();

        for (WeatherElement weatherElement : location.getWeatherElement()) {
            for (Time time : weatherElement.getTime()) {
                Date startTime = time.getStartTime();
                Date endTime = time.getEndTime();
                Date dataTime = time.getDataTime();

                if (dataTime != null) {
                    // TODO: test dataTime & date
                    continue;
                }

                if (date.after(startTime) && date.before(endTime)) {
                    PrettyWeatherElement prettyWeatherElement = new PrettyWeatherElement()
                            .setName(weatherElement.getElementName()).setMeasure(weatherElement.getElementMeasure())
                            .setValue(time.getElementValue()).setStartDate(startTime).setEndDate(endTime)
                            .setDataDate(dataTime);

                    prettyWeatherElements.add(prettyWeatherElement);
                }
            }
        }

        return prettyWeatherElements;
    }

    public List<PrettyWeatherElement> getWeatherElementsByTime(long timestamp) {
        return getWeatherElementsByTime(new Date(timestamp));
    }
}