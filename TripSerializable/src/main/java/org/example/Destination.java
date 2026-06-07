package org.example;

import java.io.Serializable;

public class Destination implements Serializable {
    private static final long serialVersionUID = 1L;
    private String city;
    private String country;

    // Пустой конструктор (необходим для сериализации/Jackson)
    public Destination() {}

    public Destination(String city, String country) {
        this.city = city;
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
