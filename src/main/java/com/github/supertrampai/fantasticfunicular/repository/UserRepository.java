package com.github.supertrampai.fantasticfunicular.repository;

import com.github.supertrampai.fantasticfunicular.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {

 /*   List<User> findByName(Integer id);
@Query(value = "select sum(amount) from user where poid=?1")
    Float sumSuborderAmount(Integer poid);

    @Modifying
    @Query(value = "update   user set status=?1 where poid=?2")
    int updateSubOrderStatusByPoid(Integer status, Integer poid);*/


}
