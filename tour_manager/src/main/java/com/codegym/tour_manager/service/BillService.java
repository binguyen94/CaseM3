package com.codegym.tour_manager.service;

import com.codegym.tour_manager.model.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BillService extends DbContext implements IBillService {
    private ITourService tourService = new TourServiceMyspl();
    private IUserService userService = new UserService();
    public Bill createBill(Bill bill) {
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO `bills` ( `id`, `createAt`, `total`, `idUser`) VALUES (?, ?, ?, ?);");
            ps.setInt(1,bill.getId());
            ps.setDate(2, Date.valueOf(bill.getCreateAt()));
            ps.setDouble(3, bill.getTotal());
            ps.setInt(4, bill.getIdUser());
            ps.executeUpdate();

            System.out.println("createBill: " + ps);
            connection.close();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }

        return bill;
    }

    public BillItem createBillItem(BillItem bill) {
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO `bill_items` ( `id`, `idBill`, `idTour`, `quantity`, `price`) VALUES (?, ?, ?, ?, ?);");
            ps.setInt(1,bill.getId());
            ps.setInt(2,bill.getIdCart());
            ps.setInt(3,bill.getIdTour());
            ps.setInt(4,bill.getQuantity());
            ps.setFloat(5,bill.getPrice());

            ps.executeUpdate();

            System.out.println("createBill: " + ps);
            connection.close();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }

        return bill;
    }

    @Override
    public List<Order> findAllOrder() {
        List<Order> orders = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement pr = connection.prepareStatement("SELECT * FROM bills b join bill_items bi where b.id = bi.idBill");
            System.out.println("findAllOrder: " + pr);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                int idUser = rs.getInt("idUser");
                int idTour = rs.getInt("idTour");
                LocalDate createAt = rs.getDate("createAt").toLocalDate();
                float price = rs.getFloat("price");
                int quantity = rs.getInt("quantity");
                double total = price * quantity;
                Order order = new Order(createAt,total,idUser,idTour,price,quantity);
                Tour tour= tourService.findById(idTour);
                order.setTour(tour);
                User user = userService.findUserById(idUser);
                order.setUser(user);
                orders.add(order);
            }
            connection.close();
        }catch (SQLException e){
            printSQLException(e);
        }
        return orders;
    }

    @Override
    public List<Order> findAllOrderById(int idUser) {
        List<Order> orders = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement pr = connection.prepareStatement("SELECT * FROM bills b join bill_items bi where b.id = bi.idBill and b.idUser = ?");
            pr.setInt(1, idUser);
            System.out.println("findAllOrder: " + pr);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                int idUserDB = rs.getInt("idUser");
                int idTour = rs.getInt("idTour");
                LocalDate createAt = rs.getDate("createAt").toLocalDate();
                float price = rs.getFloat("price");
                int quantity = rs.getInt("quantity");
                double total = price * quantity;
                Order order = new Order(createAt,total,idUserDB,idTour,price,quantity);
                Tour tour= tourService.findById(idTour);
                order.setTour(tour);
                User user = userService.findUserById(idUserDB);
                order.setUser(user);
                orders.add(order);
            }
            connection.close();
        }catch (SQLException e){
            printSQLException(e);
        }
        return orders;
    }
}
