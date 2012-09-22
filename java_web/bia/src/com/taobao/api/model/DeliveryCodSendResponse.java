package com.taobao.api.model;

public class DeliveryCodSendResponse extends TaobaoResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8807993445986420737L;
	
	public DeliveryCodSendResponse(){
		super();
	}
	
	public DeliveryCodSendResponse(TaobaoResponse rsp){
		super(rsp);
	}
	
	private Shipping shipping;
	
	public Shipping getShipping() {
		return shipping;
	}

	public void setShipping(Shipping shipping) {
		this.shipping = shipping;
	}	
}
