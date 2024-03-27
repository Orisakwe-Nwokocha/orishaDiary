package africa.semicolon.orishaDiary.data.repositories;

import africa.semicolon.orishaDiary.data.models.Entry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface EntryRepository extends MongoRepository<Entry, String> {
    List<Entry> findByAuthor(String author);
}
