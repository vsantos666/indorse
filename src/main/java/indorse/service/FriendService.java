package indorse.service;

import indorse.bean.FriendDTO;
import indorse.model.Friend;

public interface FriendService {

    public String addFriend(FriendDTO friend, String user);

    public String disableFriend(FriendDTO friend, String user);
}
