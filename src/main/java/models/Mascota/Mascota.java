package models.Mascota;

import models.Excepciones.MascotaInvalidaException;
import models.Mascota.Caracteristica.caracteristicasSensibles.CaracteristicaSensible;
import models.Personas.Duenio;
import models.persistentEntity.PersistentEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Mascotas")
public class Mascota extends PersistentEntity {
  @Enumerated
  TipoMascota tipo;
  String nombre;
  String apodo;
  int edad;
  char sexo;
  @Enumerated
  TamanioMascota tamanioMascota;
  @OneToOne(cascade = CascadeType.ALL)
  Chapita chapita;
  String descripcionFisica;
  @ElementCollection
  List<String> fotos = new ArrayList<>();
  @ManyToMany(cascade = CascadeType.ALL)
  List<CaracteristicaSensible> caracteristicas;

  public Mascota(
      TipoMascota tipo,
      String nombre,
      String apodo,
      int edad,
      char sexo,
      TamanioMascota tamanioMascota,
      String descripcionFisica,
      List<String> fotos,
      List<CaracteristicaSensible> caracteristicas
  ) {
    this.chequearTodo(tipo, nombre, apodo, edad, sexo, tamanioMascota, descripcionFisica, fotos);
    this.tipo = tipo;
    this.nombre = nombre;
    this.apodo = apodo;
    this.edad = edad;
    this.sexo = sexo;
    this.tamanioMascota = tamanioMascota;
    this.descripcionFisica = descripcionFisica;
    this.fotos.addAll(fotos);
    this.caracteristicas = caracteristicas;
  }

  public Mascota() {
  }

  private void chequearFotos(List<String> fotos) {
    if (!this.tieneAlMenosUnaFoto(fotos)) {
      throw new MascotaInvalidaException("no se ingresó fotos válidas");
    }
  }

  private boolean tieneAlMenosUnaFoto(List<String> fotos) {
    return fotos.stream().anyMatch(Objects::nonNull);
  }

  private void chequearDescripcion(String descripcionFisica) {
    if (esStringInvalido(descripcionFisica)) {
      throw new MascotaInvalidaException("no se ingresó una descripción");
    }
  }

  private void chequearSexo(char sexo) {
    if ("MmfF".indexOf(sexo) < 0) {
      throw new MascotaInvalidaException("no se ingresó el sexo correctamente");
    }
  }

  private void chequearEdad(int edad) {
    if (edad < 0) {
      throw new MascotaInvalidaException("no se ingresó la edad correctamente");
    }
  }

  private void chequearTipo(TipoMascota tipo) {
    if (tipo == null) {
      throw new MascotaInvalidaException("no se ingresó tipo");
    }
  }

  private void chequearTamanio(TamanioMascota tamanio) {
    if (tamanio == null) {
      throw new MascotaInvalidaException("no se ingresó el tamaño");
    }
  }

  private void chequearNombreyApodo(String nombre, String apodo) {
    if (this.esStringInvalido(nombre) || this.esStringInvalido(apodo)) {
      throw new MascotaInvalidaException("no ingresó nombre o apodo");
    }
  }

  private void chequearTodo(
      TipoMascota tipo,
      String nombre,
      String apodo,
      int edad,
      char sexo,
      TamanioMascota tamanioMascota,
      String descripcionFisica,
      List<String> fotos
  ) {
    this.chequearTipo(tipo);
    this.chequearNombreyApodo(nombre, apodo);
    this.chequearEdad(edad);
    this.chequearSexo(sexo);
    this.chequearDescripcion(descripcionFisica);
    this.chequearFotos(fotos);
    this.chequearTamanio(tamanioMascota);
  }

  private boolean esStringInvalido(String string) {
    return string.isEmpty();
  }

  public int getEdad() {
    return edad;
  }

  public Chapita getChapita() {
    return chapita;
  }

  public void setChapita(Duenio duenio) {
    this.chapita = new Chapita(duenio.getCantidadDeMascotas(), duenio);
  }

  public TipoMascota getTipo() {
    return tipo;
  }

  public String getNombre() {
    return nombre;
  }

  public String getApodo() {
    return apodo;
  }

  public char getSexo() {
    return sexo;
  }

  public String getDescripcionFisica() {
    return descripcionFisica;
  }

  public List<CaracteristicaSensible> getCaracteristicas() {
    return caracteristicas;
  }

  public TamanioMascota getTamanioMascota() {
    return tamanioMascota;
  }

  public List<String> getFotos() {
    return fotos;
  }
}
