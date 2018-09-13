package com.yj.web.common;

import com.yj.common.json.JsonResult;
import com.yj.domain.common.service.CommonlService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Class Name: DemoController Description: Demo事例控制层
 * 
 * @author: yizhiqiang
 * @version: 1.0
 *
 */
@RestController
@RequestMapping("/proData")
@Api("Pro项目数据查询相关服务API")
public class ProgrammerController {

	private static Logger logger = LoggerFactory.getLogger(ProgrammerController.class);

	@Autowired
	private CommonlService commonService;
	
	/********************************** Table列表 ***********************************/
	/**
	 * Table列表查询数据
	 * 
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "应用信息", notes = "根据检索分页查询应用信息-参数是实体对象")
    @RequestMapping(value = "/gridList", method = RequestMethod.POST)
    public JsonResult queryPage(@RequestBody Map<String,Object> params ) {
    	try {
			System.out.println("进来了");
    		Map<String,Object> pagination=(Map<String, Object>) params.get("pagination");
    		Map<String,Object> filters=(Map<String, Object>) params.get("filters");
    		Map<String,Object> sorter=(Map<String, Object>) params.get("sorter");
    		Map<String,Object> searchTextMap=(Map<String, Object>) params.get("searchTextMap");
    		Map<String,Object> searchParams=(Map<String, Object>) params.get("searchParams");
    		Map<String,Object> sqlParams=(Map<String, Object>) params.get("sqlParams");
    		Map<String,Object> initParams=(Map<String, Object>) params.get("initParams");
    		String sign=(String) params.get("sign");
    		if(initParams!=null){
    			for (String key : initParams.keySet()) {
    				searchParams.put(key, initParams.get(key));
    			}
    		}
			Page<Map<String,Object>> pageInfo = commonService.queryPage(pagination,filters,sorter,searchTextMap,searchParams,sqlParams,sign);
    		return JsonResult.success(pageInfo);
    	} catch (Exception e) {
    		logger.error(e.getMessage(), e);
    		return JsonResult.failure(e.getMessage());
    	}
    }
	
//	@ApiOperation(value = "应用信息", notes = "缓存此次导出需要使用的参数")
//	@RequestMapping(value = "/gridList/excel/params", method = RequestMethod.POST)
//	public JsonResult downloadIs(@RequestBody Map<String,Object> params) {
//		try {
//			String uuid=UUID.randomUUID().toString();
//			NHRedisUtils.addRedis(uuid, 30, params);
//    		return JsonResult.success(uuid);
//    	} catch (Exception e) {
//    		logger.error(e.getMessage(), e);
//    		return JsonResult.failure(e.getMessage());
//    	}
//	}
	
//	@SuppressWarnings("unchecked")
//	@ApiOperation(value = "应用信息", notes = "根据检索分页导出应用信息-参数是实体对象")
//	@RequestMapping(value = "/gridList/excel/export", method = RequestMethod.GET)
//	public void downloadIs(@RequestParam("uuid") String uuid
//			,HttpServletRequest request, HttpServletResponse response)  {
//		InputStream is = null;
//		String tmpFileName=UUID.randomUUID().toString();
//		try {
//			Object obj = NHRedisUtils.getRedisResult(uuid);
//			if(obj==null) {
//				throw new NHWarmingException("导出Excel失败，请稍后再试");
//			}
//			Map<String,Object> map=(Map<String, Object>) obj;
//			Map<String,Object> params=(Map<String, Object>) map.get("params");
//			Map<String,Object> columnsMap=(Map<String, Object>) map.get("columnsMap");
//			String excelName=(String) map.get("excelName");
//			//获取Excel文件流
//			logger.error("开始准备执行导出Excel操作："+new Date().getTime());
//			is = commonService.queryExcelIs(params,columnsMap,tmpFileName);
//			logger.error("数据准备全部完成："+new Date().getTime());
//			response.reset();
//			setFileDownloadHeader(request, response,excelName+".xlsx");
//			response.setContentType("application/x-download; charset=utf-8");
//			getOps(response,is,tmpFileName);
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			NHExpHandleUtils.throwesException(e);
//		} finally {
//			if(is!=null){
//				try {
//					is.close();
//				} catch (IOException e) {
//					logger.error("流关闭失败！");
//					e.printStackTrace();
//				}
//			}
//			File file=new File(NHFileUtils.getSystemBaseUrl()+File.separator+tmpFileName);
//			if(file.exists()) {
//				file.delete();
//			}
//		}
//	}
    
    /**********************************下拉框***********************************/
    /**
     * 级联下拉框查询数据
     * @return
     */
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "query", name = "level", dataType = "Integer", required = true, value = "查询的数据的层级"),
        @ApiImplicitParam(paramType = "query", name = "sign", dataType = "String", required = true, value = "查询数据的标志"),
        @ApiImplicitParam(paramType = "query", name = "cascaderValue", dataType = "String", required = false, value = "用来过滤的上级数据标志")
    })
    @RequestMapping(value = "/selectCascaderList", method = RequestMethod.GET)
    public JsonResult getCascaderList(@RequestParam("level") Integer level, @RequestParam("sign") String sign
    		, @RequestParam(value = "cascaderValue",required = false) String cascaderValue) {
    	try {
    		List<Map<String,Object>> list=commonService.selectCascaderList(level,sign,cascaderValue);
    		return JsonResult.success(list);
    	} catch (Exception e) {
    		logger.error(e.getMessage(), e);
    		return JsonResult.failure(e.getMessage());
    	}
    }
    
    /**
     * 普通下拉框和多选下拉框查询数据
     * @return
     */
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "query", name = "level", dataType = "Integer", required = true, value = "查询的数据的层级"),
        @ApiImplicitParam(paramType = "query", name = "sign", dataType = "String", required = true, value = "查询数据的标志"),
        @ApiImplicitParam(paramType = "query", name = "cascaderValue", dataType = "String", required = false, value = "用来过滤的上级数据标志")
    })
    @RequestMapping(value = "/selectDataList", method = RequestMethod.POST)
    public JsonResult selectDataList(@RequestBody Map<String,Object> map) {
    	try {
    		String sign= (String) map.get("sign");
    		Map<String,Object> params= (Map<String, Object>) map.get("params");
			Map<String,Object> sqlParams= (Map<String, Object>) map.get("sqlParams");
    		List<Map<String,Object>> list=commonService.selectDataList(sign,params,sqlParams);
    		return JsonResult.success(list);
    	} catch (Exception e) {
    		logger.error(e.getMessage(), e);
    		return JsonResult.failure(e.getMessage());
    	}
    }
    
    /**********************************树Tree***********************************/
    @RequestMapping(value = "/getTreeList", method = RequestMethod.POST)
    public JsonResult getTreeList(@RequestBody Map<String,Object> params) {
    	try {
    		String sign= (String) params.get("sign");
			Map<String,Object> csMap= (Map<String, Object>) params.get("params");
			Map<String,Object> sqlParams = (Map<String, Object>) params.get("sqlParams");
    		List<Map<String,Object>> data = commonService.getTreeList(sign,csMap,sqlParams);
    		return JsonResult.success(data);
    	} catch (Exception e) {
    		logger.error(e.getMessage(), e);
    		return JsonResult.failure(e.getMessage());
    	}
    }


