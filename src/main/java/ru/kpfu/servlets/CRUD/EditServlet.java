package ru.kpfu.servlets.CRUD;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.kpfu.entites.CatalogGood;
import ru.kpfu.models.UserHandler;
import ru.kpfu.repositories.CatalogDataBase;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by danil on 23.11.16.
 */
public class EditServlet extends HttpServlet {
    private UserHandler uh = new UserHandler();
    private String name ="";
    private String description="";
    private String price="";
    private String type="";
    private String fileName=null;
    private int catalogId;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(uh.checkSession(req)) {
            req.setAttribute("session", 1);
            if (req.getParameter("edit") != null) {
                CatalogGood catalogGood = null;
                 catalogId = Integer.valueOf(req.getParameter("id"));
                try {
                    catalogGood = CatalogDataBase.getInfoFromCatalog(catalogId);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                req.setAttribute("name", catalogGood.getName());
                req.setAttribute("price", catalogGood.getPrice());
                req.setAttribute("description", catalogGood.getDescription());
                req.setAttribute("img_good", catalogGood.getImg());
                getServletContext().getRequestDispatcher("/WEB-INF/views/editPage.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(req.getParameter("edit")!=null){

            boolean isMultipart = ServletFileUpload.isMultipartContent(req);
            if (!isMultipart) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
            DiskFileItemFactory factory = new DiskFileItemFactory();

            factory.setSizeThreshold(1024*1024);
            File tempDir = (File)getServletContext().getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(tempDir);

            ServletFileUpload upload = new ServletFileUpload(factory);

            upload.setSizeMax(1024 * 1024 * 10);

            try {
                List items = upload.parseRequest(req);
                Iterator iter = items.iterator();

                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();

                    if (item.isFormField()) {
                        processFormField(item);
                    } else {
                        processUploadedFile(item);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return;
            }
            if(fileName!=null) {
                CatalogGood good = new CatalogGood(
                        name,
                        Float.valueOf(price),
                        description,
                        fileName,
                        type
                );

                try {
                    CatalogDataBase.UpdateGoodInCatalog(good, catalogId);
                    resp.sendRedirect("/info?catalogId=" + catalogId);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else {
                CatalogGood good = new CatalogGood(
                        Integer.valueOf(price),
                        name,
                        description,
                        type
                );

                try {
                    CatalogDataBase.UpdateGoodInCatalogWithoutImg(good);
                    resp.sendRedirect("/info?catalogId=" + name);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


        }
    private void processUploadedFile(FileItem item) throws Exception {
        File uploadetFile = null;
        Random random = new Random();
        do{
            fileName="/images/"+random.nextInt() + item.getName();
//            String path = getServletContext().getRealPath(fileName);
            String path ="/home/danil/Programs/Programming/MotoShop-master/src/main/webapp"+fileName;
            uploadetFile = new File(path);
        }while(uploadetFile.exists());
        uploadetFile.createNewFile();
        item.write(uploadetFile);
    }
    private void processFormField(FileItem item) {
        if(item.getFieldName().equals("name")) name =item.getString();
        else if(item.getFieldName().equals("price")) price =item.getString();
        else if(item.getFieldName().equals("desription")) description =item.getString();
        else if(item.getFieldName().equals("type")) type =item.getString();
    }
    }
