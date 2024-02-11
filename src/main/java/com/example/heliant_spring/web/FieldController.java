package com.example.heliant_spring.web;

import com.example.heliant_spring.domain.field.dto.FieldRequestDto;
import com.example.heliant_spring.domain.field.dto.FieldResponseDto;
import com.example.heliant_spring.domain.field.dto.FieldUpdateDto;
import com.example.heliant_spring.domain.field.service.FieldService;
import com.example.heliant_spring.infrastructure.exceptions.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fields")
@AllArgsConstructor
@SecurityRequirement(name = "jwtAuth")
public class FieldController {

    private final FieldService fieldService;
    @Operation(summary = "Create new form field", description = "Only admin has authorization to create the form field")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created field successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FieldResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Input validation failed",
                    content = @Content)})
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<FieldResponseDto> save(@Valid @RequestBody FieldRequestDto fieldRequestDto) throws NotFoundException {
        return ResponseEntity.ok(fieldService.save(fieldRequestDto));
    }
    @Operation(summary = "Get form field by id", description = "Only admin has authorization to get form field by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched form field",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FieldResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "Form field with sent id doesn't exist",
                    content = @Content)})
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<FieldResponseDto> getById(@PathVariable Integer id) throws NotFoundException {
        return ResponseEntity.ok(fieldService.getById(id));
    }
    @Operation(summary = "Get all fields", description = "Only admin has authorization to get fields by id. Field pagination " +
            "is supported by sending page and size query parameters ")
    @Parameter(in = ParameterIn.QUERY,
            description = "Zero-based page index",
            name = "page",
            content = @Content(schema = @Schema(type = "integer", defaultValue = "0")))
    @Parameter(in = ParameterIn.QUERY,
            description = "The size of the page to be returned",
            name = "size", content = @Content(schema = @Schema(type = "integer", defaultValue = "20")))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched fields",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FieldResponseDto.class))})})
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<Page<FieldResponseDto>> get(Pageable pageable) {
        return ResponseEntity.ok(fieldService.get(pageable));
    }
    @Operation(summary = "Update field by id", description = "Only admin has authorization to update field")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated field",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FieldResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Input validation failed",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Field with sent id does not exist",
                    content = @Content)})
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<FieldResponseDto> update(@PathVariable Integer id, @Valid @RequestBody FieldUpdateDto fieldUpdateDto) throws NotFoundException {
        return ResponseEntity.ok(fieldService.update(id,fieldUpdateDto));
    }
    @Operation(summary = "Delete field by id", description = "Only admin has authorization to delete field")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted field"),
            @ApiResponse(responseCode = "404", description = "Field with sent id does not exist",
                    content = @Content)})
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) throws NotFoundException {
        fieldService.delete(id);
    }
}
