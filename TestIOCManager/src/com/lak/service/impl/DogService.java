package com.lak.service.impl;

import com.lak.annotation.AutoWired;
import com.lak.annotation.Bean;
import com.lak.dao.IDogDao;
import com.lak.entity.Dog;
import com.lak.service.IDogService;

@Bean
public class DogService implements IDogService {

    @AutoWired
    private IDogDao dogDao;
    @Override
    public void findKiller(int id) {
        Dog dog = dogDao.findDogById(id);

        System.out.println(dog);
    }
}
