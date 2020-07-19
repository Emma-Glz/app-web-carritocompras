
package com.carrito.utils;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Utils {    
    public static void distpatcherServlet(String url, HttpServletRequest request, HttpServletResponse reponse) throws  ServletException, IOException{
        request.getRequestDispatcher(url).forward(request, reponse);
    }
}
