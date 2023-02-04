package edu.tum.ase.ase23.repository;

import edu.tum.ase.ase23.model.Role;
import edu.tum.ase.ase23.model.RoleEnum;
import org.springframework.data.mongodb.repository.MongoRepository;
import edu.tum.ase.ase23.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    Optional<User> findByRFIDToken(String RFIDToken);
    List<User> findUserByRoles(Set<Role> roleSet);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}