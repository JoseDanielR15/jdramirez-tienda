package com.tienda.services;

import com.tienda.domain.Producto;
import com.tienda.repository.ProductoRepository;
import java.util.List;
import org.springFramework.beans.factory.annotation.Autowired;
import org.springFramework.stereotype.Service;
import org.springFramework.transaction.annotation.Transactional;

@Service
public class ProductoService {

    //Permite crear una unica instancia de ProductoReppsitory, y la crea automaticamente
    @Autowired
    private ProductoRepository productoRepository;
    
    @Transactional(readOnly = true)
    public List<Producto>getProductos(boolean activo) {
        vat lista=productoRepository.findAll();
        //Se valida si solo se quieren las productos activas
        if (activo){ //Solo activos...
            lista.removeIf(c-> !c.isActivo());
        }
        return lista;
    }
    
    @Transactional(readOnly=true)
    public Producto getProducto(Producto producto){
        return productoRepository.findById(producto.getIdProducto()).orElse(null);
    }
    
    @Transactional
    public void save(Producto producto){
        productoRepository.save(producto);
    }
    
    @Transactional
    public boolean delete(Producto producto){
        try{
            productoRepository.delete(producto);
            productoRepository.flush();
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
