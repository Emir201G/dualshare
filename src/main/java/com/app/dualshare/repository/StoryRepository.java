package com.app.dualshare.repository;


import com.app.dualshare.model.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {

    Optional<Story> findStoriesByPublicId(String publicId);
}
