package server;

public abstract class Action {

    public abstract ActionResponse execute(ActionRequest request);

    public boolean verify(ActionRequest request) {
        return true;
    }
}
