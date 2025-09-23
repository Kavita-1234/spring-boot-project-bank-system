package com.example.banksystem.User.Repository;

import com.example.banksystem.User.DTO.UserAdminDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAdminRowMapper implements RowMapper<UserAdminDTO> {

    @Override
    public UserAdminDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserAdminDTO userAdminDTO = new UserAdminDTO();
        userAdminDTO.setUserId(rs.getInt("user_id"));
        userAdminDTO.setFirstName(rs.getString("first_name"));
        userAdminDTO.setLastName(rs.getString("last_name"));
        userAdminDTO.setRole(rs.getString("role"));
        userAdminDTO.setEmail(rs.getString("email"));
        userAdminDTO.setPhone(rs.getString("phone"));
        userAdminDTO.setAddress(rs.getString("address"));
        userAdminDTO.setPassword(rs.getString("password"));
        userAdminDTO.setUserName(rs.getString("user_name"));
        return userAdminDTO;
    }
}
