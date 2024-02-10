package com.example.heliant_spring.web;

import com.example.heliant_spring.domain.field.dto.FieldRequestDto;
import com.example.heliant_spring.domain.field.dto.FieldResponseDto;
import com.example.heliant_spring.domain.field.dto.FieldUpdateDto;
import com.example.heliant_spring.domain.field.service.FieldService;
import com.example.heliant_spring.infrastructure.exceptions.NotFoundException;
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
public class FieldController {

    private final FieldService fieldService;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<FieldResponseDto> save(@Valid @RequestBody FieldRequestDto fieldRequestDto) throws NotFoundException {
        return ResponseEntity.ok(fieldService.save(fieldRequestDto));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<FieldResponseDto> getById(@PathVariable Integer id) throws NotFoundException {
        return ResponseEntity.ok(fieldService.getById(id));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<Page<FieldResponseDto>> get(Pageable pageable) {
        return ResponseEntity.ok(fieldService.get(pageable));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<FieldResponseDto> update(@PathVariable Integer id, @Valid @RequestBody FieldUpdateDto fieldUpdateDto) throws NotFoundException {
        return ResponseEntity.ok(fieldService.update(id,fieldUpdateDto));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) throws NotFoundException {
        fieldService.delete(id);
    }
}
