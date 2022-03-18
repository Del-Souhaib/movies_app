package com.example.movies_app.IDO;

import com.example.movies_app.Models.Film;

import java.util.List;

public interface IDAO<T> {
     Boolean create(T t);
     Boolean update(T t);
     Boolean delete(T t);
     List<T> findall();
     Film findbyid(int id);

}
