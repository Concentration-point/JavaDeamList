package org.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("part")
public class Part {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name; // 零件1、零件2等
    private String code; // UUID编号
    private Long productId; // 关联产品id
}