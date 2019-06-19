package mybatis.repository.model;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Article {
    private int id;
    @NotEmpty
    private String title;
    @NotEmpty
    @Size(min = 5,max = 20)
    private String desc;
    @NotEmpty
    private String author;
    private String thumbnail;


    @NotNull
    @Valid
    private Category category;
    public Article(){

    }

    public Article(int id, @NotEmpty String title, @NotEmpty @Size(min = 5, max = 20) String desc, @NotEmpty String author, String thumbnail, Category category) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.author = author;
        this.thumbnail = thumbnail;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", author='" + author + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", category=" + category +
                '}';
    }
}
