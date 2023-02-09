package quiz_system.domain;

import lombok.*;

/**
 * @author Eric Wang
 * @date 2/4/23 4:37 PM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Option {
    private int option_id;
    private int question_id;
    private String description;
    private boolean is_correct;
}
