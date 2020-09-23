package main.actions;

import server.Action;
import server.ActionRequest;
import server.ActionResponse;
import server.ForRequest;
import server.models.Method;

@ForRequest(
    path = "/game/create",
    method = Method.GET
)
public class CreateGameAction extends Action {

    public ActionResponse execute(ActionRequest request) {
        System.out.println(request.getBody());
        ActionResponse response = new ActionResponse.Builder()
                .setBody("<!DOCTYPE html><html><body><h1>hi</h1></body></html>")
                .build();
        return response;
    }
}
