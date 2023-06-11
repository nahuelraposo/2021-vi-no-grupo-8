package models.Refugios;

import models.Mascota.MascotaPerdida;
import models.Mascota.TamanioMascota;
import models.Mascota.TipoMascota;
import models.Refugios.api.RefugiosService;
import models.Refugios.dto.Hogar;
import models.Refugios.dto.Respuesta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BuscadorRefugios {
  RefugiosService requester;
  List<Hogar> hogares = new ArrayList<>();

  public BuscadorRefugios(RefugiosService requester) {
    this.requester = requester;
    if(requester!=null){
      cargarHogares();
    };
  }

  private void cargarHogares() {
    int offset = 1;
    Respuesta apiRefugios = requester.getRefugios(offset);
    while (offset <= apiRefugios.getPaginas()) {
      Respuesta respuesta = requester.getRefugios(offset);
      this.hogares.addAll(respuesta.getHogares());
      offset++;
    }
  }

  public List<Hogar> getAllHogares() {
    return hogares;
  }

  public List<Hogar> getHogaresParaPerros() {
    return filterHogaresParaPerros(hogares);
  }

  public List<Hogar> getHogaresParaGatos() {
    return filterHogaresParaGatos(hogares);
  }

  public List<Hogar> getHogaresConLugar() {
    return filterHogaresConLugar(hogares);
  }

  public List<Hogar> getHogaresAceptanMascotasMedianasYGrandes() {
    return filterHogaresConPatio(hogares);
  }

  public List<Hogar> getHogaresAdmisionEspecifica(List<String> caracteristicas) {
    return filterHogaresAdmisionEspecifica(hogares, caracteristicas);
  }

  public List<Hogar> getHogaresDentroDelRadio(Double radio, Ubicacion ubicacion) {
    return filterHogaresDentroDelRadio(hogares, radio, ubicacion);
  }

  public List<Hogar> buscarHogaresRecomendadosPara(MascotaPerdida mascota, Double radio) {
    return getHogaresAdecuados(mascota.getTipo(), mascota.getTamanio(), mascota.getCaracteristicas(), radio, mascota.getLugarEncuentro());
  }

  private List<Hogar> filterHogaresConPatio(List<Hogar> hogares) {
    return hogares.stream().filter(Hogar::tienePatio).collect(Collectors.toList());
  }

  private List<Hogar> filterHogaresParaPerros(List<Hogar> hogares) {
    return hogares.stream().filter(Hogar::admitePerros).collect(Collectors.toList());
  }

  private List<Hogar> filterHogaresParaGatos(List<Hogar> hogares) {
    return hogares.stream().filter(Hogar::admiteGatos).collect(Collectors.toList());
  }

  private List<Hogar> filterHogaresConLugar(List<Hogar> hogares) {
    return hogares.stream().filter(Hogar::tieneLugar).collect(Collectors.toList());
  }

  private List<Hogar> filterHogaresAdmisionEspecifica(List<Hogar> hogares, List<String> caracteristicas) {
    return hogares.stream().filter(hogar -> !Collections.disjoint(caracteristicas, hogar.getCaracteristicas())).collect(Collectors.toList());
  }

  private List<Hogar> filterHogaresSinAdmisionEspecifica(List<Hogar> hogares) {
    return hogares.stream().filter(hogar -> !hogar.tieneAdmisionEspecifica()).collect(Collectors.toList());
  }

  private List<Hogar> filterHogaresAceptanCaracteristicas(List<Hogar> hogares, List<String> caracteristicas) {
    return Stream.concat(filterHogaresSinAdmisionEspecifica(hogares).stream(),
            filterHogaresAdmisionEspecifica(hogares, caracteristicas).stream())
        .distinct().collect(Collectors.toList());
  }

  private List<Hogar> filterHogaresDentroDelRadio(List<Hogar> hogares, Double radio, Ubicacion ubicacion) {
    return hogares.stream().filter(hogar -> hogar.getUbicacion().estaDentroDelRadio(radio, ubicacion))
        .collect(Collectors.toList());
  }

  private List<Hogar> getHogaresAdecuados(TipoMascota tipo, TamanioMascota tamanio, List<String> caracteristicas,
                                          Double radio, Ubicacion ubicacion) {
    List<Hogar> hogaresRecomendados = getHogaresSegunAnimal(tipo);
    hogaresRecomendados = filtrarHogaresSegunTamanio(hogaresRecomendados, tamanio);
    hogaresRecomendados = filterHogaresAceptanCaracteristicas(hogaresRecomendados, caracteristicas);
    return filterHogaresDentroDelRadio(hogaresRecomendados, radio, ubicacion);
  }

  private List<Hogar> getHogaresSegunAnimal(TipoMascota tipo) {
    if (tipo == TipoMascota.GATO) {
      return getHogaresParaGatos();
    } else {
      return getHogaresParaPerros();
    }
  }

  private List<Hogar> filtrarHogaresSegunTamanio(List<Hogar> hogares, TamanioMascota tamanio) {
    if (tamanio == TamanioMascota.CHICA) {
      return hogares;
    } else
      return filterHogaresConPatio(hogares);
  }
}
