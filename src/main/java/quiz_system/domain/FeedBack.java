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
public class FeedBack {
    private int feedback_id;
    private int rating;
    private String description;
}
