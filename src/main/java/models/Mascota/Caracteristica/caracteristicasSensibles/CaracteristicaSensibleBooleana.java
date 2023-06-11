package models.Mascota.Caracteristica.caracteristicasSensibles;

import models.Mascota.Caracteristica.CaracteristicaIdeal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
public class CaracteristicaSensibleBooleana extends CaracteristicaSensible {
  @Column(name = "valorBooleano")
  Boolean valor;

  public CaracteristicaSensibleBooleana(CaracteristicaIdeal caracteristicaIdeal, Boolean valor) {
    super(caracteristicaIdeal);
    this.valor = valor;
  }

  public CaracteristicaSensibleBooleana() {

  }
}
