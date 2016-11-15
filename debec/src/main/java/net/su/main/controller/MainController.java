package net.su.main.controller;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import net.su.login.valueObject.LoginValueObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		return "redirect:/loginFrame.do";
	}
	
	// 메인 화면 전환
	@RequestMapping(value = "/MainFrame.do", method = RequestMethod.GET)
	public String MainFrame(Locale locale, Model model, HttpSession session, LoginValueObject loginValueObject) {

		session.getAttribute("loginUserInfo");

		return "main/MainFrame";
	}
	
	// 상단 바 프레임
	@RequestMapping(value = "/TopFrame.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String TopFrame(Model model) {

		return "main/TopFrame";
	}

}
