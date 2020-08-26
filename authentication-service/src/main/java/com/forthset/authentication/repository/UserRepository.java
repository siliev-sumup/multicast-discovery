package com.forthset.authentication.repository;

import com.forthset.authentication.domain.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

  Optional<User> findByEmail(String email);
}
