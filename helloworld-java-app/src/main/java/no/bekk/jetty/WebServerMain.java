package no.bekk.jetty;

import java.io.IOException;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.spi.container.servlet.ServletContainer;

public class WebServerMain {

	private static final Logger LOG = LoggerFactory.getLogger(WebServerMain.class);

	private static final int SERVER_PORT = 1234;

	public static void main(final String[] args) throws IOException {
		Server server = new Server(SERVER_PORT);
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		server.setHandler(context);
		ServletHolder h = new ServletHolder(new ServletContainer());
		h.setInitParameter("com.sun.jersey.config.property.packages", "no.bekk.jersey");
		h.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true");
		context.addServlet(h, "/*");
		try {
			server.start();
			LOG.info("Server startet på port " + SERVER_PORT);
			server.join();
		} catch (Exception e) {
			LOG.error("Kunne ikke starte server!", e);
		}
	}

}
