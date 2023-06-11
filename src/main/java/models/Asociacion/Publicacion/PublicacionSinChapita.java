package models.Asociacion.Publicacion;

import models.Excepciones.PersonaInvalidaException;
import models.Mascota.MascotaPerdida;
import models.Notificador.Notificador;
import models.Personas.Documento.Documento;
import models.Personas.Persona;
import models.Refugios.BuscadorRefugios;
import models.Refugios.dto.Hogar;
import models.persistentEntity.PersistentEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "PublicacionesSinChapita")
public class PublicacionSinChapita extends PersistentEntity {
  @ManyToOne
  Persona rescatista;
  String direccion;
  @OneToOne
  MascotaPerdida mascotaPerdida;
  LocalDate fechaEncuentro;
  @Transient
  List<Notificador> notificadoresRescatista;
  Boolean aprobada;
  @Transient
  BuscadorRefugios buscadorRefugios;

  public PublicacionSinChapita(
      Persona rescatista, String direccion, MascotaPerdida mascotaPerdida, LocalDate fechaEncuentro,
      List<Notificador> notificadoresRescatista, BuscadorRefugios buscadorRefugios
  ) {
    this.validarRescatista(direccion);
    this.rescatista = rescatista;
    this.direccion = direccion;
    this.mascotaPerdida = mascotaPerdida;
    this.fechaEncuentro = fechaEncuentro;
    this.notificadoresRescatista = notificadoresRescatista;
    this.buscadorRefugios = buscadorRefugios;
  }

  public PublicacionSinChapita() {

  }

  public void contactarRescatista(
      Persona duenio,
      String direccion
  ) {
    notificadoresRescatista.forEach(
        notificador -> notificador.notificar(
            this.rescatista.getContactos(),
            "Informacion del dueño de la publicacion sin chapita",
            "¡El dueño de la mascota que rescataste quiere contactarse contigo! "
                + "Los datos del dueño son: "
                + "\nNombre: " + duenio.getNombre()
                + "\nApellido: " + duenio.getApellido()
                + "\nFecha de nacimiento: " + duenio.getNacimiento()
                + "\nDocumento: " + duenio.getDocumento().getTipo() + duenio.getDocumento().getNumero()
                + "\nDirección: " + direccion
                + "\nLista de contactos: " + duenio.getContactos()));
  }

  public void aceptar() {
    this.aprobada = true;
  }

  public void rechazar() {
    this.aprobada = false;
  }

  public Boolean estaAprobada() {
    return aprobada;
  }

  public List<Hogar> obtenerHogaresAdecuados(Double radio) {
    return buscadorRefugios.buscarHogaresRecomendadosPara(mascotaPerdida, radio);
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
