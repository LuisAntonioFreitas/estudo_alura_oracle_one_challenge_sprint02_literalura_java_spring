package net.lanet.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConvertsData implements IConvertsData {
    private ObjectMapper objectMapper = new ObjectMapper();
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public <T> T getDataJsonToClass(String json, Class<T> modelTarget) {
        try {
            return objectMapper.readValue(json, modelTarget);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> getListJsonToClass(String json, Class<T> modelTarget) {
        try {
            CollectionType listModel = objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, modelTarget);

            return objectMapper.readValue(json, listModel);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> String setDataClassToJson(Class<T> modelSource) {
        try {
            String json = objectMapper.writeValueAsString(modelSource);
            return json;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <S, T> T mapDataClassToClass(S modelSource, Class<T> modelTarget) {
        try {
            return modelMapper.map(modelSource, modelTarget);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <S, T> List<T> mapListClassToClass(List<S> modelSource, Class<T> modelTarget) {
        try {
            return modelSource.stream()
                    .map(e -> modelMapper.map(e, modelTarget))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
