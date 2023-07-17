package project.TyndaPlatform.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileUploadService {

    @Value("${targetImageURL}")
    private String targetImageURL;

    @Value("${targetMusicURL}")
    private String targetMusicURL;

    public String uploadImage(MultipartFile multipartFile){
        try{

            String fileToken = DigestUtils.sha1Hex("_!");
            String fileName = fileToken + ".jpg";
            byte bytes[] = multipartFile.getBytes();
            Path path = Paths.get(targetImageURL + "/", fileName);
            if (!Files.exists(path.getParent())) {
                Files.createDirectories(path.getParent());
            }
            String suffix = UUID.randomUUID().toString();

            String newFileName = fileToken + "_" + suffix + ".jpg";
            path = Paths.get(targetImageURL, newFileName);

            Files.write(path, bytes);

            return newFileName;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String uploadMusic(MultipartFile multipartFile) {
        try {

            String fileToken = DigestUtils.sha1Hex("_!");
            String fileName = fileToken + ".mp3";
            byte bytes[] = multipartFile.getBytes();
            Path path = Paths.get(targetMusicURL + "/", fileName);
            if (!Files.exists(path.getParent())) {
                Files.createDirectories(path.getParent());
            }
            String suffix = UUID.randomUUID().toString();

            String newFileName = fileToken + "_" + suffix + ".mp3";
            path = Paths.get(targetMusicURL, newFileName);

            Files.write(path, bytes);

            return newFileName;


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
