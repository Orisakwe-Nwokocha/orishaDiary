package africa.semicolon.orishaDiary.controllers;

import africa.semicolon.orishaDiary.dtos.requests.*;
import africa.semicolon.orishaDiary.exceptions.DiaryAppException;
import africa.semicolon.orishaDiary.exceptions.InvalidArgumentException;
import africa.semicolon.orishaDiary.services.DiaryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


import java.util.List;

@RestController
public class DiaryController {
    @Autowired
    private DiaryServices diaryServices;

    @PostMapping("/register")
    public String registerUser(@RequestBody RegisterRequest request) {
        try {
            diaryServices.register(request);
            return "registration successful";
        }
        catch (DiaryAppException e) {
            return e.getMessage();
        }
    }

    @PatchMapping("/login")
    public String login(@Valid @RequestBody LoginRequest request) {
        try {
            validateUsername(request.getUsername());

            diaryServices.login(request);
            return "login successful";
        }
        catch (DiaryAppException e) {
            return e.getMessage();
        }
    }

    @PatchMapping("/logout/{name}")
    public String logout(@PathVariable("name") String username) {
        try {
            diaryServices.logout(username);
            return "logout successful";
        }
        catch (DiaryAppException e) {
            return e.getMessage();
        }
    }

    @PatchMapping("/updatePassword")
    public String updatePassword(@Validated @RequestBody UpdatePasswordRequest request) {
        try {
            validateUsername(request.getUsername());

            diaryServices.updatePassword(request);
            return "password update successful";
        }
        catch (DiaryAppException e) {
            return e.getMessage();
        }
    }

    @DeleteMapping("/deregister")
    public String deregisterUserWith(@Valid @RequestBody DeregisterRequest request) {
        try {
            validateUsername(request.getUsername());

            diaryServices.deregister(request);
            return "removed successfully";
        }
        catch (DiaryAppException e) {
            return e.getMessage();
        }
    }

    @PostMapping("createEntry")
    public String createEntry(@Valid @RequestBody CreateEntryRequest createEntryRequest) {
        try {
            validateAuthor(createEntryRequest.getAuthor());

            diaryServices.createEntryWith(createEntryRequest);
            return "created successfully";
        }
        catch (DiaryAppException e) {
            return e.getMessage();
        }
    }

    @PatchMapping("updateEntry")
    public String updateEntry(@RequestBody @Valid UpdateEntryRequest updateEntryRequest) {
        try {
            validateAuthor(updateEntryRequest.getAuthor());
            if (updateEntryRequest.getId() == null) throw new InvalidArgumentException("Entry id is null");

            diaryServices.updateEntryWith(updateEntryRequest);
            return "updated successfully";
        }
        catch (DiaryAppException e) {
            return e.getMessage();
        }
    }

    @DeleteMapping("deleteEntry/{entryId}")
    public String deleteEntryBy(@PathVariable("entryId") String id, @RequestParam(name = "username", defaultValue = "") String username) {
        try {
            diaryServices.deleteEntry(id, username);
            return "deleted successfully";
        }
        catch (DiaryAppException e) {
            return e.getMessage();
        }
    }

    @GetMapping("getEntry/{entryId}")
    public String getEntryBy(@PathVariable("entryId") String id, @RequestParam(name = "username", defaultValue = "") String username) {
        try {
            return diaryServices.getEntry(id, username).toString();
        }
        catch (DiaryAppException e) {
            return e.getMessage();
        }
    }

    @GetMapping("/getEntriesFor/{author}")
    public List<?> getEntriesFor(@PathVariable("author") String username) {
        try {
            return diaryServices.getEntriesFor(username);
        }
        catch (DiaryAppException e) {
            return List.of(e.getMessage());
        }
    }

    private static void validateUsername(String username) {
        if (username == null) throw new InvalidArgumentException("Username cannot be null");
    }

    private static void validateAuthor(String author) {
        if (author == null) throw new InvalidArgumentException("Author cannot be null");
    }
}
