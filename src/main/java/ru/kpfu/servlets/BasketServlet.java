package ru.kpfu.servlets;

import ru.kpfu.models.UserHandler;
import ru.kpfu.repositories.GoodDataBase;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by danil on 18.11.2016.
 */
public class BasketServlet extends HttpServlet {
    UserHandler uh = new UserHandler();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (uh.checkSession(req)) {
            req.setAttribute("session", 1);
            req.setAttribute("goods", GoodDataBase.getAllGoods(req));

            getServletContext().getRequestDispatcher("/WEB-INF/views/basket.jsp").forward(req, resp);
        } else resp.sendRedirect("/input");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        if(req.getParameter("deleteAll")!=null){
            try {
                GoodDataBase.removeAllGoods(req);
                req.setAttribute("goods", GoodDataBase.getAllGoods(req));

            } catch (SQLException e) {
                e.printStackTrace();
            }
        resp.setContentType("text/xml");
        resp.getWriter().println("<h3>No goods to display</h3>");
//            resp.sendRedirect("/basket");
//        }

    }
}