//	@ApiImplicitParams({
//			@ApiImplicitParam(paramType = "query", name = "sign", dataType = "String", required = true, value = "查询数据的标志"),
//			@ApiImplicitParam(paramType = "query", name = "values", dataType = "String", required = true, value = "用来过滤的上级数据标志") })
//	@RequestMapping(value = "/getSelectName", method = RequestMethod.GET)
//	public JsonResult getSelectName(@RequestParam("sign") String sign, @RequestParam("values") String values) {
//		try {
//			String names = commonService.getSelectName(sign, values);
//			return JsonResult.success(names);
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			return JsonResult.failure(e.getMessage());
//		}
//	}


//	/********************************** 文件上传到Redis缓存中  ***********************************/
//	@ApiOperation(value = "上传文件到Redis缓存中", notes = "上传文件到Redis缓存中", httpMethod = "POST")
//	@ApiImplicitParams({
//			@ApiImplicitParam(paramType = "body", name = "file", dataType = "MultipartFile", required = true, value = "文件") })
//	@RequestMapping(value = "/uploadRedis",consumes = "multipart/form-data", method = RequestMethod.POST)
//	public JsonResult uploadRedis( @RequestParam("file") MultipartFile file , @RequestParam("redisData") Integer redisData) {
//		try {
//			String uuid=UUID.randomUUID().toString();
//			Map<String,Object> map = new HashMap<String,Object>();
//			map.put("is", file.getBytes());
//			map.put("name", file.getOriginalFilename());
//			map.put("size", file.getSize());
//			map.put("uuid", uuid);
//			NHRedisUtils.addRedis(uuid, redisData, map);
//			return JsonResult.success(uuid);
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			return JsonResult.failure(e.getMessage());
//		}
//	}
	
