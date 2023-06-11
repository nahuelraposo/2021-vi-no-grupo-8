package models.Asociacion.Publicacion;

import models.Excepciones.PersonaInvalidaException;
import models.Mascota.Chapita;
import models.Mascota.MascotaPerdida;
import models.Notificador.Notificador;
import models.Personas.Documento.Documento;
import models.Personas.Duenio;
import models.Personas.Persona;
import models.persistentEntity.PersistentEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "PublicacionesConChapita")
public class PublicacionConChapita extends PersistentEntity {
  @ManyToOne(cascade = CascadeType.ALL)
  Persona rescatista;
  String direccion;
  @ManyToOne(cascade = CascadeType.ALL)
  MascotaPerdida mascotaPerdida;
  LocalDate fechaEncuentro;
  @Transient
  List<Notificador> notificadoresRescatista;
  @OneToOne(cascade = CascadeType.ALL)
  Chapita chapitaMascotaPerdida;

  public PublicacionConChapita(Persona persona, String direccion, Chapita chapitaMascotaPerdida, MascotaPerdida mascotaPerdida, LocalDate fechaEncuentro,
                               List<Notificador> notificadoresRescatista
  ) {
    this.validarRescatista(direccion);
    this.rescatista = persona;
    this.direccion = direccion;
    this.mascotaPerdida = mascotaPerdida;
    this.fechaEncuentro = fechaEncuentro;
    this.notificadoresRescatista = notificadoresRescatista;
    this.chapitaMascotaPerdida = chapitaMascotaPerdida;
    this.notificarDuenio(chapitaMascotaPerdida.getDuenio());
  }

  public PublicacionConChapita() {

  }

  public void notificarDuenio(Duenio duenio) {
    duenio.notificar("Mascota encontrada", "¡Tu mascota fue encontrada!");
  }

  public Chapita getChapitaMascotaPerdida() {
    return chapitaMascotaPerdida;
  }

  public LocalDate getFechaEncuentro() {
    return fechaEncuentro;
  }

  public String getNombre() {
    return rescatista.getNombre();
  }

  public String getApellido() {
    return rescatista.getApellido();
  }

  public LocalDate getFechaNacimiento() {
    return rescatista.getNacimiento();
  }

  public Documento getDocumento() {
    return rescatista.getDocumento();
  }

  public String getDireccion() {
    return direccion;
  }

  private void validarRescatista(String direccion) {
    if (this.esStringInvalido(direccion)) {
      throw new PersonaInvalidaException("No se ingresaron los datos de la persona correctamente");
    }
  }

  private boolean esStringInvalido(String string) {
    return string == null || string.isEmpty();
  }

}
