package com.brokepal.nighty.sys.service.account;

import com.brokepal.nighty.core.cache.Cache;
import com.brokepal.nighty.core.util.CommonUtil;
import com.brokepal.nighty.core.util.SendEmail;
import com.brokepal.nighty.sys.constant.account.AccountConstant;
import com.brokepal.nighty.sys.model.account.*;
import com.brokepal.nighty.sys.persist.account.*;
import com.brokepal.nighty.sys.storage_object.SendEmailInfo;
import com.sun.mail.smtp.SMTPAddressFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/2/23.
 */
@Service
@Transactional(propagation=Propagation.REQUIRES_NEW,readOnly=false, timeout=3)
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private AuthorityDao authorityDao;
    @Autowired
    private RoleAuthorityDao roleAuthorityDao;
    @Autowired
    private RoleUserDao roleUserDao;

    /**
     * 创建用户
     * @param user
     * @return int 成功则返回1，失败则返回0
     * @throws IllegalArgumentException 参数user不能为null，user的nickname、username,password,email都不能为null
     */
    public int createUser(User user) {
        if (user == null)
            throw new IllegalArgumentException("user can't be null");
        if (user.getNickname() == null || user.getUsername() == null || user.getPassword() == null || user.getEmail() == null || user.getSalt() == null)
            throw new IllegalArgumentException("nickname,username,password,email and salt can't be null");
        if (user.getPhone() == null)
            user.setPhone("");
        try {
            int count = userDao.createUser(user);
            for (Role role : user.getRoles()){
                roleUserDao.createRoleUser(new RoleUser.Builder().roleId(role.getId()).userId(user.getId()).build());
            }
            if (count > 0){
                System.out.println("success");
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 根据邮箱修改用户密码
     * @param email
     * @param password
     * @return int 成功则返回1，失败则返回0
     */
    public int updatePasswordByEmail(String email, String password) {
        User user = new User.Builder().email(email).password(password).build();
        return userDao.updatePassword(user);
    }

    /**
     * 根据用户名查询用户
     * @param username
     * @return User 如果查询到结果，则返回，否则，返回null
     * @throws IllegalArgumentException username不能为null或空字符串
     */
    public User getUserByUsername(String username) {
        if (username == null || "".equals(username))
            throw new IllegalArgumentException("username can't be null or empty");
        try {
            User user = userDao.getUserByUsername(username);
            putRoleAndAuthorityToUser(user);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据邮箱查询用户
     * @param emial
     * @return User 如果查询到结果，则返回，否则，返回null
     * @throws IllegalArgumentException username不能为null或空字符串
     */
    public User getUserByEmail(String emial) {
        if (emial == null || "".equals(emial))
            throw new IllegalArgumentException("emial can't be null or empty");
        try {
            User user = userDao.getUserByEmail(emial);
            putRoleAndAuthorityToUser(user);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Role> generateDefaultRoles(){
        return roleDao.findList(new Role.Builder().name("guest").build());
    }

    /**
     * 发送重置密码的验证码
     * @param email
     * @return 如果发送成功，则返回验证码，否则返回null
     */
    public String sendValidateCodeEmial(String email) {
        String validateCode = CommonUtil.createRandomString(6);
        ///邮件的内容
        String content = "请在15分钟内输入以下验证码进行重置密码： " + validateCode;
        //发送邮件
        try {
            SendEmail.send(email, content, "重置密码邮件");
            return validateCode;
        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public SendEmailInfo getSendEmailInfo(String email){
        return Cache.get(AccountConstant.RESET_PASSWORD_NAMESPACE, email);
    }

    public void setSendEmailInfo(String email, SendEmailInfo info){
        Cache.put(AccountConstant.RESET_PASSWORD_NAMESPACE, email,info);
    }

    private void putRoleAndAuthorityToUser(User user){
        //获取roles
        List<Role> roles = new ArrayList<Role>();
        List<RoleUser> roleUsers = roleUserDao.findList(new RoleUser.Builder().userId(user.getId()).build());
        for (RoleUser roleUser : roleUsers) {
            roles.add(roleDao.get(roleUser.getRoleId()));
        }
        user.setRoles(roles);
        //获取权限
        Set<Authority> authoritys = new HashSet<Authority>();
        for (Role role : roles){
            List<RoleAuthority> roleAuthoritys = roleAuthorityDao.findList(new RoleAuthority.Builder().roleId(role.getId()).build());
            for (RoleAuthority roleAuthority : roleAuthoritys) {
                authoritys.add(authorityDao.get(roleAuthority.getAuthorityId()));
            }
        }
        user.setAuthorities(new ArrayList<Authority>(authoritys));
    }



    /******************************** 以下为管理员对应的业务方法 ************************************/

    public List<User> findList(User filterEntity){
        return userDao.findList(filterEntity);
    }
}
