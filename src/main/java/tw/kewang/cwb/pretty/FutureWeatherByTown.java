package tw.kewang.cwb.pretty;

import tw.kewang.cwb.dictionary.Location;
import tw.kewang.cwb.dictionary.Parameter;
import tw.kewang.cwb.dictionary.Time;
import tw.kewang.cwb.dictionary.WeatherElement;
import tw.kewang.cwb.utils.Constants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FutureWeatherByTown {
    private Location location;
    private Date date;
    private Wind wind;
    private Comfortable comfortable;
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

            String elementName = weatherElement.getElementName();

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
                            .setName(elementName).setMeasure(weatherElement.getElementMeasure())
                            .setValue(time.getElementValue()).setStartDate(startTime).setEndDate(endTime);

                    prettyWeatherElements.add(prettyWeatherElement);

                    break;
                }
            }

            if (resultTime != null) {
                if (elementName.equals("CI")) {
                    comfortable = new Comfortable().setString(resultTime.getParameter().get(0).getParameterValue())
                            .setValue(Float.valueOf(resultTime.getElementValue())).setDataDate(resultDate);
                } else if (elementName.equals("Wind")) {
                    wind = new Wind().setDataDate(resultDate);

                    for (Parameter parameter : resultTime.getParameter()) {
                        String parameterName = parameter.getParameterName();

                        if (parameterName.equals("風級")) {
                            wind.setScale(Integer.valueOf(parameter.getParameterValue()));
                        } else if (parameterName.equals("風向縮寫")) {
                            wind.setDirectionShort(parameter.getParameterValue());
                        } else if (parameterName.equals("風向描述")) {
                            wind.setDirectionDetail(parameter.getParameterValue());
                        } else if (parameterName.equals("風速")) {
                            wind.setSpeed(Float.valueOf(parameter.getParameterValue()));
                        }
                    }
                } else {
                    PrettyWeatherElement elem = new PrettyWeatherElement().setName(elementName)
                            .setValue(resultTime.getElementValue()).setMeasure(weatherElement.getElementMeasure())
                            .setDataDate(resultDate);

                    prettyWeatherElements.add(elem);
                }
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

    public String getWeatherDescriptionShort() {
        for (PrettyWeatherElement element : prettyWeatherElements) {
            if (element.getName().equals("Wx")) {
                return element.getValue();
            }
        }

        return Constants.NOT_FOUND;
    }

    public String getWeatherDescriptionDetail() {
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
                return Float.valueOf(element.getValue());
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

    public Wind getWind() {
        return wind;
    }

    public Comfortable getComfortable() {
        return comfortable;
    }

    public static class Wind {
        private Date dataDate;
        private int scale;
        private String directionShort;
        private String directionDetail;
        private float speed;

        public Date getDataDate() {
            return dataDate;
        }

        public Wind setDataDate(Date dataDate) {
            this.dataDate = dataDate;

            return this;
        }

        public int getScale() {
            return scale;
        }

        public Wind setScale(int scale) {
            this.scale = scale;

            return this;
        }

        public String getDirectionShort() {
            return directionShort;
        }

        public Wind setDirectionShort(String directionShort) {
            this.directionShort = directionShort;

            return this;
        }

        public String getDirectionDetail() {
            return directionDetail;
        }

        public Wind setDirectionDetail(String directionDetail) {
            this.directionDetail = directionDetail;

            return this;
        }

        public float getSpeed() {
            return speed;
        }

        public Wind setSpeed(float speed) {
            this.speed = speed;

            return this;
        }
    }

    public static class Comfortable {
        private Date dataDate;
        private String string;
        private float value;

        public Date getDataDate() {
            return dataDate;
        }

        public Comfortable setDataDate(Date dataDate) {
            this.dataDate = dataDate;

            return this;
        }

        public String getString() {
            return string;
        }

        public Comfortable setString(String string) {
            this.string = string;

            return this;
        }

        public float getValue() {
            return value;
        }

        public Comfortable setValue(float value) {
            this.value = value;

            return this;
        }
    }
}