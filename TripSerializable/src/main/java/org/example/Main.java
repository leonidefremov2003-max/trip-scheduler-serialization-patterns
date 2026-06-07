package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        System.setOut(new java.io.PrintStream(System.out, true, java.nio.charset.StandardCharsets.UTF_8));


        System.out.println("=== Демонстрация шаблонов проектирования и сериализации ===\n");

        // 1. Singleton: TripManager
        TripManager manager = TripManager.getInstance();

        // 2. Observer: добавляем слушателя
        manager.addObserver(new ConsoleNotifier());

        // 3. Factory Method: создаём поездки
        Destination paris = new Destination("Париж", "Франция");
        Destination london = new Destination("Лондон", "Великобритания");
        User alice = new User("Алёна", "alena@mail.com");
        User bob = new User("Алёша", "alex@mail.com");

        Trip businessTrip = TripFactory.createBusinessTrip("TRIP-001", paris, alice);
        Trip touristTrip = TripFactory.createTouristTrip("TRIP-002", london, bob, 7);

        // Добавляем в менеджер
        manager.addTrip(businessTrip);
        manager.addTrip(touristTrip);

        System.out.println("\n--- Текущие поездки ---");
        for (Trip trip : manager.getTrips()) {
            System.out.println(trip.getId() + " -> " + trip.getDestination().getCity());
        }

        // 4. Сериализация коллекции
        List<Trip> trips = manager.getTrips();
        String serFile = "trips.ser";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(serFile))) {
            oos.writeObject(trips);
        }
        System.out.println("\nКоллекция сериализована в " + serFile);

        // 5. JSON-сериализация (Jackson)
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(trips);
        System.out.println("JSON-представление коллекции:\n" + json);

        // 6. Десериализация
        List<Trip> restoredTrips;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(serFile))) {
            restoredTrips = (List<Trip>) ois.readObject();
        }
        System.out.println("Восстановленные поездки:");
        for (Trip t : restoredTrips) {
            System.out.println(t.getId() + " " + t.getDestination().getCity());
        }
    }
}
