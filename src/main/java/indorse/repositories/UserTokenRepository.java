package indorse.repositories;

import indorse.model.UserToken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface UserTokenRepository extends JpaRepository<UserToken, Long>, PagingAndSortingRepository<UserToken, Long> {

    public UserToken findByTokenAndEndDateAfter(@Param("token") String token,@Param("endDate") Date endDate);

}
