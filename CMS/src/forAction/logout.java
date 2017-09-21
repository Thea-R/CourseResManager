package forAction;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class logout extends HttpServlet {
	public logout() {
		super();
	}

	public void destroy() {
		super.destroy();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		session.removeAttribute("id");
		session.removeAttribute("identity");
		
		//String script = "<script>alert('已登出当前账户，请重新登陆');location.href='../index.jsp'</script>";
		String script = "<script>location.href='../index.jsp'</script>";
		response.setContentType("text/html;charset=GBK");
		response.getWriter().println(script);
		return ;
	}

	public void init() throws ServletException {
	}
}
