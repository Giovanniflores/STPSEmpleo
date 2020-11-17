package mx.gob.stps.portal.web.security.filter;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ChronometerFilter implements Filter {

	@Override
	public void init(FilterConfig arg0) throws ServletException {}
	
	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {		
		request.setAttribute("TIME-START", Calendar.getInstance()); // Se registra el tiempo de inicio de peticion
        filterChain.doFilter(request, response);
        return;
	}
}
