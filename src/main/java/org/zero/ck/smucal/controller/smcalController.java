package org.zero.ck.smucal.controller;


import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/smcal")
@Log4j2
public class smcalController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        log.info(session);
        log.info(session.getAttribute("loginInfo"));
        if (session != null && session.getAttribute("loginInfo") != null) {
            // 로그인된 경우 메인 페이지로 리다이렉트
            resp.sendRedirect("/main");  // 메인 페이지의 경로를 지정
        } else {
            log.info("로그인 흔적이 없습니다.");
            // 로그인하지 않은 경우 로그인 페이지로 리다이렉트
            resp.sendRedirect("/login");   // 로그인 페이지의 경로를 지정
        }

    }

}
