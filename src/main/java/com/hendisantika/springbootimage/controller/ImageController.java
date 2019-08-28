package com.hendisantika.springbootimage.controller;

import com.hendisantika.springbootimage.entity.User;
import com.hendisantika.springbootimage.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-image
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 29/08/19
 * Time: 06.34
 */
@RestController
@RequestMapping("image")
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;

    // Return the image from the database using ResponseEntity
    @GetMapping("database/{id}")
    public ResponseEntity<byte[]> fromDatabaseAsResEntity(@PathVariable("id") Integer id) throws SQLException {

        Optional<User> primeMinisterOfIndia = imageRepository.findById(id);
        byte[] imageBytes = null;
        if (primeMinisterOfIndia.isPresent()) {

            imageBytes = primeMinisterOfIndia.get().getPhoto().getBytes(1,
                    (int) primeMinisterOfIndia.get().getPhoto().length());
        }

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }

    // Return the image from the database using HttpServletResponse
    @GetMapping(value = "database1/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void fromDatabaseAsHttpServResp(@PathVariable("id") Integer id, HttpServletResponse response)
            throws SQLException, IOException {

        Optional<User> primeMinisterOfIndia = imageRepository.findById(id);

        if (primeMinisterOfIndia.isPresent()) {

            Blob image = primeMinisterOfIndia.get().getPhoto();

            StreamUtils.copy(image.getBinaryStream(), response.getOutputStream());
        }
    }

}
