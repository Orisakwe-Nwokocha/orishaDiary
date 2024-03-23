package africa.semicolon.orishaDiary.dtos.requests;

import lombok.Data;
import javax.validation.constraints.NotNull;

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
