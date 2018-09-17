package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import entity.Category;
import service.CategoryService;
 
// 告诉spring mvc这是一个控制器类
@Controller
@RequestMapping("")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
 
    @RequestMapping("/listCategory")
    public ModelAndView listCategory() throws Exception {
        ModelAndView mav = new ModelAndView();
        List<Category> cs= categoryService.list();

        // 放入转发参数
        mav.addObject("cs", cs);
        // 放入jsp路径
        mav.setViewName("listCategory");
        return mav;
    }

    @RequestMapping("/getCategory")
    public ModelAndView listCategory(@RequestParam("id") int id) throws Exception {
        ModelAndView mav = new ModelAndView();
        Category category= categoryService.getOne(id);

        // 放入转发参数
        mav.addObject("category", category);
        // 放入jsp路径
        mav.setViewName("listCategory");
        return mav;
    }
}
