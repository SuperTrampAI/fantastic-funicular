package com.github.supertrampai.fantasticfunicular.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @version V1.0
 * @program: fantastic-funicular
 * @description: TODO
 * @author: lxh800109@gmail.com
 * @create: 2019-05-31 00:24
 * 修改历史：
 * 时间           作者          版本        描述
 * ====================================================
 **/
@Data
@ApiModel(description = "user--输入对象")
public class UserSerachInputDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private int pageNum = 1;
    private int pageSize = 10;

    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "name")
    private String name;
}
