package com.kinglian.screeninquiry.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.datamatrix.DataMatrixWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.QRCode;
import com.kinglian.screeninquiry.utils.R;
import com.kinglian.screeninquiry.utils.WeixinPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;


@Controller
@RequestMapping("/screenInquiry/screenUser")
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
