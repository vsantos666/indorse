package indorse.utils;

import org.modelmapper.ModelMapper;

public abstract class MapperUtil {
    public static <R, T> R mapObject(T source, Class<R> destination) {
        ModelMapper modelMapper = new ModelMapper();
        R entity = modelMapper.map(source, destination);
        return entity;
    }
}
