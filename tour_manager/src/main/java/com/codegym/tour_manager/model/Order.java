package com.codegym.tour_manager.model;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private LocalDate createAt;
    private double total;
    private int idUser;
    private int idTour;
    private Tour tour;
    private float price;
    private int quantity;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order(LocalDate createAt, double total, int idUser, int idTour, float price, int quantity) {
        this.createAt = createAt;
        this.total = total;
        this.idUser = idUser;
        this.idTour = idTour;
        this.price = price;
        this.quantity = quantity;
    }

    public Order() {
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdTour() {
        return idTour;
    }

    public void setIdTour(int idTour) {
        this.idTour = idTour;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
