package handlers;

import spark.Request;
import spark.Response;
import spark.Route;
import org.json.JSONObject;
import org.json.JSONArray;
import com.j256.ormlite.support.ConnectionSource;
import daos.DepartamentoDao;
import config.Database;

public class DepartamentoHanlder{
  public static Route listar = (Request request, Response response) -> {
    DepartamentoDao departamentoDao = new DepartamentoDao();
    return departamentoDao.listar();
  };

  public static Route guardar = (Request request, Response response) -> {
    String rpta = "";
    JSONObject data = new JSONObject(request.queryParams("data"));
    JSONArray nuevos = data.getJSONArray("nuevos");
    JSONArray editados = data.getJSONArray("editados");
    JSONArray eliminados = data.getJSONArray("eliminados");
    //array_nuevos = []
    //error = false
    //execption = nil
    try {
      DepartamentoDao departamentoDao = new DepartamentoDao();
      if(nuevos.length() > 0){
        for (int i = 0; i < nuevos.length(); i++) {
          JSONObject departamento = nuevos.getJSONObject(i);
          String id = departamento.getString("id");
          String nombre = departamento.getString("nombre");
          int nuevoId = departamentoDao.crear(nombre);
        }
      }
      if(editados.length() > 0){
        for (int i = 0; i < editados.length(); i++) {
          JSONObject departamento = editados.getJSONObject(i);
          String id = departamento.getString("id");
          String nombre = departamento.getString("nombre");
        }
      }
      if(eliminados.length() > 0){
        for (Object eliminado : eliminados) {
          int eleminadoId = (Integer)eliminado;
        }
      }
    } catch (Exception e) {
      //e.printStackTrace();
      String[] error = {"Se ha producido un error en  guardar los departamento", e.toString()};
      JSONObject rptaTry = new JSONObject();
      rptaTry.put("tipo_mensaje", "error");
      rptaTry.put("mensaje", error);
      rpta = rptaTry.toString();
    }
    System.out.println("A ++++++++++++++++++++++++++++++++++++++++++");
    System.out.println(rpta);
    System.out.println("B ++++++++++++++++++++++++++++++++++++++++++");
    return rpta;
  };
}

