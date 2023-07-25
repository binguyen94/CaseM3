package com.codegym.tour_manager.controller;

import com.codegym.tour_manager.AppConfig.AppConfig;
import com.codegym.tour_manager.model.ERole;
import com.codegym.tour_manager.model.Order;
import com.codegym.tour_manager.model.User;
import com.codegym.tour_manager.service.BillService;
import com.codegym.tour_manager.service.IBillService;
import com.codegym.tour_manager.service.IUserService;
import com.codegym.tour_manager.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    private IBillService billService = new BillService();
    private IUserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        boolean isAdmin = user != null && user.getRole() == ERole.ADMIN;
        if (isAdmin) {
            List<Order> orders = billService.findAllOrder();
            req.setAttribute("orders", orders);
            req.setAttribute("user", user);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(AppConfig.VIEW_DASHBOARD + "order/admin.jsp");
            requestDispatcher.forward(req, resp);
        }else{
            List<Order> orders = billService.findAllOrderById(user.getId());
            req.setAttribute("orders", orders);
            req.setAttribute("user", user);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(AppConfig.VIEW_DASHBOARD + "order/user.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}
