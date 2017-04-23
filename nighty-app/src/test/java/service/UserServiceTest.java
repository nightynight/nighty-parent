package service;

import com.brokepal.nighty.sys.model.account.User;
import com.brokepal.nighty.sys.service.account.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Administrator on 2016/9/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:spring-context.xml"})
public class UserServiceTest {
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private UserService service;

    @Test
    public void getUser(){
        System.out.println(service.getUserByUsername("aa"));
    }

    @Test
    public void getUserByEmail(){
        System.out.println(service.getUserByEmail("nightynight_cc@163.com"));
    }

    @Test
    public void createUser(){
        User user = new User.Builder().nickname("gg").username("gg").password("gg").email("gg").salt("gg").build();
        service.createUser(user);
    }

    @Test
    public void getUsers(){
        User user = new User.Builder().nickname("aa").build();
        List<User> users = service.findList(user);
        System.out.println(users);
    }
}
