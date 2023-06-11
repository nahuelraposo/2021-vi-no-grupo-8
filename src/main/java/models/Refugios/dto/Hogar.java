package models.Refugios.dto;

import java.util.List;

public class Hogar {
  String id;
  String nombre;
  UbicacionDto ubicacionDto;
  String telefono;
  Admision admisiones;
  Integer capacidad;
  Integer lugares_disponibles;
  boolean patio;
  List<String> caracteristicas;

  public boolean admitePerros() {
    return admisiones.admitePerros();
  }

  public boolean admiteGatos() {
    return admisiones.admiteGatos();
  }

  public boolean tieneLugar() {
    return lugares_disponibles > 0;
  }

  public boolean tienePatio() {
    return isPatio();
  }

  public boolean tieneAdmisionEspecifica() {
    return caracteristicas.size()>0;
  }

  public String getId() {
    return id;
  }

  public String getNombre() {
    return nombre;
  }

  public UbicacionDto getUbicacion() {
    return ubicacionDto;
  }

  public String getTelefono() {
    return telefono;
  }

  public Admision getAdmisiones() {
    return admisiones;
  }

  public Integer getCapacidad() {
    return capacidad;
  }

  public List<String> getCaracteristicas() {
    return caracteristicas;
  }

  public Integer getLugares_disponibles() {
    return lugares_disponibles;
  }

  public boolean isPatio() {
    return patio;
  }

  //Tests
  public Hogar(String id, String nombre, UbicacionDto ubicacionDto, String telefono, Admision admisiones, Integer capacidad, Integer lugares_disponibles, boolean patio, List<String> caracteristicas) {
    this.id = id;
    this.nombre = nombre;
    this.ubicacionDto = ubicacionDto;
    this.telefono = telefono;
    this.admisiones = admisiones;
    this.capacidad = capacidad;
    this.lugares_disponibles = lugares_disponibles;
    this.patio = patio;
    this.caracteristicas = caracteristicas;
  }
}
