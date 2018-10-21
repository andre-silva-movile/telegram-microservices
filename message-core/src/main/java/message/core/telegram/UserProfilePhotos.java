package message.core.telegram;

public class UserProfilePhotos {

    private Boolean ok;
    private UserProfilePhotosResult result;

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public UserProfilePhotosResult getResult() {
        return result;
    }

    public void setResult(UserProfilePhotosResult result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "UserProfilePhotos{" +
                "ok=" + ok +
                ", result=" + result +
                '}';
    }
}
