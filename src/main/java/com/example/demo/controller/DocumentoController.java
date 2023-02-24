package com.example.demo.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Linea;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping(value = "/documento")
public class DocumentoController {

	@GetMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> downloadInvoice() throws JRException, IOException {

        String documento = "\tLinea ¬Uno~ esto es una prueba de impresión de una linea, mas contenido y mas texto hasta ocupar, este es mi nombre: XnombreX|" + 
        "¬PRIMERA~\tTest1 - esto es una prueba de impresión de una linea, mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest2 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest3 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|" +
        "\t\tTest4 - esto es una prueba de impresión de una linea, mas contenido y mas contenido|";

        documento = documento.replaceAll("¬", "<style isBold='true'>").replaceAll("~", "</style>").replaceAll("XnombreX", "Miguel Angel Bernal");

        ArrayList<Linea> registros = new ArrayList<Linea>();
        int desde = 0;
        int longitudLinea = 0;
        boolean saltoLinea = false;
        for (int index = 0; index < documento.length(); index++) {
            longitudLinea++;
            saltoLinea = false;
            if(documento.substring(index, index+1).equals("|")){
                longitudLinea = 116;
                saltoLinea = true; 
            }
            if (longitudLinea > 115){
                if (saltoLinea){
                    registros.add(new Linea(documento.substring(desde, index+1).replace("|", ".") + "\n                           ."));
                } else {
                    registros.add(new Linea(documento.substring(desde, index+1) + "                           ."));
                }
                desde = index + 1;
                longitudLinea = 0;
            }
        }


		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(registros, false);

		Map<String, Object> parameters = new HashMap<>();

		JasperReport compileReport = JasperCompileManager
				.compileReport(new FileInputStream("src/main/resources/convenio.jrxml"));

		JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, parameters, beanCollectionDataSource);

		byte data[] = JasperExportManager.exportReportToPdf(jasperPrint);

		System.err.println(data);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=contratoreport.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}

}
