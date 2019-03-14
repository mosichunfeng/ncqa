package cn.neusoft.xuxiao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @作者 邓瑶
 * @项目 BaseServer
 * @包 com.core.mybatis.Tree.java
 * @时间 2014年8月14日 上午11:16:40
 * @版本 1.0.0
 * @描述
 */
public class Tree implements Serializable {

    private static final long serialVersionUID = 5401772599251813928L;

    public static String TREE_NODE_STATE_DEFAULT = "open";
    public static String TREE_NODE_STATE_CLOSED = "closed";

    private String id;
    private String text;
    private String state = Tree.TREE_NODE_STATE_DEFAULT;
    private boolean checked;
    private Object data;


    private int childSize;
    private Map<String, Object> attributes = new HashMap<String, Object>();
    private List<Tree> children = new ArrayList<Tree>();


    public Tree() {
    }

    public Tree(String rootName) {
        this.id = "-1L";
        this.text = rootName;
    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "Tree [id=" + id + ", text=" + text + ", state=" + state
                + ", checked=" + checked + ", childSize=" + childSize
                + ", children=" + children + "]";
    }

    public Tree(String id, String text, int childSize) {
        this.id = id;
        this.text = text;
        this.childSize = childSize;
    }

    public Tree(String id, String text) {
        this.id = id;
        this.text = text;
    }

    public Tree(Long id, String text, int childSize, Map<String, Object> attributes) {
        this.id = String.valueOf(id);
        this.text = text;
        this.childSize = childSize;
        this.attributes = attributes;
    }

    public Tree(Long id, String text, int childSize, Map<String, Object> attributes, boolean checked) {
        this.id = String.valueOf(id);
        this.text = text;
        this.childSize = childSize;
        this.attributes = attributes;
        this.checked = checked;
    }

    public Tree(Long id, String text, String state, boolean checked, Map<String, Object> attributes) {
        this.id = String.valueOf(id);
        this.text = text;
        this.state = state;
        this.checked = checked;
        this.attributes = attributes;
    }


    public Tree(Long id, String text, String state, Object data) {
        this.id = String.valueOf(id);
        this.text = text;
        this.state = state;
        this.data = data;
    }

    public Tree(Long id, String text, String state, boolean checkState) {
        this.id = String.valueOf(id);
        this.text = text;
        this.state = state;
        this.checked = checkState;
    }

    public Tree(Long id, String text, String state) {
        this.id = String.valueOf(id);
        this.text = text;
        this.state = state;
    }
    public Tree(String id, String text, String state) {
        this.id = id;
        this.text = text;
        this.state = state;
    }

    public int getChildSize() {
        return childSize;
    }

    public void setChildSize(int childSize) {
        this.childSize = childSize;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public List<Tree> getChildren() {
        return children;
    }

    public void setChildren(List<Tree> children) {
        this.children = children;
    }



}
