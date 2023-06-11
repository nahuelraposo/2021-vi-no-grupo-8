package models.Usuario;

import models.Usuario.ValidacionPassword.ValidadorPassword;
import models.persistentEntity.PersistentEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Usuarios")
public class Usuario extends PersistentEntity {
  String username;
  String password;
  Boolean admin = false;

  public Usuario(String username, String password, ValidadorPassword validadorPassword, Boolean esAdmin) {
    validadorPassword.validarPassword(password);
    this.username = username;
    this.password = password;
    this.admin = esAdmin;
  }

  public Usuario() {
  }

  public String getPassword() {
    return password;
  }

  public String getUsername() {
    return username;
  }

  public Boolean esAdmin() {
    return this.admin;
  }

}
