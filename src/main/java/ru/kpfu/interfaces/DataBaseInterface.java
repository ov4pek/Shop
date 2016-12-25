package ru.kpfu.interfaces;

import ru.kpfu.entites.User;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by danil on 12.11.16.
 */
    public interface DataBaseInterface {
        public void addUser(User user) throws SQLException;
        public boolean checkLogin(String login) throws IOException, SQLException;
    }

