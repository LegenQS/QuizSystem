package quiz_system.domain;

import lombok.*;

/**
 * @author Eric Wang
 * @date 2/6/23 4:32 PM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuizResult {
    private int user_choice_id;
    private int option_id;
    private String question_des;
    private String option_des;
    private boolean is_correct;
}
