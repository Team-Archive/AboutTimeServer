package com.aboutTime.web.api.docs.swagger;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

public interface SwaggerSampleContollerDocs {
    @Operation(summary = "Swagger Api Get Method 샘플")
    @GetMapping
    String ApiGetSample();
    @Operation(summary = "Swagger Api Post Method 샘플")
    @PostMapping
    String ApiPostSample();
    @Operation(summary = "Swagger Api Put Method 샘플")
    @PutMapping
    String ApiPutSample();
    @Operation(summary = "Swagger Api Delete Method 샘플")
    @DeleteMapping
    String ApiDeleteSample();
}
