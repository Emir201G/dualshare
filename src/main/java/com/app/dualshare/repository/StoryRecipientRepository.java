package com.app.dualshare.repository;

import com.app.dualshare.model.StoryRecipient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryRecipientRepository extends JpaRepository<StoryRecipient, Long> {
}
