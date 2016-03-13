package ch.bfh.swos.bookapp.service.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Entity implementation class for Entity: Book
 */
public class BookDTO implements Serializable {

    private static final long serialVersionUID = -7591860079571184677L;
    private Long id;
    private String title;
    private Date releaseDate;

    private AuthorDTO author;

    public BookDTO() {
        super();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }
}
