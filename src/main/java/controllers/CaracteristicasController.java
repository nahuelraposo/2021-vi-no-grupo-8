package controllers;

import models.Mascota.Caracteristica.*;
import models.Repositorios.RepositorioCaracteristicas;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.*;
import java.util.stream.Collectors;

public class CaracteristicasController implements WithGlobalEntityManager, TransactionalOps {

  public Void postCaracteristicas(Request request, Response response) {
    String descripcion = request.queryParams("descripcion");
    String tipo = request.queryParams("tipo");
    String valoresPosibles = request.queryParams("valoresPosibles");
    String[] parts = valoresPosibles.split(",");

    withTransaction(() -> {
      switch (tipo) {
        case "BOOLEANA":
          CaracteristicaIdealBooleana booleana = new CaracteristicaIdealBooleana(descripcion, Arrays.asList(true, false));
          RepositorioCaracteristicas.getInstance().agregarNuevaCaracteristicaIdeal(booleana);
          break;
        case "TEXTOLIBRE":
          CaracteristicaIdealTextoLibre textoLibre = new CaracteristicaIdealTextoLibre(descripcion);
          RepositorioCaracteristicas.getInstance().agregarNuevaCaracteristicaIdeal(textoLibre);
          break;
        case "STRING":
          CaracteristicaIdealStrings strings = new CaracteristicaIdealStrings(descripcion, Arrays.asList(parts));
          RepositorioCaracteristicas.getInstance().agregarNuevaCaracteristicaIdeal(strings);
          break;
        case "NUMERICA":
          CaracteristicaIdealNumerica numerica = new CaracteristicaIdealNumerica(descripcion, Arrays.stream(parts).map(Integer::new).collect(Collectors.toList()));
          RepositorioCaracteristicas.getInstance().agregarNuevaCaracteristicaIdeal(numerica);
          break;
        default:
          System.out.println("Se rompio");
          break;
      }
    });
    response.redirect("/caracteristicas");
    return null;
  }
}
