package ua.vart.portfolio.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record ClientCreateDto(@NotBlank String name, @NotBlank String lastName) {
}
