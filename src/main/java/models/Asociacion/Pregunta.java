package models.Asociacion;

import models.persistentEntity.PersistentEntity;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Preguntas")
public class Pregunta<T> extends PersistentEntity {
  String preguntaDador;
  String preguntaAdoptante;
  @ElementCollection
  List<String> respuestasPosibles = new ArrayList<>();

  public Pregunta(String preguntaDador, String preguntaAdoptante, List<T> respuestasPosibles) {
    this.preguntaDador = preguntaDador;
    this.preguntaAdoptante = preguntaAdoptante;
    this.deListaGenericaAListaString(respuestasPosibles);
  }

  public Pregunta() {
  }

  private void deListaGenericaAListaString(List<T> respuestasPosibles) {
    respuestasPosibles.forEach(this::agregarRespuesta);
  }

  private void agregarRespuesta(T respuesta) {
    this.respuestasPosibles.add(respuesta.toString());
  }

  public PreguntaRespondida responder(T respuesta) {
    this.validarRespuesta(respuesta);
    return new PreguntaRespondida(preguntaDador, preguntaAdoptante, respuesta.toString());
  }

  private void validarRespuesta(T respuesta) {
    if (!esRespuestaValida(respuesta)) {
      throw new RuntimeException("La respuesta ingresada no se encuentra dentro de las respuestas posibles.");
    }
  }

  private boolean esRespuestaValida(T respuesta) {
    return this.respuestasPosibles.stream().anyMatch(rta -> rta.equals(respuesta.toString()));
  }

  public String getPreguntaDador() {
    return preguntaDador;
  }

  public List<String> getRespuestasPosibles() {
    return respuestasPosibles;
  }

  public String getPreguntaAdoptante() {
    return preguntaAdoptante;
  }
}
