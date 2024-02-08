package com.example.heliant_spring.web;

import com.example.heliant_spring.domain.form.dto.FormRequestDto;
import com.example.heliant_spring.domain.form.dto.FormResponseDto;
import com.example.heliant_spring.domain.form.service.FormService;
import com.example.heliant_spring.infrastructure.exceptions.NotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/forms")
@AllArgsConstructor
public class FormController {

    private final FormService formService;

    @PostMapping
    public ResponseEntity<FormResponseDto> save(@Valid @RequestBody FormRequestDto formRequestDto) {
        return ResponseEntity.ok(formService.save(formRequestDto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<FormResponseDto> getById(@PathVariable Integer id) throws NotFoundException {
        return ResponseEntity.ok(formService.getById(id));
    }
    @GetMapping
    public ResponseEntity<Page<FormResponseDto>> get(Pageable pageable) {
        return ResponseEntity.ok(formService.get(pageable));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<FormResponseDto> update(@PathVariable Integer id, @Valid @RequestBody FormRequestDto formRequestDto) throws NotFoundException {
        return ResponseEntity.ok(formService.update(id,formRequestDto));
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) throws NotFoundException {
        formService.delete(id);
    }
}
