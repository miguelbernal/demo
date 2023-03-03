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

import com.example.demo.entity.Documento;
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
    private boolean negritaNoCierra = false;

    private String negrita(String linea){
        String lineaConNegrita = "";
        int apertura = 0;
        int cierre = 0;
        for (int i = 0; i < linea.length(); i++) {
            if(linea.substring(i, i+1).equals("¬")){
                apertura++;
            }
            if(linea.substring(i, i+1).equals("~")){
                cierre++;
            }
        }
        if (apertura == cierre) {
            negritaNoCierra = false;
            lineaConNegrita = linea.replaceAll("¬", "<style isBold='true'>").replaceAll("~", "</style>");
        } else {
            negritaNoCierra = true;
            linea = linea+"~";
            lineaConNegrita = linea.replaceAll("¬", "<style isBold='true'>").replaceAll("~", "</style>");
        }
        return lineaConNegrita;
    }

	@GetMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> downloadInvoice() throws JRException, IOException {

        String documento = Documento.grado_privada;

        /*documento = documento.replaceAll("¬", "<style isBold='true'>").replaceAll("~", "</style>").replaceAll("XnombreX", "Miguel Angel Bernal");*/
        documento = documento.replaceAll("XinstitucionX", "Institución de prueba");

        ArrayList<Linea> registros = new ArrayList<Linea>();
        int desde = 0;
        int longitudLinea = 0;
        boolean saltoLinea = false;
        boolean parrafoTabular = false;
        for (int index = 0; index < documento.length(); index++) {
            longitudLinea++;
            saltoLinea = false;
            if(documento.substring(index, index+1).equals("|")){
                if (parrafoTabular) {
                    longitudLinea = 81;    
                } else {
                    longitudLinea = 81;
                }
                saltoLinea = true;
            }

            if (parrafoTabular) {
                if (longitudLinea > 80){
                    if (saltoLinea){
                        Linea linea = new Linea(documento.substring(desde, index+1)
                        .replaceAll("\n", "")
                        .replace("^","")
                        .replace("|",""));
                        if (negritaNoCierra ) {
                            linea.setLinea("¬"+linea.getLinea());
                        }
                        linea.setLinea("\t\t\t" + linea.getLinea());
                        linea.setLinea(negrita(linea.getLinea()));
                        registros.add(linea);
                        negritaNoCierra = false;
                    } else {
                        Linea linea = new Linea(documento.substring(desde, index+1)
                        .replaceAll("\n", "")
                        .replace("|","")
                        .replace("^","")
                        + "                                                        .");
                        linea.setLinea("\t\t\t" + linea.getLinea());
                        if (negritaNoCierra ) {
                            linea.setLinea("¬"+linea.getLinea());
                        }
                        linea.setLinea(negrita(linea.getLinea()));
                        registros.add(linea);
                    }
                    desde = index + 1;
                    longitudLinea = 0;
                }
            } else {
                if (longitudLinea > 80){
                    if (saltoLinea){
                        Linea linea = new Linea(documento.substring(desde, index+1)
                        .replaceAll("\n", "")
                        .replaceAll("\t", "     ")
                        .replace("|",""));
                        if (negritaNoCierra ) {
                            linea.setLinea("¬"+linea.getLinea());
                        }
                        linea.setLinea(negrita(linea.getLinea()));
                        registros.add(linea);
                        negritaNoCierra = false;
                    } else {
                        Linea linea = new Linea(documento.substring(desde, index+1)
                        .replaceAll("\n", "")
                        .replaceAll("\t", "     ")
                        + "                                                        .");
                        if (negritaNoCierra ) {
                            linea.setLinea("¬"+linea.getLinea());
                        }
                        linea.setLinea(negrita(linea.getLinea()));
                        registros.add(linea);
                    }
                    desde = index + 1;
                    longitudLinea = 0;
                }
            }

            if(documento.substring(index, index+1).equals("|")){
                parrafoTabular =  false;
            }
            if(documento.substring(index, index+1).equals("^")){
                parrafoTabular = true;
            }
        }

        System.out.println("***-----------------------------");
        for (Linea linea : registros) {
            System.out.println(linea.getLinea());
        }
        System.out.println("***-----------------------------");


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
