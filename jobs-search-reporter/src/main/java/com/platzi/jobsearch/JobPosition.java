package com.platzi.jobsearch;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * Clase que representa los resultados de una busqueda
 */
public final class JobPosition {
    private String id;

    private String type;

    private String ulr;

    @SerializedName("created_at")
    private String createdAt;

    private String company;

    @SerializedName("company_url")
    private String companyUrl;

    private String location;

    private String title;

    private String description;

    @SerializedName("how_to_apply")
    private String howToApply;

    @SerializedName("company_logo")
    private String companyLogo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUlr() {
        return ulr;
    }

    public void setUlr(String ulr) {
        this.ulr = ulr;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHowToApply() {
        return howToApply;
    }

    public void setHowToApply(String howToApply) {
        this.howToApply = howToApply;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobPosition that = (JobPosition) o;
        return getId().equals(that.getId()) &&
                getType().equals(that.getType()) &&
                getUlr().equals(that.getUlr()) &&
                getCreatedAt().equals(that.getCreatedAt()) &&
                getCompany().equals(that.getCompany()) &&
                Objects.equals(getCompanyUrl(), that.getCompanyUrl()) &&
                Objects.equals(getLocation(), that.getLocation()) &&
                getTitle().equals(that.getTitle()) &&
                Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getHowToApply(), that.getHowToApply()) &&
                Objects.equals(getCompanyLogo(), that.getCompanyLogo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getType(), getUlr(), getCreatedAt(), getCompany(), getCompanyUrl(), getLocation(), getTitle(), getDescription(), getHowToApply(), getCompanyLogo());
    }

    @Override
    public String toString() {
        return "JobPosition{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", ulr='" + ulr + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", company='" + company + '\'' +
                ", companyUrl='" + companyUrl + '\'' +
                ", location='" + location + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", howToApply='" + howToApply + '\'' +
                ", companyLogo='" + companyLogo + '\'' +
                '}';
    }
}
