package indorse.service.impl;

import indorse.model.Friend;
import indorse.model.User;
import indorse.repositories.FriendRepository;
import indorse.repositories.UserRepository;
import indorse.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private FriendRepository friendRepository;

    /**
     * Method to add a friend
     * @param friend
     * @param user
     * @return
     */

    @Transactional
    @Override
    public String addFriend(Friend friend, String user) {
        try {
            Friend friend1 = friendRepository.findUserByUserIdAndFriendId(friend.getUserId().getId(),friend.getFriendId().getId());
            if(friend1!=null){
                return "The friend is already aggregated!!";
            }

            friend.setCreatedBy(user);
            User user1=userRepository.findOne(friend.getUserId().getId());
            User user2=userRepository.findOne(friend.getFriendId().getId());
            friend.setUserId(user1);
            friend.setFriendId(user2);
            friendRepository.saveAndFlush(friend);
            return null;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Method to delete a friend
     * @param friend
     * @param user
     * @return
     */
    @Transactional
    @Override
    public String disableFriend(Friend friend, String user) {
        try {
            Friend friend1 = friendRepository.findOne(friend.getId());
            friend1.setDisabled(true);
            friend1.setUpdatedBy(user);
            friendRepository.saveAndFlush(friend);
            return null;
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
