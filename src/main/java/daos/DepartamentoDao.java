package daos;

import java.util.ArrayList;
import java.util.List;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import org.json.JSONObject;
import models.Departamento;
import config.Database;

public class DepartamentoDao{
  public DepartamentoDao(){
  }

  public String listar(){
    String rpta = "";
    try {
      Database conexion = new Database();
      ConnectionSource connectionSource = conexion.getConnectionSource();
      List<JSONObject> rptaTemp = new ArrayList<JSONObject>();
      Dao<Departamento, String> departamentoDao = DaoManager.createDao(connectionSource, Departamento.class);
      QueryBuilder<Departamento, String> queryBuilder = departamentoDao.queryBuilder();
      PreparedQuery<Departamento> preparedQuery = queryBuilder.prepare();
      List<Departamento> departamentoList = departamentoDao.query(preparedQuery);
      for (Departamento departamento : departamentoList) {
        JSONObject obj = new JSONObject();
        obj.put("id", departamento.getId());
        obj.put("nombre", departamento.getNombre());
        rptaTemp.add(obj);
      }
      rpta = rptaTemp.toString();
    } catch (Exception e) {
      //e.printStackTrace();
      String[] error = {"Se ha producido un error en  listar los sistemas registrado", e.toString()};
      JSONObject rptaTry = new JSONObject();
      rptaTry.put("tipo_mensaje", "error");
      rptaTry.put("mensaje", error);
      rpta = rptaTry.toString();
    }
    return rpta;
  }
}