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


        Integer index = null;

        Connection conn;
        PreparedStatement statement = null;


            try {
                conn = dataSource.getConnection();

                if("STUDENT".equals(accountType))
                    statement = conn.prepareStatement("select * from studenci where imie=? and nazwisko=?;");
                if("KOORDYNATOR".equals(accountType))
                    statement = conn.prepareStatement("select * from koordynatorzy where imie=? and nazwisko=?;");
                if("PRACOWNIK_DZIEKANATU".equals(accountType))
                    statement = conn.prepareStatement("select * from pracownicy_dziekanatu where imie=? and nazwisko=?;");

                statement.setString(1,name);
                statement.setString(2,lastName);
                ResultSet resultSet = statement.executeQuery();
                while(resultSet.next()){
                    //user already exists
                    return 2;
                }
                password = passwordEncode.encodePassword(password);
                statement = conn.prepareStatement("insert into users (password) values (?);");
                statement.setString(1,password);
                statement.execute();

                statement = conn.prepareStatement("select username from users where password=?;");
                statement.setString(1,password);
                resultSet = statement.executeQuery();

                while (resultSet.next())
                    index=resultSet.getInt("username");

                statement = conn.prepareStatement("insert into authorities (username,authority) values (?,?);");
                statement.setInt(1,index);
                statement.setString(2,"ROLE_"+accountType);
                statement.execute();

                if("STUDENT".equals(accountType))
                    statement = conn.prepareStatement("insert into studenci(nrUSOS, imie, nazwisko) values (?,?,?);");
                if("KOORDYNATOR".equals(accountType))
                    statement = conn.prepareStatement("insert into koordynatorzy(id_k, imie, nazwisko) values (?,?,?);");
                if("PRACOWNIK_DZIEKANATU".equals(accountType))
                    statement = conn.prepareStatement("insert into pracownicy_dziekanatu(id_dz, imie, nazwisko) values (?,?,?);");

                statement.setInt(1,index);
                statement.setString(2,name);
                statement.setString(3,lastName);
                statement.execute();
            }
            catch(SQLException ex) {
                ex.printStackTrace();
            }

        return index;
    }
}
