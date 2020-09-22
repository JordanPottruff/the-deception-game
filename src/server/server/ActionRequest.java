package server;

import java.util.List;
import java.util.Map;

public class ActionRequest {

    private final Map<String, List<String>> headers;
    private final String body;

    public ActionRequest(Map<String, List<String>> headers, String body) {
        this.headers = headers;
        this.body = body;
    }
}
