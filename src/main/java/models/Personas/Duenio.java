package models.Personas;

import models.Mascota.Mascota;
import models.Notificador.Notificador;
import models.Personas.Documento.Documento;
import models.Usuario.Usuario;
import models.persistentEntity.PersistentEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Duenios")
public class Duenio extends PersistentEntity {
  @OneToMany(cascade = CascadeType.ALL)

  @JoinColumn(name = "duenio_id")
  List<Mascota> mascotas = new ArrayList<>();
  @OneToOne(cascade = CascadeType.ALL)
  Persona persona;
  @OneToOne(cascade = CascadeType.ALL)
  Usuario datosDeLogin;
  @ManyToMany(cascade = CascadeType.ALL)
  List<Notificador> notificadores;

  public Duenio(
      Persona persona,
      Usuario usuario,
      List<Notificador> notificadores
  ) {
    this.persona = persona;
    this.datosDeLogin = usuario;
    this.notificadores = notificadores;
  }

  public Duenio() {
  }

  public void agregarMascota(Mascota mascota) {
    this.mascotas.add(mascota);
    mascota.setChapita(this);
  }

  public void notificar(String asunto, String mensaje) {
    notificadores.forEach(notificador -> notificador.notificar(this.getContactos(), asunto, mensaje));
  }

  public boolean poseeLaMascota(Mascota mascota) {
    return this.mascotas.contains(mascota);
  }

  public Integer getCantidadDeMascotas() {
    return this.mascotas.size();
  }

  public List<Mascota> getMascotas() {
    return mascotas;
  }

  public List<Contacto> getContactos() {
    return persona.getContactos();
  }

  public String getNombre() {
    return persona.getNombre();
  }

  public String getApellido() {
    return persona.getApellido();
  }

  public LocalDate getNacimiento() {
    return persona.getNacimiento();
  }

  public Documento getDocumento() {
    return persona.getDocumento();
  }

  public Usuario getDatosDeLogin() {
    return datosDeLogin;
  }

  public List<Notificador> getNotificadores() {
    return notificadores;
  }

  public void agregarNotificador(Notificador notificador) {
    this.notificadores.add(notificador);
  }
}
