package com.app.dualshare.repository;


import com.app.dualshare.dto.StoryResponseDTO;
import com.app.dualshare.model.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {

    Optional<Story> findStoriesByPublicId(String publicId);

    List<Story> findByUserFirebaseCode(String publicId);

    List<Story> findByExpiresAtBefore(LocalDateTime now);

}
