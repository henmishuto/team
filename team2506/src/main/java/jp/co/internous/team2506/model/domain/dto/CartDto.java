
package jp.co.internous.team2506.model.domain.dto;

import java.util.List;

/**
 * カート画面に表示するためのDTO
 * @author インターノウス
 *
 */
public class CartDto {
	private int id;
	private int userId;
	private int productId;
	private int productCount;
	private String productName;
	private String imageFullPath;
	private int price; 
	private List<Integer> checkedIdList;
	private String createdAt;
	private String updatedAt;
	
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
    public int getSubtotal() {
        return price * productCount;
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
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(String updateAt) {
		this.updatedAt = updateAt;
	}
}
