package ru.kpfu.interfaces;

import ru.kpfu.entites.User;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Admin on 21.09.2016.
 */
public interface DataBaseInterface {
    public void addUser(User user) throws SQLException;
    public boolean checkLogin(String login) throws IOException, SQLException;
}
