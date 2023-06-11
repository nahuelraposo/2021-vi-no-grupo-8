package models.Notificador;

import models.Personas.*;
import models.persistentEntity.PersistentEntity;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.util.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", length = 2)
public abstract class Notificador extends PersistentEntity {

  public abstract void notificar(List<Contacto> contactos, String asunto, String mensaje);
}
