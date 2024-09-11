package jp.co.falsystack.sso2client.converter;

public interface ProviderUserConverter<T, R> {
    R converter(T t);
}
