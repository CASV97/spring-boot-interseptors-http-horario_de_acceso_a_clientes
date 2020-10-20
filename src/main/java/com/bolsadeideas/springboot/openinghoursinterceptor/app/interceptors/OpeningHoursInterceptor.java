package com.bolsadeideas.springboot.openinghoursinterceptor.app.interceptors;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component("openingHoursInterceptor")
public class OpeningHoursInterceptor implements HandlerInterceptor {
	@Value("${config.openinghours.open}")
	private Integer open;
	@Value("${config.openinghours.close}")
	private Integer close;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// lo primero es obtener la hora actual
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		if (hour >= open && hour <= close) {
			StringBuilder message = new StringBuilder("Wellcome to  open bussisness");
			message.append(", we attend from ");
			message.append(open);
			message.append(" hrs. ");
			message.append("to ");
			message.append(close);
			message.append(" hrs. ");
			message.append(" Thank you for visiting us.");
			// la idea es pasar el mensaje a los atributos del request
			request.setAttribute("message", message.toString());
			return true;
		}
		// si es falso manejamos el response si es falso reenviamos a otra página con el
		// context pat h
		response.sendRedirect(request.getContextPath().concat("/closed"));
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		String message = (String) request.getAttribute("message");
		modelAndView.addObject("message", message);
	}

}
