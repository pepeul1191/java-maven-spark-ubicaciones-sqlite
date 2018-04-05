package handlers;

import spark.Request;
import spark.Response;
import spark.Route;
import daos.DepartamentoDao;
//import config.App;

public class DepartamentoHanlder {
  public static Route listar = (Request request, Response response) -> {
    DepartamentoDao departamentoDao = new DepartamentoDao();
    return departamentoDao.listar();
  };
}

