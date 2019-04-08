package indorse.bean;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class FriendDTO {

    private Long id;

    @NotNull
    @Min(value = 1,message = "UserId Should not be less than 1")
    private Long userId;

    @NotNull
    @Min(value = 1,message = "FriendId Should not be less than 1")
    private Long friendId;

    public FriendDTO() {
    }

    public FriendDTO(Long id, Long userId, Long friendId) {
        this.id = id;
        this.userId = userId;
        this.friendId = friendId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
