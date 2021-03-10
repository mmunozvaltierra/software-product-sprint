package com.google.sps.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/** Handles requests sent to the /hello URL. Try running a server and navigating to /hello! */
@WebServlet("/hello")
public class HelloWorldServlet extends HttpServlet {
    

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    ArrayList<String> messages = new ArrayList<String>();
    messages.add("A fun fact about me is that I've flown a plane before! I participated in a program in 8th grade that ended with a pilot letting me fly his plane!");
    messages.add("Some of my favorite movies are The Shawshank Redemption and Pulp Fiction");
    messages.add("A place I have always wanted to visit is Rome. I hope to do so after graduating.");

    Gson gson = new Gson();
    String json = gson.toJson(messages);


    response.setContentType("application/json;");
    response.getWriter().println(json);
  }
}
