package message.core.telegram;

import java.util.List;

public class UserProfilePhotosResult {

    private Integer totalCount;
    private List<List<PhotoSize>> photos;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<List<PhotoSize>> getPhotos() {
        return photos;
    }

    public void setPhotos(List<List<PhotoSize>> photos) {
        this.photos = photos;
    }

    @Override
    public String toString() {
        return "UserProfilePhotosResult{" +
                "totalCount=" + totalCount +
                ", photos=" + photos +
                '}';
    }
}
