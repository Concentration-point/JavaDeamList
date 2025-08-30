package org.example.demo.entity;

@Data
@TableName("part")
public class Part {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name; // 零件1、零件2等
    private String code; // UUID编号
    private Long productId; // 关联产品id
}