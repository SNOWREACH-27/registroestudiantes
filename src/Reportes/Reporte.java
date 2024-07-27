/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reportes;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import EnviromentVariables.AppConfig;

/**
 *
 * @author franc
 */
public class Reporte {

    private int numeroPagina = 0;
    private java.sql.Connection conn;
    private final String URL = AppConfig.DB_URL;
    private final String USERNAME = AppConfig.DB_USERNAME;
    private final String PASSWORD = AppConfig.DB_PASSWORD;

    public void generarReporte(String sql, String Titulo, String Nombre) {
        try {
            // Crear el documento PDF
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(System.getProperty("user.home") + "/Desktop/" + Nombre));

            // Crear el encabezado y el pie de página
            PdfPageEventHelper eventHelper = new PdfPageEventHelper() {
                String fechaActual = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
                Font headerFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
                Font dateFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);

                public void onEndPage(PdfWriter writer, Document document) {
                    numeroPagina++;
                    PdfContentByte canvas = writer.getDirectContent();
                    Rectangle pageSize = document.getPageSize();
                    Font pageNumberFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);

                    ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT,
                            new Paragraph(String.format("Página %d", numeroPagina), pageNumberFont),
                            pageSize.getRight() - 36, pageSize.getTop() - 36, 0);

                }

                public void onStartPage(PdfWriter writer, Document document) {
                    PdfContentByte canvas = writer.getDirectContent();
                    Rectangle pageSize = document.getPageSize();

                    ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT,
                            new Paragraph(Titulo, headerFont),
                            pageSize.getLeft() + 36, pageSize.getTop() - 36, 0);

                    canvas.rectangle(
                            document.leftMargin(),
                            pageSize.getTop() - 5 * 72,
                            pageSize.getWidth() - document.leftMargin() - document.rightMargin(),
                            5 * 72
                    );
                    canvas.stroke();

                    ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT,
                            new Paragraph(fechaActual, dateFont),
                            pageSize.getRight() - 36, pageSize.getTop() - 54, 0);
                }
            };
            writer.setPageEvent(eventHelper);

            document.open();
            document.add(new Paragraph("\n\n"));
            // Crear la tabla para mostrar los datos
            PdfPTable table = new PdfPTable(7);
            table.setWidthPercentage(100);
            table.setSpacingBefore(20f);
            table.setSpacingAfter(20f);
            table.addCell("Nombre");
            table.addCell("Apellido");
            table.addCell("Edad");
            table.addCell("Cédula");
            table.addCell("Estado Civil");
            table.addCell("Documento");
            table.addCell("Estado");

            // Obtener los datos de la base de datos y agregarlos a la tabla
            try {
                conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                PreparedStatement statement = conn.prepareStatement(sql);
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    String nombre = result.getString("Nombre");
                    String apellido = result.getString("Apellido");
                    int edad = result.getInt("Edad");
                    String cedula = result.getString("Cedula");
                    String estadoCivil = result.getString("EstadoCivil");
                    String documento = result.getString("Documento");
                    String estado = result.getString("Estado");

                    table.addCell(nombre);
                    table.addCell(apellido);
                    table.addCell(String.valueOf(edad));
                    table.addCell(cedula);
                    table.addCell(estadoCivil);
                    table.addCell(documento);
                    table.addCell(estado);
                }

                // Agregar la tabla al documento
                document.add(table);

                // Cerrar recursos
                result.close();
                statement.close();

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    conn.close();
                }

                // Cerrar el documento
                document.close();
            }

            // Abrir el PDF generado
            abrirPDF(System.getProperty("user.home") + "/Desktop/" + Nombre);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void abrirPDF(String filePath) {
        try {
            File file = new File(filePath);
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}