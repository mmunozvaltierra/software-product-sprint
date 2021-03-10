package com.google.sps.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/form-handler")
public class FormHandlerServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    // Get the values entered in the form.
    String firstName = request.getParameter("first-name");
    String lastName = request.getParameter("last-name");
    String email = request.getParameter("email");
    String message = request.getParameter("message-content");

    // Print the value so you can see it in the server logs.
    System.out.println(firstName + " " + lastName + " wants to contact you:");
    System.out.println("Their email is: " + email);
    System.out.println("Their message was: " + message);

    // Redirect user after input
    response.sendRedirect("/submitted.html");
  }
}