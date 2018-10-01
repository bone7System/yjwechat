package com.yj.web;

import com.yj.domain.supplier.service.SupplierService;
import com.yj.domain.user.model.UserDetail;
import com.yj.exception.YjException;
import com.yj.pojo.ReSult;
import com.yj.pojo.customer.CustomerLinkManDtoC;
import com.yj.pojo.customer.CustomerLinkManDtoU;
import com.yj.pojo.supplier.*;
import com.yj.pojo.system.role.RoleSearchDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@Api(value = "/supplierApi", tags = "supplierApi", description = "供应商管理")
public class SupplierApi {
    @Autowired
    private SupplierService supplierService;

    @ApiOperation(value = "/supplier/add", nickname = "添加供应商", notes = "添加供应商")
    @RequestMapping(value = "/supplier/add", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('', 'supplier:add')")
    ReSult addSupplier(@RequestBody  SupplierDtoC supplierDtoC, @SessionAttribute("user") UserDetail user) {
        return supplierService.addSupplier(supplierDtoC,user);
    }

    @ApiOperation(value = "/supplier/update", nickname = "修改供应商", notes = "修改供应商")
    @RequestMapping(value = "/supplier/update", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('', 'supplier:update')")
    ReSult updateSupplier(@RequestBody SupplierDtoU supplierDtoU, @SessionAttribute("user") UserDetail user) throws YjException {
        return supplierService.updateSupplier(supplierDtoU,user);
    }

    @ApiOperation(value = "/supplier/getById", nickname = "获取供应商", notes = "获取供应商")
    @RequestMapping(value = "/supplier/getById", method = RequestMethod.GET, produces = {"application/json"})
    @PreAuthorize("hasPermission('admin', '')")
    ReSult getSupplierById(@RequestParam Long id) {
        return supplierService.getById(id);

    }

    @ApiOperation(value = "/supplier/search", nickname = "查询供应商", notes = "查询供应商")
    @RequestMapping(value = "/supplier/search", method = RequestMethod.GET, produces = {"application/json"})
    @PreAuthorize("hasPermission('', 'supplier:search')")
    ReSult searchSpplier(SupplierDtoS supplierDtoS, @SessionAttribute("user") UserDetail user, Pageable pageable) throws YjException {
        return supplierService.searchSupplier(supplierDtoS,user,pageable);
    }

    @ApiOperation(value = "/supplier/delete", nickname = "删除供应商", notes = "删除供应商")
    @RequestMapping(value = "/supplier/delete", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('', 'supplier:delete')")
    ReSult deleteSpplier(@RequestBody List<Long> ids, @SessionAttribute("user") UserDetail user ) throws YjException {
        return supplierService.deleteSupplier(ids.get(0),user);
    }



    @ApiOperation(value = "/spplier-linkman/add", nickname = "添加客户联系人", notes = "添加客户联系人")
    @RequestMapping(value = "/spplier-linkman/add", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('', 'spplier:add')")
    ReSult addSpplierLinkMan(@RequestBody @Valid SupplierLinkManDtoC dto, @SessionAttribute("user") UserDetail user) throws IOException {
        return supplierService.addSpplierSpplier(dto,user);
    }

    @ApiOperation(value = "/spplier-linkman/update", nickname = "添加客户联系人", notes = "添加客户联系人")
    @RequestMapping(value = "/spplier-linkman/update", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('', 'spplier:update')")
    ReSult updateSpplierLinkMan(@RequestBody SupplierLinkManDtoU dto, @SessionAttribute("user") UserDetail user) throws IOException, YjException {
        return supplierService.updateCustomerSpplier(dto,user);
    }


    @ApiOperation(value = "/spplier-linkman/search", nickname = "查询客户联系人", notes = "查询客户联系人")
    @RequestMapping(value = "/spplier-linkman/search", method = RequestMethod.GET, produces = {"application/json"})
    @PreAuthorize("hasPermission('', 'spplier:search')")
    ReSult searchSpplierLinkMan(Long khbh, @SessionAttribute("user") UserDetail user) throws IOException, YjException {
        return supplierService.searchCustomerSpplier(khbh,user);
    }


    @ApiOperation(value = "/spplier-linkman/delete", nickname = "删除客户联系人", notes = "删除客户联系人")
    @RequestMapping(value = "/spplier-linkman/delete", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('', 'spplier:delete')")
    ReSult deleteSpplierCustomer(@RequestBody List<Long> ids, @SessionAttribute("user") UserDetail user ) throws YjException {
        return supplierService.deleteLinkManSpplier(ids.get(0),user);
    }

    @ApiOperation(value = "/supplier-linkman/getById", nickname = "获取供应商", notes = "获取供应商")
    @RequestMapping(value = "/supplier-linkman/getById", method = RequestMethod.GET, produces = {"application/json"})
    @PreAuthorize("hasPermission('admin', '')")
    ReSult getSupplierLinkManById(@RequestParam Long id) {
        return supplierService.getLinkManById(id);

    }
}
