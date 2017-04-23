package dao;

/**
 * Created by chenchao on 17/4/19.
 */

import com.brokepal.nighty.sys.model.account.RoleAuthority;
import com.brokepal.nighty.sys.persist.account.RoleAuthorityDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:spring-context.xml"})
public class RoleAuthorityDaoTest {
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private RoleAuthorityDao dao;

    @Test
    public void getRoleList(){
        List<RoleAuthority> roleAuthorities = dao.findList(new RoleAuthority.Builder().build());
        System.out.println("ll");
    }

}
