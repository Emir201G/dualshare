package com.app.dualshare.repository;

import com.app.dualshare.enums.RequestStatus;
import com.app.dualshare.model.FriendRequest;
import com.app.dualshare.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {

    boolean existsBySenderAndReceiverAndStatus(User sender, User receiver, RequestStatus status);

    Optional<FriendRequest> findBySenderAndReceiver(User sender, User receiver);

}
