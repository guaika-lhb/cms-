package edy.ynmd.cms.filter;

import edy.ynmd.cms.tools.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import static edy.ynmd.cms.tools.JwtUtil.ROLE;


public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final PathMatcher pathmatcher = new AntPathMatcher();
    //private String protectUrlPattern="/admin/**";
    private String[] protectUrlPattern = {"/manage/**", "/member/**", "/auth/**"};

    public JwtAuthenticationFilter() {

    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        try {
            if (isProtectedUrl(httpServletRequest)) {
                Map<String, Object> claims = JwtUtil.validateTokenAndGetClaims(httpServletRequest);
                String role = String.valueOf(claims.get(ROLE));//获取注入到jwt中的角色
                String userid = String.valueOf(claims.get("userid"));//获取注入到jwt中的用户主键
                //最关键的部分就是这里, 我们直接注入了
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                        userid, null, Arrays.asList(() -> role)
                ));

            }
        } catch (Exception e) {
            e.printStackTrace();
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);


    }

    private boolean isProtectedUrl(HttpServletRequest request) {

        for (int i = 0; i < protectUrlPattern.length; i++) {
            if (pathmatcher.match(protectUrlPattern[i], request.getServletPath())) {
                return true;
            }
        }
        return false;
    }

}