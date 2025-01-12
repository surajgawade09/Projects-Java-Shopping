package shop.model;

public class Product {
	
	private int prodid;
	private String prodname;
	private String prodcategory;
	private float pordprice;
	private String prodimg;
	public Product() {}
	public Product(int prodid, String prodname, String prodcategory, float pordprice, String prodimg) {
		super();
		this.prodid = prodid;
		this.prodname = prodname;
		this.prodcategory = prodcategory;
		this.pordprice = pordprice;
		this.prodimg = prodimg;
	}
	public int getProdid() {
		return prodid;
	}
	public void setProdid(int prodid) {
		this.prodid = prodid;
	}
	public String getProdname() {
		return prodname;
	}
	public void setProdname(String prodname) {
		this.prodname = prodname;
	}
	public String getProdcategory() {
		return prodcategory;
	}
	public void setProdcategory(String prodcategory) {
		this.prodcategory = prodcategory;
	}
	public float getPordprice() {
		return pordprice;
	}
	public void setPordprice(float pordprice) {
		this.pordprice = pordprice;
	}
	public String getProdimg() {
		return prodimg;
	}
	public void setProdimg(String prodimg) {
		this.prodimg = prodimg;
	}
	@Override
	public String toString() {
		return "Product [prodid=" + prodid + ", prodname=" + prodname + ", prodcategory=" + prodcategory
				+ ", pordprice=" + pordprice + ", prodimg=" + prodimg + "]";
	}
	
	

}
