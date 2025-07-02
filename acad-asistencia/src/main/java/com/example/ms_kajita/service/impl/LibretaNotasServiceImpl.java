package com.example.ms_kajita.service.impl;

import com.example.ms_kajita.dto.MatriculaDto;
import com.example.ms_kajita.dto.NotasparaAreaCurricularDto;
import com.example.ms_kajita.entity.LibretaNotas;
import com.example.ms_kajita.feing.MatriculaFeing;
import com.example.ms_kajita.feing.NotasparaAreaCurricularFeing;
import com.example.ms_kajita.repository.libretaNotasRepository;
import com.example.ms_kajita.service.LibretaNotasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibretaNotasServiceImpl implements LibretaNotasService {

    @Autowired
    private libretaNotasRepository libretaNotasRepository;

    @Autowired
    private MatriculaFeing matriculaFeing;
    @Autowired
    private NotasparaAreaCurricularFeing notasparaAreaCurricularFeing;


    @Override
    public List<LibretaNotas> listar() {
        return libretaNotasRepository.findAll().stream()
                .map(libretaNotas -> {
                    libretaNotas.setCodigoMatricula(obtenerMatricula(libretaNotas.getIdMatricula().intValue()));
                    libretaNotas.setNota(obtenerLibretaNota(libretaNotas.getIdLibretaNotas()));
                    libretaNotas.setNotaFinal(obtenerLibretaNota(libretaNotas.getIdLibretaNotas()));
                    libretaNotas.setIdPlanAcademico(obtenerLibretaPlanAcad(libretaNotas.getIdLibretaNotas()));
                    return libretaNotas;
                })
                .collect(Collectors.toList());
    }

    @Override
    public LibretaNotas guardar(LibretaNotas libretaNotas) {
        LibretaNotas libretaGuardada = libretaNotasRepository.save(libretaNotas);
        libretaGuardada.setCodigoMatricula(obtenerMatricula(libretaNotas.getIdMatricula().intValue()));
        libretaGuardada.setNota(obtenerLibretaNota(libretaNotas.getIdLibretaNotas()));
        libretaGuardada.setNotaFinal(obtenerLibretaNota(libretaNotas.getIdLibretaNotas()));
        libretaGuardada.setIdPlanAcademico(obtenerLibretaPlanAcad(libretaNotas.getIdLibretaNotas()));
        return libretaGuardada;
    }

    @Override
    public LibretaNotas actualizar(LibretaNotas libretaNotas) {
        LibretaNotas libretaActualizada = libretaNotasRepository.save(libretaNotas);
        LibretaNotas libretaGuardada = libretaNotasRepository.save(libretaNotas);
        libretaActualizada.setCodigoMatricula(obtenerMatricula(libretaNotas.getIdMatricula().intValue()));
        libretaActualizada.setNota(obtenerLibretaNota(libretaNotas.getIdLibretaNotas()));
        libretaActualizada.setNotaFinal(obtenerLibretaNota(libretaNotas.getIdLibretaNotas()));
        libretaActualizada.setIdPlanAcademico(obtenerLibretaPlanAcad(libretaNotas.getIdLibretaNotas()));
        return libretaActualizada;
    }

    @Override
    public Optional<LibretaNotas> listarPorId(Integer id) {
        Optional<LibretaNotas> libretaOptional = libretaNotasRepository.findById(id);
        if (libretaOptional.isPresent()) {
            LibretaNotas libretaNotas = libretaOptional.get();
            libretaNotas.setCodigoMatricula(obtenerMatricula(libretaNotas.getIdMatricula()));
            libretaNotas.setNota(obtenerLibretaNota(libretaNotas.getIdLibretaNotas()));
            libretaNotas.setNotaFinal(obtenerLibretaNota(libretaNotas.getIdLibretaNotas()));
            libretaNotas.setIdPlanAcademico(obtenerLibretaPlanAcad(libretaNotas.getIdLibretaNotas()));
            return Optional.of(libretaNotas);
        }
        return Optional.empty();
    }

    @Override
    public void eliminarPorId(Integer id) {
        libretaNotasRepository.deleteById(id);
    }


    private String obtenerMatricula(Integer idMatricula) {
        try {
            MatriculaDto matriculaDto = matriculaFeing.buscarMatricula(idMatricula).getBody();
            if (matriculaDto != null) {
                return matriculaDto.getCodigoMatricula();
            }
        } catch (Exception e) {
            return "Curso no encontrado";
        }
        return null;
    }


    private String obtenerLibretaNota(Integer idNotasCompetencia) {
        try {
            NotasparaAreaCurricularDto notasparaAreaCurricularDto = notasparaAreaCurricularFeing.buscarnotasparaAreaCurricular(idNotasCompetencia).getBody();
            if (notasparaAreaCurricularDto != null) {
                return notasparaAreaCurricularDto.getNota();
            }
        } catch (Exception e) {
            return "Docente no encontrado";
        }
        return null;
    }
    private String obtenerLibretaNotaFinal(Integer idNotasCompetencia) {
        try {
            NotasparaAreaCurricularDto notasparaAreaCurricularDto = notasparaAreaCurricularFeing.buscarnotasparaAreaCurricular(idNotasCompetencia).getBody();
            if (notasparaAreaCurricularDto != null) {
                return notasparaAreaCurricularDto.getNotaFinal();
            }
        } catch (Exception e) {
            return "Docente no encontrado";
        }
        return null;
    }
    private String obtenerLibretaPlanAcad(Integer idNotasCompetencia) {
        try {
            NotasparaAreaCurricularDto notasparaAreaCurricularDto = notasparaAreaCurricularFeing.buscarnotasparaAreaCurricular(idNotasCompetencia).getBody();
            if (notasparaAreaCurricularDto != null) {
                return notasparaAreaCurricularDto.getIdPlanAcademico();
            }
        } catch (Exception e) {
            return "Docente no encontrado";
        }
        return null;
    }

}