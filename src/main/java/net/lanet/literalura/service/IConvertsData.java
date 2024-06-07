package net.lanet.literalura.service;

import java.util.List;

public interface IConvertsData {
    <T> T getDataJsonToClass(String json, Class<T> classModel);
    <T> List<T> getListJsonToClass(String json, Class<T> classModel);
    <T> String setDataClassToJson(Class<T> modelSource);
    <S, T> T mapDataClassToClass(S source, Class<T> targetClass);
    <S, T> List<T> mapListClassToClass(List<S> source, Class<T> targetClass);
}
