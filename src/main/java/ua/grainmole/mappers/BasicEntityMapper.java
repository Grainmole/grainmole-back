package ua.grainmole.mappers;

public interface BasicEntityMapper<T, R> {

    R mapToDto(T t);
}
