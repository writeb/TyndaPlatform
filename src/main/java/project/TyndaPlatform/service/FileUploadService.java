package project.TyndaPlatform.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import project.TyndaPlatform.dto.MusicDTO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileUploadService {

    @Value("${targetImageURL}")
    private String targetImageURL;

    @Value("${targetMusicURL}")
    private String targetMusicURL;

    @Autowired
    private MusicService musicService;

    public boolean uploadImage(MultipartFile multipartFile, MusicDTO music){
        try{

            String fileToken = DigestUtils.sha1Hex("_!");
            String fileName = fileToken + ".jpg";
            byte bytes[] = multipartFile.getBytes();
            Path path = Paths.get(targetImageURL + "/", fileName);
            Files.write(path, bytes);
            music.setImage(fileToken);
            musicService.updateMusic(music);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean uploadMusic(MultipartFile multipartFile, MusicDTO music){
        try{

            String fileToken = DigestUtils.sha1Hex("!_");
            String fileName = fileToken + ".mp3";
            byte bytes[] = multipartFile.getBytes();
            Path path = Paths.get(targetMusicURL + "/", fileName);
            Files.write(path, bytes);
            music.setAudio(fileToken);
            musicService.updateMusic(music);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
