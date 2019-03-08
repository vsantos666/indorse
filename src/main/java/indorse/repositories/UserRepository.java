package indorse.repositories;

import indorse.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by vsantos on 7/03/2019.
 */
public interface UserRepository extends JpaRepository<indorse.model.User, Long>,PagingAndSortingRepository<indorse.model.User, Long> {


    public List<User> findAllByDisabled(@Param("disabled") boolean disabled, Pageable pageable);

    public List<User> findAllByDisabledAndNameContains(@Param("disabled") boolean disabled, @Param("name") String name);

    public List<User> findAllByLoginAndPassword(@Param("login") String login,@Param("password") String password);



}
