package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.mappers;

import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.CurrencyNotFoundException;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Currency;
import com.unsa.learnify.dreamshop.warehouses.domain.models.PaginationResult;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Product;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.commons.PaginationResultResponse;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.products.ProductCreateRequest;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.products.ProductResponse;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.dtos.products.ProductUpdateRequest;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ProductRestMapper {
    private ProductRestMapper() {}
    public static ProductResponse domainToResponse(Product product) {
        return ProductResponse.builder()
            .id(product.getId())
            .name(product.getName())
            .description(product.getDescription())
            .code(product.getCode())
            .unitPrice(product.getUnitPrice())
            .currency(product.getCurrency().getCode())
            .quantity(product.getQuantity())
            .createdAt(product.getCreatedAt())
            .updatedAt(product.getUpdatedAt())
            .categories(product.getCategories().stream()
                .map(CategoryRestMapper::domainToResponse)
                .collect(Collectors.toSet()))
            .build();
    }
    public static Product createRequestToDomain(ProductCreateRequest productCreateRequest) throws CurrencyNotFoundException {
        return Product.builder()
            .id(null)
            .name(productCreateRequest.name())
            .description(productCreateRequest.description())
            .code(productCreateRequest.code())
            .unitPrice(productCreateRequest.unitPrice())
            .currency(parseCurrency(productCreateRequest.currency()))
            .build();
    }
    public static Product updateRequestToDomain(Integer productId, ProductUpdateRequest productUpdateRequest) throws CurrencyNotFoundException {
        return Product.builder()
            .id(productId)
            .name(productUpdateRequest.name())
            .description(productUpdateRequest.description())
            .unitPrice(productUpdateRequest.unitPrice())
            .currency(parseCurrency(productUpdateRequest.currency()))
            .build();
    }
    public static PaginationResultResponse<ProductResponse> domainToPaginationResponse(PaginationResult<Product> productPage) {
        return PaginationResultResponse.<ProductResponse>builder()
            .totalItems(productPage.getTotalItems())
            .totalPages(productPage.getTotalPages())
            .currentPage(productPage.getCurrentPage())
            .pageSize(productPage.getPageSize())
            .hasNextPage(productPage.getHasNextPage())
            .items(
                productPage.getItems().stream()
                    .map(ProductRestMapper::domainToResponse)
                    .toList()
            )
            .build();
    }
    private static Currency parseCurrency(String currencyCode) throws CurrencyNotFoundException {
        if (currencyCode == null || currencyCode.isEmpty()) {
            return null;
        }
        return Arrays.stream(Currency.values())
            .filter(currency -> currency.getCode().equalsIgnoreCase(currencyCode))
            .findFirst()
            .orElseThrow(() -> new CurrencyNotFoundException("Currency code '" + currencyCode + "' not found"));
    }
}
