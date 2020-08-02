package fr.it.cluster;

import Dao.connection.UserDaoImpl;
import Model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "IndexServlet", urlPatterns = {"/"})

public class IndexServlet extends HttpServlet {
    private Singleton html;
    private String name;
    private String lastname;
    private String email;
    private String password;
    private String emailLog;
    private String passwordLog;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        HttpSession session = request.getSession();
        UserDaoImpl userDaoImpl = new UserDaoImpl();

        switch (request.getRequestURI()) {
            case "/login":
                html.setIndex("login.html");
                emailLog = request.getParameter("GmailLog");
                passwordLog = request.getParameter("PasswordLog");

                User user = userDaoImpl.findUserByEmail(emailLog);
                if (user != null && user.getEmail().equals(emailLog) && passwordLog.equals(user.getPassword())) {
                    session.setAttribute("userName", user.getName());
                    session.setAttribute("userId", user.getId());
                    response.sendRedirect("/notes");
                } else {

                    response.sendRedirect("/login");
                }
                System.out.println(passwordLog + " " + emailLog);

                break;
            case "/register":


                System.out.println("hello sasha");
                html.setIndex("index.html");
                name = request.getParameter("FirstName");
                lastname = request.getParameter("LastName");
                email = request.getParameter("Gmail");
                password = request.getParameter("Password");
                User userDataBase1 = new User( name, lastname, email, password);
                userDaoImpl.save(userDataBase1);
                response.sendRedirect("/login");
                break;


        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.println(html.getIndex());
        switch (request.getRequestURI()) {
            case "/login":
                html.setIndex("login.html");
                writer.println(html.getHtml(html.getIndex()));
                break;
            case "/register":
            case "/":
                html.setIndex("index.html");
                writer.println(html.getHtml(html.getIndex()));
                System.out.println("hello olezhik");
                break;
        }


    }

    @Override
    public void init() throws ServletException {
        html = Singleton.getInstance();
        if (html.getPath().equals("")) {
            html.setPath(getServletContext().getRealPath("/html/"));
        }
//        html.setIndex("index.html");

        System.out.println("Path\t"+html.getPath());

    }
}
