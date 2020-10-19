package steve.step_definitions.RestAss.pojo;

import java.util.List;

public class Items {
    private String kind;
    private String id;
    private String etag;
    private String selfLink;
    private VolumeInfo volumeInfo;

    public Items() {

    }
    public Items(String kind, String id, String etag, String selfLink, VolumeInfo volumeInfo) {
        this.kind = kind;
        this.id = id;
        this.etag = etag;
        this.selfLink = selfLink;
        this.volumeInfo = volumeInfo;
    }

    public String getKind() {
        return kind;
    }

    public String getId() {
        return id;
    }

    public String getEtag() {
        return etag;
    }

    public String getSelfLink() {
        return selfLink;
    }

    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public void setSelfLink(String selfLink) {
        this.selfLink = selfLink;
    }

    public void setVolumeInfo(VolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }


//    public static class Builder {
//        private String kind;
//        private String id;
//        private String etag;
//        private String selfLink;
//        private VolumeInfo volumeInfo;
//
//        public Builder () {}
//
//        public Builder setKind(String kind) {
//            this.kind = kind;
//            return this;
//        }
//
//        public Builder setId(String id) {
//            this.id = id;
//            return this;
//        }
//
//        public Builder setEtag(String etag) {
//            this.etag = etag;
//            return this;
//        }
//
//        public Builder setSelfLink(String selfLink) {
//            this.selfLink = selfLink;
//            return this;
//        }
//
//        public Builder setVolumeInfo(VolumeInfo volumeInfo) {
//            this.volumeInfo = volumeInfo;
//            return this;
//        }
//
//        public Items build() {return new Items(kind, id, etag, selfLink, volumeInfo);}
//    }
}
