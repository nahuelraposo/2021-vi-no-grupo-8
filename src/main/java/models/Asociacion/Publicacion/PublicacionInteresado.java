package models.Asociacion.Publicacion;

import models.Notificador.*;
import models.Personas.Contacto;
import models.Asociacion.PreguntaRespondida;
import models.persistentEntity.PersistentEntity;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "PublicacionesInteresados")
public class PublicacionInteresado extends PersistentEntity {
  @ManyToOne
  Preferencia preferencias;
  @OneToMany
  @JoinColumn(name = "publicaciones_interesado_id")
  List<PreguntaRespondida> comodidades;
  String email;
  @Embedded
  Contacto contacto;
  @Transient
  MailSender mailSender;

  public PublicacionInteresado(Preferencia preferencias, String email, Contacto contacto, List<PreguntaRespondida> comodidades, MailSender mailSender) {
    this.preferencias = preferencias;
    this.email = email;
    this.contacto = contacto;
    this.comodidades = comodidades;
    this.mailSender = mailSender;
    this.enviarEmailDesuscripcion();
  }

  public PublicacionInteresado() {

  }

  public void recomendarAlInteresado(List<PublicacionMascotaEnAdopcion> publicacionesMascotasEnAdopcion) {
    if (!publicacionesMascotasEnAdopcion.isEmpty()) {
      mailSender.enviarMail(
          this.getEmail(),
          "¡Revisa las nuevas recomendaciones semanales que tenemos para ti!",
          this.getStringDeListaPublicaciones(publicacionesMascotasEnAdopcion)
      );
    }
  }

  private String getStringDeListaPublicaciones(List<PublicacionMascotaEnAdopcion> publicaciones) {
    return publicaciones
        .stream()
        .map(PublicacionMascotaEnAdopcion::stringPublicacion)
        .collect(Collectors.joining("\n"));
  }

  public List<PublicacionMascotaEnAdopcion> darPublicacionesQueLeGustanAlInteresado(
      List<PublicacionMascotaEnAdopcion> publicacionesMascotaEnAdopcion
  ) {
    return publicacionesMascotaEnAdopcion
        .stream()
        .filter(publi -> this.preferenciasMatcheanConPubliMascotaEnAdopcion(publi) && this.comodidadesMatcheanConNecesidades(publi))
        .collect(Collectors.toList());
  }

  public boolean preferenciasMatcheanConPubliMascotaEnAdopcion(PublicacionMascotaEnAdopcion publicacionMascotaEnAdopcion) {
    //la preferencia puede no tener todas las caract de la mascota, cambiar
    return this.preferencias.matcheaLaPubliMascotaAdopcion(publicacionMascotaEnAdopcion);
  }

  public boolean comodidadesMatcheanConNecesidades(PublicacionMascotaEnAdopcion publicacionMascotaEnAdopcion) {
    return this.comodidades.containsAll(publicacionMascotaEnAdopcion.getNecesidadesMascota());
  }

  private void enviarEmailDesuscripcion() {
    mailSender.enviarMail(
        email,
        "Link para desuscripción de la publicacion",
        "https://github.com/dds-utn/2021-vi-no-grupo-8"
    );
  }

  public Preferencia getPreferencias() {
    return preferencias;
  }

  public List<PreguntaRespondida> getComodidades() {
    return comodidades;
  }

  public String getEmail() {
    return email;
  }

  public Contacto getContacto() {
    return contacto;
  }
}
