package handlers;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import spark.Request;
import spark.Response;
import spark.Route;
import java.util.HashMap;
import java.util.Map;
import config.App;
import helpers.HomeHelper;
import helpers.ViewHelper;

public class HomeHandler {
  public static Route index = (Request request, Response response) -> {
    Config constants = ConfigFactory.parseResources("config/application.conf");
    Map<String, Object> model = new HashMap<>();
    model.put("partial", "templates/home/index.vm");
    model.put("title", "Home");
    model.put("constants", constants);
    model.put("load_css", ViewHelper.loadCSS(constants, HomeHelper.indexCSS(constants)));
    model.put("load_js", ViewHelper.loadJS(constants, HomeHelper.indexJS(constants)));
    return App.renderTemplate("templates/layouts/blank.vm", model);
  };
}

