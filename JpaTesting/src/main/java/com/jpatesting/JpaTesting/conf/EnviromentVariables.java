package com.jpatesting.JpaTesting.conf;

import java.util.Optional;

public class EnviromentVariables {
    private final String PROFILE_VARIABLE = "environment";

    public String getProfileEnviromentVariable(){
        return Optional.ofNullable(System.getenv(PROFILE_VARIABLE))
                .filter(value -> value != "test")
                .orElse("local");
    }
}
