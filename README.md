# cwb-lib-java

台灣中央氣象局 (CWB) 的非官方 open data library

[![Build Status](https://travis-ci.org/kewang/cwb-lib-java.svg?branch=master)](https://travis-ci.org/kewang/cwb-lib-java) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/tw.kewang/cwb/badge.svg)](https://maven-badges.herokuapp.com/maven-central/tw.kewang/cwb)

## Dependency

### Maven

```xml
<dependency>
  <groupId>tw.kewang</groupId>
  <artifactId>cwb</artifactId>
  <version>0.1.2</version>
</dependency>
```

### Gradle

```groovy
compile 'tw.kewang:cwb:0.1.2'
```

## How to use

At first, you must add api key.

```java
Cwb.init(System.getenv("CWB_APIKEY"));
```

### Get Future Weather By Town

```java
FutureWeatherByTown weather1 = Cwb.getFutureWeatherByTown("新莊區", 2);

System.out.println(weather1.getDescription().getStartDate());
System.out.println(weather1.getDescription().getEndDate());
System.out.println(weather1.getDescription().getDetail());
System.out.println(weather1.getDescription().getShort());
System.out.println(weather1.getComfortable().getDataDate());
System.out.println(weather1.getComfortable().getString());
System.out.println(weather1.getComfortable().getValue());
System.out.println(weather1.getApparent());
System.out.println(weather1.getTemperature());
System.out.println(weather1.getPoP());
System.out.println(weather1.getRH());
System.out.println(weather1.getWind().getDataDate());
System.out.println(weather1.getWind().getDirectionDetail());
System.out.println(weather1.getWind().getDirectionShort());
System.out.println(weather1.getWind().getScale());
System.out.println(weather1.getWind().getSpeed());
```
