package dev.dovydasvenckus.telegram.api.server

import org.eclipse.jetty.server.Connector
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.ServerConnector
import org.eclipse.jetty.servlet.ServletHandler
import org.eclipse.jetty.servlet.ServletHolder

class MockServer {

    private Server server
    private int port
    private String uri
    private int statusCode

    void start() {
        server = new Server()
        ServerConnector connector = new ServerConnector(server)
        connector.setPort(port)
        server.setConnectors([connector] as Connector[])
        ServletHandler servletHandler = new ServletHandler()
        server.setHandler(servletHandler)
        servletHandler.addServletWithMapping(new ServletHolder(new MockServlet(statusCode)), uri)

        server.start()
    }

    void stop() {
        server.stop()
    }
}
