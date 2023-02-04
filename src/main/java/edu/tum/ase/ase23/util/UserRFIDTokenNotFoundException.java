package edu.tum.ase.ase23.util;

import javax.management.relation.RelationException;

public class UserRFIDTokenNotFoundException extends RelationException {
    public UserRFIDTokenNotFoundException() {
        super();
    }

    public UserRFIDTokenNotFoundException(String message) {
        super(message);
    }
}
