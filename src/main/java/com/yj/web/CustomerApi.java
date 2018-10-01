package com.yj.web;

import com.yj.domain.customer.service.CustomerService;
import com.yj.domain.image.service.ImageService;
import com.yj.domain.user.model.UserDetail;
import com.yj.exception.YjException;
import com.yj.pojo.ReSult;
import com.yj.pojo.customer.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@Api(value = "/customer", tags = "customer", description = "客户")
public class CustomerApi {
    @Autowired
    private CustomerService customerService;

    @ApiOperation(value = "/customer/add", nickname = "添加客户", notes = "添加客户")
    @RequestMapping(value = "/customer/add", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('', 'customer:add')")
    ReSult addCustomer(@RequestBody @Valid  CustomerDtoC dto, @SessionAttribute("user") UserDetail user) throws IOException {
        return customerService.addCustomer(dto,user);
    }

    @ApiOperation(value = "/customer/update", nickname = "修改客户", notes = "修改客户")
    @RequestMapping(value = "/customer/update", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('', 'customer:update')")
    ReSult addCustomer(@RequestBody  @Valid CustomerDtoU dto, @SessionAttribute("user") UserDetail user) throws IOException, YjException {
        return customerService.updateCustomer(dto,user);
    }

    @ApiOperation(value = "/customer/getById", nickname = "获取客户", notes = "获取客户")
    @RequestMapping(value = "/customer/getById", method = RequestMethod.GET, produces = {"application/json"})
    @PreAuthorize("hasPermission('admin', '')")
    ReSult getCustomerById(@RequestParam Long id) {
        return customerService.getById(id);

    }

    @ApiOperation(value = "/customer/search", nickname = "查询客户", notes = "查询客户")
    @RequestMapping(value = "/customer/search", method = RequestMethod.GET, produces = {"application/json"})
    @PreAuthorize("hasPermission('', 'customer:search')")
    ReSult addCustomer(@RequestBody   CustomerDtoS dto, @SessionAttribute("user") UserDetail user, Pageable pageable) throws IOException, YjException {
        return customerService.searchCustomer(dto,user,pageable);
    }

    @ApiOperation(value = "/customer/delete", nickname = "删除客户", notes = "删除客户")
    @RequestMapping(value = "/customer/delete", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('', 'customer:delete')")
    ReSult searchCustomer(Long id, @SessionAttribute("user") UserDetail user ) throws YjException {
        return customerService.deleteCustomer(id,user);
    }

    @ApiOperation(value = "/customer-linkman/add", nickname = "添加客户联系人", notes = "添加客户联系人")
    @RequestMapping(value = "/customer-linkman/add", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('', 'customer:add')")
    ReSult addCustomerLinkMan(@RequestBody @Valid CustomerLinkManDtoC dto, @SessionAttribute("user") UserDetail user) throws IOException {
        return customerService.addCustomerLinkMan(dto,user);
    }

    @ApiOperation(value = "/customer-linkman/update", nickname = "添加客户联系人", notes = "添加客户联系人")
    @RequestMapping(value = "/customer-linkman/update", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('', 'customer:update')")
    ReSult updateCustomerLinkMan(@RequestBody CustomerLinkManDtoU dto, @SessionAttribute("user") UserDetail user) throws IOException, YjException {
        return customerService.updateCustomerLinkMan(dto,user);
    }


    @ApiOperation(value = "/customer-linkman/search", nickname = "查询客户联系人", notes = "查询客户联系人")
    @RequestMapping(value = "/customer-linkman/search", method = RequestMethod.GET, produces = {"application/json"})
    @PreAuthorize("hasPermission('', 'customer:search')")
    ReSult searchCustomerLinkMan(Long khbh, @SessionAttribute("user") UserDetail user) throws IOException, YjException {
        return customerService.searchCustomerLinkMan(khbh,user);
    }


    @ApiOperation(value = "/customer-linkman/delete", nickname = "删除客户联系人", notes = "删除客户联系人")
    @RequestMapping(value = "/customer-linkman/delete", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('', 'customer:delete')")
    ReSult deleteLinkManCustomer(Long id, @SessionAttribute("user") UserDetail user ) throws YjException {
        return customerService.deleteLinkManCustomer(id,user);
    }

    @ApiOperation(value = "/customer-linkman/getById", nickname = "获取客户联系人", notes = "获取客户联系人")
    @RequestMapping(value = "/supplier-linkman/getById", method = RequestMethod.GET, produces = {"application/json"})
    @PreAuthorize("hasPermission('admin', '')")
    ReSult getCustomerLinkManById(@RequestParam Long id) {
        return customerService.getLinkManById(id);

    }

}
