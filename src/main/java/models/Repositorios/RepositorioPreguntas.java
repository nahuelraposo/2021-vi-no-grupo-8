package models.Repositorios;

import models.Asociacion.Pregunta;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.List;

public class RepositorioPreguntas implements WithGlobalEntityManager {

  public void agregarPreguntaBase(Pregunta pregunta) {
    entityManager().persist(pregunta);
  }

  public List<Pregunta> getPreguntasBase() {
    return entityManager().createQuery("from Pregunta", Pregunta.class).getResultList();
  }
}
