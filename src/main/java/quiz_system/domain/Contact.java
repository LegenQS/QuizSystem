package quiz_system.domain;

import lombok.*;
import java.sql.Timestamp;

/**
 * @author Eric Wang
 * @date 2/4/23 12:37 AM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Contact {
    private int contact_id;
    private int user_id;
    private String message;
    private Timestamp time;
}
