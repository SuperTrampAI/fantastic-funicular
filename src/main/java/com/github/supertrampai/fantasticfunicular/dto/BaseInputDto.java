package com.github.supertrampai.fantasticfunicular.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


@Data
@ApiModel(description="常规--输入对象")
public class BaseInputDto  implements Serializable{
	
	private static final long serialVersionUID = 1L;

    private int pageNum=1;
    private int pageSize=10;
    private Integer id;
    @JsonFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value="结束时间",example="2018-6-1")
    private String startDate;
    @JsonFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value="结束时间",example="2018-7-1")
    private String endDate;
	
}
