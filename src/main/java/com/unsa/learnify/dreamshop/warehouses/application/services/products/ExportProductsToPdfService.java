package com.unsa.learnify.dreamshop.warehouses.application.services.products;

import com.unsa.learnify.dreamshop.warehouses.application.ports.in.products.ExportProductsToPdfServicePort;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Product;

import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class ExportProductsToPdfService implements ExportProductsToPdfServicePort {
    private final ThymeleafViewResolver thymeleafViewResolver;
    public ExportProductsToPdfService(ThymeleafViewResolver thymeleafViewResolver) {
        this.thymeleafViewResolver = thymeleafViewResolver;
    }
    @Override
    public InputStream execute(List<Product> products) throws IOException {
        Context context = new Context();
        context.setVariable("products", products);
        context.setVariable("date", java.time.LocalDateTime.now());
        String htmlContent = thymeleafViewResolver.getTemplateEngine()
            .process("products-report", context);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(htmlContent);
        renderer.layout();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        renderer.createPDF(byteArrayOutputStream);
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }
}
