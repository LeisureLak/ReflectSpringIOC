package com.lak.dao;

import com.lak.entity.Dog;

import java.util.List;

public interface IDogDao {

    Dog findDogById(int id);

    List<Dog> findAllDogs();

    List<Dog> findDogsByName();
}
