package com.ar.spring_jpa.dominio;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"carrito"})
@EqualsAndHashCode(exclude = {"nombre", "precio"})
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Double precio;

    @ManyToOne
    @JoinColumn(nullable=true)
    private Carrito carrito;
}
