package models.Repositorios;

import models.Asociacion.Publicacion.PublicacionSinChapita;
import models.Mascota.MascotaPerdida;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.List;

public class RepositorioPublicacionesSinChapita implements WithGlobalEntityManager {
  private final static RepositorioPublicacionesSinChapita INSTANCE = new RepositorioPublicacionesSinChapita();
  public static RepositorioPublicacionesSinChapita getInstance() {
    return INSTANCE;
  }

  public List<PublicacionSinChapita> listarPublicacionesSinChapita() {
    return entityManager().createQuery("from PublicacionSinChapita", PublicacionSinChapita.class).getResultList();
  }

  public void agregar(PublicacionSinChapita publi) {
    entityManager().persist(publi);
  }
}