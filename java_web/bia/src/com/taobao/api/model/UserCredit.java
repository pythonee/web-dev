package com.taobao.api.model;

/**
 * 用户信用
 * 
 * @author fengsheng
 * @since 1.0, Sep 29, 2009
 */
public class UserCredit extends TaobaoModel {

	private static final long serialVersionUID = 3095939905671716341L;

	private Integer level;
	private Integer score;
	private Integer totalNum;
	private Integer goodNum;

	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	public Integer getGoodNum() {
		return goodNum;
	}
	public void setGoodNum(Integer goodNum) {
		this.goodNum = goodNum;
	}

}
