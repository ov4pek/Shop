package ru.kpfu.servlets;

import ru.kpfu.models.UserHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Admin on 20.09.2016.
 */
public class RegistrationServlet extends HttpServlet {
    UserHandler uh = new UserHandler();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        if (uh.checkSession(req)) {
            req.setAttribute("session", 1);
        }
            getServletContext().getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try {
            if(!uh.checkError(req)) resp.sendRedirect("/main");
            else{getServletContext().getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);}
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

//    protected String getPageCode(){
//        return
//                "<!DOCTYPE html><html>"
//                        + "<head><meta charset='UTF-8'><title>Hello page</title></head>"
//                        + "<body>"
//                        + "<form action='' method='POST' >"
//                        + "<p> Enter your fullname <br>"
//                        + "<input type ='text' name='fullname'> </p>"
//                        +"<p>Enter e-mail <br> "
//                        + "<input type='email' name='login'> </p>"
//                        + "<p> Enter password at least 6 character <br>"
//                        + "<input type='password' name='password'> </p>"
//                        + "<p> Confirm password<br>"
//                        + "<input type='password' name='repassword'> </p>"
//                        + "<input type='radio' name='gender' value='1'> Male <br>"
//                        + "<input type='radio' name='gender' value='0'> Female <br>"
//                        + "<p><select name='country' required>"
//                        + "<option></option>"
//                        +"<option disabled> Select country</option>"
//                        +"<option>China</option>"
//                        +"<option>France</option>"
//                        +"<option>Germany </option>"
//                        +"<option>Russia</option>"
//                        +"<option>USA</option>"
//                        +" </select> </p>"
//                        + "<p><textarea name='aboutYourself' placeholder='Read about yourself'></textarea> </p>"
//                        +"<p><input type='checkbox' name='newsletter'> Subcsribe newsletter </p>"
//                        + "<input type='submit' value='Registration'></form>"
////                        + message
//                        + "</body></html>";
//    }

//protected String getErrorPageCode(String message){
//        return
//
//                "<!DOCTYPE html><html>"
//                        + "<head><meta charset='UTF-8'><title>Hello page</title></head>"
//                        + "<body>"
//                        + "<form action='' method='POST' >"
//                        + "<p> Enter your fullname <br>"
//                        + "<input type ='text' name='fullname' value="+name+"> </p>"
//                        +"<p>Enter e-mail <br> "
//                        + "<input type='email' name='login' value="+login+"> </p>"
//                        + "<p> Enter password at least 6 character <br>"
//                        + "<input type='password' name='password'> </p>"
//                        + "<p> Confirm password<br>"
//                        + "<input type='password' name='repassword'> </p>"
//                        + "<input type='radio' name='gender' value='1' "+ (gender.equals("1") ? " checked ":" ") +"> Male <br>"
//                        + "<input type='radio' name='gender' value='0' "+ (gender.equals("0") ? " checked ":" ") +"> Female <br>"
//                        + "<p><select name='country' required value="+country+">"
//                        + "<option></option>"
//                        +"<option disabled> Select country</option>"
//                        +"<option value='China'>China</option>"
//                        +"<option>France</option>"
//                        +"<option>Germany </option>"
//                        +"<option>Russia</option>"
//                        +"<option>USA</option>"
//                        +" </select> </p>"
//                        + "<p><textarea name='aboutYourself' placeholder='Read about yourself'>"+aboutYourself+"</textarea> </p>"
//                        +"<p><input type='checkbox' name='newsletter'> Subcsribe newsletter </p>"
//                        + "<input type='submit' value='Registration'></form>"
//                        + message
//                        + "</body></html>";
//    }

}
