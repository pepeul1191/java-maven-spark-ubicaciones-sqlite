package config;

import java.sql.SQLException;
import java.io.IOException;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

public class Database {
	protected ConnectionSource connectionSource;
	
	public Database() throws SQLException{
		String databaseUrl = "jdbc:sqlite:db/ubicaciones.db";
		connectionSource = new JdbcConnectionSource(databaseUrl);
	}

	public ConnectionSource getConnectionSource() {
		return connectionSource;
	}

	public void close() throws IOException{
    this.connectionSource.close();
  }
}
