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
 * Security ??????
 * 
 * @author joy.zhou
 * @date 2017???9???1???
 * @version 1.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties(JwtProperties.class)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	static final String[] STATIC_URLS = new String[] { "/js/**", "/css/**", "/images/**", "/**/favicon.ico" };

	/** ?????????????????? */
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
				// ??????cache??????????????????10????????????????????????
				.initialCapacity(10)
				// ??????????????????5?????????????????????????????????5????????????cache??????????????????
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
				// ??????????????????JWT????????????????????????csrf
				.csrf().disable().exceptionHandling().authenticationEntryPoint(new JwtAuthenticationEntryPoint()).and()
				// ??????X-Frame-Options
				.headers().frameOptions().disable().and()
				// ??????token??????????????????session
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				//
				.antMatchers(HttpMethod.OPTIONS).permitAll()
				// ????????????????????????????????????????????????
				.antMatchers(HttpMethod.GET, STATIC_URLS).permitAll()
				// ????????????token???rest api?????????????????????
				.antMatchers(jwtProperties.getAuthPath()).permitAll()
				// ???????????????url
				.antMatchers(AppConst.SECURITY_INGORE_URLS).permitAll()
				// ???????????????????????????????????????????????????
				.anyRequest().authenticated();

		// ??????JWT filter
		httpSecurity.addFilterAt(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

	}
}
