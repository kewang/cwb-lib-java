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
    private Description description;
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
        description = new Description();

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
                    if (elementName.equals("Wx") || elementName.equals("WeatherDescription")) {
                        description.setStartDate(startTime).setEndDate(endTime);

                        if (elementName.equals("Wx")) {
                            description.setShort(time.getElementValue());
                        } else {
                            description.setDetail(time.getElementValue());
                        }
                    } else {
                        PrettyWeatherElement prettyWeatherElement = new PrettyWeatherElement()
                                .setName(elementName).setMeasure(weatherElement.getElementMeasure())
                                .setValue(time.getElementValue()).setStartDate(startTime).setEndDate(endTime);

                        prettyWeatherElements.add(prettyWeatherElement);

                        break;
                    }
                }
            }

            if (resultTime != null) {
                if (elementName.equals("CI")) {
                    comfortable = new Comfortable().setDescription(resultTime.getParameter().get(0).getParameterValue())
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

    /**
     * 取得鄉對名稱
     *
     * @return
     */
    public String getName() {
        if (location == null) {
            return Constants.NOT_FOUND;
        }

        return location.getLocationName();
    }

    /**
     * 取得經過時間篩選遺的氣象資料
     *
     * @return
     */
    public List<PrettyWeatherElement> getWeatherElements() {
        return prettyWeatherElements;
    }

    /**
     * 取得降雨機率(%)
     *
     * @return
     */
    public float getPoP() {
        for (PrettyWeatherElement element : prettyWeatherElements) {
            if (element.getName().equals("PoP")) {
                return Float.valueOf(element.getValue()) / 100;
            }
        }

        return Float.MIN_VALUE;
    }

    /**
     * 取得體感溫度(°C)
     *
     * @return
     */
    public float getApparent() {
        for (PrettyWeatherElement element : prettyWeatherElements) {
            if (element.getName().equals("AT")) {
                return Float.valueOf(element.getValue());
            }
        }

        return Float.MIN_VALUE;
    }

    /**
     * 取得相對濕度(%)
     *
     * @return
     */
    public float getRH() {
        for (PrettyWeatherElement element : prettyWeatherElements) {
            if (element.getName().equals("RH")) {
                return Float.valueOf(element.getValue()) / 100;
            }
        }

        return Float.MIN_VALUE;
    }

    /**
     * 取得溫度(°C)
     *
     * @return
     */
    public float getTemperature() {
        for (PrettyWeatherElement element : prettyWeatherElements) {
            if (element.getName().equals("T")) {
                return Float.valueOf(element.getValue());
            }
        }

        return Float.MIN_VALUE;
    }

    /**
     * 取得風力資料
     *
     * @return
     */
    public Wind getWind() {
        return wind;
    }

    /**
     * 取得舒適度資料
     *
     * @return
     */
    public Comfortable getComfortable() {
        return comfortable;
    }

    /**
     * 取得氣象描述
     *
     * @return
     */
    public Description getDescription() {
        return description;
    }

    public static class Wind {
        private Date dataDate;
        private int scale;
        private String directionShort;
        private String directionDetail;
        private float speed;

        /**
         * 取得觀測時間
         *
         * @return
         */
        public Date getDataDate() {
            return dataDate;
        }

        public Wind setDataDate(Date dataDate) {
            this.dataDate = dataDate;

            return this;
        }

        /**
         * 取得風級(蒲福風級)
         *
         * @return
         */
        public int getScale() {
            return scale;
        }

        public Wind setScale(int scale) {
            this.scale = scale;

            return this;
        }

        /**
         * 取得簡易風向描述
         *
         * @return
         */
        public String getDirectionShort() {
            return directionShort;
        }

        public Wind setDirectionShort(String directionShort) {
            this.directionShort = directionShort;

            return this;
        }

        /**
         * 取得詳細風向描述
         *
         * @return
         */
        public String getDirectionDetail() {
            return directionDetail;
        }

        public Wind setDirectionDetail(String directionDetail) {
            this.directionDetail = directionDetail;

            return this;
        }

        /**
         * 取得風速(km/h)
         *
         * @return
         */
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
        private String description;
        private float value;

        /**
         * 取得觀測時間
         *
         * @return
         */
        public Date getDataDate() {
            return dataDate;
        }

        public Comfortable setDataDate(Date dataDate) {
            this.dataDate = dataDate;

            return this;
        }

        /**
         * 取得舒適度描述
         *
         * @return
         */
        public String getDescription() {
            return description;
        }

        public Comfortable setDescription(String description) {
            this.description = description;

            return this;
        }

        /**
         * 取得舒適度數值
         *
         * @return
         */
        public float getValue() {
            return value;
        }

        public Comfortable setValue(float value) {
            this.value = value;

            return this;
        }
    }

    public static class Description {
        private Date startDate;
        private Date endDate;
        private String shortDescription;
        private String detailDescription;

        /**
         * 取得開始時間
         *
         * @return
         */
        public Date getStartDate() {
            return startDate;
        }

        public Description setStartDate(Date startDate) {
            this.startDate = startDate;

            return this;
        }

        /**
         * 取得結束時間
         *
         * @return
         */
        public Date getEndDate() {
            return endDate;
        }

        public Description setEndDate(Date endDate) {
            this.endDate = endDate;

            return this;
        }

        /**
         * 取得簡易描述
         *
         * @return
         */
        public String getShort() {
            return shortDescription;
        }

        public Description setShort(String shortDescription) {
            this.shortDescription = shortDescription;

            return this;
        }

        /**
         * 取得詳細描述
         *
         * @return
         */
        public String getDetail() {
            return detailDescription;
        }

        public Description setDetail(String detailDescription) {
            this.detailDescription = detailDescription;

            return this;
        }
    }
}