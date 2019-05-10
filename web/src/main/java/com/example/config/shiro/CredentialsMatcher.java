package com.example.config.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 * <p>
 * TODO
 *
 * @author Administrator
 * @create 2019/5/5/0005 16:21
 * @since 1.0.0
 * 备注:写这段代码的时候，只有上帝和我知道它是干嘛的。现在，只有上帝知道。
 */
public class CredentialsMatcher extends SimpleCredentialsMatcher{

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {

        UsernamePasswordToken uToken = (UsernamePasswordToken) token;
        //获得用户输入的密码（可以采用加盐(salt)的方式去检验）
        String inPassword = new String(uToken.getPassword());
        //获得数据库中的密码
        String dbPassword = (String) info.getCredentials();
        //进行密码的对比
        return this.equals(inPassword, dbPassword);

    }
}
