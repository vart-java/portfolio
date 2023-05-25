package ua.vart.portfolio.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record ClientGetDto(Long id, String name, String lastName, String code) {
}
