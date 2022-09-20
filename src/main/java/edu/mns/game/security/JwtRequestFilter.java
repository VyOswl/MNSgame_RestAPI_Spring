package edu.mns.game.security;

import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceMnsGame userDetailsServiceMnsGame;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(("Authorization"));

        if(token != null && token.startsWith("Bearer ")){
            String jwt = token.substring(7);
            System.out.println("doFilterInternal in jwtRequestFilter, token = "+token);

            try {
                String email = jwtUtils.getTokenBody(jwt).getSubject();

                UserDetailsMnsGame userDetails = this.userDetailsServiceMnsGame.loadUserByUsername(email);
                if (jwtUtils.tokenValidation(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }catch(MalformedJwtException e){
                System.out.println("le token est malformé");
                e.printStackTrace();
            }catch(SignatureException e){
                System.out.println("le token a un corps qui ne correspond pas à la signature");
                e.printStackTrace();
            }catch(Exception e){
                System.out.println("erreur inconnue sur le traitement du token");
                e.printStackTrace();
            }
        }
        filterChain.doFilter(request, response);
    }

}