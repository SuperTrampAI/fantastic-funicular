package com.github.supertrampai.fantasticfunicular.repository;

import com.github.supertrampai.fantasticfunicular.domain.User;
import com.github.supertrampai.fantasticfunicular.dto.UserOutputDto;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    List<UserOutputDto> list();

    /*
    *   @Select 是查询类的注解，所有的查询均使用这个
        @Result 修饰返回的结果集，关联实体类属性和数据库字段一一对应，如果实体类属性和数据库属性名保持一致，就不需要这个属性来修饰。
        @Insert 插入数据库使用，直接传入实体类会自动解析属性到对应的值
        @Update 负责修改，也可以直接传入对象
        @delete 负责删除
        * 使用#符号和$符号的不同
    * */
    @Select("SELECT * FROM user")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "name", column = "name")
    })
    List<UserOutputDto> getAll();

    @Select("SELECT * FROM user WHERE id = #{id}")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "password", column = "password")
    })
    User getOne(Integer id);

    @Insert("INSERT INTO `user`(`name`,`password`,`age`,`sex`,`nickname`,`usertype`) VALUES (#{name},#{passwork},#{age},#{sex},#{nickname},#{usertype})")
    void insert(User user);

    @Update("UPDATE users SET name=#{name},nickname=#{nickname} WHERE id =#{id}")
    void update(User user);

    @Delete("DELETE FROM user WHERE id =#{id}")
    void delete(Integer id);

    // This example creates a prepared statement, something like select * from teacher where name = ?;
    //@Select("select * from user where name = #{name}")
    //User selectTeachForGivenName(@Param("name") String name);

    // This example creates n inlined statement, something like select * from teacher where name = 'someName';
    @Select("select * from user where name = '${name}'")
    User selectTeachForGivenName(@Param("name") String name);

}
