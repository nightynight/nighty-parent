package dao;

/**
 * Created by chenchao on 17/4/19.
 */

import com.brokepal.nighty.sys.model.account.RoleAuthority;
import com.brokepal.nighty.sys.model.account.RoleUser;
import com.brokepal.nighty.sys.persist.account.RoleAuthorityDao;
import com.brokepal.nighty.sys.persist.account.RoleUserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:spring-context.xml"})
public class RoleUserDaoTest {
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private RoleUserDao dao;

    @Test
    public void getRoleList(){
        List<RoleUser> roleUsers = dao.findList(new RoleUser.Builder().id("6b4ff786-401c-40f4-a439-87c9decabd89").build());
        System.out.println("ll");
    }

}
