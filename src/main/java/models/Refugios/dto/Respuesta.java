package models.Refugios.dto;

import java.util.List;

public class Respuesta {
  Integer total;
  String offset;
  List<Hogar> hogares;

  public List<Hogar> getHogares() {
    return hogares;
  }

  public Integer getTotal() {
    return total;
  }

  public Integer getPaginas() {
    int registrosPorPagina = 10;
    return (int) Math.ceil((double) total / (double) registrosPorPagina);
  }

  public Respuesta(Integer total, String offset, List<Hogar> hogares) {
    this.total = total;
    this.offset = offset;
    this.hogares = hogares;
  }
}
