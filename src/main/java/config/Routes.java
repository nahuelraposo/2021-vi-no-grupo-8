package config;

import controllers.*;
import org.apache.commons.lang3.StringUtils;
import spark.Spark;
import spark.Request;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Arrays;
import java.util.List;

public class Routes {

  static int getHerokuAssignedPort() {
    ProcessBuilder processBuilder = new ProcessBuilder();
    if (processBuilder.environment().get("PORT") != null) {
      return Integer.parseInt(processBuilder.environment().get("PORT"));
    }

    return 8080; //return default port if heroku-port isn't set (i.e. on localhost)
  }

  public static void configure() {
    Spark.port(getHerokuAssignedPort());

    // Engine & Controllers
    HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();

    HomeController homeController = new HomeController();
    SessionController sessionController = new SessionController();
    RegistroMascotasController registroMascotasController = new RegistroMascotasController();
    MascotasEncontradasController mascotasEncontradasController = new MascotasEncontradasController();
    MascotasEncontradasConChapitaController mascotaEncontradaConChapitaController = new MascotasEncontradasConChapitaController();
    MascotasPerdidasController mascotasPerdidasController = new MascotasPerdidasController();
    MascotasEncontradasSinChapitaController mascotasEncontradasSinChapitaController = new MascotasEncontradasSinChapitaController();
    MascotasEnAdopcionController mascotasEnAdopcionController = new MascotasEnAdopcionController();
    UsersController usersController = new UsersController();
    CaracteristicasController caracteristicasController = new CaracteristicasController();

    // Routing
    Spark.get("/", homeController::getHome, engine);

    // Register
    Spark.post("/usuarios", sessionController::register, engine);

    // Session
    Spark.get("/login", sessionController::showLogin, engine);
    Spark.post("/login", sessionController::createSession);
    Spark.post("/logout", sessionController::logout);

    // Registro de mascota
    Spark.get("/mascotas/nueva", registroMascotasController::formNuevaMascota, engine);
    Spark.post("/mascotas", registroMascotasController::crearMascota);
    Spark.get("/mascotas", usersController::getMascotas, engine);

    // Encontre una mascota
    Spark.get("/mascotasEncontradas", mascotasEncontradasController::mascotaEncontrada, engine);
    Spark.get("/mascotasEncontradasSinChapita", mascotasEncontradasSinChapitaController::mascotaEncontradaSinChapita, engine);
    Spark.get("/mascotasEncontradasConChapita", mascotaEncontradaConChapitaController::mascotaEncontradaConChapita, engine);

    Spark.post("/mascotasEncontradasConChapita", mascotaEncontradaConChapitaController::crearPublicacionConChapita);
    Spark.post("/mascotasEncontradasSinChapita", mascotasEncontradasSinChapitaController::crearPublicacionSinChapita);

    // Mascotas Perdidas
    Spark.get("/mascotasPerdidas", mascotasPerdidasController::mascotasPerdidas, engine);

    // Mascotas en Adopcion
    Spark.get("/mascotasEnAdopcion", mascotasEnAdopcionController::mascotasEnAdopcion, engine);

    //Caracteristicas
    Spark.post("/caracteristicas", caracteristicasController::postCaracteristicas);
    Spark.get("/caracteristicas", usersController::mostrarAdministracionCaracteristicas, engine);
  }

  private static Boolean sesionSinIniciar(Request request) {
    // Agregar nombres de rutas en base a cuales requieran tener sesion iniciada para poder accederla
    List<String> routesWithLogin = Arrays.asList("/caracteristicas");
    String pathInfo = request.pathInfo();
    return request.session().attribute("user_id") == null &&
        routesWithLogin.stream().anyMatch(route -> StringUtils.containsAny(pathInfo, route)) &&
        !StringUtils.equals(pathInfo, "/login");
  }

}
