package indorse.security;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by vsantos on 7/03/2019.
 */
@Component
public class AuthSecurityAuditorAware implements AuditorAware<String> {

    public static final String SYSTEM_ACCOUNT = "system";

    @Override
    public Optional<String> getCurrentAuditor() {
//        String userName = SecurityUtils.getCurrentLogin();
        String userName = null;
        //return (userName != null ? userName : SYSTEM_ACCOUNT);
        return  null;
    }
}