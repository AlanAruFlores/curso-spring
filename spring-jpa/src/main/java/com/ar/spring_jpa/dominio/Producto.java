package com.ar.spring_jpa.dominio;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Double precio;

    @ManyToOne
    @JoinColumn(name="carrito_id", nullable=false)
    private Carrito carrito;
}
