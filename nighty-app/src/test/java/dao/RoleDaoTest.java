package dao;

/**
 * Created by chenchao on 17/4/19.
 */

import com.brokepal.nighty.sys.model.account.Role;
import com.brokepal.nighty.sys.persist.account.RoleDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:spring-context.xml"})
public class RoleDaoTest {
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private RoleDao dao;

    @Test
    public void getRole(){
        System.out.println(dao.get("20170326164710"));
    }

    @Test
    public void getRoleList(){
        List<Role> roles = dao.findList(new Role.Builder().name("admin").build());
        System.out.println("ll");
    }

}
