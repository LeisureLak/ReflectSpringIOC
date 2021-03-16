package com.lak.dao.impl;

import com.lak.annotation.Bean;
import com.lak.dao.IDogDao;
import com.lak.entity.Dog;

import java.util.List;

@Bean
public class DogDaoImpl implements IDogDao {


    @Override
    public Dog findDogById(int id) {
        return new Dog("teddy", 12, "red");
    }

    @Override
    public List<Dog> findAllDogs() {
        return null;
    }

    @Override
    public List<Dog> findDogsByName() {
        return null;
    }
}
