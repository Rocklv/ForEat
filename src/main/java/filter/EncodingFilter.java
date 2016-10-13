package filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * EncodingFilter
 */
public class EncodingFilter implements Filter {
    private String encoding;

    public void init(FilterConfig filterConfig) throws ServletException {
        if (encoding != null)
            encoding = filterConfig.getInitParameter("encoding");
        else
            encoding="utf-8";
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        request.setCharacterEncoding(encoding);
        request.setCharacterEncoding(encoding);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    public void destroy() {
        encoding = null;
    }
}
