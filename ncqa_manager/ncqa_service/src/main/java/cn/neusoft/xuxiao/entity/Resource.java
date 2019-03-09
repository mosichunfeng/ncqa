package cn.neusoft.xuxiao.entity;

import cn.neusoft.xuxiao.Tree;
import com.baomidou.mybatisplus.annotations.TableField;
import com.magicbeans.base.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author magicbeans
 * @since 2017-08-30
 */
public class Resource extends BaseEntity<Resource> {

	private static final long serialVersionUID = 1L;

	@TableField("res_name")
	private String resName;
	/**
	 * 资源类型，菜单或都按钮(menu,button)
	 */
	private String type;

	private String url;

	private String code;

	@TableField("parent_id")
	private String parentId;

	private Boolean enable;

	private Integer listorder;


	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getListorder() {
		return listorder;
	}

	public void setListorder(Integer listorder) {
		this.listorder = listorder;
	}


	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Resource{" +
				"resName=" + resName +
				", type=" + type +
				", url=" + url +
				", code=" + code +
				", parentId=" + parentId +
				", listorder=" + listorder +
				"}";
	}


	public static void initTree(List<Resource> list, Tree rootTree) {
		for (Resource item : list) {
			String parentId = item.getParentId() == null ? "-1L" : item.getParentId();
			if ( parentId.equals(rootTree.getId())) {
				Tree tree = new Tree(item.getId(), item.getResName(), Tree.TREE_NODE_STATE_DEFAULT);
				tree.setChecked(false);
				tree.setData(item.getUrl());
				rootTree.getChildren().add(tree);
				initTree(list, tree);
			}
		}
	}
}
