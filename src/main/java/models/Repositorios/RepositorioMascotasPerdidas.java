package models.Repositorios;

import models.Mascota.MascotaPerdida;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.List;

public class RepositorioMascotasPerdidas implements WithGlobalEntityManager {
  private final static RepositorioMascotasPerdidas INSTANCE = new RepositorioMascotasPerdidas();
  public static RepositorioMascotasPerdidas getInstance() {
    return INSTANCE;
  }

  public List<MascotaPerdida> listarMascotasPerdidas() {
    return entityManager().createQuery("from MascotaPerdida", MascotaPerdida.class).getResultList();
  }

  public void agregar(MascotaPerdida nuevaMascota) {
    entityManager().persist(nuevaMascota);
  }
}