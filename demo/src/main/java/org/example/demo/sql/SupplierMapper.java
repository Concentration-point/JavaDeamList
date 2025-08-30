package org.example.demo.sql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.demo.entity.Supplier;

@Mapper
public interface SupplierMapper extends BaseMapper<Supplier> {}

