package com.yj.web.background;

import com.yj.common.json.JsonResult;
import com.yj.domain.dmk.model.YjDmkClEntity;
import com.yj.domain.dmk.model.YjDmkFlEntity;
import com.yj.domain.dmk.service.YjDmkClService;
import com.yj.domain.dmk.service.YjDmkFlService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 *
 * @author: yizhiqiang
 * @version: 1.0
 *
 */
@RestController
@RequestMapping("/dmk")
@Api("代码库查询相关服务API")
public class DmkController {

	private static Logger logger = LoggerFactory.getLogger(DmkController.class);

	@Autowired
	private YjDmkFlService yjDmkFlService;
	@Autowired
	private YjDmkClService yjDmkClService;

	@ApiOperation(value = "根据id查询表单条数据信息", notes = "查询单条数据信息")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "path", name = "pkid", dataType = "Integer", required = true, value = "表的主键") })
	@RequestMapping(value = "/fl/getByPkid", method = RequestMethod.GET)
	public JsonResult getByPkid(@RequestParam(value="pkid",required=true) Integer pkid) {
		try {
			YjDmkFlEntity dto = yjDmkFlService.get(pkid);
			return JsonResult.success(dto);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return JsonResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value = "插入一条数据", notes = "插入一条数据")
	@ApiImplicitParam(paramType = "body", name = "YjDmkFlEntity", value = "一条数据", required = true, dataType = "YjDmkFlEntity")
	@RequestMapping(value = "/fl/insert", method = RequestMethod.POST)
	public JsonResult insert(@RequestBody YjDmkFlEntity dto) {
		try {
			Integer rs = yjDmkFlService.insert(dto);
			return JsonResult.success(rs);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return JsonResult.failure(e.getMessage());
		}
	}


	@ApiOperation(value = "更新一条数据")
	@ApiImplicitParam(paramType = "body", name = "YjDmkFlEntity", value = "图文类型信息", required = true, dataType = "YjDmkFlEntity")
	@RequestMapping(value = "/fl/update", method = RequestMethod.POST)
	public JsonResult update(@RequestBody YjDmkFlEntity po) {
		try {
			Integer rs = yjDmkFlService.update(po);
			return JsonResult.success(rs);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return JsonResult.failure(e.getMessage());
		}
	}

	@ApiOperation("根据主键删除信息")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "path", name = "pkid", dataType = "Integer", required = true, value = "表的主键")
	})
	@RequestMapping(value = "/fl/delete", method = RequestMethod.POST)
	public JsonResult delete(@RequestBody YjDmkFlEntity dto) {
		try {
			Integer rs = yjDmkFlService.delete(dto.getPkid());
			return JsonResult.success(rs);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return JsonResult.failure(e.getMessage());
		}
	}


    @ApiOperation(value = "获取某一个分类下的所有的此昂两", notes = "获取某一个分类下的所有的此昂两")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "pkid", dataType = "Integer", required = true, value = "表的主键") })
    @RequestMapping(value = "/cl/queryClList", method = RequestMethod.GET)
    public JsonResult queryClList(@RequestParam(value="dmbz",required=true) String dmbz) {
        try {
            List<Map<String,Object>> list = yjDmkFlService.queryClList(dmbz);
            return JsonResult.success(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }

	@ApiOperation(value = "插入一条数据", notes = "插入一条数据")
	@ApiImplicitParam(paramType = "body", name = "YjDmkClEntity", value = "一条数据", required = true, dataType = "YjDmkClEntity")
	@RequestMapping(value = "/cl/insertClMulti", method = RequestMethod.POST)
	public JsonResult insert(@RequestBody List<YjDmkClEntity> dtos) {
		try {
			Integer rs = yjDmkFlService.insertClMulti(dtos);
			return JsonResult.success(rs);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return JsonResult.failure(e.getMessage());
		}
	}

	@ApiOperation("根据主键删除信息")
	@ApiImplicitParam(paramType = "body", name = "params", value = "主键数组")
	@RequestMapping(value = "/cl/deleteMulti", method = RequestMethod.POST)
	public JsonResult delete(@RequestBody Integer[] pkids) {
		try {
			Integer rs = yjDmkClService.deleteMulti(pkids);
			return JsonResult.success(rs);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return JsonResult.failure(e.getMessage());
		}
	}

	@ApiOperation("根据主键删除信息")
	@ApiImplicitParam(paramType = "body", name = "params", value = "主键数组")
	@RequestMapping(value = "/cl/deleteByDmbzAndDm", method = RequestMethod.POST)
	public JsonResult deleteByDmbzAndDm(@RequestBody YjDmkClEntity dto) {
		try {
			Integer rs = yjDmkClService.deleteByDmbzAndDm(dto);
			return JsonResult.success(rs);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return JsonResult.failure(e.getMessage());
		}
	}

    @ApiOperation("根据主键删除信息")
    @RequestMapping(value = "/cl/getFdmListByFflid/{fflid}", method = RequestMethod.GET)
    public JsonResult getFdmListByFflid(@PathVariable String dm) {
        try {
            List<Map<String,Object>> list=yjDmkClService.getFdmListByFflid(dm);
            return JsonResult.success(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }

    @ApiOperation("修改常量的状态")
    @ApiImplicitParam(paramType = "body", name = "params", value = "主键数组")
    @RequestMapping(value = "/cl/{zt}/operateStatus", method = RequestMethod.POST)
    public JsonResult operateStatus(@RequestBody Integer[] pkids, @PathVariable(value="zt",required=true) String zt) {
        try {
            Integer rs = yjDmkClService.operateStatus(pkids,zt);
            return JsonResult.success(rs);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }

}
