package project.TyndaPlatform.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;

@Controller
public class FileController {

    @Value("${loadImageURL}")
    private String loadImageURL;

    @Value("${loadMusicURL}")
    private String loadMusicURL;

    @GetMapping(value = "/get-image/{token}", produces = MediaType.ALL_VALUE)
    public @ResponseBody byte[] getImage(@PathVariable(name = "token", required = false) String token) throws IOException {
        String imageURL = loadImageURL + "default.png";
        if (token!=null){
            imageURL = loadImageURL + token;
        }
        InputStream in;
        try{
            ClassPathResource resource = new ClassPathResource(imageURL);
            in = resource.getInputStream();
        } catch (Exception e){
            imageURL = loadImageURL + "default.png";
            ClassPathResource resource = new ClassPathResource(imageURL);
            in = resource.getInputStream();
        }
        return IOUtils.toByteArray(in);
    }

    @GetMapping(value = "/get-music/{musicToken}", produces = MediaType.ALL_VALUE)
    public @ResponseBody byte[] getMusic(@PathVariable(name = "musicToken", required = false) String token) throws IOException {
        String musicURL = loadMusicURL;
        if (token!=null){
            musicURL = loadMusicURL + token;
        }
        InputStream in;
        try{
            ClassPathResource resource = new ClassPathResource(musicURL);
            in = resource.getInputStream();
        } catch (Exception e){
            musicURL = loadMusicURL;
            ClassPathResource resource = new ClassPathResource(musicURL);
            in = resource.getInputStream();
        }
        return IOUtils.toByteArray(in);
    }

}
