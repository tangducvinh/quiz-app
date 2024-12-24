package com.quiz_app.quiz_app.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    long id;
     String username;
     String password;
     String firstName;
     String lastName;
     LocalDate dob;
}
