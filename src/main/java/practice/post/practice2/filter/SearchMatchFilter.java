package practice.post.practice2.filter;

import practice.post.practice2.User;
import practice.post.practice2.UserCache;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.logging.LogRecord;

public class SearchMatchFilter extends BaseFilter{
    private static final UserCache USER_CACHE = UserCache.getInstance();

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        String login = req.getParameter("login");

        Collection<User> users = USER_CACHE.values();

        boolean match = false;

        for(User user: users){
            if(user.getLogin().equals(login)){
                match = true;
                break;
            }
        }
        if(match){
            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/Error.jsp");
            dispatcher.forward(req, resp);
        }
        else filterChain.doFilter(req, resp);
        }

    }

