package com.acad.matriculaservice.Util;

import com.acad.matriculaservice.Entity.Matricula;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

public class MatriculaPdfGenerator {

    public static ByteArrayInputStream generarPdf(List<Matricula> matriculas) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Font titulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph encabezado = new Paragraph("LISTADO DE MATRÍCULAS", titulo);
            encabezado.setAlignment(Element.ALIGN_CENTER);
            document.add(encabezado);
            document.add(new Paragraph(" "));

            PdfPTable tabla = new PdfPTable(6);
            tabla.setWidthPercentage(100);
            tabla.setWidths(new int[]{1, 3, 3, 3, 3, 2});

            Stream.of("ID", "Usuario", "Código", "Institución", "Plan Académico", "Nivel")
                    .forEach(header -> {
                        PdfPCell celda = new PdfPCell();
                        celda.setPhrase(new Phrase(header));
                        celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        tabla.addCell(celda);
                    });

            for (Matricula m : matriculas) {
                tabla.addCell(String.valueOf(m.getIdMatricula()));
                tabla.addCell(m.getUsuario());
                tabla.addCell(m.getCodigoMatricula());
                tabla.addCell(m.getInstitucion());
                tabla.addCell(m.getPlanAcademico());
                tabla.addCell(m.getNivel());
            }

            document.add(tabla);
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
