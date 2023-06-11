package models.Repositorios;

import models.Asociacion.Asociacion;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import models.Asociacion.Publicacion.PublicacionMascotaEnAdopcion;

import java.util.List;

public class RepositorioAsociaciones implements WithGlobalEntityManager {

  public void enviarRecomendacionesAInteresados(List<PublicacionMascotaEnAdopcion> publicacionesMascotasEnAdopcion) {
    List<Asociacion> asociaciones = entityManager().createQuery("from models.Asociacion.Asociacion.Asociacion.Asociacion", Asociacion.class).getResultList();
    asociaciones.forEach(asociacion -> asociacion.enviarRecomendacionesAInteresados(publicacionesMascotasEnAdopcion));
  }

  public void agregarAsociacion(Asociacion asociacion) {
    entityManager().persist(asociacion);
  }

}
