package com.yj.web.erp;

import com.yj.common.json.JsonResult;
import com.yj.domain.commondity.service.ErpCommodityService;
import com.yj.domain.user.model.UserDetail;
import com.yj.pojo.commidity.ErpCommodityPojo;
import com.yj.pojo.commidity.ErpCommodityU;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/commodity")
@Api("商品数据操作相关服务API")
public class CommodityController {
    private static Logger logger = LoggerFactory.getLogger(CommodityController.class);

    @Autowired
    private ErpCommodityService erpCommodityService;

    @ApiOperation(value = "根据id查询表单条数据信息", notes = "查询单条数据信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "pkid", dataType = "Integer", required = true, value = "表的主键") })
    @RequestMapping(value = "/getByPkid", method = RequestMethod.GET)
    public JsonResult getByPkid(@RequestParam(value="pkid",required=true) Integer pkid) {
        try {
            Map<String, Object> dto = erpCommodityService.get(pkid);
            return JsonResult.success(dto);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return JsonResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "插入一个商品", notes = "插入一个商品")
    @ApiImplicitParam(paramType = "body", name = "ErpCommodityPojo", value = "一条数据", required = true, dataType = "ErpCommodityPojo")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public JsonResult insert(@RequestBody ErpCommodityPojo dto, @SessionAttribute("user") UserDetail user) {
        try {
            Integer rs = erpCommodityService.insert(dto,user);
            return JsonResult.success(rs);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return JsonResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "修改一个商品", notes = "修改入一个商品")
    @ApiImplicitParam(paramType = "body", name = "ErpCommodityPojo", value = "一条数据", required = true, dataType = "ErpCommodityPojo")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public JsonResult update(@RequestBody ErpCommodityU dto, @SessionAttribute("user") UserDetail user) {
        try {
            Integer rs = erpCommodityService.update(dto,user);
            return JsonResult.success(rs);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return JsonResult.failure(e.getMessage());
        }
    }

    @ApiOperation("根据主键删除信息")
    @ApiImplicitParam(paramType = "body", name = "params", value = "主键数组")
    @RequestMapping(value = "/deleteMulti", method = RequestMethod.POST)
    public JsonResult delete(@RequestBody Integer[] pkids,Boolean isDelete) {
        try {
            Integer rs = erpCommodityService.deleteMulti(pkids,isDelete);
            return JsonResult.success(rs);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return JsonResult.failure(e.getMessage());
        }
    }

}
