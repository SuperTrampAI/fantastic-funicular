package com.github.supertrampai.fantasticfunicular.domain;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import lombok.Data;


/**
 * @version V1.0
 * @program: fantastic-funicular
 * @description: TODO
 * @author: lxh800109@gmail.com
 * @create: 2019-05-26 14:19
 * 修改历史：
 * 时间           作者          版本        描述
 * ====================================================
 **/
@Data
public class BeanDomain implements Comparable<BeanDomain> {

    private Integer id;
    private String name;
    private String sex;

    public BeanDomain(Integer id, String name, String sex) {
        this.id = id;
        this.name = name;
        this.sex = sex;
    }

    public BeanDomain() {
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof BeanDomain) {
            BeanDomain that = (BeanDomain) o;
            return Objects.equal(id, that.id)
                    && Objects.equal(name, that.name)
                    && Objects.equal(sex, that.sex);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id,this.name,this.sex);
    }

    @Override
    public int compareTo(BeanDomain beanDomain) {
        return ComparisonChain.start()
                .compare(name, beanDomain.id)
                .compare(id, beanDomain.name)
                .compare(sex, beanDomain.sex, Ordering.natural().nullsLast())
                .result();
    }
}
