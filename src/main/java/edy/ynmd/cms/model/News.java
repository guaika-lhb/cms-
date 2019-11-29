package edy.ynmd.cms.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class News {
    private String newsid;
    private String title;
    private Long pbdata;
    private String content;
    private String author;
    private Short px;
    private String coverpic;
    private String remark;

    @Id
    @GeneratedValue(generator = "uuid2" )   //指定生成器名称
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator" )  //生成器名称，uuid生成类
    @Column(name = "newsid")
    public String getNewsid() {
        return newsid;
    }

    public void setNewsid(String newsid) {
        this.newsid = newsid;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "pbdata")
    public Long getPbdata() {
        return pbdata;
    }

    public void setPbdata(Long pbdata) {
        this.pbdata = pbdata;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "author")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Basic
    @Column(name = "px")
    public Short getPx() {
        return px;
    }

    public void setPx(Short px) {
        this.px = px;
    }

    @Basic
    @Column(name = "coverpic")
    public String getCoverpic() {
        return coverpic;
    }

    public void setCoverpic(String coverpic) {
        this.coverpic = coverpic;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        News news = (News) o;

        if (newsid != null ? !newsid.equals(news.newsid) : news.newsid != null) return false;
        if (title != null ? !title.equals(news.title) : news.title != null) return false;
        if (pbdata != null ? !pbdata.equals(news.pbdata) : news.pbdata != null) return false;
        if (content != null ? !content.equals(news.content) : news.content != null) return false;
        if (author != null ? !author.equals(news.author) : news.author != null) return false;
        if (px != null ? !px.equals(news.px) : news.px != null) return false;
        if (coverpic != null ? !coverpic.equals(news.coverpic) : news.coverpic != null) return false;
        if (remark != null ? !remark.equals(news.remark) : news.remark != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = newsid != null ? newsid.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (pbdata != null ? pbdata.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (px != null ? px.hashCode() : 0);
        result = 31 * result + (coverpic != null ? coverpic.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }
}
