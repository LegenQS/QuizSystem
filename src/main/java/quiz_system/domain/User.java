package quiz_system.domain;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    int user_id;
    String username;
    String first_name;
    String last_name;
    String address;
    String email;
    String password;
    String phone;
    boolean is_admin;
    boolean is_active;

//    public User(int user_id, String username, String password) {
//        this.user_id = user_id;
//        this.username = username;
//        this.password = password;
//    }
}
