package com.cloud.cqc.service.admin;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.cloud.cqc.framework.core.utils.RandomUtils;
import com.cloud.cqc.service.CQCServiceTest;
import com.cloud.cqc.service.admin.service.IUserService;
import com.cloud.cqc.service.admin.vo.UserVO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CQCServiceTest.class, value = { "spring.profiles.active=dev" })
public class UserServiceTest {

	@Autowired
	private IUserService userService;

	private Long id;

	/**
	 * 测试插入
	 */
	@Test
	@Ignore
	public void insert() {

		UserVO entity = new UserVO();
		entity.setUsername("userName");
		entity.setPassword("password");
		entity.setSalt(RandomUtils.randomNumbers(6));

		userService.insert(entity);

		id = entity.getId();

		Assert.notNull(entity.getId(), "the id must not be null!");

	}

	/**
	 * 查询
	 */
	@Test
	@Ignore
	public void select() {
		userService.selectList(null);
	}

	/**
	 * 删除
	 */
	@Test
	@Ignore
	public void delete() {

		userService.deleteById(id);

	}

}
