package africa.semicolon.orishaDiary.dtos.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateEntryRequest {
    @NotNull
    private String author;
    @NotNull
    private String title;
    @NotNull
    private String body;
    @NotNull
    private String id;
}
