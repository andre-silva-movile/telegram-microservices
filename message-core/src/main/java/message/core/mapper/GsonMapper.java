package message.core.mapper;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonMapper implements Mapper {

    public static GsonMapper DEFAULT = new GsonMapper(defaultConfiguration());
    public static GsonMapper SNACK_CASE = new GsonMapper(snackCaseConfiguration());
    private Gson gson = new Gson();

    private GsonMapper(Gson gson) {
        this.gson = gson;
    }

    private static Gson defaultConfiguration() {
        return new Gson();
    }

    public static Gson snackCaseConfiguration() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    @Override
    public <T> String serialize(T object) {
        try {
            return gson.toJson(object);
        } catch (RuntimeException e) {
            return null;
        }
    }

    @Override
    public <T> T deserialize(String content, Class<T> classOfT) {
        try {
            return gson.fromJson(content, classOfT);
        } catch (RuntimeException e) {
            return null;
        }
    }

}
