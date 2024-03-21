package africa.semicolon.orishaDiary.data.repositories;

import africa.semicolon.orishaDiary.data.models.Entry;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface EntryRepository extends MongoRepository<Entry, String> {
}
