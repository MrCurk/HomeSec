package mr.curk.util;

public enum State {
    ON, OFF;

    public boolean toBoolen() {
        return (this == State.ON);
    }
}
