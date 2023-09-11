package ua.vart.portfolio.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record FeedbackCreateDto(@NotBlank String code, @NotBlank String text) {
}
