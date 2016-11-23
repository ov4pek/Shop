package ru.kpfu.servlets.CRUD;

import ru.kpfu.models.UserHandler;
import ru.kpfu.repositories.GoodDataBase;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Admin on 23.10.2016.
 */
public class GoodRemoveServlet extends HttpServlet {
    UserHandler uh = new UserHandler();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (uh.checkSession(req)) {
            try {
                GoodDataBase.removeGood(Integer.valueOf(req.getParameter("id")));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resp.sendRedirect("/basket");
        } else resp.sendRedirect("/input");
    }
}
