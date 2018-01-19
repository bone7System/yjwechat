package com.yj.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * easui中的tree_data.json数据,只能有一个root节点
 * [{   
    "id":1,   
    "text":"Folder1",   
    "iconCls":"icon-save",   
    "children":[{   
        "text":"File1",   
        "checked":true  
    }]   
}] 
 * 提供静态方法formatTree(List<TreeJson> list) 返回结果
 * TreeJson.formatTree(treeJsonlist) ;
 * @author lwb
 *
 */
public class OrganizationTree implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id ;
    private Long pid ;
    private String orgname ;
    private String orgdesc;
    private String orgcode;
    private Long status;
    private List<OrganizationTree> children = new ArrayList<OrganizationTree>() ;
    /******** setter and getter **********/
    public static List<OrganizationTree> formatTree(List<OrganizationTree> list) {
        OrganizationTree root = new OrganizationTree();
        OrganizationTree node = new OrganizationTree();
        List<OrganizationTree> treelist = new ArrayList<OrganizationTree>();// 拼凑好的json格式的数据
        List<OrganizationTree> parentnodes = new ArrayList<OrganizationTree>();// parentnodes存放所有的父节点
        
        if (list != null && list.size() > 0) {
            root = list.get(0) ;
            //循环遍历oracle树查询的所有节点
            for (int i = 1; i < list.size(); i++) {
                node = list.get(i);
                if(node.getPid().equals(root.getId())){
                    //为tree root 增加子节点
                    parentnodes.add(node) ;
                    root.getChildren().add(node) ;
                }else{
                    //获取root子节点的孩子节点
                    getChildrenNodes(parentnodes, node);
                    parentnodes.add(node) ;
                }
            }    
        }
        treelist.add(root) ;
        return treelist ;
    }

   /* public static List<OrganizationTree> formatTreeNew(List<OrganizationTree> list) {
        OrganizationTree root = new OrganizationTree();
        OrganizationTree node = new OrganizationTree();
        List<OrganizationTree> treelist = new ArrayList<OrganizationTree>();// 拼凑好的json格式的数据
        List<OrganizationTree> parentnodes = new ArrayList<OrganizationTree>();// parentnodes存放所有的父节点

        if (list != null && list.size() > 0) {
            root = list.get(0) ;
            //循环遍历oracle树查询的所有节点
            for (int i = 1; i < list.size(); i++) {
                node = list.get(i);
                if(node.getPid().equals(root.getId())){
                    //为tree root 增加子节点
                    parentnodes.add(node) ;
                    root.getChildren().add(node) ;
                }else{
                    //获取root子节点的孩子节点
                    getChildrenNodes(parentnodes, node);
                    parentnodes.add(node) ;
                }
            }
        }
        SetChildNull(root);
        //treelist.add(root) ;
        List<OrganizationTree> finallist=new ArrayList<OrganizationTree>();
        if(root.getPid().equals("-1")){
          List<OrganizationTree> zdxxlist=  root.getChildren();
            for(OrganizationTree temp:zdxxlist){
                if(temp.getCode().equals(ApiResource.PARENT_ORGCODE)){

                     finallist=temp.getChildren();
                     temp.setPid(temp.getId());
                     temp.setChildren(null);
                     finallist.add(temp);
                }

            }
        }else if(root.getPid().equals(ApiResource.PARENT_ORGCODE)){

                    finallist=root.getChildren();
                    root.setPid(root.getId());
                    root.setChildren(null);
                    finallist.add(root);
        }
        treelist=finallist;
        return treelist ;
    }*/

    public static List<OrganizationTree> formatTreeALL(List<OrganizationTree> list) {
        OrganizationTree root = new OrganizationTree();
        OrganizationTree node = new OrganizationTree();
        List<OrganizationTree> treelist = new ArrayList<OrganizationTree>();// 拼凑好的json格式的数据
        List<OrganizationTree> parentnodes = new ArrayList<OrganizationTree>();// parentnodes存放所有的父节点

        if (list != null && list.size() > 0) {
            root = list.get(0) ;
            //循环遍历oracle树查询的所有节点
            for (int i = 1; i < list.size(); i++) {
                node = list.get(i);
                if(node.getPid().equals(root.getId())){
                    //为tree root 增加子节点
                    parentnodes.add(node) ;
                    root.getChildren().add(node) ;
                }else{
                    //获取root子节点的孩子节点
                    getChildrenNodes(parentnodes, node);
                    parentnodes.add(node) ;
                }
            }
        }
        SetChildNull(root);
        treelist.add(root) ;
        return treelist ;
    }

    public static void SetChildNull(OrganizationTree zdxx){
        for (OrganizationTree temp : zdxx.getChildren()){
            if(temp.getChildren().size()>0){
                SetChildNull(temp);
            }else{
                temp.setChildren(null);
            }
        }
    }

    private static void getChildrenNodes(List<OrganizationTree> parentnodes, OrganizationTree node) {
        //循环遍历所有父节点和node进行匹配，确定父子关系
        for (int i = parentnodes.size() - 1; i >= 0; i--) {
            OrganizationTree pnode = parentnodes.get(i);
            //如果是父子关系，为父节点增加子节点，退出for循环
            if (pnode.getId().equals(node.getPid())) {
                //pnode.setState("open") ;//关闭二级树
                pnode.getChildren().add(node) ;
                return ;
            } else {
                //如果不是父子关系，删除父节点栈里当前的节点，
                //继续此次循环，直到确定父子关系或不存在退出for循环
                parentnodes.remove(i) ;
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public String getOrgdesc() {
        return orgdesc;
    }

    public void setOrgdesc(String orgdesc) {
        this.orgdesc = orgdesc;
    }

    public String getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(String orgcode) {
        this.orgcode = orgcode;
    }


    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public List<OrganizationTree> getChildren() {
        return children;
    }

    public void setChildren(List<OrganizationTree> children) {
        this.children = children;
    }
}