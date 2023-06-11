package models.Personas;

import models.Excepciones.PersonaInvalidaException;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Arrays;
import java.util.List;

@Embeddable
public class Contacto {
  @Column(name = "nombreContacto")
  String nombre;
  @Column(name = "apellidoContacto")
  String apellido;
  @Column(name = "telefonoContacto")
  Integer telefono;
  @Column(name = "emailContacto")
  String email;

  public Contacto(String nombre, String apellido, Integer telefono, String email) {
    this.chequearValidez(nombre, apellido, telefono, email);
    this.nombre = nombre;
    this.apellido = apellido;
    this.telefono = telefono;
    this.email = email;
  }

  public Contacto() {

  }

  private void chequearValidez(String nombre, String apellido, Integer telefono, String email) {
    if (this.existeAlgunStringInvalido(Arrays.asList(nombre, apellido, email)) || !this.esUnTelefonoValido(telefono)) {
      throw new PersonaInvalidaException("Dato de contacto incompleto");
    }
  }

  private boolean esUnTelefonoValido(Integer telefono) {
    return telefono > 0;
  }

  private boolean existeAlgunStringInvalido(List<String> listaDeStrings) {
    return listaDeStrings.stream().anyMatch(this::esStringInvalido);
  }

  private boolean esStringInvalido(String string) {
    return string == null || string.isEmpty();
  }

  public String getNombre() {
    return nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public int getTelefono() {
    return telefono;
  }

  public String getEmail() {
    return email;
  }

}
