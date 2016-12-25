package ru.kpfu.servlets;

import ru.kpfu.models.UserHandler;
import ru.kpfu.repositories.CatalogDataBase;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by danil on 18.11.2016.
 */
public class MainPage extends HttpServlet{
    UserHandler handler = new UserHandler();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            if (handler.checkSession(req)) {
                req.setAttribute("session", 1);
            }
            req.setAttribute("catalog_goods", CatalogDataBase.getLast8GoodsFromCatalog());
        if(req.getSession().getAttribute("inputLogin")!=null && req.getSession().getAttribute("inputLogin").equals("admin@gmail.com")) {
            resp.sendRedirect("/admin");
        } else{
            getServletContext().getRequestDispatcher("/WEB-INF/views/mainPage.jsp").forward(req, resp);

        }
    }
}

