package com.example.journalApp.controller;

import com.example.journalApp.entity.journalEntry;
import com.example.journalApp.services.journalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class journalEntryControllerV2 {

    @Autowired
    private journalEntryService journalEntryService;

    @GetMapping
    public List<journalEntry> getAll() {
        return journalEntryService.getAll();
    }

    @PostMapping
    public ResponseEntity<journalEntry> addEntry(@RequestBody journalEntry entry) {
        try{
            entry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(entry);
            return new ResponseEntity<journalEntry>(entry, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<journalEntry>(HttpStatus.BAD_REQUEST);
        }
        }


    @GetMapping("id/{myId}")
    public ResponseEntity<journalEntry> getEntryById(@PathVariable ObjectId myId) {
        Optional<journalEntry> journalEntry = journalEntryService.findJournalEntryById(myId);
        if (journalEntry.isPresent()) {
            return new ResponseEntity<journalEntry>(journalEntry.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("id/{myId}")
    public  journalEntry updateEntryById(@PathVariable ObjectId myId,@RequestBody journalEntry entry){
        journalEntry old = journalEntryService.findJournalEntryById(myId).orElse(null);
        if(old != null){
            old.setTitle(entry.getTitle() != null && !entry.getTitle().isEmpty() ? entry.getTitle() : old.getTitle());
            old.setContent(entry.getContent() != null && !entry.getContent().isEmpty() ? entry.getContent() : old.getContent());
        }
        journalEntryService.saveEntry(old);
        return old;
    }

    @DeleteMapping("id/{myId}")
    public boolean deleteEntryById(@PathVariable ObjectId myId) {
        journalEntryService.deleteEntryById(myId);
        return true;
    }

}
