package org.example;

import java.util.ArrayList;

public class Client {
    private String lastName;
    private String firstName;
    private String email;
    private ArrayList<Event> eventTickets;

    public Client(String lastName, String firstName, String email, ArrayList<Event> eventTickets) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.eventTickets = eventTickets;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Event> getEventTickets() {
        return eventTickets;
    }

    public void setEventTickets(ArrayList<Event> eventTickets) {
        this.eventTickets = eventTickets;
    }

    @Override
    public String toString() {
        return "Client{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", eventTickets=" + eventTickets +
                '}';
    }
}
