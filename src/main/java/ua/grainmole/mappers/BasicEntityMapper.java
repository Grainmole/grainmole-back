package ua.grainmole.mappers;

public interface BasicEntityMapper<T, R> {

    R mapEntityToDto(T t);
}
