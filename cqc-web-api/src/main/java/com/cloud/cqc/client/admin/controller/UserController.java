package com.cloud.cqc.client.admin.controller;

import javax.validation.Valid;

import com.alibaba.druid.support.json.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.cqc.client.admin.model.PasswordModel;
import com.cloud.cqc.client.common.controller.CRUDController;
import com.cloud.cqc.client.configure.password.PasswordEncoderHandler;
import com.cloud.cqc.client.configure.password.PasswordEncoderHandler.SaltPassword;
import com.cloud.cqc.client.security.service.CachingUserDetailsService;
import com.cloud.cqc.framework.mvc.http.RestEntity;
import com.cloud.cqc.service.admin.entity.User;
import com.cloud.cqc.service.admin.searchable.UserSearchable;
import com.cloud.cqc.service.admin.service.IUserService;
import com.cloud.cqc.service.admin.vo.UserVO;

/**
 * <p>
 * 用户 controller
 * </p>
 *
 * @author joy.zhou
 * @version 1.0
 * @date 2017年8月31日
 */
@RestController
@RequestMapping("/admin/user")
public class UserController extends CRUDController<User, UserVO, UserSearchable, IUserService> {

    @Autowired
    private PasswordEncoderHandler passwordEncoderHandler;
    @Autowired
    private UserDetailsService userDetailsService;
    @Value("${app.init-password}")
    private String initPassword;

    /**
     * 新增
     *
     * @return
     */
    //@Override
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public RestEntity<?> saveA(@Valid @RequestBody UserVO vo) {
        SaltPassword saltPassword = passwordEncoderHandler.encodePassword(initPassword);
        vo.setSalt(saltPassword.getSalt());
        vo.setPassword(saltPassword.getPassword());
        baseService.insert(vo);
        return resultOk(JSONUtils.toJSONString(vo));
    }

    /**
     * 修改
     *
     * @param id ID
     * @return
     */
    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public RestEntity<?> update(@PathVariable Long id, @RequestBody UserVO vo) {
        vo.setId(id);
        baseService.update(vo);
        // 更新缓存
        if (userDetailsService instanceof CachingUserDetailsService) {
            ((CachingUserDetailsService) userDetailsService)
                    .removeUserFromCache(baseService.select(vo.getId()).getUsername());
        }
        return resultOk();
    }

    /**
     * 修改用户密码
     *
     * @param vo 用户资料
     * @return
     */
    @RequestMapping(value = "/password", method = RequestMethod.PUT)
    public RestEntity<?> updatePassword(@RequestBody PasswordModel model) {
        SaltPassword saltPassword = passwordEncoderHandler.encodePassword(model.getPassword());
        UserVO vo = new UserVO();
        vo.setId(model.getId());
        vo.setSalt(saltPassword.getSalt());
        vo.setPassword(saltPassword.getPassword());
        baseService.update(vo);
        // 更新缓存
        if (userDetailsService instanceof CachingUserDetailsService) {
            ((CachingUserDetailsService) userDetailsService)
                    .removeUserFromCache(baseService.select(vo.getId()).getUsername());
        }
        return resultOk();
    }

    /**
     * 根据用户名获取角色
     *
     * @param staffNo 员工编号
     */
    @RequestMapping(value = "roles/{username}", method = RequestMethod.GET)
    public RestEntity<?> getRoles(@PathVariable String username) {
        return resultOk(baseService.selectUserRoles(username));
    }

}
