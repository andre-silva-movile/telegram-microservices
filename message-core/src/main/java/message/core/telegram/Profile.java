package message.core.telegram;

public class Profile {

    private Boolean ok;
    private ProfileResult result;

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public ProfileResult getResult() {
        return result;
    }

    public void setResult(ProfileResult result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "ok=" + ok +
                ", result=" + result +
                '}';
    }
}
