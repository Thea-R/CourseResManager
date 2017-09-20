package forFilter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class filterAll implements Filter {

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest=(HttpServletRequest)request;
        HttpServletResponse httpResponse=(HttpServletResponse)response;
        HttpSession session=httpRequest.getSession();
        
        String identity=(String)session.getAttribute("identity");
        if(identity!=null){
            chain.doFilter(request, response);
        }
        else{
        	String script = "<script>alert('没有访问权限，请登陆');location.href='/CMS/index.jsp'</script>";
    		httpResponse.setContentType("text/html;charset=GBK");
    		httpResponse.getWriter().println(script);
            //httpResponse.sendRedirect(httpRequest.getContextPath()+"/index.jsp");
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

}