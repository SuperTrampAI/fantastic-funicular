package com.github.supertrampai.fantasticfunicular.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @version V1.0
 * @program: fantastic-funicular
 * @description: TODO
 * @author: lxh800109@gmail.com
 * @create: 2019-05-29 23:40
 * 修改历史：
 * 时间           作者          版本        描述
 * ====================================================
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserRepository {

    @Autowired
    private UserRepository userRepository;
    @Test
    public void testPageQuery() throws Exception {
        int page=1,size=10;
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);
        //userRepository.findALL(pageable);
        userRepository.findByNameLike("name");
    }

}
