package com.proj.ecommerce.security.jwt;

import com.proj.ecommerce.model.UserPrincipal;
import com.proj.ecommerce.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class InternalApiAuthenticationFilter extends OncePerRequestFilter {


    private final String accessKey;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getRequestURI().startsWith("/api/internal");
    }

    public InternalApiAuthenticationFilter(String accessKey) {
           this.accessKey = accessKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        try {
            String requestKey = SecurityUtils.extractAuthTokenFromRequest(request);
            if(requestKey==null || !requestKey.equals(accessKey)){
                log.warn("Internal key endpoint requested without/wrong key uri: {} ",request.getRequestURI());
                throw new RuntimeException("UNAUTHORIZED");
            } else if (requestKey!=null && requestKey.equals(accessKey)) {

                UserPrincipal user = UserPrincipal.createAdmin();
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }


        }catch (Exception e){
            log.error("Could not set authentication in security context",e);

        }
        filterChain.doFilter(request,response);
    }


}
