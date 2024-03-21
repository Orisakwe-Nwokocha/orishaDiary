package africa.semicolon.orishaDiary.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Diary {
    private String username;
    private String password;
    @Id
    private String id;
    private boolean isLocked = true;
}
