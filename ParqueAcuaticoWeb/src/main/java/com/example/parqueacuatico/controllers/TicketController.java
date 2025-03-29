package com.example.parqueacuatico.controllers;

import com.example.parqueacuatico.models.DetalleCompra;
import com.example.parqueacuatico.services.DetalleCompraService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Map;

import javax.imageio.ImageIO;


@Controller
public class TicketController {

    @Autowired
    private DetalleCompraService detalleCompraService;

    @GetMapping("/ticket")
    public String mostrarTicket(Model model, @RequestParam double total, @RequestParam double pago) {
        // Obtener los detalles de la compra desde el servicio
        List<DetalleCompra> detalles = detalleCompraService.obtenerDetallesCompra();

        // Generar un código de barras único
        String codigoBarras = generarCodigoBarras();

        // Obtener la fecha actual
        Date fecha = new Date();

        // Añadir los atributos al modelo para la vista
        model.addAttribute("total", total);
        model.addAttribute("pago", pago);
        model.addAttribute("detalles", detalles);
        model.addAttribute("fecha", fecha);
        model.addAttribute("codigoBarras", codigoBarras);

        // Redirigir a la vista de ticket.html
        return "ticket";
    }

    // Método para generar un código de barras único
    // Método para generar un código de barras único
    private String generarCodigoBarras() {
        try {
            // Generar un número aleatorio para el código de barras
            String codigo = String.valueOf((int) (Math.random() * 1000000000));

            // Definir los parámetros del código de barras
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            hints.put(EncodeHintType.MARGIN, 1); // Márgenes del código de barras

            // Crear la matriz de bits para el código de barras
            BitMatrix bitMatrix = new MultiFormatWriter().encode(codigo, BarcodeFormat.CODE_128, 300, 150, hints);

            // Convertir la matriz de bits en una imagen BufferedImage
            BufferedImage image = new BufferedImage(300, 150, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < 300; x++) {
                for (int y = 0; y < 150; y++) {
                    image.setRGB(x, y, bitMatrix.get(x, y) ? 0x000000 : 0xFFFFFF); // Poner píxel negro o blanco
                }
            }

            // Convertir la imagen a un arreglo de bytes
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "PNG", baos);
            baos.flush();
            byte[] imageBytes = baos.toByteArray();
            baos.close();

            // Convertir los bytes de la imagen a Base64
            return java.util.Base64.getEncoder().encodeToString(imageBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
