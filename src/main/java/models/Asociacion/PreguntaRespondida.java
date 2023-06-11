package models.Asociacion;

import models.persistentEntity.PersistentEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PreguntasRespondidas")
public class PreguntaRespondida extends PersistentEntity {
  String preguntaDador;
  String preguntaAdoptante;
  String respuesta;

  public PreguntaRespondida(String preguntaDador, String preguntaAdoptante, String respuesta) {
    this.preguntaDador = preguntaDador;
    this.preguntaAdoptante = preguntaAdoptante;
    this.respuesta = respuesta;
  }

  public PreguntaRespondida() {

  }

  public String getRespuesta() {
    return respuesta;
  }

  public String getPreguntaDador() {
    return preguntaDador;
  }

  public String getPreguntaAdoptante() {
    return preguntaAdoptante;
  }


}
