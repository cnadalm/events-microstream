package com.quatro.events.entity;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Event {

    private static final int DEFAULT_MAXSEATS = 100;

    public enum Status {
        DRAFT, PUBLISHED, CANCELLED
    }

    private final String id;
    private String title;
    private String description;
    private int maxSeats = DEFAULT_MAXSEATS;
    private Status status;

    private LocalDate startOn;
    private LocalDate endOn;
    private LocalTime scheduleFrom;
    private LocalTime scheduleTo;

    private EventAddress address;

    private final String organizerId;
    private final List<String> joinedProfileIds;

    private final Instant createdAt;
    private Instant updatedAt;

    public Event(String postalCode, String title, String description, LocalDate startOn, LocalDate endOn, LocalTime scheduleFrom, LocalTime scheduleTo, EventAddress address, String organizerId) {
        super();
        this.id = postalCode + "-" + UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.startOn = startOn;
        this.endOn = endOn;
        this.scheduleFrom = scheduleFrom;
        this.scheduleTo = scheduleTo;
        this.address = address;
        this.organizerId = organizerId;
        this.joinedProfileIds = new ArrayList<>();
        this.status = Status.DRAFT;
        this.createdAt = this.updatedAt = Instant.now();
    }

    public String getId() {
        return id;
    }

    public String getOrganizerId() {
        return organizerId;
    }

    public List<String> getJoinedProfileIds() {
        return joinedProfileIds;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaxSeats() {
        return maxSeats;
    }

    public void setMaxSeats(int maxSeats) {
        this.maxSeats = maxSeats;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getStartOn() {
        return startOn;
    }

    public void setStartOn(LocalDate startOn) {
        this.startOn = startOn;
    }

    public LocalDate getEndOn() {
        return endOn;
    }

    public void setEndOn(LocalDate endOn) {
        this.endOn = endOn;
    }

    public LocalTime getScheduleFrom() {
        return scheduleFrom;
    }

    public void setScheduleFrom(LocalTime scheduleFrom) {
        this.scheduleFrom = scheduleFrom;
    }

    public LocalTime getScheduleTo() {
        return scheduleTo;
    }

    public void setScheduleTo(LocalTime scheduleTo) {
        this.scheduleTo = scheduleTo;
    }

    public EventAddress getAddress() {
        return address;
    }

    public void setAddress(EventAddress address) {
        this.address = address;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Event{id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", description=").append(description);
        sb.append(", maxSeats=").append(maxSeats);
        sb.append(", status=").append(status);
        sb.append(", startOn=").append(startOn);
        sb.append(", endOn=").append(endOn);
        sb.append(", scheduleFrom=").append(scheduleFrom);
        sb.append(", scheduleTo=").append(scheduleTo);
        sb.append(", address=").append(address);
        sb.append(", organizerId=").append(organizerId);
        sb.append(", joinedProfileIds=").append(joinedProfileIds);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append('}');
        return sb.toString();
    }
    
}
