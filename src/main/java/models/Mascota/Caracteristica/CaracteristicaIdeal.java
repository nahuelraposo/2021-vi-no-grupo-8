package models.Mascota.Caracteristica;

import models.Mascota.Caracteristica.caracteristicasSensibles.CaracteristicaSensible;
import models.persistentEntity.PersistentEntity;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public abstract class CaracteristicaIdeal extends PersistentEntity {
  String descripcion;

  public String getDescripcion() {
    return descripcion;
  }

  public CaracteristicaSensible crearCaracteristicaSensible(String valorPosible) {
    return null;
  }


}

