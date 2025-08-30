package org.example.demo.service;

import org.example.demo.entity.Part;
import org.example.demo.entity.Product;
import org.example.demo.entity.Supplier;
import org.example.demo.sql.PartMapper;
import org.example.demo.sql.ProductMapper;
import org.example.demo.sql.SupplierMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    private SupplierMapper supplierMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private PartMapper partMapper;


    @Transactional // 事务管理：若中间步骤失败，自动回滚
    public void addSupplier(Supplier supplier) {
        // 1. 新增供应商
        supplierMapper.insert(supplier);
        Long supplierId = supplier.getId(); // 获取自增的供应商id

        // 2. 新增该供应商下的产品
        List<Product> products = supplier.getProducts();
        for (Product product : products) {
            product.setSupplierId(supplierId); // 设置产品关联的供应商id
            productMapper.insert(product);
            Long productId = product.getId(); // 获取自增的产品id

            // 3. 新增该产品下的零件（零件编号用UUID）
            List<Part> parts = product.getParts();
            for (Part part : parts) {
                part.setProductId(productId); // 设置零件关联的产品id
                part.setCode(UUID.randomUUID().toString()); // 生成UUID作为零件编号
                partMapper.insert(part);
            }
        }
    }
}
