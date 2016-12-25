package ru.kpfu.servlets;

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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Created by danil on 22.11.16.
 */
//@MultipartConfig(fileSizeThreshold=1024*1024*5, // 5MB
//        maxFileSize=1024*1024*10,      // 10MB
//        maxRequestSize=1024*1024*50)
public class AdminServlet extends HttpServlet{
    private String fileName="";
    private char[] symbols ={'q','w','e','r','t','y','u','i','o','p','a','s','d','f','g','h','j','k','l'};
    UserHandler uh = new UserHandler();
    private static final String SAVE_DIR = "/images";
   private String name ="";
   private String description="";
   private String price="";
   private String type="";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(uh.checkSession(req)) {
            req.setAttribute("session", 1);
            if (req.getSession().getAttribute("inputLogin").equals("admin@gmail.com")) {

                getServletContext().getRequestDispatcher("/WEB-INF/views/adminPage.jsp").forward(req, resp);
            }
        }else resp.sendRedirect("/input");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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


        try {

            CatalogGood good = new CatalogGood(
                    name,
                    Integer.valueOf(price),
                    description,
                    fileName,
                    type
            );

            try {
                CatalogDataBase.AddGoodToCatalog(good);
                resp.sendRedirect("/admin");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }catch (NumberFormatException e){
            req.setAttribute("errPrice","Введите корректную цену");
            getServletContext().getRequestDispatcher("/WEB-INF/views/adminPage.jsp").forward(req, resp);
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








//        String appPath = getServletContext().getRealPath("");
//        String fileName="";
//        File fileSaveDir = new File(appPath+SAVE_DIR);
//        if (!fileSaveDir.exists()) {
//            fileSaveDir.mkdir();
//        }
//        Random random = new Random();
//
//        for (Part part : req.getParts()) {
//            fileName = extractFileName(part);
//            File uploadetfile = null;
//            fileName=symbols[random.nextInt(18)]+symbols[random.nextInt(18)]+symbols[random.nextInt(18)]
//                    +symbols[random.nextInt(18)]+symbols[random.nextInt(18)]+symbols[random.nextInt(18)]
//                    +symbols[random.nextInt(18)]+symbols[random.nextInt(18)]+symbols[random.nextInt(18)]
//                    +symbols[random.nextInt(18)]
//                    +symbols[random.nextInt(18)]+String.valueOf(random.nextInt(10000));
//            try {
//                uploadetfile = new File(appPath + SAVE_DIR + File.separator + fileName);
//                uploadetfile.createNewFile();
//                part.write(appPath + SAVE_DIR + File.separator + fileName);
//
//            }
//            catch(IOException e){
//                appPath="/home/danil/Programs/Programming/MotoShop-master/src/main/webapp/";
//                uploadetfile = new File(appPath + SAVE_DIR + File.separator + fileName);
//                part.write(appPath + SAVE_DIR + File.separator + fileName);
//            }
//        }
//    }
//    /**
//     * Extracts file name from HTTP header content-disposition
//     */
//    private String extractFileName(Part part) {
//        String contentDisp = part.getHeader("content-disposition");
//        String[] items = contentDisp.split(";");
//        for (String s : items) {
//            if (s.trim().startsWith("name")) {
//                return s.substring(s.indexOf("=") + 2, s.length()-1);
//            }
//        }
//        return "";
//    }
}
