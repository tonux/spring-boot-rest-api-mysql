package sn.atos.api.restapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.atos.api.restapi.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
