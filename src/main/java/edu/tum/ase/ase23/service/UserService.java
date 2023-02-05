package edu.tum.ase.ase23.service;

import edu.tum.ase.ase23.model.Role;
import edu.tum.ase.ase23.model.RoleEnum;
import edu.tum.ase.ase23.model.User;
import edu.tum.ase.ase23.repository.RoleRepository;
import edu.tum.ase.ase23.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> getUserByRFIDToken(String RFIDToken) {
        return userRepository.findByRFIDToken(RFIDToken);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public List<User> getCustomers() {
        return userRepository.findAll().stream()
                .filter(user -> user.getRoles().stream()
                        .anyMatch(role -> role.getRoleEnum() == RoleEnum.ROLE_CUSTOMER))
                .collect(Collectors.toList());
    }

    public List<User> getDeliverers() {
        return userRepository.findAll().stream()
                .filter(user -> user.getRoles().stream()
                        .anyMatch(role -> role.getRoleEnum() == RoleEnum.ROLE_DELIVERER))
                .collect(Collectors.toList());
    }

    public List<User> getDispatcher() {
        return userRepository.findAll().stream()
                .filter(user -> user.getRoles().stream()
                        .anyMatch(role -> role.getRoleEnum() == RoleEnum.ROLE_DISPATCHER))
                .collect(Collectors.toList());
    }
}
