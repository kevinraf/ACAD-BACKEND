package com.yqq.acadinstitucion.Feign;

import com.yqq.acadinstitucion.Dto.SedeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "acad-sede")
public interface SedeFeign {

    @GetMapping("/sedes/{id}")
    SedeDto getSedeById(@PathVariable Long id);
}