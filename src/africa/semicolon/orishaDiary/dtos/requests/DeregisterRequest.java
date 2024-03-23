package africa.semicolon.orishaDiary.dtos.requests;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class DeregisterRequest {
    @NotNull
    private String username;
    @NotNull
    private String password;
}
