package com.app.SpringSecurityApp.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "permissions")
public class PermisionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // el nombre es unico, no nulo y no se puede actualizar su valor
    @Column(unique = true, nullable = false, updatable = false)
    private String name;



}
