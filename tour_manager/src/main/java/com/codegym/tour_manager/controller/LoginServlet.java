package com.codegym.tour_manager.controller;

import com.codegym.tour_manager.AppConfig.AppConfig;
import com.codegym.tour_manager.model.ERole;
import com.codegym.tour_manager.model.User;
import com.codegym.tour_manager.service.IUserService;
import com.codegym.tour_manager.service.UserService;
import com.codegym.tour_manager.utils.PasswordUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")

public class LoginServlet extends HttpServlet {
    private IUserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(AppConfig.VIEW_FRONTEND + "login.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        List<String> errors = new ArrayList<>();

        User user = userService.findUserByUserName(username);
        if (user != null && PasswordUtils.isValidPassword(password, user.getPassword())) {
            req.getSession().setAttribute("user", user);
//            resp.sendRedirect("/home");
            boolean isAdmin = user.getRole() == ERole.ADMIN;
            if (isAdmin) {
                resp.sendRedirect("/admin");
            }
            boolean isUser = user.getRole() == ERole.USER;
            if (isUser) {
                resp.sendRedirect("/home");
            }

        }else {
            errors.add("Tên tài khoản hoặc mật khẩu chưa chính xác!");
            req.setAttribute("errors",errors);
            // thêm các message lỗi vào đây
            req.getRequestDispatcher(AppConfig.VIEW_FRONTEND + "login.jsp").forward(req, resp);
        }

    }
}
