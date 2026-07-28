package com.app.dualshare.repository;

import com.app.dualshare.model.Story;
import com.app.dualshare.model.StoryRecipient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoryRecipientRepository extends JpaRepository<StoryRecipient, Long> {
    List<StoryRecipient> findByReceiverFirebaseUid(String firebaseUid);}
