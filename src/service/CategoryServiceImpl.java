package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dream.common.redis.dao.BaseRedisDao;
import com.dream.common.redis.dao.DBRedisPoll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dao.CategoryMapper;
import entity.Category;

@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    private BaseRedisDao baseRedisDao;

    public List<Category> list() {
        try {

            boolean add = baseRedisDao.stringAdd("xiaodeng1", "xiaowei");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryMapper.list();
    }

    @Override
    public Category getOne(int id) throws Exception {
        Category category = new Category();
        long startTimeRedis = System.currentTimeMillis();
        Map<String, String> map = null;
        for(int i = 0; i < 1000; i++){
            map = baseRedisDao.mapGetAll("id_" + id);
        }
        long endTimeRedis = System.currentTimeMillis();
        long redisTime = endTimeRedis - startTimeRedis;
        System.out.println("redis查询时间：" + redisTime+"毫秒");
        if (map != null && !map.isEmpty()) {
            category.setId(Integer.parseInt(map.get("id")));
            category.setName((map.get("name")));
            return category;
        }
        long startTimeSQL = System.currentTimeMillis();
        category = categoryMapper.get(id);
        long endTimeSQL = System.currentTimeMillis();
        long sqlTime = endTimeSQL - startTimeSQL;
        System.out.println("SQL查询时间：" + sqlTime+"毫秒");

        Map<String, String> map1 = new HashMap<>();
        map1.put("id", category.getId() + "");
        map1.put("name", category.getName());
        boolean flag = baseRedisDao.mapAddMap("id_" + id, map1);
        if (flag) {
            System.out.println("存入redis成功");

        } else {
            System.out.println("存入redis失败！！！");
        }
        return category;
    }

}
