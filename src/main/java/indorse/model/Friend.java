package indorse.model;

import indorse.model.base.BaseEntityAudit;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="FRIEND",schema = "indorse")
@XmlRootElement
public class Friend extends BaseEntityAudit {

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User userId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "FRIEND_ID", nullable = false)
    private User friendId;

    public Friend() {
    }

    public Friend(User userId, User friendId) {
        this.userId = userId;
        this.friendId = friendId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public User getFriendId() {
        return friendId;
    }

    public void setFriendId(User friendId) {
        this.friendId = friendId;
    }
}
