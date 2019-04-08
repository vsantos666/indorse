package indorse.service.impl;

import indorse.bean.FriendDTO;
import indorse.model.Friend;
import indorse.model.User;
import indorse.repositories.FriendRepository;
import indorse.repositories.UserRepository;
import indorse.service.FriendService;
import indorse.utils.MapperUtil;
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
     * @param friendDTO
     * @param user
     * @return
     */

    @Transactional
    @Override
    public String addFriend(FriendDTO friendDTO, String user) {
        try {
            Friend friend = new Friend();
            Friend friend1 = friendRepository.findUserByUserIdAndFriendId(friendDTO.getUserId(),friendDTO.getFriendId());
            if(friend1!=null){
                return "The friend is already aggregated!!";
            }

            friend.setCreatedBy(user);
            User user1=userRepository.findById((long)friendDTO.getUserId());
            User user2=userRepository.findById((long)friendDTO.getFriendId());
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
    public String disableFriend(FriendDTO friend, String user) {
        try {
            Friend friend1 = friendRepository.findById((long)friend.getId());
            friend1.setDisabled(true);
            friend1.setUpdatedBy(user);
            friendRepository.saveAndFlush(friend1);
            return null;
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
