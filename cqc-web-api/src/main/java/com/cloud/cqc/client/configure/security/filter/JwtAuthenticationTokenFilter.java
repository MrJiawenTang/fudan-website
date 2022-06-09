package com.cloud.cqc.client.configure.security.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cloud.cqc.client.common.constant.AppConst;
import com.cloud.cqc.client.configure.security.JwtProperties;
import com.cloud.cqc.client.configure.security.JwtTokenUtil;
import com.cloud.cqc.framework.core.utils.MessageTools;

import io.jsonwebtoken.JwtException;

/**
 *
 * <p>
 * jwt 校验过滤器
 * </p>
 *
 * @author joy.zhou
 * @date 2017年8月31日
 * @version 1.0
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private JwtProperties jwtProperties;
	/** token异常 */
	static final String ERROR_TOKEN = "auth.token.error";
	/** token过期 */
	static final String ERROR_EXPIRED = "auth.token.expire";

	private List<AntPathRequestMatcher> requestMatchers = new ArrayList<>();

	@Override
	protected void initFilterBean() throws ServletException {
		requestMatchers.add(new AntPathRequestMatcher(jwtProperties.getAuthPath()));
		for (String ingoreUrl : AppConst.SECURITY_INGORE_URLS) {
			requestMatchers.add(new AntPathRequestMatcher(ingoreUrl));
		}
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

        if (!"OPTIONS".equals(request.getMethod()) && !this.antMatchers(request)) {
            String requestHeader = request.getHeader(this.jwtProperties.getHeader());
            if (requestHeader != null && requestHeader.startsWith(this.jwtProperties.getTokenHeader())) {
                String authToken = requestHeader.substring(this.jwtProperties.getTokenHeader().length());

                try {
                    if (this.jwtTokenUtil.isTokenExpired(authToken)) {
                        response.sendError(401, "auth.token.expire");
                        return;
                    }
                } catch (JwtException var8) {
                    response.sendError(401, MessageTools.message("auth.token.error", new Object[0]));
                    return;
                }

                UserDetails userDetails = this.userDetailsService.loadUserByUsername(this.jwtTokenUtil.getUsernameFromToken(authToken));
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, (Object)null, userDetails.getAuthorities());
                authentication.setDetails((new WebAuthenticationDetailsSource()).buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                chain.doFilter(request, response);
            } else {
                response.sendError(401, MessageTools.message("auth.token.error", new Object[0]));
            }
        } else {
            chain.doFilter(request, response);
        }

/*		if ("OPTIONS".equals(request.getMethod()) || antMatchers(request)) {
			chain.doFilter(request, response);
			return;
		}

		final String requestHeader = request.getHeader(jwtProperties.getHeader());

		if (requestHeader == null || !requestHeader.startsWith(jwtProperties.getTokenHeader())) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, MessageTools.message(ERROR_TOKEN));
			return;
		}

		String authToken = requestHeader.substring(jwtProperties.getTokenHeader().length());
		// 解析token
		try {
			if (jwtTokenUtil.isTokenExpired(authToken)) {
				// token过期
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ERROR_EXPIRED);
				return;
			}
		} catch (JwtException e) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, MessageTools.message(ERROR_TOKEN));
			return;
		}

		UserDetails userDetails = userDetailsService.loadUserByUsername(jwtTokenUtil.getUsernameFromToken(authToken));

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
				userDetails.getAuthorities());
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authentication);*/

		chain.doFilter(request, response);

	}

	protected boolean antMatchers(HttpServletRequest request) {

		for (AntPathRequestMatcher requestMatcher : requestMatchers) {
			if (requestMatcher.matches(request)) {
				return true;
			}
		}

		return false;
	}

}
