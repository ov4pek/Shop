package ru.kpfu.repositories;

import ru.kpfu.interfaces.DataBaseInterface;
import ru.kpfu.models.DBWrapper;
import ru.kpfu.entites.User;

import java.io.*;
import java.sql.*;

/**
 * Created by danil on 12.11.2016.
 */
public class UserDataBase implements DataBaseInterface{
    private static Connection conn;
    private static Statement stmt;
    private static ResultSet rs;

    public void addUser(User user) throws SQLException {
        conn = DBWrapper.getConection();
//         stmt = conn.createStatement();
        PreparedStatement st = conn.prepareStatement(
                        " INSERT INTO users (login, password, name, gender, country, aboutyourself, newsletter)" +
                        "VALUES (?, ?,?, ?, ?,?,?);");

        st.setString(1, user.getLogin());
        st.setString(2, user.getPassword());
        st.setString(3, user.getName());
        st.setString(4, user.getGender());
        st.setString(5, user.getCountry());
        st.setString(6, user.getAboutYourself());
        st.setString(7, user.getNewsletter());
        st.execute();
    }


    public boolean checkLogin(String login) throws IOException, SQLException {
        conn = DBWrapper.getConection();
        stmt = conn.createStatement();
        rs = stmt.executeQuery(
                "SELECT login FROM users WHERE login='" + login + "'");
        if (rs.next()) return true;
        else return false;
    }

    public boolean checkLoginAndPassword(String login, String password) throws SQLException {
        conn = DBWrapper.getConection();
        stmt = conn.createStatement();
        rs = stmt.executeQuery(
                "SELECT login, password FROM users WHERE login='" + login + "' AND password='" + password + "'");
        if (rs.next()) return true;
        else return false;

    }

}
