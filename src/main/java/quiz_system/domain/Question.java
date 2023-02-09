package quiz_system.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Question {
    private int question_id;
    private int cat_id;
    private String description;
}
