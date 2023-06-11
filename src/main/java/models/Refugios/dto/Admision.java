package models.Refugios.dto;

public class Admision {
  boolean perros;
  boolean gatos;

  public boolean admiteGatos() {
    return isGatos();
  }

  public boolean admitePerros() {
    return isPerros();
  }

  public boolean isPerros() {
    return perros;
  }

  public boolean isGatos() {
    return gatos;
  }

  public Admision(boolean perros, boolean gatos) {
    this.perros = perros;
    this.gatos = gatos;
  }
}
