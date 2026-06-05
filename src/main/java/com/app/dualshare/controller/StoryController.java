package com.app.dualshare.controller;

import com.app.dualshare.dto.StoryResponseDTO;
import com.app.dualshare.service.interfaces.IStoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/stories")
public class StoryController {

    private final IStoryService storyService;

    @Autowired
    public StoryController(IStoryService storyService) {
        this.storyService = storyService;
    }

    @PostMapping("/upload-story")
    public ResponseEntity<StoryResponseDTO> uploadStory(@RequestParam("storyFile") MultipartFile storyFile,
                                                        Authentication authentication) {

        String firebaseUid = authentication.getName();

        StoryResponseDTO storyResponseDTO = storyService.uploadStory(firebaseUid, storyFile);

        return ResponseEntity.ok(storyResponseDTO);
    }

    @DeleteMapping("/delete-story/{publicId}")
    public ResponseEntity<String> deleteStory(@PathVariable String publicId,
                                              Authentication authentication) {

        String firebaseUid = authentication.getName();
        storyService.deleteStory(firebaseUid, publicId);

        return ResponseEntity.ok("Story delete");
    }

    @GetMapping("/all-stories")
    public ResponseEntity<List<StoryResponseDTO>> getAllStories(Authentication authentication) {

        String firebaseUid = authentication.getName();

        return ResponseEntity.ok(storyService.getStories(firebaseUid));
    }
}
