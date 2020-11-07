package dev.dovydasvenckus.telegram.api.server

import javax.servlet.ServletException
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class MockServlet extends HttpServlet {

    private int responseStatus

    MockServlet(int responseStatus) {
        this.responseStatus = responseStatus
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json")
        response.setStatus(responseStatus)
        response.getWriter().println("{ \"status\": \"ok\"}")
        response.setContentType("application/json;charset=UTF-8");
    }
}
