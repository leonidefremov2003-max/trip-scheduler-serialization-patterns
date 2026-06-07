package org.example;

public class ConsoleNotifier implements TripObserver {
    @Override
    public void onTripAdded(Trip trip) {
        System.out.println("[Observer] Новая поездка: " + trip.getId() +
                " -> " + trip.getDestination().getCity() +
                " (" + trip.getDurationDays() + " дн.)");
    }
}
