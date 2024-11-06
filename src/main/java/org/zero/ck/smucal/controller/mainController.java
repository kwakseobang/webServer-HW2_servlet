package org.zero.ck.smucal.controller;

import lombok.extern.log4j.Log4j2;
import org.zero.ck.smucal.dto.CalenderDTO;
import org.zero.ck.smucal.service.CalenderSerivce;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/main")
@Log4j2
public class mainController extends HttpServlet {
    private CalenderSerivce calenderSerivce = CalenderSerivce.INSTANCE;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<CalenderDTO> dtoList = calenderSerivce.listAll();
            log.info(dtoList);
            req.setAttribute("calenderList", dtoList);
            HttpSession session = req.getSession();
            log.info(session.getAttribute("loginInfo"));
            req.setAttribute("userInfo",session.getAttribute("loginInfo"));
            req.getRequestDispatcher("/WEB-INF/calender/main.jsp").forward(req,resp);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServletException("list error");
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
