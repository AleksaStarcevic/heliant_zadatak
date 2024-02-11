package com.example.heliant_spring.web;

import com.example.heliant_spring.domain.field_filled.dto.FieldFilledRequestDto;
import com.example.heliant_spring.domain.field_filled.dto.FieldFilledResponseDto;
import com.example.heliant_spring.domain.field_filled.dto.FieldFilledUpdateDto;
import com.example.heliant_spring.domain.field_filled.service.FieldFilledService;
import com.example.heliant_spring.infrastructure.exceptions.NotFoundException;
import com.example.heliant_spring.infrastructure.exceptions.ValueMatchFieldTypeException;
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
@RequestMapping("/api/v1/fields-filled")
@AllArgsConstructor
@SecurityRequirement(name = "jwtAuth")
public class FieldFilledController {

    private final FieldFilledService fieldFilledService;
    @Operation(summary = "Create new filled field")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created filled field successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FieldFilledResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Input validation failed",
                    content = @Content)})
    @PostMapping
    public ResponseEntity<FieldFilledResponseDto> save(@Valid @RequestBody FieldFilledRequestDto fieldFilledRequestDto) throws NotFoundException, ValueMatchFieldTypeException {
        return ResponseEntity.ok(fieldFilledService.save(fieldFilledRequestDto));
    }
    @Operation(summary = "Get filled field by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched filled field",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FieldFilledResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "Filled field with sent id doesn't exist",
                    content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<FieldFilledResponseDto> getById(@PathVariable Integer id) throws NotFoundException {
        return ResponseEntity.ok(fieldFilledService.getById(id));
    }
    @Operation(summary = "Get all filled fields", description = "Filled field pagination is supported by sending page " +
            "and size query parameters ")
    @Parameter(in = ParameterIn.QUERY,
            description = "Zero-based page index",
            name = "page",
            content = @Content(schema = @Schema(type = "integer", defaultValue = "0")))
    @Parameter(in = ParameterIn.QUERY,
            description = "The size of the page to be returned",
            name = "size", content = @Content(schema = @Schema(type = "integer", defaultValue = "20")))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched filled fields",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FieldFilledResponseDto.class))})})
    @GetMapping
    public ResponseEntity<Page<FieldFilledResponseDto>> get(Pageable pageable) {
        return ResponseEntity.ok(fieldFilledService.get(pageable));
    }
    @Operation(summary = "Update filled field by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated filled field",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FieldFilledResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Input validation failed",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Filled field with sent id does not exist",
                    content = @Content)})
    @PatchMapping("/{id}")
    public ResponseEntity<FieldFilledResponseDto> update(@PathVariable Integer id, @Valid @RequestBody FieldFilledUpdateDto fieldFilledUpdateDto) throws NotFoundException, ValueMatchFieldTypeException {
        return ResponseEntity.ok(fieldFilledService.update(id,fieldFilledUpdateDto));
    }
    @Operation(summary = "Delete filled field by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted filled field"),
            @ApiResponse(responseCode = "404", description = "Filled field with sent id does not exist",
                    content = @Content)})
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) throws NotFoundException {
        fieldFilledService.delete(id);
    }
}
