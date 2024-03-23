package africa.semicolon.orishaDiary.dtos.requests;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class CreateEntryRequest {
    @NotNull
    private String author;
    @NotNull
    private String title;
    @NotNull
    private String body;
}
