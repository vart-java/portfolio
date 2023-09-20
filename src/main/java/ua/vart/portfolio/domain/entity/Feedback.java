package ua.vart.portfolio.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import ua.vart.portfolio.domain.base.BaseEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EqualsAndHashCode(callSuper = true, exclude = "code")
public class Feedback extends BaseEntity {

    @Column(length = 2048)
    private String text;

    @OneToOne
    @JoinColumn(name = "code_id")
    private Code code;
}
