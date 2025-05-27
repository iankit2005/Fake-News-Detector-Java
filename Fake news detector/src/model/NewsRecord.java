package model;

import java.time.LocalDateTime;

public class NewsRecord {
    private int id;
    private String headline;
    private String result;
    private LocalDateTime checkedAt;

    // Constructors
    public NewsRecord() {}

    public NewsRecord(String headline, String result) {
        this.headline = headline;
        this.result = result;
        this.checkedAt = LocalDateTime.now();
    }

    public NewsRecord(int id, String headline, String result, LocalDateTime checkedAt) {
        this.id = id;
        this.headline = headline;
        this.result = result;
        this.checkedAt = checkedAt;
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public LocalDateTime getCheckedAt() {
        return checkedAt;
    }

    public void setCheckedAt(LocalDateTime checkedAt) {
        this.checkedAt = checkedAt;
    }

    @Override
    public String toString() {
        return "NewsRecord{" +
                "id=" + id +
                ", headline='" + headline + '\'' +
                ", result='" + result + '\'' +
                ", checkedAt=" + checkedAt +
                '}';
    }
}
