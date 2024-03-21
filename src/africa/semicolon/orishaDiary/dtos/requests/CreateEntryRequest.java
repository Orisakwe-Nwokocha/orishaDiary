package africa.semicolon.orishaDiary.dtos.requests;

import lombok.Data;

@Data
public class CreateEntryRequest {
    private String author;
    private String title;
    private String body;
}
