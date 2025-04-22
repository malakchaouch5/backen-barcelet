package com.example.Braclet.Controlleur;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.Braclet.Entity.MyAppUser;
import java.io.IOException;

@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtUtils jwtUtils;
    private final MyAppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor with required arguments
    public OAuth2AuthenticationSuccessHandler(JwtUtils jwtUtils,
                                              MyAppUserRepository userRepository,
                                              PasswordEncoder passwordEncoder) {
        this.jwtUtils = jwtUtils;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        // Check if user exists
        MyAppUser user = userRepository.findByEmail(email)
                .orElseGet(() -> {
                    MyAppUser newUser = new MyAppUser();
                    newUser.setEmail(email);
                    newUser.setUsername(name);
                    newUser.setPassword(passwordEncoder.encode("oauth2password"));
                    return userRepository.save(newUser);
                });

        // Generate JWT
        String jwt = jwtUtils.generateToken(user);

        // Redirect to frontend with token
        String redirectUrl = "http://your-frontend.com/auth-success?token=" + jwt;
        getRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }
}
