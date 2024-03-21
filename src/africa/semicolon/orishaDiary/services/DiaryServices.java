package africa.semicolon.orishaDiary.services;

import africa.semicolon.orishaDiary.data.models.Diary;
import africa.semicolon.orishaDiary.data.models.Entry;
import africa.semicolon.orishaDiary.dtos.requests.*;

import java.util.List;

public interface DiaryServices {
    void register(RegisterRequest registerRequest);
    Diary findDiaryBy(String username);
    void login(LoginRequest loginRequest);
    void logout(String username);
    void deregister(RemoveUserRequest removeUserRequest);
    void createEntryWith(CreateEntryRequest createEntryRequest);
    void updateEntryWith(UpdateEntryRequest updateEntryRequest);
    void deleteEntry(String id, String username);
    Entry getEntry(String id, String username);
    List<Entry> getEntriesFor(String username);
}
