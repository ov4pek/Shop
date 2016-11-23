package ru.kpfu.servlets;

import ru.kpfu.models.UserHandler;
import ru.kpfu.repositories.CatalogDataBase;
import ru.kpfu.repositories.GoodDataBase;
import ru.kpfu.repositories.UserDataBase;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Admin on 18.10.2016.
 */
public class CatalogServlet extends HttpServlet {
    UserHandler uh = new UserHandler();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (uh.checkSession(req)) {
            req.setAttribute("session", 1);
        }
            try {
                String type = req.getParameter("type");
                req.setAttribute("type", type);
                req.setAttribute("catalog_goods", CatalogDataBase.getAllGoodsFromCatalog(type));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            getServletContext().getRequestDispatcher("/WEB-INF/views/catalog.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name_good");
//        float price =  Float.valueOf(req.getParameter("price"));
//        if (req.getParameter("add") != null) {
//            int quantity = Integer.valueOf(req.getParameter("quantity"));
//            System.out.println(req.getParameter("catalog_id"));
//            System.out.println(req.getAttribute("catalog_id"));
//            Good good = new Good(Integer.valueOf(req.getParameter("catalog_good_id")),
//                    quantity,
//                    req.getSession().getAttribute("inputLogin").toString());
//            try {
//                GoodDataBase.addGood(good);
////                resp.sendRedirect("/catalog");
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }

//        if (req.getParameter("basket") != null) {
//            resp.sendRedirect("/basket");
//        }
    }
}

