package edu.tum.ase.ase23.repository;

import edu.tum.ase.ase23.model.Role;
import edu.tum.ase.ase23.model.RoleEnum;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByRoleEnum(RoleEnum roleEnum);
}
