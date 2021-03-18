package com.google.sps.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.KeyFactory;

@WebServlet("/form-handler")
public class FormHandlerServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    // Get the values entered in the form.
    String firstName = request.getParameter("first-name");
    String lastName = request.getParameter("last-name");
    String fullName = firstName + " " + lastName;
    String email = request.getParameter("email");
    String message = request.getParameter("message-content");

    //Store values in Datastore
    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    KeyFactory keyFactory = datastore.newKeyFactory().setKind("Message");
    FullEntity messageEntity =
        Entity.newBuilder(keyFactory.newKey())
            .set("name", fullName)
            .set("email", email)
            .set("content", message)
            .build();
    datastore.put(messageEntity);

    // Redirect user after input
    response.sendRedirect("/submitted.html");
  }
}