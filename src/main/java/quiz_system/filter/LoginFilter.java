package quiz_system.filter;

import quiz_system.domain.User;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");

            if (user != null && user.is_active()) {
                System.out.println(request.getRequestURI());

                if (request.getRequestURI().startsWith("/admin")) {
                    if (user.is_admin()) {
                        filterChain.doFilter(request, response);
                    }
                    else
                        response.sendRedirect("/user_home");
                }
                else
                    filterChain.doFilter(request, response);
            }
            else
                response.sendRedirect("/login");
        } else {
            // redirect back to the login page if user is not logged in!
            response.sendRedirect("/login");
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return "/login".equals(path) || "/register".equals(path) || "/common_feedback".equals(path);
    }
}
