package africa.semicolon.orishaDiary.data.repositories;

import africa.semicolon.orishaDiary.data.models.Diary;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface DiaryRepository extends MongoRepository<Diary, String> {



}
