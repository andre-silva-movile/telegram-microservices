package message.core.mapper;

public interface Mapper {
    <T> String serialize(T object);

    <T> T deserialize(String content, Class<T> classOfT);
}
