package mybatis.controller;

import mybatis.repository.model.Category;
import mybatis.service.CategoryService.CategoryService;
import mybatis.utility.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/category")
    public String index(ModelMap modelMap, Paging paging,HttpServletRequest httpServletRequest){

        Category category= new Category();
        category.setId(categoryService.getLastId()+1);
        modelMap.addAttribute("categoryNew",category);

        /*pagination*/
        List<Category> pagingList= new ArrayList<>();
        pagingList= categoryService.findAllFilter(paging);

        String url = httpServletRequest.getRequestURL().toString();
        String[] arr = url.split("/");
        String domain=arr[0] + "//" + arr[2];

        modelMap.addAttribute("categories",pagingList);
        modelMap.addAttribute("domain",domain+"/category");
        modelMap.addAttribute("total",categoryService.countAllCate());
        return "category/index";
    }
    @PostMapping("/addcate")
    public String add__(ModelMap modelMap,@Valid @ModelAttribute("categoryNew") Category categorys, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            modelMap.addAttribute("categoryNew",categorys);
            return "category/add";
        }
        categoryService.add(categorys);
        return "redirect:/category";
    }
    @GetMapping("/addcate")
    public String add(ModelMap modelMap){
        Category category =new Category();
        category.setId(categoryService.getLastId()+1);
        modelMap.addAttribute("categoryNew",category);
        return "category/add";
    }
    @GetMapping("/update_cate/{id}")
    public String update(@PathVariable int id, ModelMap modelMap){
        modelMap.addAttribute("category",categoryService.findById(id));
        return "category/update";
    }
    @PostMapping("/update_cate")
    public String update_(@Valid @ModelAttribute Category category, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "category/update";
        }
        categoryService.update(category);
        return "redirect:/category";
    }

    @GetMapping("/del_cate/{id}")
    public String del(@PathVariable("id") int id){
        categoryService.delete(id);
        return "redirect:/category";
    }

}
