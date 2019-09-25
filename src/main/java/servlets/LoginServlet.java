package servlets;

import entities.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private static final String ACTUAL_LOGIN = "posh";
    private static final String ACTUAL_PASSWORD = "123";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        System.out.println(request.getParameter("login") + " " + request.getParameter("password"));
        if (login.equals(ACTUAL_LOGIN) && password.equals(ACTUAL_PASSWORD)) {
            User user = new User(login, password);
            request.getSession().setAttribute("user", user);
            response.sendRedirect("/profile");
        } else {
            response.sendRedirect("/login");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            response.sendRedirect("/profile");
        } else {
            response.getWriter().write("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Title</title>\n" +
                    "</head>\n" +
                    "<body>\n" +

                    "    <form action=\"/login\" method=\"post\">\n" +
                    "        <input type=\"text\" name=\"login\"> \n" +
                    "        <input type=\"password\" name=\"password\">\n" +
                    "        <input type=\"submit\">\n" +
                    "    </form>\n" +

                    "</body>\n" +
                    "</html>");


        }


    }
}
