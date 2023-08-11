// package com.oburnett127.socialmedia.config;

// import org.springframework.security.web.csrf.CsrfToken;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;
// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import java.io.IOException;

// //Decided to use header for csrf token instead of a cookie because of the new spring security
// //standards and to provide for protection against BREACH attacks.

// @Component
// public class CsrfHeaderFilter extends OncePerRequestFilter {

//     @Override
//     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
//                                     FilterChain filterChain) throws ServletException, IOException {
//         CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
//         if (csrf != null) {
//             response.setHeader("X-CSRF-TOKEN", csrf.getToken());
//         }
//         filterChain.doFilter(request, response);
//     }
// }
