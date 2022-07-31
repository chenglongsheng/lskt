package person.cls.lskt.vod.service;

import java.io.InputStream;

public interface VodService {

    String uploadVideo(InputStream is, String originalFilename);

    void removeVideo(String videoSourceId);

}
