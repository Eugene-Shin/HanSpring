package com.example.demo.dto.response;

import org.springframework.data.domain.Page;

import java.util.List;

public record PageDto<T>(
        List<T> content,
        Integer pageNum,
        Integer pageSize,
        Integer totalPages,
        Long totalElements,
        Boolean first,
        Boolean last
) {
    public static <T> PageDto<T> from(Page<?> page, List<T> content) {
        return new PageDto<>(
                content,
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.isFirst(),
                page.isLast()
        );
    }
}