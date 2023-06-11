package models.Mascota.Caracteristica.caracteristicasSensibles;

import models.Mascota.Caracteristica.CaracteristicaIdeal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("S")
public class CaracteristicaSensibleString extends CaracteristicaSensible {
  @Column(name = "valorString")
  String valor;

  public CaracteristicaSensibleString(CaracteristicaIdeal caracteristicaIdeal, String valor) {
    super(caracteristicaIdeal);
    this.valor = valor;
  }

  public CaracteristicaSensibleString() {

  }
}
