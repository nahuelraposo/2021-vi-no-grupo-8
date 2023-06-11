package models.Mascota.Caracteristica;

import models.Mascota.Caracteristica.caracteristicasSensibles.CaracteristicaSensible;
import models.Mascota.Caracteristica.caracteristicasSensibles.CaracteristicaSensibleNumerica;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue("N")
public class CaracteristicaIdealNumerica extends CaracteristicaIdeal {
  @ElementCollection
  @Column(name = "valoresPosiblesNumericos")
  List<Integer> valoresPosibles;

  public CaracteristicaIdealNumerica(String descripcion, List<Integer> valores) {
    this.descripcion = descripcion;
    this.valoresPosibles = valores;
  }

  public CaracteristicaIdealNumerica() {

  }

  public List<Integer> getValoresPosibles() {
    return valoresPosibles;
  }

  public CaracteristicaSensible crearCaracteristicaSensible(Integer num) {
    return new CaracteristicaSensibleNumerica(this, num);
  }

}
