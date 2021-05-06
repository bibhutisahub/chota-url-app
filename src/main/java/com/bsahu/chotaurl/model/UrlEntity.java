package com.bsahu.chotaurl.model;



import javax.persistence.*;

/**
 * @author bsahu
 *
 */
@Entity(name = "url")
@Table(name = "url")
public class UrlEntity {

    private Long id;

    @Lob
    @Column(name = "long_url", length = 100000)
    private String longUrl;

    @Column(name = "short_url")
    private String shortUrl;
    
    @Column(name = "view_count")
    private int viewCount=0;

    public UrlEntity() {
    }

    public UrlEntity(Long id, String longUrl, String shortUrl) {
        this.id = id;
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
    }

    public UrlEntity(String longUrl) {
        this.longUrl = longUrl;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "url_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	@Override
	public String toString() {
		return "UrlEntity [id=" + id + ", longUrl=" + longUrl + ", shortUrl=" + shortUrl + ", viewCount=" + viewCount
				+ "]";
	}


}
