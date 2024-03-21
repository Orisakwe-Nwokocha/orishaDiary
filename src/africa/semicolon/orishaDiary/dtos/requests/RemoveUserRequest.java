package africa.semicolon.orishaDiary.dtos.requests;

import lombok.Data;

@Data
public class RemoveUserRequest {
    private String username;
    private String password;
}
