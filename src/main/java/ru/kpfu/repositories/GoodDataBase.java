package ru.kpfu.repositories;

import ru.kpfu.entites.CatalogGood;
import ru.kpfu.entites.Comment;
import ru.kpfu.entites.Good;
import ru.kpfu.models.DBWrapper;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 23.10.2016.
 */
public class GoodDataBase {
    private static Connection conn;
    private static Statement stmt;
    private static ResultSet rs;


    public static void addGood(Good good) throws SQLException {
        conn = DBWrapper.getConection();
        PreparedStatement st = conn.prepareStatement(
                " INSERT INTO goods_list (catalog_good_id, quantity, user_login)" +
                        "VALUES (?,?,?)");
        st.setInt(1, good.getCatalogId());
        st.setInt(2, good.getQuantity());
        st.setString(3, good.getUserLogin());
        st.execute();

    }

    public static List<Good> getAllGoods(HttpServletRequest req) {
        ArrayList<Good> goods = new ArrayList<Good>();
        conn = DBWrapper.getConection();
        String login = req.getSession().getAttribute("inputLogin").toString();
        ResultSet rs = null;
        try {
            rs = conn.createStatement().executeQuery(
                    "select c.good_name,c.good_price,c.good_img, g.* from goods_list g LEFT JOIN catalog c ON" +
                            " g.catalog_good_id=c.catalog_good_id " +
                            "WHERE user_login='" + login + "'");
            while (rs.next()) {
                goods.add(new Good(
                        rs.getInt("catalog_good_id"),
                        rs.getString("good_img"),
                        rs.getInt("good_list_id"),
                        rs.getString("good_name"),
                        rs.getFloat("good_price"),
                        rs.getInt("quantity")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goods;
    }

    public static List<Comment> getComments(HttpServletRequest req, int catalogId){
        ArrayList<Comment> comments = new ArrayList<Comment>();
        conn = DBWrapper.getConection();
        ResultSet rs = null;
        try {
            rs = conn.createStatement().executeQuery(
                    "select com.comment_text,com.comment_date, us.name from comments com LEFT JOIN users us ON"+
                    " com.user_login=us.login  WHERE (catalog_good_id="+catalogId+");");
            while (rs.next()) {
                comments.add(new Comment(
                        rs.getString("name"),
                        rs.getString("comment_text"),
                        rs.getString("comment_date")
                        ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }
    public static void AddComment(Comment comment) throws SQLException {
        conn = DBWrapper.getConection();
        PreparedStatement st = conn.prepareStatement(
                " INSERT INTO comments (catalog_good_id, user_login, comment_text, comment_date)" +
                        "VALUES (?,?,?,?);");
        st.setInt(1, comment.getCatalogId());
        st.setString(2, comment.getUserName());
        st.setString(3, comment.getText());
        st.setString(4, comment.getDate());
        st.execute();
    }

    public static int getLastId(String login) throws SQLException {
        int goodId = 0;
        conn = DBWrapper.getConection();
        rs = conn.createStatement().executeQuery("SELECT * FROM goods_list " +
                "WHERE good_list_id = (SELECT MAX(good_list_id) from goods_list) AND user_login='" + login + "'");
        while (rs.next()) {
            goodId = rs.getInt("good_list_id");
        }
        return goodId;
    }

    public static void removeAllGoods(HttpServletRequest req) throws SQLException {

        String login = req.getSession().getAttribute("inputLogin").toString();
        conn.createStatement().executeQuery("DELETE FROM goods_list WHERE user_login='" + login + "'");
    }

    public static void removeGood(int id) throws SQLException {
        conn = DBWrapper.getConection();
        conn.createStatement().executeQuery("DELETE FROM goods_list WHERE good_list_id='" + id + "'");
    }

    public static void setQuantityInBasket(int basketId, int quantity) throws SQLException {
        conn = DBWrapper.getConection();
        conn.createStatement().executeQuery("UPDATE goods_list SET quantity='" + quantity + "' WHERE good_list_id='" + basketId + "'");
    }

    public static float getTotalPrice(String login) throws SQLException {
        float totalprice=0;
        conn = DBWrapper.getConection();
        rs = conn.createStatement().executeQuery(
                "select c.good_price, g.quantity from goods_list g LEFT JOIN catalog c ON " +
                        "g.catalog_good_id=c.catalog_good_id  WHERE user_login='" + login + "'");
        while(rs.next()){
            float price =rs.getFloat("good_price");
            int quantity=rs.getInt("quantity");
            totalprice+=price*quantity;
        }
        return totalprice;
    }
}
