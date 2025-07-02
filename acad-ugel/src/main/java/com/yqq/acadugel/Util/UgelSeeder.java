package com.yqq.acadugel.Util;

import com.yqq.acadugel.Entity.Ugel;
import com.yqq.acadugel.Repocitory.UgelRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UgelSeeder implements CommandLineRunner {

    private final UgelRepository ugelRepository;

    public UgelSeeder(UgelRepository ugelRepository) {
        this.ugelRepository = ugelRepository;
    }

    @Override
    public void run(String... args) {
        if (ugelRepository.count() == 0) {
            Ugel ugel1 = new Ugel(null, "UGEL San Román");
            Ugel ugel2 = new Ugel(null, "UGEL Azángaro");
            Ugel ugel3 = new Ugel(null, "UGEL Lampa");
            Ugel ugel4 = new Ugel(null, "UGEL Chucuito");
            Ugel ugel5 = new Ugel(null, "UGEL Melgar");

            ugelRepository.save(ugel1);
            ugelRepository.save(ugel2);
            ugelRepository.save(ugel3);
            ugelRepository.save(ugel4);
            ugelRepository.save(ugel5);

            System.out.println("✅ Datos de UGEL insertados correctamente en H2.");
        } else {
            System.out.println("ℹ️ Los datos de UGEL ya existen en H2, no se insertaron duplicados.");
        }
    }
}
