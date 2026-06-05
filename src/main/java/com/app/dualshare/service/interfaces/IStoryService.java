package com.app.dualshare.service.interfaces;

import com.app.dualshare.dto.StoryResponseDTO;
import com.app.dualshare.model.Story;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IStoryService {

    StoryResponseDTO uploadStory(String firebaseUid, MultipartFile file);

    List<StoryResponseDTO> getStories(String firebaseUid);

    void deleteStory(String firebaseUid, String publicId);

    void expiredStories();

}
