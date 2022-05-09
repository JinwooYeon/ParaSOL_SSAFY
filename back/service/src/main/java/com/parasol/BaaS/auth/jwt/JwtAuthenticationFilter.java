package com.parasol.BaaS.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.parasol.BaaS.auth.jwt.util.JwtTokenUtil;
import com.parasol.BaaS.db.entity.User;
import com.parasol.BaaS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    @Autowired
    private UserService userService;
	
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, UserService userService) {
		super(authenticationManager);
		this.userService = userService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
        String header = request.getHeader(JwtTokenUtil.HEADER_STRING);

        if (header == null || !header.startsWith(JwtTokenUtil.TOKEN_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }
        
        try {
            Authentication authentication = getAuthentication(request);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception ex) {
            return;
        }
        
        filterChain.doFilter(request, response);
	}
	
	@Transactional(readOnly = true)
    public Authentication getAuthentication(HttpServletRequest request) throws Exception {
        String token = request.getHeader(JwtTokenUtil.HEADER_STRING);
        if (token != null) {
            JWTVerifier verifier = JwtTokenUtil.getVerifier();
            String message = JwtTokenUtil.handleError(token);
            if(message == null || "expired".equals(message)) {
                return null;
            }

            DecodedJWT decodedJWT = verifier.verify(token.replace(JwtTokenUtil.TOKEN_PREFIX, ""));
            String userId = decodedJWT.getSubject();
            if (userId != null) {
                User user = userService.getUserByUserId(userId);
                if(user != null) {
                    UserDetail userDetails = new UserDetail(user);
                    UsernamePasswordAuthenticationToken jwtAuthentication = new UsernamePasswordAuthenticationToken(userId,
                            null, userDetails.getAuthorities());
                    jwtAuthentication.setDetails(userDetails);
                    return jwtAuthentication;
                }
            }
            return null;
        }
        return null;
    }
}