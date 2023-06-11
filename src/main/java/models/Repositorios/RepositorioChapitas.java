package models.Repositorios;

import models.Mascota.Chapita;
import models.Personas.Duenio;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioChapitas implements WithGlobalEntityManager {
  private final static RepositorioChapitas INSTANCE = new RepositorioChapitas();

  public static RepositorioChapitas getInstance() {
    return INSTANCE;
  }

  public boolean chapitaRegistrada(Integer idChapita){
    return this.getChapitas()
        .stream()
        .anyMatch(chapita -> chapita.getId().equals(idChapita));
  }

  public Chapita obtenerChapita(Integer id){
    return this.getChapitas()
        .stream()
        .filter(chapita -> chapita.getId().equals(id))
        .collect(Collectors.toList())
        .get(0);
  }

  public List<Chapita> getChapitas() {
    return entityManager().createQuery("from Chapita", Chapita.class).getResultList();
  }
}
