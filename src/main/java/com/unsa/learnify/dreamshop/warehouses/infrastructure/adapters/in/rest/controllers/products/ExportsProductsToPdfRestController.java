package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.controllers.products;

import com.unsa.learnify.dreamshop.warehouses.application.ports.in.products.ExportProductsToPdfServicePort;

import com.unsa.learnify.dreamshop.warehouses.application.ports.in.products.FindProductsServicePort;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Page;
import com.unsa.learnify.dreamshop.warehouses.domain.models.PaginationResult;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Product;
import com.unsa.learnify.dreamshop.warehouses.domain.models.ProductFilters;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.products.ProductQueryRequest;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.utils.IntegerUtils;

import jakarta.validation.Valid;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/v1/products")
public class ExportsProductsToPdfRestController {
    private FindProductsServicePort findProductsServicePort;
    private ExportProductsToPdfServicePort exportProductsToPdfService;
    public ExportsProductsToPdfRestController(
        FindProductsServicePort findProductsServicePort,
        ExportProductsToPdfServicePort exportProductsToPdfService
    ) {
        this.findProductsServicePort = findProductsServicePort;
        this.exportProductsToPdfService = exportProductsToPdfService;
    }
    @GetMapping("/reports")
    public ResponseEntity<InputStreamResource> exportProductsToPdf(
        @ModelAttribute @Valid ProductQueryRequest productQueryRequest
    ) throws IOException {
        Integer page = IntegerUtils.safeParseInteger(productQueryRequest.getPage(), 0);
        Integer size = IntegerUtils.safeParseInteger(productQueryRequest.getSize(), 0);
        Page pageable = Page.builder().number(page).size(size).build();
        ProductFilters productFilters = ProductFilters.builder()
            .page(pageable)
            .name(productQueryRequest.getName())
            .description(productQueryRequest.getDescription())
            .code(productQueryRequest.getCode())
            .minPrice(productQueryRequest.getMinPrice())
            .maxPrice(productQueryRequest.getMaxPrice())
            .minQuantity(IntegerUtils.safeParseInteger(productQueryRequest.getMinQuantity(), null))
            .maxQuantity(IntegerUtils.safeParseInteger(productQueryRequest.getMaxQuantity(), null))
            .categoryId(IntegerUtils.safeParseInteger(productQueryRequest.getCategoryId(), null))
            .build();
        PaginationResult<Product> productPaginationResult = this.findProductsServicePort.execute(productFilters);
        if (productPaginationResult.getItems().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        InputStream pdfInputStream = this.exportProductsToPdfService.execute(productPaginationResult.getItems());
        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_PDF)
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=products.pdf")
            .body(new InputStreamResource(pdfInputStream));
    }
}
