package com.example.simpleboard.crud;

import com.example.simpleboard.common.Api;
import com.example.simpleboard.common.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/*
 * DTO -> Entity -> DTO
 */
public class CRUDAbstractService<DTO, ENTITY> implements CRUDInterface<DTO>{

    @Autowired(required = false)
    private JpaRepository<ENTITY, Long> repository;

    @Autowired(required = false)
    private Converter<DTO, ENTITY> converter;


    @Override
    public DTO create(DTO dto) {
        // dto -> entity
        var entity = converter.toEntity(dto);
        // entity -> save
        repository.save(entity);
        // save -> dto
        var convertDto = converter.toDTO(entity);
        return convertDto;
    }

    @Override
    public Optional<DTO> read(Long id) {
        var optionalEntity = repository.findById(id);
        var dto = optionalEntity.map(
                it -> {
                    return converter.toDTO(it);
                }
        ).orElseGet(() -> null);

        return Optional.ofNullable(dto);
    }

    @Override
    public DTO update(DTO dto) {
        var entity = converter.toEntity(dto);
        repository.save(entity);
        // DTO로 만들어서 반환
        return converter.toDTO(entity);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Api<List<DTO>> list(Pageable pageable) {
        var list = repository.findAll(pageable);

        var pagination = Pagination.builder()
                .page(list.getNumber())
                .size(list.getSize())
                .currentElements(list.getNumberOfElements())
                .totalElements(list.getTotalElements())
                .totalPage(list.getTotalPages())
                .build();

//        var dtoList = list.stream().map(converter::toDTO).collect(Collectors.toList());

        var dtoList = list.stream()
                .map(it -> {
                    return converter.toDTO(it);
                })
                .collect(Collectors.toList());

        var response = Api.<List<DTO>>builder()
                .body(dtoList)
                .pagination(pagination)
                .build();

        return response;
    }
}
