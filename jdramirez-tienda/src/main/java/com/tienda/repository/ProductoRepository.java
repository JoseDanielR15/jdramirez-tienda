/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.repository;

import com.tienda.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
 
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    
    public List<Producto> findByActivoTrue();
    
    //Ejemplo de método utilizando consultas derivadas
    public List<Producto> findByPrecioBetweenOrderBtPreciosAsc(double precioInf, double precioSup);
    
    //Ejemplo de método utilizando consultas JQL
    @Query(value = "SELECT p FROM Producto p WHERE p.precio BETWEEN :precioInf AND : precioSup ORDER BY p.precio ASC")
    public List<Producto> consultaJPQL(@Param("precioInf") double precioInf, @Param("precioSup") double precioSup);
    
    //Ejemplo de método utilizando consultas SQL nativas
    @Query(nativeQuery = true,
            value = "SELECT * FROM producto p WHERE p.precio BETWEEN :precioInf AND : precioSup ORDER BY p.precio ASC" )
    public List<Producto> consultasSQL(@Param("precioInf") double precioInf, @Param("precioSup") double precioSup);
 
}