//	/********************************** Excel文件上传操作  ***********************************/
//
//	@ApiOperation(value = "下载导入控件模板", notes = "下载导入控件模板", httpMethod = "GET")
//	@ApiImplicitParams({
//			@ApiImplicitParam(paramType = "string", name = "sign", dataType = "String", required = true, value = "标志")
//	})
//	@RequestMapping(value = "/getTemplate", method = RequestMethod.GET)
//	public void getFileByName(@RequestParam("fileName") String fileName
//			,HttpServletRequest request, HttpServletResponse response) {
//		InputStream is = null;
//		try {
//			if(fileName!=null) {
//				fileName = URLDecoder.decode(fileName, "UTF-8");
//			}
//			is = commonService.getFileByName("excelTemp", fileName);
//			response.reset();
//			setFileDownloadHeader(request, response,fileName);
//			response.setContentType("application/x-download; charset=utf-8");
//			getOps(response,is,null);
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			NHExpHandleUtils.throwesException(e);
//		} finally {
//			if(is!=null){
//				try {
//					is.close();
//				} catch (IOException e) {
//					logger.error("流关闭失败！");
//					e.printStackTrace();
//				}
//			}
//		}
//	}
	
//	@ApiOperation(value = "下载导入的错误信息", notes = "下载导入的错误信息", httpMethod = "GET")
//	@ApiImplicitParams({
//			@ApiImplicitParam(paramType = "string", name = "uuid", dataType = "String", required = true, value = "文件名") })
//	@RequestMapping(value = "/getExcelErrorFile", method = RequestMethod.GET)
//	public void getExcelErrorFile(@RequestParam("uuid") String uuid
//			,HttpServletRequest request, HttpServletResponse response) {
//		InputStream is = null;
//		try {
//			RedisResultDTO po = NHRedisUtils.getRedisUploadResult(uuid);
//			is = NHInputStreamUtils.byteToInputStream(po.getIs());
//			String fileName = po.getName();
//			response.reset();
//			setFileDownloadHeader(request, response,fileName);
//			response.setContentType("application/x-download; charset=utf-8");
//			getOps(response,is,null);
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			NHExpHandleUtils.throwesException(e);
//		} finally {
//			if(is!=null){
//				try {
//					is.close();
//				} catch (IOException e) {
//					logger.error("流关闭失败！");
//					e.printStackTrace();
//				}
//			}
//		}
//	}
	
//	@ApiOperation(value = "获取导入的状态信息", notes = "获取导入的状态信息", httpMethod = "GET")
//	@RequestMapping(value = "/getProgressStatus" , method = RequestMethod.GET)
//	public JsonResult getProgressStatus(@RequestParam("uuid") String uuid) {
//		try {
//			Object obj = NHRedisUtils.getRedisResult("process"+uuid);
//			return JsonResult.success(obj);
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			return JsonResult.failure(e.getMessage());
//		}
//	}
//

	/********************************** 文件上传到Excel中（已废弃）  ***********************************/
//	@ApiOperation(value = "处理Excel文件，返回List数据", notes = "处理Excel文件，返回List数据", httpMethod = "POST")
//	@ApiImplicitParams({
//			@ApiImplicitParam(paramType = "body", name = "file", dataType = "MultipartFile", required = true, value = "Excel文件") })
//	@RequestMapping(value = "/operateExcelFile",consumes = "multipart/form-data", method = RequestMethod.POST)
//	public JsonResult operateExcelFile( @RequestParam("file") MultipartFile file , @RequestParam Map<String, Object> titleParams) {
//		try {
//			List<Map<String, Object>> result = commonService.operateExcelFile(file, titleParams);
//			String uuid=UUID.randomUUID().toString();
//			NHRedisUtils.addRedis(uuid, 30, result);
//			return JsonResult.success(uuid);
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			return JsonResult.failure(e.getMessage());
//		}
//	}
	
