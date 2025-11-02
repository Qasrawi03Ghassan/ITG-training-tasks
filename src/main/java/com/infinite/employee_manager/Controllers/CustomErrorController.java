package com.infinite.employee_manager.Controllers;

import org.apache.tomcat.util.http.Method;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class CustomErrorController implements ErrorController{

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {

        Object statusObj = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Integer status = statusObj != null ? Integer.valueOf(statusObj.toString()) : 0;

        String message = (String) request.getAttribute(RequestDispatcher.ERROR_MESSAGE);

        
        switch (status) {

            case 404:
                message = "The page you are looking for does not exist";
                break;
            case 500:
                message = "Oops! Something went wrong on the server";
                break;
            default:
                if (message == null || message.isEmpty()) {
                    message = "An unexpected error occurred.";
                }
        }
        
        model.addAttribute("status", status);
        model.addAttribute("message", message);

        return "error";
    }      

    @RequestMapping("/access-denied")
    public String showPreventAccess() {
        return "access-denied";
    }
    
}
