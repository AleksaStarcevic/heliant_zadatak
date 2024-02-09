package com.example.heliant_spring.web;

import com.example.heliant_spring.domain.statistics.dto.StatisticsRequestDto;
import com.example.heliant_spring.domain.statistics.dto.StatisticsResponseDto;
import com.example.heliant_spring.domain.statistics.dto.StatisticsUpdateDto;
import com.example.heliant_spring.domain.statistics.service.StatisticsService;
import com.example.heliant_spring.infrastructure.exceptions.NotFoundException;
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
public class StatisticsController {

    private final StatisticsService statisticsService;

    @PostMapping
    public ResponseEntity<StatisticsResponseDto> save(@Valid @RequestBody StatisticsRequestDto statisticsRequestDto) {
        return ResponseEntity.ok(statisticsService.save(statisticsRequestDto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<StatisticsResponseDto> getById(@PathVariable Integer id) throws NotFoundException {
        return ResponseEntity.ok(statisticsService.getById(id));
    }
    @GetMapping
    public ResponseEntity<Page<StatisticsResponseDto>> get(Pageable pageable) {
        return ResponseEntity.ok(statisticsService.get(pageable));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StatisticsResponseDto> update(@PathVariable Integer id, @Valid @RequestBody StatisticsUpdateDto statisticsUpdateDto) throws NotFoundException {
        return ResponseEntity.ok(statisticsService.update(id,statisticsUpdateDto));
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) throws NotFoundException {
        statisticsService.delete(id);
    }
}
