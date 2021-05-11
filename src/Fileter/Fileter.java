package Fileter;

import java.io.IOException
;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




public class Fileter implements Filter{

	@Override
	public void destroy() {
		
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
			//Logger logger = Logger.getLogger(Fileter.class);
			HttpServletRequest Request = (HttpServletRequest) request;
			HttpServletResponse Response = (HttpServletResponse) response;
			String url = Request.getRequestURI();
			String ctxPath = Request.getContextPath();
			String targetURL = url.substring(ctxPath.length());
			HttpSession session = Request.getSession();
			Integer name = (Integer) session.getAttribute("name");
			Integer adminname=(Integer) session.getAttribute("adminname");
			Integer teacherid =(Integer) session.getAttribute("id");
			if(!targetURL.equals("/index.jsp")&&!targetURL.equals("/error.html")){
				if(name==null&&adminname==null&&teacherid==null){
					Response.sendRedirect("index.jsp");
					return;
				}else{
					chain.doFilter(Request, Response);
					return;
				}
			}else{
					chain.doFilter(Request, Response);
					return ;
			}
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
