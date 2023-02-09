package quiz_system.domain;

import lombok.*;

import java.util.List;

/**
 * @author Eric Wang
 * @date 2/6/23 1:38 PM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuestionOption {
    private Question question;
    private List<Option> options;
    private int seq_id;
    private Option user_option;
}
