package praxis.eventreligi.app.repository;

import java.util.List;

import javax.validation.constraints.NotNull;

import praxis.eventreligi.app.model.User;

public interface AuthRepositoryInf {

    User login(@NotNull String email, @NotNull String password);

    User save(User user);

    List<User> findAll();

    Boolean checkExistEmail(@NotNull String email);
}
