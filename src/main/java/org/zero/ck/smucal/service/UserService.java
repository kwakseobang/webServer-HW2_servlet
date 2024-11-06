package org.zero.ck.smucal.service;

import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.zero.ck.smucal.dao.UserDAO;
import org.zero.ck.smucal.domain.UserVO;
import org.zero.ck.smucal.dto.UserDTO;
import org.zero.ck.smucal.utill.MapperUtil;

@Log4j2
public enum UserService {

    INSTANCE;
    private UserDAO dao;
    private ModelMapper modelMapper;

    UserService() {
        dao = new UserDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }

    public UserDTO login(String username, String password) throws Exception {

        try {
            UserVO vo = dao.getUser(username,password);

            // DB에서 해당 정보가 없을 시
            if(vo == null) {
                throw  new Exception("사용자 정보를 찾을 수 없습니다.");
            }
            UserDTO userDTO = modelMapper.map(vo, UserDTO.class);
            return userDTO;

        } catch (Exception e) {
            throw new Exception("로그인 중 오류가 발생했습니다.", e);
        }

    }

    public void register(UserDTO userDTO) throws Exception{
        try {
            UserVO userVO = modelMapper.map(userDTO, UserVO.class);
            boolean isExist = dao.isExist(userVO.getUsername());
            // 정보가 존재하지 않으면 회원가입
            if(!isExist) {
                log.info(userVO);
                dao.create(userVO);
            } else {
                throw new Exception("해당 유저가 이미 존재합니다.");
            }
        }catch (Exception e){
            throw new Exception("해당 유저가 이미 존재합니다.");
        }
    }


}
