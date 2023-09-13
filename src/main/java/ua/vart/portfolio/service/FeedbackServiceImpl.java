package ua.vart.portfolio.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.vart.portfolio.domain.entity.Feedback;
import ua.vart.portfolio.exception.FeedbackNotFoundException;
import ua.vart.portfolio.repository.FeedbackRepository;

@RequiredArgsConstructor
@Service
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepository feedbackRepository;

    public Page<Feedback> findAll(Pageable pageable) {
        return feedbackRepository.findAll(pageable);
    }

    @Transactional
    public Feedback create(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    public Feedback findById(Long id) {
        return feedbackRepository.findById(id).orElseThrow(() -> new FeedbackNotFoundException("Feedback not found with id: " + id));
    }

    @Transactional
    public Feedback update(Feedback feedback) {
        if (!feedbackRepository.existsById(feedback.getId()))
            throw new FeedbackNotFoundException(("Feedback not found before update, id: " + feedback.getId()));
        return feedbackRepository.save(feedback);
    }

    @Transactional
    public void delete(Long id) {
        var feedback = feedbackRepository.findById(id).orElseThrow(()->new FeedbackNotFoundException(("Feedback not found before delete, id: " + id)));
        var code = feedback.getCode();
        code.setFeedback(null);
        feedbackRepository.delete(feedback);
    }
}
