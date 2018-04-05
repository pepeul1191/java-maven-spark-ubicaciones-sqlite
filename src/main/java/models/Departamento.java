package models;

import java.io.Serializable;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "departamentos")
public class Departamento implements Serializable {
  private static final long serialVersionUID = 1L;
  @DatabaseField(id = true)
  private int id;
  @DatabaseField(canBeNull = false, columnName = "nombre")
  private String nombre;

  Departamento(){}

  public int getId() { return id; }

  public void setId(int id) { this.id = id; }

  public String getNombre() { return nombre; }

  public void setNombre(String nombre) { 
    this.nombre = nombre; 
  }
}
