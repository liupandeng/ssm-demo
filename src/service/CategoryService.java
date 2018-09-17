package service;

import java.util.List;

import entity.Category;

public interface CategoryService {
    List<Category> list() throws Exception;
    Category getOne(int id) throws Exception;
}
