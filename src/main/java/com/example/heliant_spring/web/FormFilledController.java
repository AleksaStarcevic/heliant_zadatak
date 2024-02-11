package com.example.heliant_spring.web;

import com.example.heliant_spring.domain.form_filled.dto.FormFilledRequestDto;
import com.example.heliant_spring.domain.form_filled.dto.FormFilledResponseDto;
import com.example.heliant_spring.domain.form_filled.service.FormFilledService;
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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/forms-filled")
@AllArgsConstructor
@SecurityRequirement(name = "jwtAuth")
public class FormFilledController {

    private final FormFilledService formFilledService;
    @Operation(summary = "Create new form filled")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created form filled successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FormFilledResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Input validation failed",
                    content = @Content)})
    @PostMapping
    public ResponseEntity<FormFilledResponseDto> save(@Valid @RequestBody FormFilledRequestDto formFilledRequestDto) throws NotFoundException {
        return ResponseEntity.ok(formFilledService.save(formFilledRequestDto));
    }

    @Operation(summary = "Get form filled by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched form filled",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FormFilledResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "Form filled with sent id doesn't exist",
                    content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<FormFilledResponseDto> getById(@PathVariable Integer id) throws NotFoundException {
        return ResponseEntity.ok(formFilledService.getById(id));
    }
    @Operation(summary = "Get all forms filled", description = "Form filled pagination is supported by sending page " +
            "and size query parameters ")
    @Parameter(in = ParameterIn.QUERY,
            description = "Zero-based page index",
            name = "page",
            content = @Content(schema = @Schema(type = "integer", defaultValue = "0")))
    @Parameter(in = ParameterIn.QUERY,
            description = "The size of the page to be returned",
            name = "size", content = @Content(schema = @Schema(type = "integer", defaultValue = "20")))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched forms filled",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FormFilledResponseDto.class))})})
    @GetMapping
    public ResponseEntity<Page<FormFilledResponseDto>> get(Pageable pageable) {
        return ResponseEntity.ok(formFilledService.get(pageable));
    }
    @Operation(summary = "Update form filled by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated form filled",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FormFilledResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Input validation failed",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Filled form with sent id does not exist",
                    content = @Content)})
    @PatchMapping("/{id}")
    public ResponseEntity<FormFilledResponseDto> update(@PathVariable Integer id, @Valid @RequestBody FormFilledRequestDto formFilledRequestDto) throws NotFoundException {
        return ResponseEntity.ok(formFilledService.update(id,formFilledRequestDto));
    }
    @Operation(summary = "Delete form filled by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted filled form"),
            @ApiResponse(responseCode = "404", description = "Filled form with sent id does not exist",
                    content = @Content)})
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) throws NotFoundException {
        formFilledService.delete(id);
    }
}
