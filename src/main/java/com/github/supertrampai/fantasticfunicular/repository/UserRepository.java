package com.github.supertrampai.fantasticfunicular.repository;

import com.github.supertrampai.fantasticfunicular.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByNameLike(String name);

    //Page<User> findByName(String name, Pageable pageable);

   // @Query("select u.id,u.name from user where name=?1")
    //List<User> findByName(String name);
 /*   List<User> findByName(Integer id);
@Query(value = "select sum(amount) from user where poid=?1")
    Float sumSuborderAmount(Integer poid);

    @Modifying
    @Query(value = "update   user set status=?1 where poid=?2")
    int updateSubOrderStatusByPoid(Integer status, Integer poid);*/

    //findXXBy,readAXXBy,queryXXBy,countXXBy
    //findByUserNameOrEmail
    //deleteById
    //countByUserName
    //LIKE 、 IgnoreCase、 OrderBy   findByEmailLike findByUserNameIgnoreCase findByUserNameOrderByEmailDesc

   // User findFirstByNameByNicknameAsc();
    //User findTopByUserByIdDesc();
    //Page<User> queryFirst10ByNickname(String nickname, Pageable pageable);
    //List<User> findFirst10ByNickname(String nickname, Sort sort);
    //List<User> findTop10ByNickname(String nickname, Pageable pageable);

}
