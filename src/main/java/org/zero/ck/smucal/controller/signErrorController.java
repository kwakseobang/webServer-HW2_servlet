package org.zero.ck.smucal.controller;


import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@WebServlet("/signup/error")
public class signErrorController extends HttpServlet {


    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws ServletException, IOException {

            log.info("signError...");
            req.getRequestDispatcher("/WEB-INF/user/signUpError.jsp").forward(req,resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("login".equals(action)) {
            // 로그인 페이지로 리다이렉트
            resp.sendRedirect("/login");
        } else if ("signup".equals(action)) {
            // 회원가입 페이지로 리다이렉트
            resp.sendRedirect("/signup");
        }
    }
}
