package com.cloud.cqc.client.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cloud.cqc.client.common.constant.AppConst;
import com.cloud.cqc.client.configure.password.PasswordEncoderHandler;
import com.cloud.cqc.client.configure.security.JwtAuthenticationEntryPoint;
import com.cloud.cqc.client.configure.security.JwtProperties;
import com.cloud.cqc.client.configure.security.JwtTokenUtil;
import com.cloud.cqc.client.configure.security.filter.JwtAuthenticationTokenFilter;
import com.cloud.cqc.client.security.service.AuthUserDetailsService;
import com.cloud.cqc.client.security.service.CachingUserDetailsService;
import com.cloud.cqc.client.security.service.GuavaUserCache;
import com.google.common.cache.CacheBuilder;

/**
 * 
 * Security 配置
 * 
 * @author joy.zhou
 * @date 2017年9月1日
 * @version 1.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties(JwtProperties.class)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	static final String[] STATIC_URLS = new String[] { "/js/**", "/css/**", "/images/**", "/**/favicon.ico" };

	/** 盐噪声属性名 */
	static final String PROPERTY_NAME_SALT = "salt";

	@Autowired
	private JwtProperties jwtProperties;

	@Bean
	public JwtTokenUtil jwtTokenUtil() {
		return new JwtTokenUtil();
	}

	protected UserCache guavaUserCache() {
		GuavaUserCache userCache = new GuavaUserCache();
		// @formatter:off
		userCache.setCache(CacheBuilder.newBuilder()
				// 设置cache的初始大小为10，要合理设置该值
				.initialCapacity(10)
				// 设置并发数为5，即同一时间最多只能有5个线程往cache执行写入操作
				.concurrencyLevel(5).build());
		// @formatter:on
		return userCache;
	}

	@Bean
	public AuthUserDetailsService authUserDetailsService() {
		return new AuthUserDetailsService();
	}

	@Bean
	@Autowired
	public UserDetailsService userDetailsService() {
		CachingUserDetailsService userDetailsService = new CachingUserDetailsService(authUserDetailsService());
		userDetailsService.setUserCache(guavaUserCache());

		return userDetailsService;
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public JwtAuthenticationTokenFilter authenticationTokenFilterBean() {
		return new JwtAuthenticationTokenFilter();
	}

	@Bean
	public MessageDigestPasswordEncoder passwordEncoder() {
		return new Md5PasswordEncoder();
	}

	@Bean
	public PasswordEncoderHandler passwordEncoderHandler() {
		return new PasswordEncoderHandler(passwordEncoder());
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder())
				.addObjectPostProcessor(new ObjectPostProcessor<DaoAuthenticationProvider>() {
					public <O extends DaoAuthenticationProvider> O postProcess(O object) {
						ReflectionSaltSource saltSource = new ReflectionSaltSource();
						saltSource.setUserPropertyToUse(PROPERTY_NAME_SALT);
						object.setSaltSource(saltSource);
						return object;
					}
				});
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				// 由于使用的是JWT，我们这里不需要csrf
				.csrf().disable().exceptionHandling().authenticationEntryPoint(new JwtAuthenticationEntryPoint()).and()
				// 关闭X-Frame-Options
				.headers().frameOptions().disable().and()
				// 基于token，所以不需要session
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				//
				.antMatchers(HttpMethod.OPTIONS).permitAll()
				// 允许对于网站静态资源的无授权访问
				.antMatchers(HttpMethod.GET, STATIC_URLS).permitAll()
				// 对于获取token的rest api要允许匿名访问
				.antMatchers(jwtProperties.getAuthPath()).permitAll()
				// 忽略验证的url
				.antMatchers(AppConst.SECURITY_INGORE_URLS).permitAll()
				// 除上面外的所有请求全部需要鉴权认证
				.anyRequest().authenticated();

		// 添加JWT filter
		httpSecurity.addFilterAt(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

	}
}
