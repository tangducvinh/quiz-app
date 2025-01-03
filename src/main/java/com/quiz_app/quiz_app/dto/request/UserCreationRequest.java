package com.quiz_app.quiz_app.dto.request;

import com.quiz_app.quiz_app.exception.ErrorCode;
import com.quiz_app.quiz_app.validator.DobConstraint;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

// create method set
@Setter
// create method get
@Getter
// create constructor
@NoArgsConstructor
@AllArgsConstructor
// create builder class
@Builder
// default access modifier is private
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min =3, message = "USERNAME_INVALID")
     String username;

    @Size(min = 8, message = "USERNAME_INVALID_PASSWORD")
     String password;
     String firstName;
     String lastName;
     @DobConstraint(min = 12, message = "INVALID_DOB")
     LocalDate dob;
}
