package com.hoo.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.hoo.entity.OperateResult;

@WebService
@SOAPBinding(style = Style.RPC)
public class PicService implements IPicService {

	public OperateResult downPic(@WebParam(name = "type") String type,@WebParam(name = "name") String name) {
		OperateResult operateResult = new OperateResult();
		if(org.apache.commons.lang.StringUtils.isBlank(name)){
			operateResult.setErrMsg("name不能为空");
			return operateResult;
		}
		if(org.apache.commons.lang.StringUtils.isBlank(type)){
			operateResult.setErrMsg("type不能为空");
			return operateResult;
		}
		try {
			String host="http://hospscm.jiankangle.com/sci/";
			String path="uploads/weixin/";
			switch (type) {
			case "order":
				break;
			default:
				break;
			}
			
			// 构造URL
			URL url = new URL(host+path+name);
			// 打开连接
			URLConnection con = url.openConnection();
			// 设置请求超时为5s
			con.setConnectTimeout(5 * 1000);
			// 输入流
			InputStream is = con.getInputStream();
			// 1K的数据缓冲
			byte[] bs = new byte[1024];
			// 读取到的数据长度
			int len;
			// 输出的文件流
			File sf = new File("d:\\scipic\\");
			if (!sf.exists()) {
				sf.mkdirs();
			}
			OutputStream os = new FileOutputStream(sf.getPath() + "\\" + name);
			// 开始读取
			while ((len = is.read(bs)) != -1) {
				os.write(bs, 0, len);
			}
			// 完毕，关闭所有链接
			os.close();
			is.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			operateResult.setErrMsg(e.getMessage());
			return operateResult;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			operateResult.setErrMsg(e.getMessage());
			return operateResult;
		} catch (IOException e) {
			e.printStackTrace();
			operateResult.setErrMsg(e.getMessage());
			return operateResult;
		}
		operateResult.setResultCode("0");
		return operateResult;
	}



}
