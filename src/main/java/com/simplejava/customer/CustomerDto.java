package com.simplejava.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.simplejava.util.masking.MaskedField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * Description :
 * User: Tanveer Haider
 * Date: 5/3/2023
 * Time: 3:53 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto  {
    private long id;
    private String firstName;
    private String lastName;

    @MaskedField
    private String phoneNumber;
    @JsonProperty("emailAddress")
    @MaskedField
    private String emailId;

    @MaskedField
    private String ssn;


}

