package com.yj.utils;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class NHDataSourceXmlReader {
	private static Logger logger=LoggerFactory.getLogger(NHDataSourceXmlReader.class);
	
	public static Map<String,Object> read(String fileName,String sign) throws FileNotFoundException{
		//获取根节点元素对象  
		Element root=null;
		try {
			//创建SAXReader对象  
			SAXReader reader = new SAXReader();  
			//读取文件 转换成Document  
			String filePath = "programmer"+File.separator+fileName+File.separator+"dataSource.xml";
			Resource resource = new ClassPathResource(filePath);
			Document document = reader.read(resource.getInputStream());  
			root = document.getRootElement();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
        return getMap(root,sign);
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String,Object> getMap(Element node,String sign){
		Iterator<Element> iterator = node.elementIterator();  
        while(iterator.hasNext()){  
            Element e = iterator.next();  
            List<Attribute> list = e.attributes(); 
            String id=list.get(0).getValue();
            if(sign.equals(id)){
            	return listNodes(e);
            }
        }
		return null;
	} 
	
	//遍历当前节点下的所有节点  
    @SuppressWarnings("unchecked")
	public static Map<String,Object> listNodes(Element node){  
        Map<String,Object> resultMap=new HashMap<String,Object>();
        List<Map<String,Object>> levelList=new ArrayList<Map<String,Object>>();
        Iterator<Element> iterator = node.elementIterator();  
        while(iterator.hasNext()){  
            Element e = iterator.next();  
            String nodeName=e.getName();
            //如果是sql语句
            if("SQL".equals(e.getName().toUpperCase())){
            	//获取sql语句
            	resultMap.put("sql", e.getText().trim());
            }else if("LEVELS".equals(e.getName().toUpperCase())){
            	//获取层级的配置数据
            	Iterator<Element> levelsIterator = e.elementIterator();  
                while(levelsIterator.hasNext()){  
                	Map<String,Object> map=new HashMap<String,Object>();
                    Element levelE = levelsIterator.next();  
                    List<Attribute> leveList = levelE.attributes();
                    for (Attribute levelAttr : leveList) {
                    	map.put(levelAttr.getName(), levelAttr.getValue());
					}
                    levelList.add(map);
                }
                resultMap.put("levels", levelList);
            }
        }
        return resultMap;
    }  
    
    public static void main(String[] args) {
//		System.out.println(read("select","001"));
	}
	
}
