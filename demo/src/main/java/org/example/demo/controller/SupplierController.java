package org.example.demo.controller;

import org.example.demo.entity.Supplier;
import org.example.demo.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @PostMapping("/add")
    public String addSupplier(@RequestBody Supplier supplier) {
        try {
            supplierService.addSupplier(supplier);
            return "添加成功";
        } catch (Exception e) {
            return "添加失败：" + e.getMessage();
        }
    }
}