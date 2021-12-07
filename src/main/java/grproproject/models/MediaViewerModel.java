package grproproject.models;

import grproproject.managers.mediaManager.Media;

public class MediaViewerModel implements Model {

    private Media media;

    public MediaViewerModel(Media media) {
        this.media = media;
    }

    public Media getMedia() {
        return media;
    }
}
