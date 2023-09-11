package ua.vart.portfolio.domain.mapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;
import ua.vart.portfolio.domain.dto.FeedbackCreateDto;
import ua.vart.portfolio.domain.dto.FeedbackGetDto;
import ua.vart.portfolio.domain.entity.Feedback;

@Component
public class FeedbackMapper {
    public FeedbackGetDto toFeedBackGetDto(Feedback feedback) {
        return new FeedbackGetDto(
                feedback.getId(),
                feedback.getCreatedDate().toString(),
                feedback.getCode().getClient().getName(),
                feedback.getText()
        );
    }

    public Feedback toFeedback(FeedbackCreateDto feedbackCreateDto) {
        return Feedback.builder()
                .text(feedbackCreateDto.text())
                .build();
    }

    public Page<FeedbackGetDto> toFeedbackGetDtoPage(Page<Feedback> feedbackPage) {
        return new PageImpl<>(
                feedbackPage.getContent().stream().map(this::toFeedBackGetDto).toList(),
                feedbackPage.getPageable(),
                feedbackPage.getTotalElements()
        );
    }
}
