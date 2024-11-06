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


@WebServlet("/login")
@Log4j2
public class loginController extends HttpServlet {

    private UserService userService = UserService.INSTANCE;

    @Override
    protected void doGet(
            HttpServletRequest req
            , HttpServletResponse resp
    ) throws ServletException, IOException {

        log.info("login 페이지 요청.............");

        req.getRequestDispatcher("/WEB-INF/user/login.jsp").forward(req,resp);

    }
    @Override
    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws ServletException, IOException {

        log.info("login 시도.............");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            UserDTO userDTO = userService.login(username,password);
            log.info("userDTO:" + userDTO);

            // 사용자 정보 세션에 저장
            HttpSession session = req.getSession();
            session.setAttribute("loginInfo",userDTO);
            log.info("loginInfo:" + session.getAttribute("loginInfo"));
            log.info("Redirecting to mainPage..");
            resp.sendRedirect("/main");

        } catch (Exception e) {
            resp.sendRedirect("/login/error");
            log.info(e.getMessage());
        }

    }
}
