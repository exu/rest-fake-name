package org.achumakin.model;

import com.github.javafaker.Faker;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NameModel {

    private String firstName;
    private String lastName;
    private String prefix;

    public static NameModel getRandom() {
        var fakeName = new Faker().name();
        return NameModel
                .builder()
                .firstName(fakeName.firstName())
                .lastName(fakeName.lastName())
                .prefix(fakeName.prefix())
                .build();
    }

}
