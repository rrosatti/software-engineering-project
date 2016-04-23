package com.example.rodri.tempnow.temperature;

/**
 * Created by rodri on 4/23/2016.
 */
public class Temperature {

    private double temperature;
    private char scale;

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public char getScale() {
        return scale;
    }

    public void setScale(char scale) {
        this.scale = scale;
    }

    public double celsiusToFahrenheit() {
        //F =  C × 1.8 + 32
        double f = (this.getTemperature() * 1.8) + 32;
        return f;
    }

    public double celsiusToKelvin() {
        //K = C  + 273.15
        double k = this.getTemperature() + 273.15;
        return k;
    }

    public double fahrenheitToCelsius() {
        //C = ( F - 32) / 1.8
        double c = (this.getTemperature() - 32) / 1.8;
        return c;
    }

    public double fahrenheitToKelvin() {
        //K = ( F + 459.67) / 1.8
        double k = (this.getTemperature() + 459.67) / 1.8;
        return k;
    }

    public double kelvinToCelsius() {
        //C = K - 273.15
        double c = this.getTemperature() - 273.15;
        return c;
    }

    public double kelvinToFahrenheit() {
        //F = K × 1.8 - 459.67
        double f = (this.getTemperature() * 1.8) - 459.67;
        return f;
    }

}
