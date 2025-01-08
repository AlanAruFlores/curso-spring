package com.app.SpringSecurityApp.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/*Entidad usuario para guardar en la base de datos*/
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true) // no se puede repetir username
    private String username;

    private String password;

    @Column(name = "is_enabled")
    private Boolean isEnabled;
    @Column(name="account_No_expired")
    private Boolean accountNoExpired;
    @Column(name = "account_No_locked")
    private Boolean accountNoLocked;
    @Column(name="credential_No_expired")
    private Boolean credentialNoExpired;


    //Relacion many to many donde un usuario puede tener varios roles y un rol puede estar asociado
    //a varios usuarios

    /*CascadeType.All:
     significa que todas las acciones que realizamos con el objeto principal deben repetirse
     para sus objetos dependientes.
    * */
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="users_roles", joinColumns = @JoinColumn(name="user_id")
            , inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<RolesEntity> setRoles = new HashSet<>();
}
