package models.Asociacion.Publicacion;

import models.Asociacion.PreguntaRespondida;
import models.Mascota.Mascota;
import models.Personas.*;
import models.Personas.Duenio;
import models.persistentEntity.PersistentEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PublicacionesMascotasEnAdopcion")
public class PublicacionMascotaEnAdopcion extends PersistentEntity {
  @ManyToOne
  Duenio duenio;
  @OneToOne
  Mascota mascota;
  @OneToMany
  @JoinColumn(name = "publicaciones_mascotas_en_adopcion_id")
  List<PreguntaRespondida> necesidadesMascota;

  public PublicacionMascotaEnAdopcion(Duenio duenio, Mascota mascota, List<PreguntaRespondida> necesidades) {
    this.duenio = duenio;
    this.mascota = mascota;
    this.necesidadesMascota = necesidades;
  }

  public PublicacionMascotaEnAdopcion() {
  }

  public void contactarseConDuenio(Contacto contacto) {
    duenio.notificar(
        "Interesado en adopcion de mascota",
        "¡Una persona está interesada en adoptar a tu mascota!"
            + "\nLos datos del interesado son: "
            + "\nNombre: " + contacto.getNombre()
            + "\nApellido: " + contacto.getApellido()
            + "\nTeléfono: " + contacto.getTelefono()
            + "\nEmail: " + contacto.getEmail());
  }

  public Mascota getMascota() {
    return mascota;
  }

  public Duenio getDuenio() {
    return duenio;
  }

  public List<PreguntaRespondida> getNecesidadesMascota() {
    return necesidadesMascota;
  }

  public String stringPublicacion() {
    return "Informacion de la publicacion de mascota en adopcion:"
        + "\nNombre del duenio actual de la mascota: " + duenio.getNombre() + " " + duenio.getApellido()
        + "\nNombre de la mascota: " + mascota.getNombre()
        + "\nTamaño de la mascota: " + mascota.getTamanioMascota()
        + "\nDescripcion de la mascota: " + mascota.getDescripcionFisica()
        + "\nSexo de mascota: " + mascota.getSexo()
        + "\nEdad de mascota(años): " + mascota.getEdad();
  }

}
