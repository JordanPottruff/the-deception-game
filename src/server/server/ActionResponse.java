package server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionResponse {

    private Map<String, List<String>> headers;
    private String body;

    private ActionResponse(Map<String, List<String>> headers, String body) {
        this.headers = headers;
        this.body = body;
    }

    public Map<String, List<String>> getHeaders() {
        return new HashMap<>(headers);
    }

    public String getBody() {
        return body;
    }

    public static class Builder {

        private Map<String, List<String>> headers;
        private StringBuilder body;

        public Builder() {
            headers = new HashMap<>();
            body = new StringBuilder();
        }

        public Builder setHeaders(Map<String, List<String>> headers) {
            this.headers = headers;
            return this;
        }

        public Builder addHeader(String key, List<String> value) {
            this.headers.put(key, value);
            return this;
        }

        public Builder addAllHeaders(Map<String, List<String>> headers) {
            this.headers.putAll(headers);
            return this;
        }

        public Builder setBody(String body) {
            this.body = new StringBuilder(body);
            return this;
        }

        public Builder appendBody(String moreBody) {
            this.body.append(moreBody);
            return this;
        }

        public ActionResponse build() {
            return new ActionResponse(this.headers, this.body.toString());
        }
    }
}
