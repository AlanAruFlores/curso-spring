package com.ar.spring_jpa.dominio;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"usuariosAgregados"})
@EqualsAndHashCode(exclude = {"usuariosAgregados"})
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="user_friend",
            joinColumns = {
                    @JoinColumn(name="user_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "friend_id")
            }
    )
    private Set<Usuario> usuariosAgregados;
}
