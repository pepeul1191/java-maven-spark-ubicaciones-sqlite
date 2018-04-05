package handlers;

import spark.Request;
import spark.Response;
import spark.Route;
import org.json.JSONObject;
import org.json.JSONArray;
import com.j256.ormlite.support.ConnectionSource;
import java.util.ArrayList;
import java.util.List;
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
    List<JSONObject> listJSONNuevos = new ArrayList<JSONObject>();
    boolean error = false;
    String execption = "";
    DepartamentoDao departamentoDao = new DepartamentoDao();
    try {
      if(nuevos.length() > 0){
        for (int i = 0; i < nuevos.length(); i++) {
          JSONObject departamento = nuevos.getJSONObject(i);
          String antiguoId = departamento.getString("id");
          String nombre = departamento.getString("nombre");
          int nuevoId = departamentoDao.crear(nombre);
          JSONObject temp = new JSONObject();
          temp.put("temporal", antiguoId);
          temp.put("nuevo_id", nuevoId);
          listJSONNuevos.add(temp);
        }
      }
      if(editados.length() > 0){
        for (int i = 0; i < editados.length(); i++) {
          JSONObject departamento = editados.getJSONObject(i);
          int id = departamento.getInt("id");
          String nombre = departamento.getString("nombre");
          departamentoDao.editar(id, nombre);
        }
      }
      if(eliminados.length() > 0){
        for (Object eliminado : eliminados) {
          int eleminadoId = (Integer)eliminado;
          departamentoDao.eliminar(eleminadoId);
        }
      }
    } catch (Exception e) {
      error = true;
      execption = e.toString();
      //Sequel::Rollback
    } finally {
      departamentoDao.close();
    }
    if(error){
      String[] cuerpoMensaje = {"Se ha producido un error en  guardar los departamento", execption};
      JSONObject rptaMensaje = new JSONObject();
      rptaMensaje.put("tipo_mensaje", "error");
      rptaMensaje.put("mensaje", cuerpoMensaje);
      rpta = rptaMensaje.toString();
    }else{
      String[] cuerpoMensaje = {"Se ha registrado los cambios en los departamentos", listJSONNuevos.toString()};
      JSONObject rptaMensaje = new JSONObject();
      rptaMensaje.put("tipo_mensaje", "success");
      rptaMensaje.put("mensaje", cuerpoMensaje);
      rpta = rptaMensaje.toString();
    }
    return rpta;
  };
}

