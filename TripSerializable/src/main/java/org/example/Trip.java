package org.example;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.*;

public class Trip implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private Destination destination;
    private User organizer;
    private int durationDays;


    private transient String tempCache = "temporary-data";


    @JsonIgnore         // transient, только для Json
    private String internalNote = "confidential";


    private Trip(Builder builder) {
        this.id = builder.id;
        this.destination = builder.destination;
        this.organizer = builder.organizer;
        this.durationDays = builder.durationDays;
    }


    public Trip() {}


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public Destination getDestination() {
        return destination;
    }
    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public User getOrganizer() {
        return organizer;
    }
    public void setOrganizer(User organizer) {
        this.organizer = organizer;
    }

    public int getDurationDays() {
        return durationDays;
    }
    public void setDurationDays(int durationDays) {
        this.durationDays = durationDays;
    }

    public String getTempCache() {
        return tempCache;
    }
    public void setTempCache(String tempCache) {
        this.tempCache = tempCache;
    }

    public String getInternalNote() {
        return internalNote;
    }
    public void setInternalNote(String internalNote) {
        this.internalNote = internalNote;
    }


    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        System.out.println("[Сериализация] Trip " + id + " записан");
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.tempCache = "восстановлено после десериализации";
        System.out.println("[Десериализация] Trip " + id + " восстановлен");
    }


    public static class Builder {
        private String id;
        private Destination destination;
        private User organizer;
        private int durationDays;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder destination(Destination dest) {
            this.destination = dest;
            return this;
        }

        public Builder organizer(User user) {
            this.organizer = user;
            return this;
        }

        public Builder durationDays(int days) {
            this.durationDays = days;
            return this;
        }

        public Trip build() {
            return new Trip(this);
        }
    }
}
