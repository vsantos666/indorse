package indorse.repositories;

import indorse.model.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface FriendRepository extends JpaRepository<Friend, Long>, PagingAndSortingRepository<Friend, Long> {

    @Query("SELECT a FROM Friend a WHERE a.userId.id = :userId and a.friendId.id = :friendId ")
    public Friend findUserByUserIdAndFriendId(@Param("userId") Long userId, @Param("friendId") Long friendId);

}
