package steve.step_definitions.RestAss.pojo;

import java.util.List;

public class VolumeInfo {
    String title;
    String subtitle;
    String publisher;
    String publishedDate;
    String description;
    String printType;
    String maturityRating;
    Boolean allowAnonLogging;
    String contentVersion;
    String language;
    String previewLink;
    String infoLink;
    String canonicalVolumeLink;
    List<String> authors;
    List<IndustryIdentidiers> industryIdentifiers;

    public VolumeInfo() {

    }

//    public VolumeInfo(String title, String subtitle, String publisher, String publishedDate, String description, String printType, String maturityRating, Boolean allowAnonLogging, String contentVersion, String language, String previewLink, String infoLink, String canonicalVolumeLink, List<IndustryIdentidiers> industryIdentifiers) {
    public VolumeInfo(String title, String subtitle, String publisher, String publishedDate, String description, String printType, String maturityRating, Boolean allowAnonLogging, String contentVersion, String language, String previewLink, String infoLink, String canonicalVolumeLink, List<String> authors, List<IndustryIdentidiers> industryIdentifiers) {
        this.title = title;
        this.subtitle = subtitle;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.description = description;
        this.printType = printType;
        this.maturityRating = maturityRating;
        this.allowAnonLogging = allowAnonLogging;
        this.contentVersion = contentVersion;
        this.language = language;
        this.previewLink = previewLink;
        this.infoLink = infoLink;
        this.canonicalVolumeLink = canonicalVolumeLink;
        this.authors = authors;
        this.industryIdentifiers = industryIdentifiers;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public String getPrintType() {
        return printType;
    }

    public String getMaturityRating() {
        return maturityRating;
    }

    public Boolean getAllowAnonLogging() {
        return allowAnonLogging;
    }

    public String getContentVersion() {
        return contentVersion;
    }

    public String getLanguage() {
        return language;
    }

    public String getPreviewLink() {
        return previewLink;
    }

    public String getInfoLink() {
        return infoLink;
    }

    public String getCanonicalVolumeLink() {
        return canonicalVolumeLink;
    }

    public List<IndustryIdentidiers> getIndustryIdentifiers() {
        return industryIdentifiers;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrintType(String printType) {
        this.printType = printType;
    }

    public void setMaturityRating(String maturityRating) {
        this.maturityRating = maturityRating;
    }

    public void setAllowAnonLogging(Boolean allowAnonLogging) {
        this.allowAnonLogging = allowAnonLogging;
    }

    public void setContentVersion(String contentVersion) {
        this.contentVersion = contentVersion;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setPreviewLink(String previewLink) {
        this.previewLink = previewLink;
    }

    public void setInfoLink(String infoLink) {
        this.infoLink = infoLink;
    }

    public void setCanonicalVolumeLink(String canonicalVolumeLink) {
        this.canonicalVolumeLink = canonicalVolumeLink;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public void setIndustryIdentifiers(List<IndustryIdentidiers> industryIdentifiers) {
        this.industryIdentifiers = industryIdentifiers;
    }
}
