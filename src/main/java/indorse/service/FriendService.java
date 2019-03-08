package indorse.service;

import indorse.model.Friend;

public interface FriendService {

    public String addFriend(Friend friend, String user);

    public String disableFriend(Friend friend, String user);
}
