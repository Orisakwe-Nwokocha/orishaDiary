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
    @Autowired
    private EntryRepository repository;

    @Override
    public void save(Entry entry) {
        repository.save(entry);
    }

    @Override
    public void deleteEntry(String id) {
        Optional<Entry> entry = repository.findById(id);
        if (entry.isEmpty()) throw new EntryNotFoundException("Entry not found");

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
}
