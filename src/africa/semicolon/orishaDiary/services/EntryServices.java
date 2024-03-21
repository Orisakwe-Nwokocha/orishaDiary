package africa.semicolon.orishaDiary.services;

import africa.semicolon.orishaDiary.data.models.Entry;

import java.util.List;

public interface EntryServices {
    void save(Entry entry);
    void deleteEntry(String id);
    Entry getEntry(String id);
    List<Entry> getEntriesFor(String username);
}
