package org.zabalburu.gt;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class ServerListener
 *
 */
@WebListener
public class ServerListener implements ServletContextListener {
	private final String TAG = "Iniciando/Finalizando Servlet: ";
    /**
     * Default constructor. 
     */
    public ServerListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         System.out.println(TAG + "Servlet inicializando...");
         System.out.println(TAG + sce.getServletContext().getServerInfo());
         System.out.println(TAG + System.currentTimeMillis());
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
    	System.out.println(TAG + "Servlet finalizando...");
    	System.out.println(TAG + sce.getServletContext().getServerInfo());
    	System.out.println(TAG + System.currentTimeMillis());
    }
	
}
