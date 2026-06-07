package org.example;

public class TripFactory {

    public static Trip createBusinessTrip(String id, Destination destination, User organizer) {
        return new Trip.Builder()
                .id(id)
                .destination(destination)
                .organizer(organizer)
                .durationDays(2)
                .build();
    }


    public static Trip createTouristTrip(String id, Destination destination, User organizer, int days) {
        return new Trip.Builder()
                .id(id)
                .destination(destination)
                .organizer(organizer)
                .durationDays(days)
                .build();
    }
}
