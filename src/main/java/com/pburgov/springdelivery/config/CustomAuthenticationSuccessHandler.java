package com.pburgov.springdelivery.config;

import com.pburgov.springdelivery.dao.entity.User;
import com.pburgov.springdelivery.dao.service.UserService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private UserService userService;
    protected final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    public CustomAuthenticationSuccessHandler( UserService userService ) {
        this.userService = userService;
    }

    @Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		System.out.println("\n\nIn customAuthenticationSuccessHandler\n\n");

		String userName = authentication.getName();
		
		User theUser = userService.findByUserName(userName);
		
		HttpSession session = request.getSession();
		session.setAttribute("user", theUser);

		response.sendRedirect(request.getContextPath() + "/repartos/list");
	}

}
