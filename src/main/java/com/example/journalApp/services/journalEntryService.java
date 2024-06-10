package com.example.journalApp.services;

import com.example.journalApp.entity.journalEntry;
import com.example.journalApp.repository.journalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class journalEntryService {

    @Autowired
    private journalEntryRepository journalEntryRepository;

    public void saveEntry(journalEntry entry) {
        journalEntryRepository.save(entry);
    }

    public List<journalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<journalEntry> findJournalEntryById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    public void deleteEntryById(ObjectId id) {
        journalEntryRepository.deleteById(id);
    }

    public void  updateEntryById(ObjectId id, journalEntry entry) {
        journalEntryRepository.save(entry);
    }
}
