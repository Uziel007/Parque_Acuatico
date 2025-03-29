package com.example.parqueacuatico.services;

import com.example.parqueacuatico.models.DetalleCompra;
import com.example.parqueacuatico.repositories.DetalleCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleCompraService {

    @Autowired
    private DetalleCompraRepository detalleCompraRepository;

    // Método para obtener todos los detalles de la compra
    public List<DetalleCompra> obtenerDetallesCompra() {
        // Esto puede ser adaptado para obtener los detalles de una compra específica si es necesario
        return detalleCompraRepository.findAll();  // O puedes usar otro filtro
    }
}
