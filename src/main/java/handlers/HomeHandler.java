package handlers;

import spark.Request;
import spark.Response;
import spark.Route;
import java.util.HashMap;
import java.util.Map;
import config.App;

public class HomeHandler {
  public static Route index = (Request request, Response response) -> {
    Map<String, Object> model = new HashMap<>();
    model.put("partial", "templates/home/index.vm");
    model.put("title", "Home");
    return App.renderTemplate("templates/layouts/blank.vm", model);
  };
}

