package edu.tum.ase.ase23.util;

import javax.management.relation.RelationException;

public class UserEmailNotFoundException extends RelationException {
    public UserEmailNotFoundException() {
        super();
    }

    public UserEmailNotFoundException(String message) {
        super(message);
    }
}

