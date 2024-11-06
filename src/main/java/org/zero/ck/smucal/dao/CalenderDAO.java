package org.zero.ck.smucal.dao;

import lombok.Cleanup;
import org.zero.ck.smucal.domain.CalenderVO;
import org.zero.ck.smucal.domain.UserVO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CalenderDAO {

    public void create(CalenderVO vo) throws Exception {
        String sql = "insert into calender (author_id, date, content) values (?, ?, ?)";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, vo.getAuthor().getId());
        preparedStatement.setDate(2, Date.valueOf(vo.getDate()));
        preparedStatement.setString(3, vo.getContent());

        preparedStatement.executeUpdate();

    }


    private UserVO selectUser(int id)throws Exception {

        String sql = "select * from user where id = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setLong(1, id);

        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();
        UserVO vo = UserVO.builder()
                .id(resultSet.getInt(1))
                .username(resultSet.getString(2))
                .password(resultSet.getString(3))
                .build();

        return vo;
    }
    public List<CalenderVO> selectAll()throws Exception  {

        String sql = "select * from calender";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();


        List<CalenderVO> list = new ArrayList<>();

        while(resultSet.next()) {
//            selectUser
            UserVO getUser = selectUser(resultSet.getInt("author_id"));
            CalenderVO vo = CalenderVO.builder()
                    .id(resultSet.getInt("id"))
                    .author(getUser)
                    .date(resultSet.getDate("date").toLocalDate())
                    .content(resultSet.getString("content"))
                    .build();

            list.add(vo);
        }

        return list;
    }

    public void updateCalender(CalenderVO vo)throws Exception{

        String sql = "update calender set date =?, content = ? where id =?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setDate(1, Date.valueOf(vo.getDate()));
        preparedStatement.setString(2, vo.getContent());
        preparedStatement.setInt(3, vo.getId());

        preparedStatement.executeUpdate();
    }

    public CalenderVO getCalender(int id)throws Exception {

        String sql = "select * from calender where id = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setLong(1, id);

        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();
        UserVO getUser = selectUser(resultSet.getInt("author_id"));
        CalenderVO vo = CalenderVO.builder()
                .id(resultSet.getInt("id"))
                .author(getUser)
                .date(resultSet.getDate("date").toLocalDate())
                .content(resultSet.getString("content"))
                .build();

        return vo;
    }
}
