package models.Mascota.Caracteristica;

import models.Mascota.Caracteristica.caracteristicasSensibles.CaracteristicaSensible;
import models.Mascota.Caracteristica.caracteristicasSensibles.CaracteristicaSensibleString;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue("S")
public class CaracteristicaIdealStrings extends CaracteristicaIdeal {
  @ElementCollection
  @Column(name = "valoresPosiblesString")
  List<String> valoresPosibles;

  public CaracteristicaIdealStrings(String descripcion, List<String> valores) {
    this.descripcion = descripcion;
    this.valoresPosibles = valores;
  }

  public CaracteristicaIdealStrings() {

  }

  public List<String> getValoresPosibles() {
    return valoresPosibles;
  }

  public CaracteristicaSensible crearCaracteristicaSensible(String string) {
    return new CaracteristicaSensibleString(this, string);
  }
}
