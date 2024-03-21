package africa.semicolon.orishaDiary.controllers;

import africa.semicolon.orishaDiary.dtos.requests.*;
import africa.semicolon.orishaDiary.exceptions.DiaryAppException;
import africa.semicolon.orishaDiary.services.DiaryServices;
import africa.semicolon.orishaDiary.services.DiaryServicesImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DiaryController {
    private final DiaryServices diaryServices = new DiaryServicesImpl();

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
    public String login(@RequestBody LoginRequest request) {
        try {
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

    @PostMapping("/deregister")
    public String deregisterUserWith(@RequestBody RemoveUserRequest request) {
        try {
            diaryServices.deregister(request);
            return "removed successfully";
        }
        catch (DiaryAppException e) {
            return e.getMessage();
        }
    }

    @PostMapping("createEntry")
    public String createEntry(@RequestBody CreateEntryRequest createEntryRequest) {
        try {
            diaryServices.createEntryWith(createEntryRequest);
            return "created successfully";
        }
        catch (DiaryAppException e) {
            return e.getMessage();
        }
    }

    public String updateEntry(UpdateEntryRequest request) {
        try {
            diaryServices.updateEntryWith(request);
            return "updated successfully";
        }
        catch (DiaryAppException e) {
            return e.getMessage();
        }
    }

    public String deleteEntryBy(int id, String username) {
        try {
            diaryServices.deleteEntry(id, username);
            return "deleted successfully";
        }
        catch (DiaryAppException e) {
            return e.getMessage();
        }
    }

    public String getEntryBy(int id, String username) {
        try {
            return String.valueOf(diaryServices.getEntry(id, username));
        }
        catch (DiaryAppException e) {
            return e.getMessage();
        }
    }

    @GetMapping("/diary/{username}")
    public List<?> getEntriesFor(@PathVariable String username) {
        try {
            return diaryServices.getEntriesFor(username);
        }
        catch (DiaryAppException e) {
            return List.of(e.getMessage());
        }
    }
}
