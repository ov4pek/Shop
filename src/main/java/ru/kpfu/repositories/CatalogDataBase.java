package ru.kpfu.repositories;

import ru.kpfu.entites.CatalogGood;
import ru.kpfu.entites.Good;
import ru.kpfu.models.DBWrapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 27.10.2016.
 */
public class CatalogDataBase {
    private static Connection conn;
    private static ResultSet rs;

    public static List<CatalogGood> getAllGoodsFromCatalog(String type) throws SQLException {
        ArrayList<CatalogGood> catalogGoods = new ArrayList<CatalogGood>();
        conn = DBWrapper.getConection();
        ResultSet rs = null;
        rs = conn.createStatement().executeQuery("SELECT* FROM catalog WHERE type='" + type + "'");
        while (rs.next()) {
            catalogGoods.add(new CatalogGood(
                    rs.getInt("catalog_good_id"),
                    rs.getString("good_name"),
                    rs.getInt("good_price"),
                    rs.getString("good_description"),
                    rs.getString("good_img")));
        }
        return catalogGoods;
    }

    public static List<CatalogGood> getLast8GoodsFromCatalog() {
        ArrayList<CatalogGood> catalogGoods = new ArrayList<CatalogGood>();
        conn = DBWrapper.getConection();
        try {
            rs = conn.createStatement().executeQuery(
                    "SELECT * FROM catalog ORDER BY catalog_good_id DESC LIMIT 8"
            );
            while (rs.next()) {
                catalogGoods.add(new CatalogGood(
                        rs.getInt("catalog_good_id"),
                        rs.getString("good_name"),
                        rs.getInt("good_price"),
                        rs.getString("good_description"),
                        rs.getString("good_img")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return catalogGoods;
    }

    public static CatalogGood getInfoFromCatalog(int catalogId) throws SQLException {
        conn = DBWrapper.getConection();
        CatalogGood catalogGood = null;
        rs = conn.createStatement().executeQuery("SELECT * FROM catalog " +
                "WHERE catalog_good_id=" + catalogId);
        while (rs.next()) {
            catalogGood = new CatalogGood(
//                    rs.getInt("catalog_good_id"),
                    rs.getString("good_name"),
                    rs.getInt("good_price"),
                    rs.getString("good_description"),
                    rs.getString("good_img")
            );
        }
        return catalogGood;
    }

    public static void AddGoodToCatalog(CatalogGood good) throws SQLException {
        conn = DBWrapper.getConection();
        PreparedStatement st = conn.prepareStatement(
                "INSERT INTO catalog (good_name, good_price, good_description, good_img, type)" +
                        "VALUES (?,?,?,?,?)");
        st.setString(1,good.getName());
        st.setDouble(2,good.getPrice());
        st.setString(3,good.getDescription());
        st.setString(4,good.getImg());
        st.setString(5,good.getType());
        st.execute();
    }
    public static void UpdateGoodInCatalog(CatalogGood good, int catalogId) throws SQLException {
        conn = DBWrapper.getConection();
        PreparedStatement st = conn.prepareStatement(
                "UPDATE catalog SET good_name=?, good_price=?, good_description=?, good_img=?, type=? WHERE " +
                        "catalog_good_id="+catalogId);
        st.setString(1,good.getName());
        st.setDouble(2,good.getPrice());
        st.setString(3,good.getDescription());
        st.setString(4,good.getImg());
        st.setString(5,good.getType());
        st.execute();
    }
    public static void UpdateGoodInCatalogWithoutImg(CatalogGood good) throws SQLException {
        conn = DBWrapper.getConection();
        PreparedStatement st = conn.prepareStatement(
                "UPDATE catalog SET good_name=?, good_price=?, good_description=?, type=?");
        st.setString(1,good.getName());
        st.setDouble(2,good.getPrice());
        st.setString(3,good.getDescription());
        st.setString(4,good.getType());
        st.execute();
    }


    }

