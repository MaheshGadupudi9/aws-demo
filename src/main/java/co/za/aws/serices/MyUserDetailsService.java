package co.za.aws.serices;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mahesh gadupudi
 * @project adaptris-regulatory-compliance
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    private Map<String, User>  userMap = new HashMap<>();

    public MyUserDetailsService() {
        userMap.put("foo",new User("foo","foo", new ArrayList<>()));
        userMap.put("boo",new User("boo","foo", new ArrayList<>()));
        userMap.put("rem",new User("rem","foo", new ArrayList<>()));
        userMap.put("ren",new User("ren","foo", new ArrayList<>()));
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userMap.get(userName);
    }
}
