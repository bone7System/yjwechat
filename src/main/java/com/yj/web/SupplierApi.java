package com.yj.web;

import com.yj.domain.supplier.service.SupplierService;
import com.yj.domain.user.model.UserDetail;
import com.yj.exception.YjException;
import com.yj.pojo.ReSult;
import com.yj.pojo.supplier.SupplierDtoC;
import com.yj.pojo.supplier.SupplierDtoS;
import com.yj.pojo.supplier.SupplierDtoU;
import com.yj.pojo.system.role.RoleSearchDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "/supplierApi", tags = "/supplierApi", description = "供应商管理")
public class SupplierApi {
    @Autowired
    private SupplierService supplierService;

    @ApiOperation(value = "/supplier/add", nickname = "添加供应商", notes = "添加供应商")
    @RequestMapping(value = "/supplier/add", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('', 'supplier:add')")
    ReSult searchRole(@RequestBody  SupplierDtoC supplierDtoC, @SessionAttribute("user") UserDetail user) {
        return supplierService.addSupplier(supplierDtoC,user);
    }

    @ApiOperation(value = "/supplier/update", nickname = "修改供应商", notes = "修改供应商")
    @RequestMapping(value = "/supplier/update", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('', 'supplier:update')")
    ReSult searchRole(@RequestBody SupplierDtoU supplierDtoU, @SessionAttribute("user") UserDetail user) throws YjException {
        return supplierService.updateSupplier(supplierDtoU,user);
    }

    @ApiOperation(value = "/supplier/search", nickname = "查询供应商", notes = "查询供应商")
    @RequestMapping(value = "/supplier/search", method = RequestMethod.GET, produces = {"application/json"})
    @PreAuthorize("hasPermission('', 'supplier:search')")
    ReSult searchRole(SupplierDtoS supplierDtoS, @SessionAttribute("user") UserDetail user, Pageable pageable) throws YjException {
        return supplierService.searchSupplier(supplierDtoS,user,pageable);
    }

    @ApiOperation(value = "/supplier/delete", nickname = "删除供应商", notes = "删除供应商")
    @RequestMapping(value = "/supplier/delete", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('', 'supplier:delete')")
    ReSult searchRole(Long id, @SessionAttribute("user") UserDetail user ) throws YjException {
        return supplierService.deleteSupplier(id,user);
    }
}
