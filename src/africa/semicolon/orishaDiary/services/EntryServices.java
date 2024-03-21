package africa.semicolon.orishaDiary.services;

import africa.semicolon.orishaDiary.data.models.Entry;

import java.util.List;

public interface EntryServices {
    void save(Entry entry);
    void deleteEntry(int id);
    Entry getEntry(int id);
    List<Entry> getEntriesFor(String username);
}
