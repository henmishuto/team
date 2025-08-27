package jp.co.internous.team2506.model.form;

import java.io.Serializable;
import java.util.List;

/**
 * カートフォーム
 * @author インターノウス
 *
 */
public class CartForm implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private int userId;
	private int productId;
	private int productCount;
	private String productName;
	private String imageFullPath;
	private int subTotal;
	private int price; 
	private List<Integer> checkedIdList;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getProductCount() {
		return productCount;
	}
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getImageFullPath() {
		return imageFullPath;
	}
	public void setImageFullPath(String imageFullPath) {
		this.imageFullPath = imageFullPath;
	}
	public int getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(int subTotal) {
		this.subTotal = subTotal;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public List<Integer> getCheckedIdList() {
		return checkedIdList;
	}
	public void setCheckedIdList(List<Integer> checkedIdList) {
		this.checkedIdList = checkedIdList;
	}
}
