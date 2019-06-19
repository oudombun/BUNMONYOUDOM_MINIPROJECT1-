package mybatis.repository.model;

public class ArticleFilter {
    private String title;
    private Integer cate_id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCate_id() {
        return cate_id;
    }

    public void setCate_id(Integer cate_id) {
        this.cate_id = cate_id;
    }
}
