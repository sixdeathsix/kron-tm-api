package com.example.krontmapi.service;

import com.example.krontmapi.entity.ObjectType;
import com.example.krontmapi.repository.ObjectRepository;
import com.example.krontmapi.repository.ObjectTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.krontmapi.entity.Object;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ObjectService {

    private final ObjectRepository objectRepository;
    private final ObjectTypeRepository objectTypeRepository;

    public List<Object> getAll() throws Exception {

        var objects = objectRepository.findAll();

        if (objects.isEmpty()) {
            throw new Exception("Объекты не найдены");
        }

        return objects;
    }

    public Object getOneObject(Integer id) throws Exception {

        if (!objectRepository.existsById(id)) {
            throw new Exception("Объект не найден");
        }

        return objectRepository.findById(id).get();
    }

    public List<ObjectType> getAllTypes() throws Exception {
        var objectTypes = objectTypeRepository.findAll();

        if (objectTypes.isEmpty()) {
            throw new Exception("Ничего не найдено");
        }

        return objectTypes;
    }

}
