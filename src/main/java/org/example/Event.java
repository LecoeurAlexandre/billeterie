package org.example;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;

public class Event {
    private String name;
    private Date date;
    private LocalTime heure;
    private Place[] place;
    private float price;
    private int sales;

    public Event(String name, Date date, LocalTime heure, Place[] place, float price, int sales) {
        this.name = name;
        this.date = date;
        this.heure = heure;
        this.place = place;
        this.price = price;
        this.sales = sales;
    }

    public void checkAvailability() {

    }

    public void ticketSale() {
        // vérifier disponibilité en faisant appel à checkAvailability()
        setSales(this.sales +=1);
    }

    public void cancelPurchase() {
        setSales(this.sales -=1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LocalTime getHeure() {
        return heure;
    }

    public void setHeure(LocalTime heure) {
        this.heure = heure;
    }

    public Place[] getPlace() {
        return place;
    }

    public void setPlace(Place[] place) {
        this.place = place;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", heure='" + heure + '\'' +
                ", place=" + Arrays.toString(place) +
                ", price=" + price +
                ", sales=" + sales +
                '}';
    }
}
