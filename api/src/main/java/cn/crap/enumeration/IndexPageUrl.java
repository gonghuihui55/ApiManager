package cn.crap.enumeration;

public enum IndexPageUrl {
	INDEX("index.do", "默认首页"),
	RECOMMEND_PROJECT("index.do#/project/list/false/NULL","推荐项目"),
	MY_PROJECT("index.do#/project/list/true/NULL","我的项目");
	private String name;
	private String value;
	
	private IndexPageUrl(String value, String name){
		this.name = name;
		this.value = value;
	}
	public String getName(){
		return name;
	}
	public String getValue(){
		return value;
	}
}