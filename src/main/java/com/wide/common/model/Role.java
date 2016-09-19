package com.wide.common.model;

import java.util.ArrayList;
import java.util.List;

import com.wide.common.model.base.BaseRole;
import com.wide.common.model.query.QueryRole;
import com.wide.util.DateUtil;
import com.wide.viewmodel.DataTablesModel;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class Role extends BaseRole<Role> {
	public static final Role dao = new Role();
     /**
      * 按用户id查询角色列表
      * @param id
      * @return
      */
	public List<Role> getRoleByuserID(String id) {
		// TODO Auto-generated method stub
		
		List<Role> lists = find("select t1.* from sys_role t1,sys_user_role t2 where t1.id = t2.role_id and t2.user_id = ? ", id);
		return lists;
	}
     /**
      * 查询全部角色列表
      * @return
      */
	public List<Role> getRollAll() {
		// TODO Auto-generated method stub
		
		List<Role> lists = find("select t1.* from sys_role t1 where t1.del_flag = 0 ");
		
		return lists;
	}
	/**
	 * datatables 查询
	 * @param @param pageNum
	 * @param @param iDisplayLength
	 * @param @param select
	 * @param @param sqlExceptSelect
	 * @param @param search    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	public DataTablesModel pageDataTables(int pageNum, int pageSize, QueryRole queryRole) {
	    final List<Object> parameters = new ArrayList<Object>();
	    String select = "select id,name,role_type,create_by,create_date ";
	    StringBuilder sqlExceptSelect = new StringBuilder(" from sys_role  ");
	    /**
	    if (search!=null&&!search.equals("")) {
	        sqlExceptSelect.append(" AND (b.title like ? or b.content like ? )");
	        parameters.add("%" + search + "%");
	        parameters.add("%" + search + "%");
	    } 
	     **/
	    sqlExceptSelect.append(whereQuery(queryRole));
	    sqlExceptSelect.append(orderbyQuery(queryRole));
	    
	    return this.paginateDataTables(pageNum, pageSize, select, sqlExceptSelect.toString());
	}
	
	/**
	 * query where查询
	 * 
	 * */
	private String whereQuery(QueryRole query){
		String where=" where 1=1  and del_flag = 0 ";
		if(query.getRolename()!=null&&!query.getRolename().equals("")){
			where += " and name like '%"+query.getRolename()+"%'";
		}
		if(query.getRoletype()!=null&&!query.getRoletype().equals("")){
			where  +=" and role_type = '"+query.getRoletype()+"'";
		}
		if(query.getStarttimes()!=null&&!query.getStarttimes().equals("")){
			where  +=" and create_date >= '"+DateUtil.toDateTimeStr(query.getStarttimes())+"'";
		}
		if(query.getEndtimes()!=null&&!query.getEndtimes().equals("")){
			where  +=" and create_date <=  '"+DateUtil.toDateTimeStr(query.getEndtimes()).replace("00:00:00", "23:59:59")+"'";
		}
		return where;
		
	}
	/**
	 * query order by 
	 * 
	 * */
	private String orderbyQuery(QueryRole query){
		String orderby = " order by create_date desc ";
		return orderby;
	}

}