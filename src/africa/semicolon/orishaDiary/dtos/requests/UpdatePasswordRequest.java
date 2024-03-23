package africa.semicolon.orishaDiary.dtos.requests;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class UpdatePasswordRequest {
    @NotNull
    private String username;
    @NotNull
    private String oldPassword;
    @NotNull
    private String newPassword;
}
