package africa.semicolon.orishaDiary.services;

import africa.semicolon.orishaDiary.data.models.Entry;
import africa.semicolon.orishaDiary.data.repositories.EntryRepository;
import africa.semicolon.orishaDiary.exceptions.EmptyEntryListException;
import africa.semicolon.orishaDiary.exceptions.EntryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntryServicesImpl implements EntryServices {
    private long count;
    @Autowired
    private EntryRepository repository;

    @Override
    public void save(Entry entry) {
        if (isNew(entry)) addIdTo(entry);
        else update(entry);

        repository.save(entry);
    }

    @Override
    public void deleteEntry(String id) {
        if (!repository.existsById(id)) throw new EntryNotFoundException("Entry not found");

        repository.deleteById(id);
    }

    @Override
    public Entry getEntry(String id) {
        Optional<Entry> entry = repository.findById(id);
        if (entry.isEmpty()) throw new EntryNotFoundException("Entry not found");

        return entry.get();
    }

    @Override
    public List<Entry> getEntriesFor(String username) {
        List<Entry> entries = repository.findByAuthor(username.toLowerCase());
        if (entries.isEmpty()) throw new EmptyEntryListException("No entry found");

        return entries;
    }

    private void update(Entry entry) {
        repository.findById(entry.getId()).ifPresent(oldEntry -> entry.setDateCreated(oldEntry.getDateCreated()));
    }

    private void addIdTo(Entry entry) {
        entry.setId(generateId());
    }

    private String generateId() {
        if (repository.existsById(String.valueOf(++count))) generateId();
        return String.valueOf(count);
    }

    private boolean isNew(Entry entry) {
        return entry.getId() == null;
    }
}
