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
                    PrettyWeatherElement elem1 = new PrettyWeatherElement().setName("CIString")
                            .setValue(resultTime.getParameter().get(0).getParameterValue())
                            .setMeasure(weatherElement.getElementMeasure()).setDataDate(resultDate);
                    PrettyWeatherElement elem2 = new PrettyWeatherElement().setName("CIValue")
                            .setValue(resultTime.getElementValue()).setMeasure(weatherElement.getElementMeasure())
                            .setDataDate(resultDate);

                    prettyWeatherElements.add(elem1);
                    prettyWeatherElements.add(elem2);
                } else if (elementName.equals("Wind")) {
                    for (Parameter parameter : resultTime.getParameter()) {
                        PrettyWeatherElement elem = new PrettyWeatherElement().setValue(parameter.getParameterValue())
                                .setMeasure(parameter.getParameterUnit()).setDataDate(resultDate);

                        String parameterName = parameter.getParameterName();

                        if (parameterName.equals("風級")) {
                            elem.setName("WindScale");
                        } else if (parameterName.equals("風向縮寫")) {
                            elem.setName("WindDirectionShort");
                        } else if (parameterName.equals("風向描述")) {
                            elem.setName("WindDirectionDetail");
                        } else if (parameterName.equals("風速")) {
                            elem.setName("WindSpeed");
                        }

                        prettyWeatherElements.add(elem);
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

    public String getComfortableString() {
        for (PrettyWeatherElement element : prettyWeatherElements) {
            if (element.getName().equals("CIString")) {
                return element.getValue();
            }
        }

        return Constants.NOT_FOUND;
    }

    public float getComfortableValue() {
        for (PrettyWeatherElement element : prettyWeatherElements) {
            if (element.getName().equals("CIValue")) {
                return Float.valueOf(element.getValue());
            }
        }

        return Float.MIN_VALUE;
    }

    public int getWindScale() {
        for (PrettyWeatherElement element : prettyWeatherElements) {
            if (element.getName().equals("WindScale")) {
                return Integer.valueOf(element.getValue());
            }
        }

        return Integer.MIN_VALUE;
    }

    public String getWindDirectionShort() {
        for (PrettyWeatherElement element : prettyWeatherElements) {
            if (element.getName().equals("WindDirectionShort")) {
                return element.getValue();
            }
        }

        return Constants.NOT_FOUND;
    }

    public String getWindDirectionDetail() {
        for (PrettyWeatherElement element : prettyWeatherElements) {
            if (element.getName().equals("WindDirectionDetail")) {
                return element.getValue();
            }
        }

        return Constants.NOT_FOUND;
    }

    public float getWindSpeed() {
        for (PrettyWeatherElement element : prettyWeatherElements) {
            if (element.getName().equals("WindSpeed")) {
                return Float.valueOf(element.getValue());
            }
        }

        return Float.MIN_VALUE;
    }
}