package africa.semicolon.orishaDiary.dtos.requests;

import lombok.Data;

@Data
public class UpdateEntryRequest {
    private String author;
    private String title;
    private String body;
    private String id;

}
