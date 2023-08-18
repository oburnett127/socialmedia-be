package com.oburnett127.socialmedia.service;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import com.oburnett127.socialmedia.model.Friend;
import com.oburnett127.socialmedia.model.FriendStatus;
import com.oburnett127.socialmedia.model.request.FriendStatusRequest;
import com.oburnett127.socialmedia.model.request.RequestFriendRequest;
import com.oburnett127.socialmedia.repository.FriendRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FriendService {
    private final FriendRepository friendRepository;

    public FriendService(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    @SneakyThrows
    public boolean getFriendStatus(FriendStatusRequest friendStatusRequest) {
        //System.out.println("getFriendStatus method start: loggedInUID: " + friendStatusRequest.getLoggedInUserId()
        // + " otherUID: " + friendStatusRequest.getOtherUserId());

        Optional<Friend> friend = friendRepository.findByFromUserIdAndToUserId(friendStatusRequest.getLoggedInUserId(), 
                                                                                    friendStatusRequest.getOtherUserId());
        if(friend.isPresent() && friend.get().getStatus() == FriendStatus.FRIEND) return true;
        else {
            Optional<Friend> friendOpt = friendRepository.findByFromUserIdAndToUserId(friendStatusRequest.getOtherUserId(), 
                                                                                    friendStatusRequest.getLoggedInUserId());
            if(friendOpt.isPresent() && friendOpt.get().getStatus() == FriendStatus.FRIEND) return true;
            else return false;
        }
    }

    public List<Integer> getFriendUserIds(int userId) {
        Set<Integer> friendUserIds = new HashSet<>();

        List<Friend> friendRecsFrom = friendRepository.findByFromUserId(userId);
        friendRecsFrom.stream()
                .filter(friend -> friend.getStatus() == FriendStatus.FRIEND)
                .forEach(friend -> friendUserIds.add(friend.getToUserId()));

        List<Friend> friendRecsTo = friendRepository.findByToUserId(userId);
        friendRecsTo.stream()
                .filter(friend -> friend.getStatus() == FriendStatus.FRIEND)
                .forEach(friend -> friendUserIds.add(friend.getFromUserId()));

        friendUserIds.remove(userId);

        return new ArrayList<>(friendUserIds);
    }


    @SneakyThrows
    public List<Integer> getOutgoingRequestsByUserId(int fromUserId) {
        List<Friend> friendRecs = friendRepository.findByFromUserId(fromUserId);

        return  friendRecs.stream()
                    .filter(friend -> friend.getStatus() == FriendStatus.PENDING)
                    .map(friend -> friend.getToUserId())
                    .collect(Collectors.toList());
    }

    @SneakyThrows
    public List<Integer> getIncomingRequestsByUserId(int toUserId) {
        List<Friend> friendRecs = friendRepository.findByToUserId(toUserId);

        return  friendRecs.stream()
                    .filter(friend -> friend.getStatus() == FriendStatus.PENDING)
                    .map(friend -> friend.getFromUserId())
                    .collect(Collectors.toList());
    }

    @SneakyThrows
    public void requestFriend(Friend friend) {
        friendRepository.save(friend);   
    }

    @SneakyThrows
    public void acceptFriend(RequestFriendRequest requestFriendRequest) {
        int fromUserId = requestFriendRequest.getFromUserId();
        int toUserId = requestFriendRequest.getToUserId();
        Optional<Friend> friendOpt = friendRepository.findByFromUserIdAndToUserId(fromUserId, toUserId);
        if(friendOpt.isPresent()) {
            Friend friend = friendOpt.get();
            friend.setStatus(FriendStatus.FRIEND);
            friendRepository.save(friend);
        }
    }

    @SneakyThrows
    public void deleteFriend(int userId1, int userId2) {
        Optional<Friend> friendOpt = friendRepository.findByFromUserIdAndToUserId(userId1, userId2);

        if (friendOpt.isPresent()) {
            friendRepository.delete(friendOpt.get());
        } else {
            friendOpt = friendRepository.findByFromUserIdAndToUserId(userId2, userId1);
            if (friendOpt.isPresent()) {
                friendRepository.delete(friendOpt.get());
            }
        }
    }
}