package com.example.heliant_spring.web;

import com.example.heliant_spring.domain.form_filled.dto.FormFilledRequestDto;
import com.example.heliant_spring.domain.form_filled.dto.FormFilledResponseDto;
import com.example.heliant_spring.domain.form_filled.service.FormFilledService;
import com.example.heliant_spring.infrastructure.exceptions.NotFoundException;
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
public class FormFilledController {

    private final FormFilledService formFilledService;

    @PostMapping
    public ResponseEntity<FormFilledResponseDto> save(@Valid @RequestBody FormFilledRequestDto formFilledRequestDto) throws NotFoundException {
        return ResponseEntity.ok(formFilledService.save(formFilledRequestDto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<FormFilledResponseDto> getById(@PathVariable Integer id) throws NotFoundException {
        return ResponseEntity.ok(formFilledService.getById(id));
    }
    @GetMapping
    public ResponseEntity<Page<FormFilledResponseDto>> get(Pageable pageable) {
        return ResponseEntity.ok(formFilledService.get(pageable));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<FormFilledResponseDto> update(@PathVariable Integer id, @Valid @RequestBody FormFilledRequestDto formFilledRequestDto) throws NotFoundException {
        return ResponseEntity.ok(formFilledService.update(id,formFilledRequestDto));
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) throws NotFoundException {
        formFilledService.delete(id);
    }
}
