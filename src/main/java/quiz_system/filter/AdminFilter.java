//package com.bfs.logindemo.filter;
//
//import com.bfs.logindemo.domain.User;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@WebFilter("/*")
//public class AdminFilter extends OncePerRequestFilter {
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain)
//            throws ServletException, IOException {
//
//        HttpSession session = request.getSession(false);
//        System.out.println("get into admin filter");
//
//        if (session != null && session.getAttribute("user") != null) {
//            User user = (User) session.getAttribute("user");
//            if (user.is_admin()) {
//                System.out.println(user);
//                filterChain.doFilter(request, response);
//            }
//            else {
//                System.out.println("user is not the admin");
//                response.sendRedirect("/logout");
//            }
//        } else {
//            // redirect back to the login page if user is not logged in!
//            response.sendRedirect("/logout");
//        }
//    }
//
//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//        String path = request.getRequestURI();
//        return !path.startsWith("/admin");
//    }
//}