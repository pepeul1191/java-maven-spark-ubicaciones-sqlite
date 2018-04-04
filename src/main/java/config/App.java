package config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

class App {
  public static void main(String args[]){
    Config constants = ConfigFactory.parseResources("config/application.conf");
    System.out.println("Hola mundo!??");
    System.out.println("BASE_URL : " + constants.getString("base_url"));
  }
}