package models.Mascota.Caracteristica;

import models.Mascota.Caracteristica.caracteristicasSensibles.CaracteristicaSensible;
import models.Mascota.Caracteristica.caracteristicasSensibles.CaracteristicaSensibleBooleana;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue("B")
public class CaracteristicaIdealBooleana extends CaracteristicaIdeal {
  @ElementCollection
  @Column(name = "valoresPosiblesBooleanos")
  List<Boolean> valoresPosibles;

  public CaracteristicaIdealBooleana(String descripcion, List<Boolean> valores) {
    this.descripcion = descripcion;
    this.valoresPosibles = valores;
  }

  public CaracteristicaIdealBooleana() {

  }

  public List<Boolean> getValoresPosibles() {
    return valoresPosibles;
  }

  public CaracteristicaSensible crearCaracteristicaSensible(Boolean bool) {
    return new CaracteristicaSensibleBooleana(this, bool);
  }
}
