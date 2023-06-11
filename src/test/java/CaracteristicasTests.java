import models.Excepciones.CaracteristicaInvalidaException;
import models.Repositorios.RepositorioCaracteristicas;
import models.Mascota.Caracteristica.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import javax.persistence.EntityTransaction;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/*
public class CaracteristicasTests extends AbstractPersistenceTest implements WithGlobalEntityManager {
  EntityTransaction tx = PerThreadEntityManagers.getEntityManager().getTransaction();
  RepositorioCaracteristicas repositorioCaracteristicas = new RepositorioCaracteristicas();

  @BeforeEach
  void init() {
    entityManager().clear();
    tx.begin();
  }

  @AfterEach
  void finish() {
    tx.rollback();
  }

  @Test
  void elAdminPuedeCargarCaracteristicasEnumeradasDeDistintosTipos() {
    CaracteristicaIdeal caracteristicaIdeal1 = new CaracteristicaIdealBooleana("Tiene manchas", Arrays.asList(true, false));
    CaracteristicaIdeal caracteristicaIdeal2 = new CaracteristicaIdealNumerica("Edad aprox", Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 12, 13, 14, 15, 16, 17, 18, 19, 20));
    CaracteristicaIdeal caracteristicaIdeal3 = new CaracteristicaIdealStrings("Color de pelo", Arrays.asList("Marron", "Blanco", "Negro"));

    repositorioCaracteristicas.agregarNuevaCaracteristicaIdeal(caracteristicaIdeal1);
    repositorioCaracteristicas.agregarNuevaCaracteristicaIdeal(caracteristicaIdeal2);
    repositorioCaracteristicas.agregarNuevaCaracteristicaIdeal(caracteristicaIdeal3);

    assertTrue(repositorioCaracteristicas.estaEnLaListaDeCaracteristicas("Tiene manchas"));
    assertTrue(repositorioCaracteristicas.estaEnLaListaDeCaracteristicas("Edad aprox"));
    assertTrue(repositorioCaracteristicas.estaEnLaListaDeCaracteristicas("Color de pelo"));
  }

  @Test
  void elAdminNoPuedeCargarCaracteristicasDelMismoTipo() {
    CaracteristicaIdeal caracteristicaIdeal1 = new CaracteristicaIdealStrings("Pelaje", Arrays.asList("Marron", "Blanco", "Negro"));
    CaracteristicaIdeal caracteristicaIdeal2 = new CaracteristicaIdealStrings("Pelaje", Arrays.asList("Rojo", "Atigresado"));

    repositorioCaracteristicas.agregarNuevaCaracteristicaIdeal(caracteristicaIdeal1);

    assertThrows(
        CaracteristicaInvalidaException.class,
        () -> repositorioCaracteristicas.agregarNuevaCaracteristicaIdeal((caracteristicaIdeal2))
    );
  }

  @Test
  void elAdminPuedeCargarCaracteristicasDeTextoLibre() {
    CaracteristicaIdeal caracteristicaIdeal1 = new CaracteristicaIdealTextoLibre("Temperamento");

    repositorioCaracteristicas.agregarNuevaCaracteristicaIdeal(caracteristicaIdeal1);

    assertTrue(repositorioCaracteristicas.estaEnLaListaDeCaracteristicas("Temperamento"));
  }
}
*/
