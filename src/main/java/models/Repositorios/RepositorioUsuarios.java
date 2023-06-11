package models.Repositorios;

import models.Excepciones.UsuarioInvalidoException;
import models.Personas.Duenio;
import models.Usuario.Usuario;
import models.Usuario.ValidacionPassword.ValidadorPassword;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.List;

public class RepositorioUsuarios implements WithGlobalEntityManager {
  private final static RepositorioUsuarios INSTANCE = new RepositorioUsuarios();

  public static RepositorioUsuarios getInstance() {
    return INSTANCE;
  }

  public void agregarUsuario(Usuario usuario) {
    ValidadorPassword validadorPassword = new ValidadorPassword();
    if (!usuario.getPassword().isEmpty() && !usuario.getUsername().isEmpty()) {
      validadorPassword.validarPassword(usuario.getPassword());
      entityManager().persist(usuario);
    } else {
      throw new UsuarioInvalidoException("El usuario o contraseña no pueden estar vacios o incompletos");
    }
  }

  public List<Usuario> getUsuarios() {
    return entityManager().createQuery("from Usuario", Usuario.class).getResultList();
  }

  public Usuario getUsuario(String username) {
    return entityManager()
        .createQuery("from Usuario where username = :user", Usuario.class)
        .setParameter("user", username).getResultList()
        .stream().findFirst().orElse(null);
  }

  public Usuario getById(Long id) {
    return entityManager().find(Usuario.class, id);
  }
}
