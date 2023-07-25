package com.codegym.tour_manager.service;

import com.codegym.tour_manager.model.Bill;
import com.codegym.tour_manager.model.BillItem;
import com.codegym.tour_manager.model.Order;

import java.util.List;

public interface IBillService {
    Bill createBill(Bill bill);
    BillItem createBillItem(BillItem bill);
    List<Order> findAllOrder();
    List<Order> findAllOrderById(int idUser);

}
