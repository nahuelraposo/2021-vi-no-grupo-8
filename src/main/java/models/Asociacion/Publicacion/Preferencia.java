package models.Asociacion.Publicacion;

import models.Mascota.Caracteristica.caracteristicasSensibles.CaracteristicaSensible;
import models.Mascota.Mascota;
import models.Mascota.TamanioMascota;
import models.Mascota.TipoMascota;
import models.persistentEntity.PersistentEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Preferencias")
public class Preferencia extends PersistentEntity {
  @Enumerated
  TipoMascota tipoMascota;
  @Enumerated
  TamanioMascota tamanio;
  char sexo;
  int edad;
  @ManyToMany
  List<CaracteristicaSensible> caracteristicas;

  public Preferencia(TipoMascota tipo, TamanioMascota tamanio, char sexo, List<CaracteristicaSensible> caracteristicas) {
    this.tipoMascota = tipo;
    this.caracteristicas = caracteristicas;
    this.edad = 123;  // puede ser una preferencia de edad tambien
    this.tamanio = tamanio;
    this.sexo = sexo;
  }

  public Preferencia() {
  }

  public TipoMascota getTipoMascota() {
    return tipoMascota;
  }

  public TamanioMascota getTamanio() {
    return tamanio;
  }

  public char getSexo() {
    return sexo;
  }

  public int getEdad() {
    return edad;
  }

  public boolean matcheaLaPubliMascotaAdopcion(PublicacionMascotaEnAdopcion publicacion) {
    Mascota mascotaPublicacion = publicacion.getMascota();

    return mascotaPublicacion.getTipo() == this.getTipoMascota()
        && mascotaPublicacion.getSexo() == this.getSexo()
        && mascotaPublicacion.getTamanioMascota() == this.getTamanio();
  }
}
