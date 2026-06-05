package com.app.dualshare.service.impl;

import com.app.dualshare.dto.CloudinaryResponseDTO;
import com.app.dualshare.dto.StoryResponseDTO;
import com.app.dualshare.enums.MediaType;
import com.app.dualshare.exceptions.CloudinaryServiceException;
import com.app.dualshare.exceptions.EmptyStoryListException;
import com.app.dualshare.exceptions.NotPermissionDeleteException;
import com.app.dualshare.exceptions.UserNotFoundByUidException;
import com.app.dualshare.mapper.StoryMapper;
import com.app.dualshare.model.Story;
import com.app.dualshare.model.User;
import com.app.dualshare.repository.StoryRepository;
import com.app.dualshare.repository.UserRepository;
import com.app.dualshare.service.interfaces.ICloudinaryService;
import com.app.dualshare.service.interfaces.IStoryService;
import com.cloudinary.utils.ObjectUtils;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class StoryServiceImpl implements IStoryService {

    private final StoryRepository storyRepository;
    private final ICloudinaryService cloudinaryService;
    private final UserRepository userRepository;

    private final StoryMapper storyMapper;

    public StoryServiceImpl(StoryRepository storyRepository,
                            ICloudinaryService cloudinaryService,
                            UserRepository userRepository,
                            StoryMapper storyMapper
    ) {
        this.storyRepository = storyRepository;
        this.cloudinaryService = cloudinaryService;
        this.userRepository = userRepository;
        this.storyMapper = storyMapper;
    }

    @Transactional
    @Override
    public StoryResponseDTO uploadStory(String firebaseUid, MultipartFile file) {

        User user = userRepository.findByFirebaseCode(firebaseUid)
                .orElseThrow(() -> new UserNotFoundByUidException(firebaseUid));

        CloudinaryResponseDTO cloudinaryResponseDTO = cloudinaryService.uploadFile(file);


        Story story = new Story();
        story.setUser(user);
        story.setPublicId(cloudinaryResponseDTO.getPublicId());
        story.setMediaUrl(cloudinaryResponseDTO.getUrl());
        story.setMediaType(cloudinaryResponseDTO.getResourceType()
                .equalsIgnoreCase("video") ? MediaType.VIDEO : MediaType.PHOTO);
        story.setCreatedAt(LocalDateTime.now());
        story.setExpiresAt(LocalDateTime.now().plusHours(24));

        storyRepository.save(story);

        return storyMapper.toDTO(story);
    }

    @Override
    public List<StoryResponseDTO> getStories(String firebaseUid) {

        User user = userRepository.findByFirebaseCode(firebaseUid)
                .orElseThrow(() -> new UserNotFoundByUidException(firebaseUid));

        List<Story> stories = storyRepository.findByUserFirebaseCode(user.getFirebaseCode());

        if (stories.isEmpty()) {
            throw new EmptyStoryListException(firebaseUid);
        }

        return storyMapper.toDTOList(stories);
    }

    @Override
    public void deleteStory(String firebaseUid, String publicId) {

        Story story = storyRepository.findStoriesByPublicId(publicId)
                .orElseThrow(() -> new UserNotFoundByUidException(publicId));

        if (!story.getUser().getFirebaseCode().equals(firebaseUid)) {
            throw new NotPermissionDeleteException(firebaseUid);
        }

        cloudinaryService.deletedFile(publicId, story.getMediaType().name().toLowerCase());

        storyRepository.delete(story);

    }

    @Scheduled(fixedRate = 60000)
    @Override
    public void expiredStories() {

        List<Story> expiredStories = storyRepository
                .findByExpiresAtBefore(LocalDateTime.now());

        for (Story story : expiredStories) {

            try {
                String resourceType = story.getMediaType().name().toLowerCase();

                cloudinaryService.deletedFile(story.getPublicId(), resourceType);

                storyRepository.delete(story);
            } catch (
                    CloudinaryServiceException e) {
                System.out.printf("Cloudinary service exception: %s%n", e.getMessage());
            }
        }

    }
}
