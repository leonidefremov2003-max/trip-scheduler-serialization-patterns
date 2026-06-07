package org.example;

import java.util.ArrayList;
import java.util.List;

public class TripManager {
    private static TripManager instance;
    private List<Trip> trips = new ArrayList<>();
    private List<TripObserver> observers = new ArrayList<>();


    private TripManager() {}


    public static TripManager getInstance() {
        if (instance == null) {
            instance = new TripManager();
        }
        return instance;
    }


    public void addTrip(Trip trip) {
        trips.add(trip);
        notifyObservers(trip);
    }

    public List<Trip> getTrips() {
        return trips;
    }


    public void addObserver(TripObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(TripObserver observer) {
        observers.remove(observer);
    }


    private void notifyObservers(Trip trip) {
        for (TripObserver observer : observers) {
            observer.onTripAdded(trip);
        }
    }
}
