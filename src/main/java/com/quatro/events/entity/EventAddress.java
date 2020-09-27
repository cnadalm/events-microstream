package com.quatro.events.entity;

import javax.json.JsonObject;

public class EventAddress {

    private final String streetType;
    private final String streetName;
    private final String streetNumber;
    private final EventGeolocation geolocation;

    public EventAddress(String streetType, String streetName, String streetNumber) {
        this(streetType, streetName, streetNumber, null);
    }

    public EventAddress(String streetType, String streetName, String streetNumber, EventGeolocation geolocation) {
        this.streetType = streetType;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.geolocation = geolocation;
    }

    public String getStreetType() {
        return streetType;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public EventGeolocation getGeolocation() {
        return geolocation;
    }

    public static EventAddress of(JsonObject json) {
        EventGeolocation geolocation = null;
        if (json.containsKey("geolocation")) {
            JsonObject jsonGeo = json.getJsonObject("geolocation");
            geolocation = new EventGeolocation(jsonGeo.getString("longitude"), jsonGeo.getString("latitude"));
        }
        return new EventAddress(
            json.getString("streetType", ""),
            json.getString("streetName", ""),
            json.getString("streetNumber", ""),
            geolocation);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EventAddress{streetType=").append(streetType);
        sb.append(", streetName=").append(streetName);
        sb.append(", streetNumber=").append(streetNumber);
        sb.append(", geolocation=").append(geolocation);
        sb.append('}');
        return sb.toString();
    }
    
    

}
