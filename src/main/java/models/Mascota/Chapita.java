package models.Mascota;

import models.Personas.Duenio;

import javax.persistence.*;

@Entity
@Table(name = "Chapitas")
public class Chapita {
  @Id
  Integer id;
  @ManyToOne(cascade = CascadeType.ALL)
  Duenio duenio;

  public Chapita(Integer id, Duenio duenio) {
    this.id = id;
    this.duenio = duenio;
  }

  public Chapita() {

  }

  public Duenio getDuenio() {
    return duenio;
  }

  public Integer getId() {
    return this.id;
  }

}
