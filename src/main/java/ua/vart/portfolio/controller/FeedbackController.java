package ua.vart.portfolio.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.vart.portfolio.domain.dto.FeedbackCreateDto;
import ua.vart.portfolio.domain.dto.FeedbackGetDto;
import ua.vart.portfolio.domain.mapper.FeedbackMapper;
import ua.vart.portfolio.service.CodeService;
import ua.vart.portfolio.service.FeedbackService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/feedbacks")
public class FeedbackController {
    private final FeedbackService feedbackService;
    private final FeedbackMapper feedbackMapper;
    private final CodeService codeService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid FeedbackCreateDto feedbackCreateDto) {
        var feedback = feedbackMapper.toFeedback(feedbackCreateDto);
        feedback.setCode(codeService.use(feedbackCreateDto.code()));
        feedback = feedbackService.create(feedback);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(feedback.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackGetDto> read(@PathVariable Long id) {
        var feedback = feedbackService.findById(id);
        return ResponseEntity.ok(feedbackMapper.toFeedBackGetDto(feedback));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid FeedbackCreateDto feedbackCreateDto) {
        var feedback = feedbackService.findById(id);
        feedback.setText(feedbackCreateDto.text());
        feedbackService.update(feedback);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        feedbackService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public Page<FeedbackGetDto> findAll(Pageable pageable) {
        return feedbackMapper.toFeedbackGetDtoPage(feedbackService.findAll(pageable));
    }
}
