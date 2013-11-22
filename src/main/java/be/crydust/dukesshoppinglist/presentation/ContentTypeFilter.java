package be.crydust.dukesshoppinglist.presentation;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kristof
 */
public class ContentTypeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // NOOP
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (response instanceof HttpServletResponse) {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            if (!httpServletResponse.containsHeader("Content-Type")) {
                httpServletResponse.addHeader("Content-Type", "text/html; charset=UTF-8");
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // NOOP
    }
}
