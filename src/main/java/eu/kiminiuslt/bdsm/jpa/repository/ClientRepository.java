package eu.kiminiuslt.bdsm.jpa.repository;

import eu.kiminiuslt.bdsm.jpa.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {

  @Query(value = "SELECT u FROM Client u JOIN FETCH u.roles WHERE u.username = :username")
  Optional<Client> findUserByEmailWithAuthorities(String username);
}