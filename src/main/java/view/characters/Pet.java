package view.characters;

import view.characters.types.PetTypes;

import java.util.stream.Stream;


public class Pet {
    private final PetTypes type;

    public Pet(PetTypes type) {
        this.type = type;
    }


    public PetTypes getType() {
        return type;
    }

    public static boolean isValidType(String type) {
        return Stream.of(PetTypes.values()).anyMatch(v -> v.name().equalsIgnoreCase(type));
    }
}

