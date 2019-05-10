package com.example.config.shiro;

import com.example.model.Module;
import com.example.model.Role;
import com.example.model.User;
import com.example.service.UserServiceImpl;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 * <p>
 * TODO
 *
 * @author Administrator
 * @create 2019/5/5/0005 16:05
 * @since 1.0.0
 * 备注:写这段代码的时候，只有上帝和我知道它是干嘛的。现在，只有上帝知道。
 */
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private UserServiceImpl userService;

    /**
     * 授权
     * @param principal
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        ////获取session中的用户
        User user = (User) principal.fromRealm(this.getClass().getName()).iterator().next();
        List<String> permissions = new ArrayList<>();
        Set<Role> roles = user.getRoles();
        if (roles.size() > 0) {
            for (Role role : roles) {
                Set<Module> modules = role.getModules();
                if (modules.size() >0) {
                    for (Module module : modules) {
                        permissions.add(module.getMname());
                    }
                }
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissions);
        return info;
    }

    /**
     * 认证，登录
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken uToken = (UsernamePasswordToken) token;
        String username = uToken.getUsername();
        User user = new User();
        user.setUsername(username);
        List<User> userList = userService.selectByExample(user);
        if (userList.size() != 1) {
            throw new AuthenticationException("认证出错,用户名称为"+ username);
        }
        User u = userList.get(0);
        //放入shiro， 调用CredentialsMatcher 检验密码
        return new SimpleAuthenticationInfo(u, u.getPassword(), this.getClass().getName());
    }





}
