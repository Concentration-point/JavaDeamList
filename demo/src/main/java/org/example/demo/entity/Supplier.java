package org.example.demo.entity;

@Data
@TableName("supplier")
public class Supplier {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name; // 供应商名称（需为自己名字拼音全拼，如zhangsan）
    private String contact;
    private String contactPhone;
    private String address;
    private Integer status;
    // 关联的产品列表（用于接收参数，存储时通过外键关联）
    private List<Product> products; 
}