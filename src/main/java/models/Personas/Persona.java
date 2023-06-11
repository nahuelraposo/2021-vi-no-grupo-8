package models.Personas;

import models.Excepciones.PersonaInvalidaException;
import models.Personas.Documento.Documento;
import models.persistentEntity.PersistentEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Personas")
public class Persona extends PersistentEntity {
  String nombre;
  String apellido;
  LocalDate nacimiento;
  @Embedded
  Documento documento;
  @ElementCollection
  List<Contacto> contactos;

  public Persona(String nombre, String apellido, Documento documento, LocalDate nacimiento, List<Contacto> contactos) {
    this.chequearValidez(nombre, apellido, nacimiento, documento, contactos);
    this.nombre = nombre;
    this.apellido = apellido;
    this.nacimiento = nacimiento;
    this.documento = documento;
    this.contactos = contactos;
  }

  public Persona() {

  }

  private void chequearValidez(String nombre, String apellido, LocalDate nacimiento, Documento documento, List<Contacto> contactos) {
    if (existeAlgunStringInvalido(Arrays.asList(nombre, apellido)) || noTieneContactos(contactos)) {
      throw new PersonaInvalidaException("No se ingresaron los datos de la persona correctamente");
    }
  }

  private boolean existeAlgunStringInvalido(List<String> listaDeStrings) {
    return listaDeStrings.stream().anyMatch(this::esStringInvalido);
  }

  private boolean noTieneContactos(List<Contacto> contactos) {
    return contactos.isEmpty() || contactos.stream().anyMatch(Objects::isNull);
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

  public LocalDate getNacimiento() {
    return nacimiento;
  }

  public Documento getDocumento() {
    return documento;
  }

  public List<Contacto> getContactos() {
    return contactos;
  }
}
