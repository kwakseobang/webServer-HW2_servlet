package org.zero.ck.smucal.controller;


import lombok.extern.log4j.Log4j2;
import org.zero.ck.smucal.domain.UserVO;
import org.zero.ck.smucal.dto.CalenderDTO;
import org.zero.ck.smucal.dto.UserDTO;
import org.zero.ck.smucal.service.CalenderSerivce;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

@Log4j2
@WebServlet("/modify")
public class modifyController extends HttpServlet {

    private CalenderSerivce calenderSerivce = CalenderSerivce.INSTANCE;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int cno = Integer.parseInt(req.getParameter("cno"));
            CalenderDTO calenderDTO = calenderSerivce.getCalender(cno);
            log.info(calenderDTO.getContent());
            log.info(calenderDTO.getDate());
            //모델 담기
            req.setAttribute("dto", calenderDTO);
            req.getRequestDispatcher("/WEB-INF/calender/modify.jsp").forward(req, resp);

        }catch(Exception e){
            log.error(e.getMessage());
            throw new ServletException("modify get... error");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserDTO userDTO = (UserDTO) session.getAttribute("loginInfo");

        // 수정할 CalenderDTO 객체 생성
        UserVO userVO = UserVO.builder()
                .id(userDTO.getId())
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .build();

        CalenderDTO calenderDTO = CalenderDTO.builder()
                .id(Integer.parseInt(req.getParameter("id")))
                .author(userVO)
                .date(LocalDate.parse(req.getParameter("date")))
                .content(req.getParameter("content"))
                .build();


        log.info("/modify POST...");
        log.info(calenderDTO);
        try {
            calenderSerivce.updateCalender(calenderDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/main");
    }
}
