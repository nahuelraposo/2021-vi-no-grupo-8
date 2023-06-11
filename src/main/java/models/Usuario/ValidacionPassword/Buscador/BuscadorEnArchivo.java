package models.Usuario.ValidacionPassword.Buscador;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class BuscadorEnArchivo {

  public static boolean estaIncluida(String palabraBuscada, String direccionDelArchivo) {

    Condicion condicion = String::equals;

    return encontrar(palabraBuscada, direccionDelArchivo, condicion);
  }

  public static boolean tieneCaracteresConsecutivos(String palabra, String direccionDelArchivo) {
    Condicion condicion = String::contains;

    return encontrar(palabra, direccionDelArchivo, condicion);
  }

  public static boolean encontrar(String palabraBuscada,
                                  String direccionDelArchivo, Condicion condicion) {
    try (Stream<String> stream = Files.lines(Paths.get(direccionDelArchivo))) {
      return stream.anyMatch(line -> condicion.compararStrings(palabraBuscada, line));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
