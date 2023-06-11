package models.Mascota.Caracteristica;

import models.Mascota.Caracteristica.caracteristicasSensibles.CaracteristicaSensible;
import models.Mascota.Caracteristica.caracteristicasSensibles.CaracteristicaSensibleString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("T")
public class CaracteristicaIdealTextoLibre extends CaracteristicaIdeal {

  public CaracteristicaIdealTextoLibre(String descripcion) {
    this.descripcion = descripcion;
  }

  public CaracteristicaIdealTextoLibre() {
  }

  public CaracteristicaSensible crearCaracteristicaSensible(String textoAsociado) {
    return new CaracteristicaSensibleString(this, textoAsociado);
  }
}
