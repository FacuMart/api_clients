package com.api.api_clients.clients;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "clientes")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nombre")
    @NotBlank(message = "The name must not be empty.")
    private String name;
    @NotBlank(message = "The email must not be empty.")
    @Email(message = "The email format is invalid.")
    @Column(name = "email")
    private String email;
    @NotBlank(message = "The phone must not be empty.")
    @Column(name = "telefono")
    private String phone;
}
