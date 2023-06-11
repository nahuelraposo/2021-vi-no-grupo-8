package models.Mascota.Caracteristica.caracteristicasSensibles;

import models.Mascota.Caracteristica.CaracteristicaIdeal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("N")
public class CaracteristicaSensibleNumerica extends CaracteristicaSensible {
  @Column(name = "valorNumerico")
  Integer valor;

  public CaracteristicaSensibleNumerica(CaracteristicaIdeal caracteristicaIdeal, Integer valor) {
    super(caracteristicaIdeal);
    this.valor = valor;
  }

  public CaracteristicaSensibleNumerica() {

  }
}
