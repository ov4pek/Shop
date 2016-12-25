package ru.kpfu.models;

import org.junit.Test;
import org.mockito.Mockito;
import ru.kpfu.entites.User;
import ru.kpfu.repositories.UserDataBase;

import static org.junit.Assert.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by danil on 25.12.16.
 */
public class UserHandlerTest extends Mockito {
    ArrayList<User> users = mock(ArrayList.class);
    @Test
    public void shouldCreateUser() throws IOException, SQLException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse responce = mock(HttpServletResponse.class);

        when(request.getParameter("fullname")).thenReturn("test");
        when(request.getParameter("login")).thenReturn("test");
        when(request.getParameter("password")).thenReturn("test");
        when(request.getParameter("gender")).thenReturn("0");
        when(request.getParameter("country")).thenReturn("test");
        when(request.getParameter("aboutYourself")).thenReturn("test");
        when(request.getParameter("newsletter")).thenReturn("0");

        new UserHandler().checkError(request);
        User user = new User(request.getParameter("login"),
                request.getParameter("password"),
                request.getParameter("name"),
                request.getParameter("gender"),
                request.getParameter("country"),
                request.getParameter("aboutYourself"),
                request.getParameter("newsletter")
        );
        users.add(user);

        verify(users).add(user);
    }
}
