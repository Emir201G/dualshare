package com.app.dualshare.service.interfaces;

import com.app.dualshare.dto.StoryResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IStoryService {

    StoryResponseDTO uploadStory(String firebaseUid, MultipartFile file,String shareCode);

    List<StoryResponseDTO> getMyStories(String firebaseUid);
    List<StoryResponseDTO> getStoriesRecipient(String firebaseUid);

    void deleteStory(String firebaseUid, String publicId);

    void expiredStories();



}
