package org.zero.ck.smucal.controller;

import lombok.extern.log4j.Log4j2;
import org.zero.ck.smucal.dto.UserDTO;
import org.zero.ck.smucal.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;



@Log4j2
@WebServlet("/signup")
public class signController extends HttpServlet {

    private UserService userService = UserService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("signUp get.............");
        req.getRequestDispatcher("/WEB-INF/user/signUp.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDTO userDTO = UserDTO.builder()
                .username(req.getParameter("username"))
                .password(req.getParameter("password"))
                .build();

        log.info("/signup POST...");
        log.info(userDTO);
        try {
            userService.register(userDTO);

            HttpSession session = req.getSession();
            session.setAttribute("loginInfo",userDTO);
            resp.sendRedirect("/main");
        } catch (Exception e) {
            log.info(e.getMessage());
            resp.sendRedirect("/signup/error");
        }






    }
}
