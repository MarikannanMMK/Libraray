package com.mmk.library.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank
    @Size(max=250)
    private String firstName;
    @NotBlank
    @Size(max=250)
    private String lastName;
    @NotBlank
    @Size(max=250)
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    @Size(max=250)
    private String memberId;
    @NotBlank
    @Size(max=250)
    private String member;
    private String role;
    private String isEnable;
    private String issuedBookCount;


}
