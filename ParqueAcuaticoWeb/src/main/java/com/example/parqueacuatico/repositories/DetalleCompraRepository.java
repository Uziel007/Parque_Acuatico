package com.example.parqueacuatico.repositories;

import com.example.parqueacuatico.models.DetalleCompra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleCompraRepository extends JpaRepository<DetalleCompra, Long> {
    // Métodos adicionales si es necesario
}
