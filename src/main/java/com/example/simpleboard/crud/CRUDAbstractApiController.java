package com.example.simpleboard.crud;

import com.example.simpleboard.common.Api;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public abstract class CRUDAbstractApiController<DTO, ENTITY> implements CRUDInterface<DTO>{


    @Autowired(required = false)
    private CRUDAbstractService<DTO, ENTITY> service;

    @PostMapping("")
    @Override
    public DTO create(
            @Valid @RequestBody DTO dto) {
        return service.create(dto);
    }

    @GetMapping("/id/{id}")
    @Override
    public Optional<DTO> read(
            @PathVariable Long id) {
        return service.read(id);
    }

    @PutMapping("")
    @Override
    public DTO update(
            @Valid @RequestBody DTO dto) {
        return service.update(dto);
    }

    @DeleteMapping("")
    @Override
    public void delete(
            @PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/all")
    @Override
    public Api<List<DTO>> list(
            @PageableDefault Pageable pageable) {
        return service.list(pageable);
    }
}
