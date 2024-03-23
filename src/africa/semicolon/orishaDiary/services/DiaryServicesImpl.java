package africa.semicolon.orishaDiary.services;

import africa.semicolon.orishaDiary.data.models.Diary;
import africa.semicolon.orishaDiary.data.models.Entry;
import africa.semicolon.orishaDiary.data.repositories.DiaryRepository;
import africa.semicolon.orishaDiary.dtos.requests.*;
import africa.semicolon.orishaDiary.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiaryServicesImpl implements DiaryServices {
    @Autowired
    private DiaryRepository repository;
    @Autowired
    private EntryServices entryServices;

    @Override
    public void register(RegisterRequest request) {
        validate(request);

        Diary newDiary = new Diary();
        newDiary.setUsername(request.getUsername().toLowerCase());
        newDiary.setPassword(request.getPassword());

        repository.save(newDiary);
    }

    private void validate(RegisterRequest request) {
        validateNull(request);
        validateBlank(request);
        validateDuplicate(request);
    }

    @Override
    public void login(LoginRequest request) {
        Diary foundDiary = findDiaryBy(request.getUsername().toLowerCase());
        validatePasswordOf(foundDiary, request.getPassword());

        foundDiary.setLocked(false);
        repository.save(foundDiary);
    }

    @Override
    public void logout(String username) {
        Diary foundDiary = findDiaryBy(username.toLowerCase());
        foundDiary.setLocked(true);

        repository.save(foundDiary);
    }

    @Override
    public void updatePassword(UpdatePasswordRequest updatePasswordRequest) {
        String username = updatePasswordRequest.getUsername().toLowerCase();
        Diary foundDiary = findDiaryBy(username);
        checkLockStatusOf(foundDiary);
        validatePasswordOf(foundDiary, updatePasswordRequest.getOldPassword());

        foundDiary.setPassword(updatePasswordRequest.getNewPassword());
        repository.save(foundDiary);
    }

    @Override
    public void deregister(DeregisterRequest request) {
        Diary foundDiary = findDiaryBy(request.getUsername().toLowerCase());
        checkLockStatusOf(foundDiary);
        validatePasswordOf(foundDiary, request.getPassword());

        repository.delete(foundDiary);
    }

    @Override
    public void createEntryWith(CreateEntryRequest request) {
        Diary foundDiary = findDiaryBy(request.getAuthor().toLowerCase());
        checkLockStatusOf(foundDiary);

        Entry entry = new Entry();
        entry.setTitle(request.getTitle());
        entry.setBody(request.getBody());
        entry.setAuthor(request.getAuthor().toLowerCase());

        entryServices.save(entry);
    }

    @Override
    public void updateEntryWith(UpdateEntryRequest request) {
        Diary foundDiary = findDiaryBy(request.getAuthor().toLowerCase());
        checkLockStatusOf(foundDiary);

        Entry entry = new Entry();
        entry.setTitle(request.getTitle());
        entry.setBody(request.getBody());
        entry.setAuthor(request.getAuthor().toLowerCase());
        entry.setId(request.getId());

        entryServices.save(entry);
    }

    @Override
    public void deleteEntry(String id, String username) {
        Diary foundDiary = findDiaryBy(username.toLowerCase());
        checkLockStatusOf(foundDiary);

        entryServices.deleteEntry(id);
    }

    @Override
    public Entry getEntry(String id, String username) {
        Diary foundDiary = findDiaryBy(username.toLowerCase());
        checkLockStatusOf(foundDiary);

        return entryServices.getEntry(id);
    }

    @Override
    public List<Entry> getEntriesFor(String username) {
        Diary foundDiary = findDiaryBy(username.toLowerCase());
        checkLockStatusOf(foundDiary);

        return entryServices.getEntriesFor(username);
    }

    private void validateDuplicate(RegisterRequest request) {
        String username = request.getUsername().toLowerCase();
        boolean isDuplicate = repository.findById(username).isPresent();
        if (isDuplicate) throw new UsernameExistsException("Username already exists.");
    }

    private void validateBlank(RegisterRequest request) {
        boolean isBlank = request.getUsername().isBlank() || request.getPassword().isBlank();
        if (isBlank) throw new InvalidArgumentException("Username and password cannot be blank.");
    }

    private void validateNull(RegisterRequest request) {
        boolean isNull = request.getUsername() == null || request.getPassword() == null;
        if (isNull) throw new InvalidArgumentException("Username and password cannot be null.");
    }

    private Diary findDiaryBy(String username) {
        Optional<Diary> foundDiary = repository.findById(username.toLowerCase());
        if (foundDiary.isEmpty()) throw new UserNotFoundException("User not found.");

        return foundDiary.get();
    }

    private void checkLockStatusOf(Diary diary) {
        if (diary.isLocked()) throw new IllegalDiaryStateException("You need to login to use this service.");
    }

    private void validatePasswordOf(Diary diary, String password) {
        boolean isIncorrect = !diary.getPassword().equals(password);
        if (isIncorrect) throw new IncorrectPasswordException("Password is incorrect.");
    }
}
