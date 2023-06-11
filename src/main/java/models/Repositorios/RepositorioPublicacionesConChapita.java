package models.Repositorios;

import models.Asociacion.Publicacion.PublicacionConChapita;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

public class RepositorioPublicacionesConChapita implements WithGlobalEntityManager, TransactionalOps {
  private final static RepositorioPublicacionesConChapita INSTANCE = new RepositorioPublicacionesConChapita();
  public static RepositorioPublicacionesConChapita getInstance() {
    return INSTANCE;
  }

  public void agregar(PublicacionConChapita publicacionConChapita) {
    entityManager().persist(publicacionConChapita);
  }
}
