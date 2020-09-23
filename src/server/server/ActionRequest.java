package server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionRequest {

    private final Map<String, List<String>> headers;
    private final String body;

    public ActionRequest(Map<String, List<String>> headers, String body) {
        this.headers = headers;
        this.body = body;
    }

    public Map<String, List<String>> getHeaders() {
        return new HashMap<>(headers);
    }

    public String getBody() {
        return body;
    }
}
