package africa.semicolon.orishaDiary.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Document
public class Entry {
    @Id
    private String id;
    private String title;
    private String body;
    private LocalDateTime dateCreated = LocalDateTime.now();
    private String author;

    @Override
    public String toString() {
        String asterisk = "*".repeat(50);
        String dateCreated = this.dateCreated.format(DateTimeFormatter.ofPattern("dd/MMMM/yyyy HH:mm:ss a"));
        String format = "%s%nGist %d%nDate Created: %s%nGossip Title: %s%nLatest Gist: %s%n%s%n";

        return String.format(format, asterisk, id, dateCreated, title, body, asterisk);
    }
}
