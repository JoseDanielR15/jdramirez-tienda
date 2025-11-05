package com.tienda.controller;

import com.tienda.services.ProductoServices;
import com.tienda.services.CategoriaServices; //Codigo nuevo de la tarea Practicas-2 semana 9
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {

    
    private final ProductoServices productoServices;
    private final CategoriaServices categoriaServices; //Codigo nuevo de la tarea Practicas-2 semana 9

    //Codigo nuevo de la tarea Practicas-2 semana 9
    public ConsultaController(ProductoServices productoServices, CategoriaServices categoriaServices) {
        this.productoServices = productoServices;
        this.categoriaServices = categoriaServices;
    }
    
    //Codigo nuevo de la tarea Practicas-2 semana 9
    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = productoServices.getProductos(false);
        var categorias = categoriaServices.getCategorias(true);
        model.addAttribute("productos", lista);
        model.addAttribute("categorias", categorias);
        return "/consultas/listado";
}

    
    @PostMapping("/consultaDerivada")
    public String consultaDerivada(@RequestParam() double precioInf,
            @RequestParam() double precioSup, Model model) {
        var lista = productoServices.consultaDerivada(precioInf, precioSup);
        model.addAttribute("productos", lista);
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);
        return "/consultas/listado";
    }
    
    @PostMapping("/consultaJPQL")
    public String consultaJPQL(@RequestParam() double precioInf,
            @RequestParam() double precioSup, Model model) {
        var lista = productoServices.consultaJPQL(precioInf, precioSup);
        model.addAttribute("productos", lista);
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);
        return "/consultas/listado";
    }
    
    @PostMapping("/consultaSQL")
    public String consultaSQL(@RequestParam() double precioInf,
            @RequestParam() double precioSup, Model model) {
        var lista = productoServices.consultaSQL(precioInf, precioSup);
        model.addAttribute("productos", lista);
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);
        return "/consultas/listado";
    }

    //Codigo nuevo de la tarea Practicas-2 semana 9
    @PostMapping("/consultaAmpliada")
    public String consultaAmpliada(@RequestParam("idCategoria") Integer idCategoria, Model model) {
        var productosPorCategoria = productoServices.getProductosPorCategoria(idCategoria);
        var categorias = categoriaServices.getCategorias(true);
        model.addAttribute("productosPorCategoria", productosPorCategoria);
        model.addAttribute("categorias", categorias);
        return "/consultas/listado";
    }

}
