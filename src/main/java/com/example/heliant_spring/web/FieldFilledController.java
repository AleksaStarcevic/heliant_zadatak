package com.example.heliant_spring.web;

import com.example.heliant_spring.domain.field_filled.dto.FieldFilledRequestDto;
import com.example.heliant_spring.domain.field_filled.dto.FieldFilledResponseDto;
import com.example.heliant_spring.domain.field_filled.dto.FieldFilledUpdateDto;
import com.example.heliant_spring.domain.field_filled.service.FieldFilledService;
import com.example.heliant_spring.infrastructure.exceptions.NotFoundException;
import com.example.heliant_spring.infrastructure.exceptions.ValueMatchFieldTypeException;
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
public class FieldFilledController {

    private final FieldFilledService fieldFilledService;

    @PostMapping
    public ResponseEntity<FieldFilledResponseDto> save(@Valid @RequestBody FieldFilledRequestDto fieldFilledRequestDto) throws NotFoundException, ValueMatchFieldTypeException {
        return ResponseEntity.ok(fieldFilledService.save(fieldFilledRequestDto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<FieldFilledResponseDto> getById(@PathVariable Integer id) throws NotFoundException {
        return ResponseEntity.ok(fieldFilledService.getById(id));
    }
    @GetMapping
    public ResponseEntity<Page<FieldFilledResponseDto>> get(Pageable pageable) {
        return ResponseEntity.ok(fieldFilledService.get(pageable));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<FieldFilledResponseDto> update(@PathVariable Integer id, @Valid @RequestBody FieldFilledUpdateDto fieldFilledUpdateDto) throws NotFoundException, ValueMatchFieldTypeException {
        return ResponseEntity.ok(fieldFilledService.update(id,fieldFilledUpdateDto));
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) throws NotFoundException {
        fieldFilledService.delete(id);
    }
}
