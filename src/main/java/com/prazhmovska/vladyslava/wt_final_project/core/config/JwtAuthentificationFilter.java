package com.prazhmovska.vladyslava.wt_final_project.core.config;

import com.prazhmovska.vladyslava.wt_final_project.model.dto.TokenDto;
import com.prazhmovska.vladyslava.wt_final_project.model.repository.UserRepository;
import com.prazhmovska.vladyslava.wt_final_project.service.JwtService;
import com.prazhmovska.vladyslava.wt_final_project.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthentificationFilter extends OncePerRequestFilter {
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String HEADER_NAME = "Authorization";
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(
             HttpServletRequest request,
             HttpServletResponse response,
             FilterChain filterChain
    ) throws ServletException, IOException {


        var authHeader = request.getHeader(HEADER_NAME);
        if (isEmpty(authHeader) || !authHeader.startsWith(BEARER_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

         var jwt = authHeader.substring(BEARER_PREFIX.length());
        var username = jwtService.extractUserName(jwt);

        if (!isEmpty(username) && SecurityContextHolder.getContext().getAuthentication() == null) {

            TokenDto details=userRepository.findByEmail(username)
                    .map(user->new TokenDto(user.getEmail(), user.getId()))
                    .orElseThrow(() -> new RuntimeException("User not found"));

            if (jwtService.isTokenValid(jwt, details)) {
                SecurityContext context = SecurityContextHolder.createEmptyContext();

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        details,
                        null,
                        null
            );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                context.setAuthentication(authToken);
                SecurityContextHolder.setContext(context);
            }
        }
        filterChain.doFilter(request, response);
    }
    private boolean isEmpty(String token) {
        return token == null || token.isEmpty();
    }
}
