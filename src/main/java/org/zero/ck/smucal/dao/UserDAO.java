package org.zero.ck.smucal.dao;

import lombok.Cleanup;
import org.zero.ck.smucal.domain.UserVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    public UserVO getUser(String username, String password) throws Exception {
        String sql = "select id,username,password from user where username=? and password=?";

        UserVO userVO = null;
        try {
            @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            // 정보 없을 시 비어있음
            @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
            // 비어있으면 null
            if(resultSet.next()) {
                userVO = UserVO.builder()
                        .id(resultSet.getInt(1))
                        .username(resultSet.getString(2))
                        .password(resultSet.getString(3))
                        .build();
            } else {
                throw new Exception("사용자 정보가 존재하지 않습니다." + username);
            }

        } catch (Exception e) {
            throw new Exception("사용자 정보가 존재하지 않습니다." + username);
        }
        return userVO;
    }



    public void create(UserVO vo) throws Exception {
        String sql = "insert into user(username,password) values (?, ?)";

        UserVO userVO = null;
        try {
            @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //data
            preparedStatement.setString(1, vo.getUsername());
            preparedStatement.setString(2, vo.getPassword());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new Exception("DB 저장 실패: " + vo);
        }


    }

    public boolean isExist(String username) throws Exception {
        String sql = "SELECT EXISTS (SELECT 1 FROM user WHERE username = ?)";
        try {
            @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //? 위치
            preparedStatement.setString(1, username);
            @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getBoolean(1); // 1이면 존재, 0이면 없음
            }
        } catch (Exception e) {
            throw  new Exception("사용자 정보가 존재하지 않습니다." + username);
        }

        // 결과가 없을 경우 false 반환
        return false;

    }
}
