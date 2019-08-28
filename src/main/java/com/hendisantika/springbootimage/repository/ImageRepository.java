package com.hendisantika.springbootimage.repository;

import com.hendisantika.springbootimage.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-image
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 29/08/19
 * Time: 06.34
 */
@Repository
public interface ImageRepository extends JpaRepository<User, Integer> {
}

