package com.springmvc.entity;

import com.springmvc.type.ReservationStatus;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "reservations")
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "begins_at")
    private LocalDate beginsAt;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "ends_at")
    private LocalDate endsAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ReservationStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    public Reservation(User user, Vehicle vehicle, LocalDate beginsAt, LocalDate endsAt, ReservationStatus status) {
        this.user = user;
        this.vehicle = vehicle;
        this.beginsAt = beginsAt;
        this.endsAt = endsAt;
        this.status = status;
    }

    public Reservation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDate getBeginsAt() {
        return beginsAt;
    }

    public void setBeginsAt(LocalDate beginsAt) {
        this.beginsAt = beginsAt;
    }

    public LocalDate getEndsAt() {
        return endsAt;
    }

    public void setEndsAt(LocalDate endsAt) {
        this.endsAt = endsAt;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", beginsAt=" + beginsAt +
                ", endsAt=" + endsAt +
                ", status=" + status +
                ", user=" + user +
                '}';
    }
}
