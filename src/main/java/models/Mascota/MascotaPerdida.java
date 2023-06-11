package models.Mascota;

import models.Excepciones.MascotaInvalidaException;
import models.Refugios.Ubicacion;
import models.persistentEntity.PersistentEntity;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "MascotasPerdidas")
public class MascotaPerdida extends PersistentEntity {
  @ElementCollection
  List<String> fotos;
  String descripcionEstado;
  @Embedded
  Ubicacion lugarEncuentro;
  @Enumerated
  TipoMascota tipo;
  @Enumerated
  TamanioMascota tamanio;
  @ElementCollection
  List<String> caracteristicas;

  public MascotaPerdida(List<String> fotos, String descripcionEstado, Ubicacion lugarEncuentro, TipoMascota tipo, TamanioMascota tamanio, List<String> caracteristicas) {
    validarDatos(fotos, descripcionEstado);
    this.fotos = fotos;
    this.descripcionEstado = descripcionEstado;
    this.lugarEncuentro = lugarEncuentro;
    this.tipo = tipo;
    this.tamanio = tamanio;
    this.caracteristicas = caracteristicas;
  }

  public MascotaPerdida() {
  }

  public List<String> getFotos() {
    return fotos;
  }

  public String getDescripcionEstado() {
    return descripcionEstado;
  }

  public Ubicacion getLugarEncuentro() {
    return lugarEncuentro;
  }

  public TipoMascota getTipo() {
    return tipo;
  }

  public TamanioMascota getTamanio() {
    return tamanio;
  }

  public List<String> getCaracteristicas() {
    return caracteristicas;
  }

  private void validarDatos(List<String> fotos, String descripcionEstado) {
    if (this.existeAlgunStringInvalido(Collections.singletonList(descripcionEstado))) {
      throw new MascotaInvalidaException(
          "No ingresó los datos de la mascota encontrada correctamente");
    }
    this.chequearFotos(fotos);
  }

  private boolean existeAlgunStringInvalido(List<String> listaDeStrings) {
    return listaDeStrings.stream().anyMatch(this::esStringInvalido);
  }

  private boolean esStringInvalido(String string) {
    return string == null || string.isEmpty();
  }

  private void chequearFotos(List<String> fotos) {
    if (fotos.isEmpty() || fotos.stream().anyMatch(foto -> foto == null || foto.isEmpty())) {
      throw new MascotaInvalidaException(
          "Falta por lo menos una foto de la mascota para la publicacion");
    }
  }
}
