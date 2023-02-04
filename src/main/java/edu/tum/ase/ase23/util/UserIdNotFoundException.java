package edu.tum.ase.ase23.util;

import javax.management.relation.RelationException;

public class UserIdNotFoundException extends RelationException {

    public UserIdNotFoundException() {
        super();
    }

    public UserIdNotFoundException(String message) {
        super(message);
    }
}
