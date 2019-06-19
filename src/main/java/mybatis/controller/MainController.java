package mybatis.controller;

import mybatis.repository.model.Article;
import mybatis.repository.model.ArticleFilter;
import mybatis.repository.model.Category;
import mybatis.service.ArticleService.ArticleService;
import mybatis.service.CategoryService.CategoryService;
import mybatis.utility.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class MainController {
    @Autowired
    ArticleService articleService;

    @Autowired
    CategoryService categoryService;

    @GetMapping({"/","home"})
    public String index(ArticleFilter articleFilter, Paging paging, ModelMap modelMap){
        List<Article> pagingList= new ArrayList();
        Article article= new Article();

        /*add record*/
        article.setId(articleService.getLastId()+1);
        modelMap.addAttribute("articleNew",article);
        modelMap.addAttribute("allCate",categoryService.findAll());
        /*pagination*/
        pagingList=articleService.findAllFilter(articleFilter,paging);
        modelMap.addAttribute("articles",pagingList);
        modelMap.addAttribute("filter",articleFilter);
        modelMap.addAttribute("total",articleService.countAllArticles(articleFilter));
        return "index";
    }


    @GetMapping("/add")
    public String addData(ModelMap modelMap){
        Article article= new Article();
        article.setId(articleService.getLastId());
        modelMap.addAttribute("article",article);
        modelMap.addAttribute("allCate",categoryService.findAll());
        return "adddata";
    }

    @PostMapping("/add")
    public String add_(ModelMap modelMap,@Valid @ModelAttribute Article article, BindingResult bindingResult, @RequestParam MultipartFile file){
        if(bindingResult.hasErrors()){
            modelMap.addAttribute("allCate",categoryService.findAll());
            return "adddata";
        }else{
            /*file upload*/
            String serverPath=System.getProperty("user.dir")+"\\src\\main\\resources\\static\\images\\";
            String filename= UUID.randomUUID().toString()+"."+file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(".")+1);
            if(!file.isEmpty()){
                try{
                    Files.copy(file.getInputStream(),Paths.get(serverPath,filename));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else{
                filename="default.png";
            }
            article.setId(articleService.getLastId());
            article.setThumbnail(filename);
            /* end file upload*/
        }
        articleService.add(article);
        return "redirect:/";
    }
    @GetMapping("/update/{id}")
    public String update(@PathVariable int id, ModelMap modelMap){
        modelMap.addAttribute("article",articleService.findById(id));
        modelMap.addAttribute("allCate",categoryService.findAll());
        return "update";
    }
    @PostMapping("/update")
    public String update_(ModelMap modelMap,@Valid @ModelAttribute Article article, BindingResult bindingResult,@RequestParam("file") MultipartFile file){
        if(bindingResult.hasErrors()){
            modelMap.addAttribute("allCate",categoryService.findAll());
            return "update";
        }else{
            String filename="";
            if(!file.isEmpty()){
                String serverPath=System.getProperty("user.dir")+"\\src\\main\\resources\\static\\images\\";
                filename= UUID.randomUUID().toString()+"."+file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(".")+1);
                try{
                    Files.copy(file.getInputStream(),Paths.get(serverPath,filename));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else{
                filename=article.getThumbnail();
            }
            article.setThumbnail(filename);

            /* end file upload*/
        }
        articleService.update(article);
        return "redirect:/";
    }
    @GetMapping("/view/{id}")
    public String view(ModelMap modelMap, @PathVariable("id") int id){
        modelMap.addAttribute("article",articleService.findById(id));
        return "view";
    }
    @GetMapping("/del/{id}")
    public String del(@PathVariable("id") int id){
        articleService.delete(id);
        return "redirect:/";
    }

    @GetMapping("navbar")
    public String getNabar() {
        return "fragement/navbar :: nav1";
    }

    @GetMapping("navbar-dropdown")
    public String getDropDownNavbar() {
        return "fragement/navbar :: nav2";
    }

}
