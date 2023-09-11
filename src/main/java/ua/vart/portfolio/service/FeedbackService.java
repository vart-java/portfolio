package ua.vart.portfolio.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.vart.portfolio.domain.entity.Feedback;

public interface FeedbackService {
    Page<Feedback> findAll(Pageable pageable);

    Feedback create(Feedback feedback);

    Feedback findById(Long id);

    Feedback update(Feedback feedback);

    void delete(Long id);
}

