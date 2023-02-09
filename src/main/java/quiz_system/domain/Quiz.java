package quiz_system.domain;

import lombok.*;
import java.sql.Timestamp;

/**
 * @author Eric Wang
 * @date 2/4/23 4:34 PM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Quiz {
    private int quiz_id;
    private int user_id;
    private int cat_id;
    private String quiz_name;
    private Timestamp start_time;
    private Timestamp end_time;
    private int score;
}
