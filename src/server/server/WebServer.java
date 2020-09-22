package server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import server.models.Method;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;

import static server.models.Constants.*;

public class WebServer {

    private final HttpServer server;

    public WebServer(String address, int port, int maxBacklog) throws IOException {
        this.server = HttpServer.create(new InetSocketAddress(address, port), maxBacklog);
    }

    public void addAction(Action action) {
        ForRequest actionAnnotation = action.getClass().getAnnotation(ForRequest.class);
        Method actionMethod = actionAnnotation.method();
        String path = actionAnnotation.path();

        ActionHandler handler = new ActionHandler(action, actionMethod);
        server.createContext(path, handler);
    }

    public class ActionHandler implements HttpHandler {

        private final Action action;
        private final Method method;

        public ActionHandler(Action action, Method method) {
            this.action = action;
            this.method = method;
        }

        public void handle(HttpExchange exchange) throws IOException {
            // Create request
            Map<String, List<String>> requestHeaders = exchange.getRequestHeaders();
            String requestBody = exchange.getRequestBody().toString();
            Method requestMethod = Method.valueOf(exchange.getRequestMethod());
            ActionRequest request = new ActionRequest(requestHeaders, requestBody);

            // Verify proper HTTP method is being used.
            if (method != requestMethod) {
                exchange.sendResponseHeaders(HTTP_BAD_REQUEST, -1);
                return;
            }

            // Run verification on the request.
            if (!action.verify(request)) {
                exchange.sendResponseHeaders(HTTP_FORBIDDEN, -1);
                return;
            }

            // Execute action.
            ActionResponse response = action.execute(request);

            // Return response.
            exchange.getResponseHeaders().putAll(response.getHeaders());
            exchange.sendResponseHeaders(HTTP_OK, response.getBody().length());
            exchange.getResponseBody().write(response.getBody().getBytes());
        }
    }
}
