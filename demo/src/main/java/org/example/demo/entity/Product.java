package org.example.demo.entity;

@Data
@TableName("product")
public class Product {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String type; // 产品类型：电子产品、日常用品等
    private Long supplierId; // 关联供应商id
    // 关联的零件列表
    private List<Part> parts;
}