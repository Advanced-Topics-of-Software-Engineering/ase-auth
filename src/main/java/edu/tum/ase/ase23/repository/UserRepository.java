package edu.tum.ase.ase23.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import edu.tum.ase.ase23.model.User;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}