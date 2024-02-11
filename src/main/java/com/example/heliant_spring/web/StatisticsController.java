package com.example.heliant_spring.web;

import com.example.heliant_spring.domain.statistics.dto.StatisticsRequestDto;
import com.example.heliant_spring.domain.statistics.dto.StatisticsResponseDto;
import com.example.heliant_spring.domain.statistics.dto.StatisticsUpdateDto;
import com.example.heliant_spring.domain.statistics.service.StatisticsService;
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
@RequestMapping("/api/v1/statistics")
@AllArgsConstructor
@SecurityRequirement(name = "jwtAuth")
public class StatisticsController {

    private final StatisticsService statisticsService;
    @Operation(summary = "Create new statistics")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created statistics successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = StatisticsResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Input validation failed",
                    content = @Content)})
    @PostMapping
    public ResponseEntity<StatisticsResponseDto> save(@Valid @RequestBody StatisticsRequestDto statisticsRequestDto) {
        return ResponseEntity.ok(statisticsService.save(statisticsRequestDto));
    }
    @Operation(summary = "Get statistics by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched statistics",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = StatisticsResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "Statistics with sent id doesn't exist",
                    content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<StatisticsResponseDto> getById(@PathVariable Integer id) throws NotFoundException {
        return ResponseEntity.ok(statisticsService.getById(id));
    }
    @Operation(summary = "Get all statistics", description = "Statistics pagination is supported by sending page " +
            "and size query parameters ")
    @Parameter(in = ParameterIn.QUERY,
            description = "Zero-based page index",
            name = "page",
            content = @Content(schema = @Schema(type = "integer", defaultValue = "0")))
    @Parameter(in = ParameterIn.QUERY,
            description = "The size of the page to be returned",
            name = "size", content = @Content(schema = @Schema(type = "integer", defaultValue = "20")))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched statistics",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = StatisticsResponseDto.class))})})
    @GetMapping
    public ResponseEntity<Page<StatisticsResponseDto>> get(Pageable pageable) {
        return ResponseEntity.ok(statisticsService.get(pageable));
    }
    @Operation(summary = "Update statistics by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated statistics",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = StatisticsResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Input validation failed",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Statistics with sent id does not exist",
                    content = @Content)})
    @PatchMapping("/{id}")
    public ResponseEntity<StatisticsResponseDto> update(@PathVariable Integer id, @Valid @RequestBody StatisticsUpdateDto statisticsUpdateDto) throws NotFoundException {
        return ResponseEntity.ok(statisticsService.update(id,statisticsUpdateDto));
    }
    @Operation(summary = "Delete statistics by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted statistics"),
            @ApiResponse(responseCode = "404", description = "Statistics with sent id does not exist",
                    content = @Content)})
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) throws NotFoundException {
        statisticsService.delete(id);
    }
}
