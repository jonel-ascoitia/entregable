package com.example.senati.config; // O donde tengas tu configuración web

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // Esto asegura que cualquier URL que no sea manejada por otro controller (como /api/**)
    // sea redirigida a la raíz, permitiendo que Angular tome el control del routing.
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Redirige cualquier ruta sin handler a la página raíz ("/")
        // El patrón "/**/{[path:[^\\.]*}" ignora las rutas que contienen un punto (como CSS, JS, imágenes).
        registry.addViewController("/{spring:[^\\.]*}")
                .setViewName("forward:/");
    }
}
