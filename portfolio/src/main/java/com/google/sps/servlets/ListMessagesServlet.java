
package com.google.sps.servlets;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.OrderBy;
import com.google.cloud.datastore.StructuredQuery.PropertyFilter;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

/** Servlet responsible for listing messages. */
@WebServlet("/list-messages")
public class ListMessagesServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();

    Query<Entity> query =
        Query.newEntityQueryBuilder().setKind("Message").setOrderBy(OrderBy.desc("name")).build();
    QueryResults<Entity> results = datastore.run(query);

    ArrayList<String> messages = new ArrayList<String>();
    
    while (results.hasNext()) {
      Entity entity = results.next();

      String name = entity.getString("name");
      String content = entity.getString("content");
      String result = name + ": " + content;
      messages.add(result);
    }
    if(messages.size() == 0){
        messages.add("There are currently no messages");
    }

    Gson gson = new Gson();

    response.setContentType("application/json;");
    response.getWriter().println(gson.toJson(messages));
  }
}
