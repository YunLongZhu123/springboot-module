package com.springboot.demo.config;

import com.springboot.demo.bean.UserBean;
import com.springboot.demo.service.PermissionService;
import com.springboot.demo.service.RoleMangeService;
import com.springboot.demo.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleMangeService roleMangeService;

    @Autowired
    private PermissionService permissionService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String)principals.getPrimaryPrincipal();


        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        SimpleAuthenticationInfo authenticationInfo =null;
        if(token instanceof UsernamePasswordToken) {
            UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
            String username = usernamePasswordToken.getUsername();
            //根据用户名获取用户信息
            UserBean userBean = userService.findByUserName(username);
            if(userBean == null) {
                throw new UnknownAccountException();//没找到用户
            }
            if(userBean.getStatus().intValue() == 1) {
                throw new LockedAccountException();//账号锁定
            }

           authenticationInfo = new SimpleAuthenticationInfo(username,userBean.getPassword(), ByteSource.Util.bytes(userBean.getPasswordSalt()),getName());
        }
        return authenticationInfo;
    }
}
