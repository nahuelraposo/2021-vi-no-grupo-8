package models.Repositorios;

import models.Excepciones.CaracteristicaInvalidaException;
import models.Mascota.Caracteristica.CaracteristicaIdeal;
import models.Mascota.Mascota;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.List;
import java.util.stream.Collectors;

public class RepositorioCaracteristicas implements WithGlobalEntityManager {
  private final static RepositorioCaracteristicas INSTANCE = new RepositorioCaracteristicas();

  public static RepositorioCaracteristicas getInstance() {
    return INSTANCE;
  }

  public void agregarNuevaCaracteristicaIdeal(CaracteristicaIdeal caracteristicaIdeal) {
    if (estaEnLaListaDeCaracteristicas(caracteristicaIdeal.getDescripcion())) {
      throw new CaracteristicaInvalidaException("Ya existe este tipo");
    }
    entityManager().persist(caracteristicaIdeal);
  }

  public boolean estaEnLaListaDeCaracteristicas(String tipo) {
    List<CaracteristicaIdeal> plantillaCaraceristicasIdeales = entityManager().createQuery("from CaracteristicaIdeal", CaracteristicaIdeal.class).getResultList();
    return plantillaCaraceristicasIdeales
        .stream()
        .map(CaracteristicaIdeal::getDescripcion)
        .collect(Collectors.toList())
        .contains(tipo);
  }

  public List<CaracteristicaIdeal> getCaracteristicasIdeales() {
    return entityManager().createQuery("from CaracteristicaIdeal", CaracteristicaIdeal.class).getResultList();
  }
}

