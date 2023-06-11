package models.Repositorios;

import models.Excepciones.UsuarioInvalidoException;
import models.Personas.Duenio;
import models.Usuario.Usuario;
import models.Usuario.ValidacionPassword.ValidadorPassword;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.List;

public class RepositorioDuenios implements WithGlobalEntityManager{

  private final static RepositorioDuenios INSTANCE = new RepositorioDuenios();

  public static RepositorioDuenios getInstance() {
    return INSTANCE;
  }

  public void agregarDuenio(Duenio duenio) {
      entityManager().persist(duenio);
  }

  public List<Duenio> getDuenios() {
    return entityManager().createQuery("from Duenio", Duenio.class).getResultList();
  }

  public Duenio getDuenio(String username) {
    Usuario usuario = RepositorioUsuarios.getInstance().getUsuario(username);
    return entityManager()
        .createQuery("from Duenio where datosDeLogin = :login", Duenio.class)
        .setParameter("login", usuario).getResultList()
        .stream().findFirst().orElse(null);
  }

  public Usuario getById(Long id) {
    return entityManager().find(Usuario.class, id);
  }
}
