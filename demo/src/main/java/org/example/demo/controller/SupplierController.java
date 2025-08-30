package org.example.demo.controller;

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