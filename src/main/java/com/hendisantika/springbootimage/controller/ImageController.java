package com.hendisantika.springbootimage.controller;

import com.hendisantika.springbootimage.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
