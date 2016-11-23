package ru.kpfu.servlets;

import ru.kpfu.entites.CatalogGood;
import ru.kpfu.entites.Comment;
import ru.kpfu.models.UserHandler;
import ru.kpfu.repositories.CatalogDataBase;
import ru.kpfu.repositories.GoodDataBase;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Admin on 26.10.2016.
 */
public class GoodInfoServlet extends HttpServlet {
    UserHandler handler = new UserHandler();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CatalogGood catalogGood;

            if (handler.checkSession(req)) {
                req.setAttribute("session", 1);
            }
            try {
                int catalogId = Integer.valueOf(req.getParameter("catalogId"));
                catalogGood = CatalogDataBase.getInfoFromCatalog(catalogId);
                req.setAttribute("catalogId", catalogId);
                req.setAttribute("good_name", catalogGood.getName());
                req.setAttribute("good_price", catalogGood.getPrice());
                req.setAttribute("good_description", catalogGood.getDescription());
                req.setAttribute("img_good", catalogGood.getImg());

                req.setAttribute("comments", GoodDataBase.getComments(req, catalogId));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        if (req.getSession().getAttribute("inputLogin") != null && req.getSession().getAttribute("inputLogin").equals("admin@gmail.com")) {
            getServletContext().getRequestDispatcher("/WEB-INF/views/goodInfoAdmin.jsp").forward(req, resp);
        }
            else getServletContext().getRequestDispatcher("/WEB-INF/views/goodInfo.jsp").forward(req, resp);
        }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(handler.checkSession(req)) {
            Date d = new Date();
            SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy hh:mm");
            if (req.getParameter("btnAddComment") != null) {
                int catalogId = Integer.valueOf(req.getParameter("catalogId"));
                try {
                    GoodDataBase.AddComment(new Comment(
                            req.getSession().getAttribute("inputLogin").toString(),
                            req.getParameter("comment"),
                            format1.format(d),
                            catalogId
                    ));
                    resp.sendRedirect("/info?catalogId="+catalogId);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(req.getParameter("edit")!=null){

            }
            if(req.getParameter("remove")!=null){

            }
        } else resp.sendRedirect("/input");
    }
}
