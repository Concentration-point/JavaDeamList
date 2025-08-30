package org.example.demo.sql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.demo.entity.Product;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {}