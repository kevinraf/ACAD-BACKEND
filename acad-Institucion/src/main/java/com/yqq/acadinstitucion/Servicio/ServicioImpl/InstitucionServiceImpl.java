package com.yqq.acadinstitucion.Servicio.ServicioImpl;

import com.yqq.acadinstitucion.Dto.InstitucionResponse;
import com.yqq.acadinstitucion.Dto.SedeDto;
import com.yqq.acadinstitucion.Dto.UgelDto;
import com.yqq.acadinstitucion.Entity.Institucion;
import com.yqq.acadinstitucion.Feign.SedeFeign;
import com.yqq.acadinstitucion.Feign.UgelFeign;
import com.yqq.acadinstitucion.Repository.InstitucionRepository;
import com.yqq.acadinstitucion.Servicio.InstitucionService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstitucionServiceImpl implements InstitucionService {

    @Autowired
    private InstitucionRepository institucionRepository;

    @Autowired
    private SedeFeign sedeFeign;

    @Autowired
    private UgelFeign ugelFeign;

    @Override
    public Institucion save(Institucion institucion) {
        return institucionRepository.save(institucion);
    }

    @Override
    public List<Institucion> listar() {
        return institucionRepository.findAll();
    }

    @Override
    public Institucion buscar(Long id) {
        return institucionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Institución con ID " + id + " no encontrada"));
    }

    @Override
    public Institucion actualizar(Institucion institucion) {
        if (!institucionRepository.existsById(institucion.getId())) {
            throw new RuntimeException("No se puede actualizar. Institución no encontrada con ID: " + institucion.getId());
        }
        return institucionRepository.save(institucion);
    }

    @Override
    public void eliminar(Long id) {
        if (!institucionRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. Institución no encontrada con ID: " + id);
        }
        institucionRepository.deleteById(id);
    }

    @Override
    @CircuitBreaker(name = "buscarInstitucionPorNombreCB", fallbackMethod = "fallbackBuscarPorNombre")
    public List<InstitucionResponse> findByNombre(String nombre) {
        List<Institucion> instituciones = institucionRepository.findByNombreContainingIgnoreCase(nombre);
        return mapearAResponse(instituciones);
    }

    @Override
    @CircuitBreaker(name = "listarInstitucionesCB", fallbackMethod = "fallbackListarInstituciones")
    public List<InstitucionResponse> findAllConDatosCompletos() {
        List<Institucion> instituciones = institucionRepository.findAll();
        return mapearAResponse(instituciones);
    }

    // Método reutilizable para convertir entidades a respuestas completas
    private List<InstitucionResponse> mapearAResponse(List<Institucion> instituciones) {
        List<InstitucionResponse> responses = new ArrayList<>();

        for (Institucion institucion : instituciones) {
            InstitucionResponse response = new InstitucionResponse();
            response.setId(institucion.getId());
            response.setNombre(institucion.getNombre());
            response.setDireccion(institucion.getDireccion());

            // Obtener datos de la sede
            SedeDto sede = null;
            try {
                if (institucion.getSedeId() != null) {
                    sede = sedeFeign.getSedeById(institucion.getSedeId());
                }
            } catch (Exception e) {
                System.out.println("⚠️ Error al obtener sede con ID " + institucion.getSedeId() + ": " + e.getMessage());
            }

            // Obtener datos de la ugel
            UgelDto ugel = null;
            try {
                if (institucion.getUgelId() != null) {
                    ugel = ugelFeign.getUgelById(institucion.getUgelId());
                }
            } catch (Exception e) {
                System.out.println("⚠️ Error al obtener UGEL con ID " + institucion.getUgelId() + ": " + e.getMessage());
            }

            response.setNombreSede(sede != null ? sede.getNombreSede() : "Sede no disponible");
            response.setNombreUgel(ugel != null ? ugel.getNombreDeLaUgel() : "UGEL no disponible");

            responses.add(response);
        }

        return responses;
    }

    // Fallbacks en caso de fallo en el CircuitBreaker
    public List<InstitucionResponse> fallbackBuscarPorNombre(String nombre, Throwable throwable) {
        System.out.println("❌ Fallback activado en findByNombre(): " + throwable.getMessage());
        return generarListaFallback();
    }

    public List<InstitucionResponse> fallbackListarInstituciones(Throwable throwable) {
        System.out.println("❌ Fallback activado en findAllConDatosCompletos(): " + throwable.getMessage());
        return generarListaFallback();
    }

    private List<InstitucionResponse> generarListaFallback() {
        InstitucionResponse fallback = new InstitucionResponse();
        fallback.setNombre("Servicio no disponible");
        fallback.setDireccion("-");
        fallback.setNombreSede("Sede no disponible");
        fallback.setNombreUgel("UGEL no disponible");

        List<InstitucionResponse> fallbackList = new ArrayList<>();
        fallbackList.add(fallback);
        return fallbackList;
    }
}
