package Fileter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SpingFileter implements HandlerInterceptor{
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3){
		
	}
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		
		
	}
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj) throws Exception {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			/*HttpSession session=request.getSession();
			String name =(String) session.getAttribute("name");
			if(name==null){
				response.sendRedirect("index.html");
				return false;
			}else{
				return true;
			}
			*/
			return true;
		        
	}

}
