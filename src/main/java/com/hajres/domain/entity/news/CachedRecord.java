package com.hajres.domain.entity.news;

import com.hajres.news.News;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Duration;
import java.time.Instant;

@Entity
@Table(name = "cached_records")
public class CachedRecord {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String url;
    @Column
    private String json;
    @Column
    private Instant timestamp;

    public CachedRecord() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isOutdated() {
        return Duration.between(timestamp, Instant.now()).compareTo(Duration.ofMinutes(News.CACHING_DURATION_MINUTES)) > 0;
    }
}
