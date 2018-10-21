package message.core.telegram;

public class File {

    private Boolean ok;
    private FileResult result;

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public FileResult getResult() {
        return result;
    }

    public void setResult(FileResult result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "File{" +
                "ok=" + ok +
                ", result=" + result +
                '}';
    }
}
