package com.github.supertrampai.fantasticfunicular.dto;

import com.github.supertrampai.fantasticfunicular.valid.First;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel(description = "user--输入对象")
public class UserInputDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private int pageNum = 1;
    private int pageSize = 10;

    @ApiModelProperty(value = "id")
    @NotNull(groups = {First.class})
    private Integer id;

    @ApiModelProperty(value = "name")
    private String name;

    private String password;
    private Integer age;
    private Integer sex;
    private String nickname;
    private Integer usertype;
    private Integer status;

}
