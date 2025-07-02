package com.yqq.acadinstitucion.Feign;

import com.yqq.acadinstitucion.Dto.UgelDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "acad-ugel")
public interface UgelFeign {
    @GetMapping("/ugeles/{id}")
    UgelDto getUgelById(Integer ugelId);
}
