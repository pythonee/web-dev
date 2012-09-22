package com.taobao.api.model;

public class DeliverySendResponse extends TaobaoListResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8807993445986420737L;
	
	public DeliverySendResponse(){
		super();
	}
	
	public DeliverySendResponse(TaobaoResponse rsp){
		super(rsp);
	}
	
	private Shipping shipping;
	
	public Shipping getShipping() {
		return shipping;
	}

	public void setShipping(Shipping shipping) {
		this.shipping = shipping;
	}

	public boolean isDeliverSuccess() {
		return shipping.isDeliverSuccess();
	}

	public void setDeliverSuccess(boolean deliverSuccess) {
		this.shipping.setDeliverSuccess(deliverSuccess);
	}
	
}
