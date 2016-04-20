package com.hoo.service;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.hoo.entity.OperateResult;
@WebService
@SOAPBinding(style = Style.RPC)
public interface IPicService {
	
	public OperateResult downPic(@WebParam(name ="type")String type,@WebParam(name ="name")String name);

}
