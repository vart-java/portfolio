package ua.vart.portfolio.domain.dto;

import ua.vart.portfolio.domain.entity.Code;

import java.util.Set;

public record ClientGetDto(Long id, String name, String lastName, Set<Code> codesSet) {
}
