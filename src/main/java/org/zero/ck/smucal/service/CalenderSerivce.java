package org.zero.ck.smucal.service;

import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.zero.ck.smucal.dao.CalenderDAO;
import org.zero.ck.smucal.domain.CalenderVO;
import org.zero.ck.smucal.domain.UserVO;
import org.zero.ck.smucal.dto.CalenderDTO;
import org.zero.ck.smucal.dto.UserDTO;
import org.zero.ck.smucal.utill.MapperUtil;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
public enum CalenderSerivce {
    INSTANCE;

    private CalenderDAO dao;
    private ModelMapper modelMapper;

    CalenderSerivce() {
        dao = new CalenderDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }
    // create
    public void register(CalenderDTO calenderDTO) throws Exception {
        try {
            CalenderVO calenderVO = modelMapper.map(calenderDTO,CalenderVO.class);

            dao.create(calenderVO); //int 를 반환하므로 이를 이용해서 예외처리도 가능
            // 비영속성 에러가 있었음 디비에서 정보 가져오자..

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    //read
    public CalenderDTO getCalender(int id) throws Exception {
        CalenderVO calenderVO = dao.getCalender(id);
        CalenderDTO calenderDTO = modelMapper.map(calenderVO, CalenderDTO.class);
        return calenderDTO;

    }

    public List<CalenderDTO> listAll() throws Exception {

        List<CalenderVO> voList = dao.selectAll();

        log.info("voList.................");
        log.info(voList);

        List<CalenderDTO> dtoList = voList.stream()
                .map(vo -> modelMapper.map(vo,CalenderDTO.class))
                .collect(Collectors.toList());

        return dtoList;
    }
    //update
    public void updateCalender(CalenderDTO calenderDTO) throws Exception {

        CalenderVO calenderVO = modelMapper.map(calenderDTO, CalenderVO.class);

        dao.updateCalender(calenderVO);
    }



}
