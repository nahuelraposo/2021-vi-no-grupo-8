package models.Mascota.Caracteristica.caracteristicasSensibles;

import models.Mascota.Caracteristica.CaracteristicaIdeal;
import models.persistentEntity.PersistentEntity;

import javax.persistence.*;

@Entity
@Table(name = "CaracteristicasSensibles")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public abstract class CaracteristicaSensible extends PersistentEntity {
  @ManyToOne
  CaracteristicaIdeal tipoCaracteristica;

  public CaracteristicaSensible(CaracteristicaIdeal caracteristicaIdeal) {
    this.tipoCaracteristica = caracteristicaIdeal;
  }

  public CaracteristicaSensible() {
  }

  public String getTipoCaracteristica() {
    return tipoCaracteristica.getDescripcion();
  }

}