//	@ApiOperation(value = "下载文件", notes = "下载文件", httpMethod = "GET")
//	@ApiImplicitParams({
//			@ApiImplicitParam(paramType = "string", name = "fileName", dataType = "String", required = true, value = "文件名") })
//	@RequestMapping(value = "/getFileByName", method = RequestMethod.GET)
//	public void getFileByName(@RequestParam("fileRoot") String fileRoot, @RequestParam("fileName") String fileName,
//			HttpServletResponse resp) {
//		BufferedInputStream bis = null;
//		OutputStream os = null;
//		try {
//			if(fileName!=null) {
//				fileName = URLDecoder.decode(fileName, "UTF-8");
//			}
//			InputStream rs = commonService.getFileByName(fileRoot, fileName);
//			resp.setContentType("application/octet-stream; charset=utf-8");
//			resp.setHeader("Content-Disposition", "attachment;filename=\"" + new String(fileName.getBytes("gb2312"),"ISO-8859-1") + "\"");
//			byte[] buff = new byte[1024];
//			os = resp.getOutputStream();
//			bis = new BufferedInputStream(rs);
//			int i = bis.read(buff);
//			while (i != -1) {
//				os.write(buff, 0, buff.length);
//				os.flush();
//				i = bis.read(buff);
//			}
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//		} finally {
//			if (bis != null) {
//				try {
//					bis.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}
	
	
	
	
	
	
	
//	public static String setFileDownloadHeader(HttpServletRequest request, HttpServletResponse response, String fileName) {
//        final String userAgent = request.getHeader("USER-AGENT");
//        try {
//            String finalFileName = null;
//            if(StringUtils.contains(userAgent, "MSIE")||StringUtils.contains(userAgent, "like Gecko")){//IE浏览器
//                finalFileName = URLEncoder.encode(fileName,"UTF8");
//                response.setHeader("Content-Disposition", "attachment; filename=\"" + finalFileName + "\"");
//                logger.error("使用IE下载文件："+fileName);
//            }else if(StringUtils.contains(userAgent, "Mozilla")){//google,火狐浏览器
//            	finalFileName = URLEncoder.encode(fileName,"UTF-8");
//                response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + finalFileName);
//                logger.error("使用google mozilla 下载文件："+fileName);
//            }else{
//                finalFileName = URLEncoder.encode(fileName,"UTF8");//其他浏览器
//                response.setHeader("Content-Disposition", "attachment; filename=\"" + finalFileName + "\"");
//                logger.error("使用未知浏览器 下载文件："+fileName);
//            }
//            return finalFileName;
//        } catch (UnsupportedEncodingException e){
//        	logger.error(e.getMessage());
//        }
//        return fileName;
//    }
//
//	private void getOps(HttpServletResponse response,InputStream is, String tmpFileName){
//		OutputStream os = null;
//		byte[] buffer = new byte[1024];
//		BufferedInputStream bis = null;
//		try {
//			response.addHeader("content-length", is.available() + "");
//			os = response.getOutputStream();
//			bis = new BufferedInputStream(is);
//			int i = bis.read(buffer);
//			while (i != -1) {
//				os.write(buffer, 0, i);
//				i = bis.read(buffer);
//			}
//			os.flush();
//		} catch (IOException e) {
//			logger.error(e.getMessage(), e);
//		} finally {
//			if (is != null) {
//				try {
//					is.close();
//				} catch (IOException e) {
//					logger.error(e.getMessage(), e);
//				}
//			}
//			if (bis != null) {
//				try {
//					bis.close();
//				} catch (IOException e) {
//					logger.error(e.getMessage(), e);
//				}
//			}
//			if (os != null) {
//				try {
//					os.close();
//				} catch (IOException e) {
//					logger.error(e.getMessage(), e);
//				}
//			}
//			File file=new File(NHFileUtils.getSystemBaseUrl()+File.separator+tmpFileName);
//			if(file.exists()) {
//				file.delete();
//			}
//		}
//	}
}
