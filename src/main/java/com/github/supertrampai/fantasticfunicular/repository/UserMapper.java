package com.github.supertrampai.fantasticfunicular.repository;

import com.github.supertrampai.fantasticfunicular.dto.UserOutputDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    List<UserOutputDto> list();
}
