package org.zero.ck.smucal.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.zero.ck.smucal.dao.UserDAO;
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

@WebServlet("/register")
@Log4j2
public class registerController extends HttpServlet {

    private CalenderSerivce calenderSerivce = CalenderSerivce.INSTANCE;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("register 페이지...");
        req.getRequestDispatcher("/WEB-INF/calender/register.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 작성자 id, 시간, 내용 저장.
        try {
            HttpSession session = req.getSession();
            log.info("register loginInfo:" + session.getAttribute("loginInfo"));
            //유저 정보도 저장
            UserDTO userDTO = (UserDTO) session.getAttribute("loginInfo");
            UserVO userVO = UserVO.builder()
                    .id(userDTO.getId())
                    .username(userDTO.getUsername())
                    .password(userDTO.getPassword())
                    .build();
            CalenderDTO calenderDTO = CalenderDTO.builder()
                    .author(userVO)
                    .date(LocalDate.parse(req.getParameter("date")))
                    .content(req.getParameter("content"))
                    .build();
            calenderSerivce.register(calenderDTO);
            resp.sendRedirect("/main");
            log.info("캘린더 등록..");
        } catch (Exception e) {
            log.info("캘린더 등록 실패: {}", e.getMessage());
            resp.sendRedirect("/register");
        }

    }

}
