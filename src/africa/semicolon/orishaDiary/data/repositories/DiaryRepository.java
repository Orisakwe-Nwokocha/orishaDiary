package africa.semicolon.orishaDiary.data.repositories;

import africa.semicolon.orishaDiary.data.models.Diary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface DiaryRepository extends MongoRepository<Diary, String> {
}
