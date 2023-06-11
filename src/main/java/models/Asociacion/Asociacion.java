package models.Asociacion;

import models.Asociacion.Publicacion.PublicacionInteresado;
import models.Asociacion.Publicacion.PublicacionMascotaEnAdopcion;
import models.Asociacion.Publicacion.PublicacionSinChapita;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import models.Repositorios.RepositorioPreguntas;
import models.persistentEntity.PersistentEntity;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "Asociaciones")
public class Asociacion extends PersistentEntity {
  @OneToMany
  @JoinColumn(name = "asociacion_id")
  private List<PublicacionSinChapita> publicacionesSinChapita = new ArrayList<>();
  @OneToMany
  @JoinColumn(name = "asociacion_id")
  private List<PublicacionMascotaEnAdopcion> publicacionesMascotasEnAdopcion = new ArrayList<>();
  @OneToMany
  @JoinColumn(name = "asociacion_id")
  private List<PublicacionInteresado> publicacionesDeInteresados = new ArrayList<>();
  @ManyToMany
  private List<Pregunta> preguntasConRespuestasPosibles = new ArrayList<>();
  @Transient
  private RepositorioPreguntas repositorioPreguntas;

  public Asociacion(RepositorioPreguntas repo) {
    this.repositorioPreguntas = repo;
  }

  public Asociacion() {

  }

  public void agregarPublicacion(PublicacionSinChapita publicacionRescate) {
    this.publicacionesSinChapita.add(publicacionRescate);
  }

  public List<PublicacionSinChapita> listarMascotasEncontradasLosUltimos10Dias() {
    return publicacionesSinChapita
        .stream()
        .filter(publicacionRescate -> this.diasEntreFechaYHoy(publicacionRescate.getFechaEncuentro()) <= 10)
        .collect(Collectors.toList());
  }

  public void enviarRecomendacionesAInteresados(List<PublicacionMascotaEnAdopcion> publicacionesMascotasEnAdopcion) {
    this.getPublicacionesDeInteresados()
        .forEach(publi -> publi.recomendarAlInteresado
            (this.publicacionesQueLeGustanAlInteresado(publicacionesMascotasEnAdopcion, publi)));
  }

  public List<PublicacionMascotaEnAdopcion> publicacionesQueLeGustanAlInteresado(
      List<PublicacionMascotaEnAdopcion> publicacionesMascotaEnAdopcion,
      PublicacionInteresado publicacionInteresado
  ) {
    return publicacionInteresado.darPublicacionesQueLeGustanAlInteresado(publicacionesMascotaEnAdopcion);
  }

  public void cargarNuevaAdopcion(PublicacionMascotaEnAdopcion publicacionMascotaEnAdopcion) {
    publicacionesMascotasEnAdopcion.add(publicacionMascotaEnAdopcion);
  }

  public void agregarInteresado(PublicacionInteresado publicacionInteresado) {
    publicacionesDeInteresados.add(publicacionInteresado);
  }

  public void agregarPregunta(Pregunta preguntaRespuestasPosibles) {
    this.preguntasConRespuestasPosibles.add(preguntaRespuestasPosibles);
  }

  public List<PublicacionSinChapita> getPublicacionesRescate() {
    return publicacionesSinChapita;
  }

  public List<Pregunta> getPreguntasConRespuestasPosibles() {
    return Stream
        .concat(this.preguntasConRespuestasPosibles.stream(), this.repositorioPreguntas.getPreguntasBase().stream())
        .collect(Collectors.toList());
  }

  public List<Pregunta> getPreguntasBase() {
    return this.repositorioPreguntas.getPreguntasBase();
  }

  private long diasEntreFechaYHoy(LocalDate fecha) {
    return ChronoUnit.DAYS.between(fecha, LocalDate.now());
  }

  public List<PublicacionMascotaEnAdopcion> getPublicacionesMascotasEnAdopcion() {
    return publicacionesMascotasEnAdopcion;
  }

  public List<PublicacionInteresado> getPublicacionesDeInteresados() {
    return publicacionesDeInteresados;
  }

  public void darDeBajaPublicacion(PublicacionInteresado publicacion) {
    publicacionesDeInteresados.remove(publicacion);
  }

}
