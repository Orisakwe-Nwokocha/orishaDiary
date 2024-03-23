package africa.semicolon.orishaDiary.dtos.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DeregisterRequest {
    @NotNull
    private String username;
    @NotNull
    private String password;
}
