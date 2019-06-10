package com.miniusos_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class Registration {

    @Autowired
    DataSource dataSource;

    @Autowired
    PasswordEncode passwordEncode;

    public Registration(){ }

    public int register(String accountType, String name, String lastName, String password){
        Connection conn;
        PreparedStatement statement;
        try {
            conn = dataSource.getConnection();
            statement = conn.prepareStatement("select * from users where username=?");
            statement.setString(1,name);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                //user already exists
                return 2;
            }
            password = passwordEncode.encodePassword(password);
            statement = conn.prepareStatement("insert into users (username,password) values (?,?);");
            statement.setString(1,name);
            statement.setString(2,password);
            statement.execute();

            statement = conn.prepareStatement("insert into authorities (username,authority) values (?,?);");
            statement.setString(1,name);
            statement.setString(2,"ROLE_"+accountType);
            statement.execute();
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
        return 1;
    }
}
