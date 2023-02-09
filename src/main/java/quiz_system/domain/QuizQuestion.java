package quiz_system.domain;

import lombok.*;

/**
 * @author Eric Wang
 * @date 2/4/23 4:35 PM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuizQuestion {
    private int quiz_id;
    private int question_id;
    private int user_choice_id;
}
