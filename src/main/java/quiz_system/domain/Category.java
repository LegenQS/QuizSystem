package quiz_system.domain;

import lombok.*;

/**
 * @author Eric Wang
 * @date 2/4/23 4:36 PM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Category {
    private int cat_id;
    private String cat_name;
}
