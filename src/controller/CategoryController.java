package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import entity.Category;
import service.CategoryService;
 
// ����spring mvc����һ����������
@Controller
@RequestMapping("")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
 
    @RequestMapping("/listCategory")
    public ModelAndView listCategory() throws Exception {
        ModelAndView mav = new ModelAndView();
        List<Category> cs= categoryService.list();

        // ����ת������
        mav.addObject("cs", cs);
        // ����jsp·��
        mav.setViewName("listCategory");
        return mav;
    }

    @RequestMapping("/getCategory")
    public ModelAndView listCategory(@RequestParam("id") int id) throws Exception {
        ModelAndView mav = new ModelAndView();
        Category category= categoryService.getOne(id);

        // ����ת������
        mav.addObject("category", category);
        // ����jsp·��
        mav.setViewName("listCategory");
        return mav;
    }
}
