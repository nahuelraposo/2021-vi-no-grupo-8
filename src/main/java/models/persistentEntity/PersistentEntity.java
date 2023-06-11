package models.persistentEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class PersistentEntity {
  @Id
  @GeneratedValue
  long id;

  public long getId() {
    return id;
  }
}
