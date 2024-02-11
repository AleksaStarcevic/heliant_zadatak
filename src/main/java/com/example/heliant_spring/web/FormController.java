package com.example.heliant_spring.web;

import com.example.heliant_spring.domain.form.dto.FormRequestDto;
import com.example.heliant_spring.domain.form.dto.FormResponseDto;
import com.example.heliant_spring.domain.form.service.FormService;
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
@RequestMapping("/api/v1/forms")
@AllArgsConstructor
@SecurityRequirement(name = "jwtAuth")
public class FormController {

    private final FormService formService;
    @Operation(summary = "Create new form", description = "Only admin has authorization to create the form")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created form successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FormResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Input validation failed",
                    content = @Content)})
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<FormResponseDto> save(@Valid @RequestBody FormRequestDto formRequestDto) {
        return ResponseEntity.ok(formService.save(formRequestDto));
    }
    @Operation(summary = "Get form by id", description = "Only admin has authorization to get form by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched form",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FormResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "Form with sent id doesn't exist",
                    content = @Content)})
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<FormResponseDto> getById(@PathVariable Integer id) throws NotFoundException {
        return ResponseEntity.ok(formService.getById(id));
    }
    @Operation(summary = "Get all forms", description = "Only admin has authorization to get form by id. Form pagination " +
            "is supported by sending page and size query parameters ")
    @Parameter(in = ParameterIn.QUERY,
            description = "Zero-based page index",
            name = "page",
            content = @Content(schema = @Schema(type = "integer", defaultValue = "0")))
    @Parameter(in = ParameterIn.QUERY,
            description = "The size of the page to be returned",
            name = "size", content = @Content(schema = @Schema(type = "integer", defaultValue = "20")))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched forms",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FormResponseDto.class))})})
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<Page<FormResponseDto>> get(Pageable pageable) {
        return ResponseEntity.ok(formService.get(pageable));
    }
    @Operation(summary = "Update form by id", description = "Only admin has authorization to update form")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(schema = @Schema(implementation = FormRequestDto.class)))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated form",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FormResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Input validation failed",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Form with sent id does not exist",
                    content = @Content)})
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<FormResponseDto> update(@PathVariable Integer id, @Valid @RequestBody FormRequestDto formRequestDto) throws NotFoundException {
        return ResponseEntity.ok(formService.update(id,formRequestDto));
    }
    @Operation(summary = "Delete form by id", description = "Only admin has authorization to delete form")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted form"),
            @ApiResponse(responseCode = "404", description = "Form with sent id does not exist",
                    content = @Content)})
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) throws NotFoundException {
        formService.delete(id);
    }
}
