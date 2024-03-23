package africa.semicolon.orishaDiary.dtos.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateEntryRequest {
    @NotNull
    private String author;
    @NotNull
    private String title;
    @NotNull
    private String body;
}
