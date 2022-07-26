package com.cloud.cqc.client.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.cqc.service.admin.service.IUserService;
import com.cloud.cqc.service.admin.vo.UserVO;
import com.cloud.cqc.client.common.controller.SecurityController;
import com.cloud.cqc.client.configure.password.PasswordEncoderHandler;
import com.cloud.cqc.client.configure.password.PasswordEncoderHandler.SaltPassword;
import com.cloud.cqc.client.security.model.SecurityPassword;
import com.cloud.cqc.client.security.service.CachingUserDetailsService;
import com.cloud.cqc.framework.mvc.http.RestEntity;

/**
 * <p>
 * 用户权限资源
 * </p>
 *
 * @author Joy.zhou
 *
 */
@RestController
@RequestMapping("/code")
public class CodeResourceController extends SecurityController {

    @Autowired
    private IUserService userService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoderHandler passwordEncoderHandler;

    /**
     * 获取用户资源信息
     *
     * @return
     */
    @RequestMapping(value = "/resource", method = RequestMethod.GET)
    public RestEntity<?> getResource(Authentication auth) {

        return resultOk(auth.getPrincipal());
    }

    /**
     * 修改用户资料
     *
     * @param user
     *            用户资料
     * @param auth
     *            当前登录用户
     * @return
     */
    @RequestMapping(value = "/resource", method = RequestMethod.PUT)
    public RestEntity<?> updateResource(@RequestBody UserVO vo) {
        vo.setId(getCurrentUser().getId());
        userService.update(vo);
        this.refreshCache();
        return resultOk();
    }

    /**
     * 修改用户密码
     *
     * @param user
     *            用户资料
     * @param auth
     *            当前登录用户
     * @return
     */
    @RequestMapping(value = "/password", method = RequestMethod.PUT)
    public RestEntity<?> updatePassword(@RequestBody SecurityPassword securityPassword) {
        SaltPassword saltPassword = passwordEncoderHandler.encodePassword(securityPassword.getPassword());
        UserVO vo = new UserVO();
        vo.setId(getCurrentUser().getId());
        vo.setSalt(saltPassword.getSalt());
        vo.setPassword(saltPassword.getPassword());
        userService.update(vo);
        this.refreshCache();
        return resultOk();
    }

    private void refreshCache() {
        // 更新缓存
        if (userDetailsService instanceof CachingUserDetailsService) {
            ((CachingUserDetailsService) userDetailsService).removeUserFromCache(getCurrentUser().getUsername());
        }
    }

}
