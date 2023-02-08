package racingcar.dto;

import static racingcar.exception.ExceptionMessage.*;

import java.util.Arrays;
import java.util.List;

public class RacingCarNamesRequest {
    private final List<String> names;

    private RacingCarNamesRequest(List<String> names) {
        this.names = names;
    }

    private static void validate(String[] names) {
        for (String name : names) {
            validateEmptyName(name);
            validateNameLength(name);
        }
        validateDuplicateNames(names);
    }

    private static void validateDuplicateNames(String[] names) {
        long noDuplicateCount = Arrays.stream(names)
                .distinct()
                .count();
        if (noDuplicateCount != names.length) {
            throw new IllegalArgumentException(DUPLICATE_CAR_NAME.getMessage());
        }
    }

    private static void validateEmptyName(String name) {
        if(name.isBlank()) {
            throw new IllegalArgumentException(BLANK_CAR_NAME.getMessage());
        }
    }

    private static void validateNameLength(String name) {
        if(name.length() > 6) {
            throw new IllegalArgumentException(OUT_OF_CAR_NAME_LENGTH.getMessage());
        }
    }

    public static RacingCarNamesRequest of(String input) {
        String[] names = input.split(",");
        validate(names);
        return new RacingCarNamesRequest(List.of(names));
    }

    public List<String> getNames() {
        return names;
    }
}