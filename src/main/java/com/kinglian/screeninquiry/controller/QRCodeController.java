package com.kinglian.screeninquiry.controller;

import com.google.zxing.WriterException;

import com.kinglian.screeninquiry.utils.R;
import com.kinglian.screeninquiry.utils.WeixinPay;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
@RequestMapping("/kk")
public class QRCodeController {



	/**
	 * 字符串转换接口
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @param url

	 * @return
	 * @throws IOException
	 * @throws WriterException
	 */
	@RequestMapping(value = {"qrcode"}, method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
//	@ResponseBody
	public R execute(HttpServletRequest httpServletRequest,
					 HttpServletResponse httpServletResponse, String url) throws IOException, WriterException {
		WeixinPay.encodeQrcode(url, httpServletResponse);
		R r=new R();
		return r;
	}
}
