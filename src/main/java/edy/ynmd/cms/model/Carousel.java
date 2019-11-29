package edy.ynmd.cms.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Carousel {
    private String carouselid;
    private String pic;
    private String title;
    private String discription;
    private String content;

    @Id
    @GeneratedValue(generator = "uuid2" )   //指定生成器名称
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator" )  //生成器名称，uuid生成类
    @Column(name = "carouselid")
    public String getCarouselid() {
        return carouselid;
    }

    public void setCarouselid(String carouselid) {
        this.carouselid = carouselid;
    }

    @Basic
    @Column(name = "pic")
    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
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
    @Column(name = "discription")
    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Carousel carousel = (Carousel) o;

        if (carouselid != null ? !carouselid.equals(carousel.carouselid) : carousel.carouselid != null) return false;
        if (pic != null ? !pic.equals(carousel.pic) : carousel.pic != null) return false;
        if (title != null ? !title.equals(carousel.title) : carousel.title != null) return false;
        if (discription != null ? !discription.equals(carousel.discription) : carousel.discription != null)
            return false;
        if (content != null ? !content.equals(carousel.content) : carousel.content != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = carouselid != null ? carouselid.hashCode() : 0;
        result = 31 * result + (pic != null ? pic.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (discription != null ? discription.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }
}
