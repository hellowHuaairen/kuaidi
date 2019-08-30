package com.wangzg.kuaidi.service.impl;
import com.wangzg.kuaidi.service.KuaiDiQueryService;
import com.wangzg.kuaidi.util.MD5Utils;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


/**
 * 实时查询请求Demo
 * @author Administrator
 *
 */
@Service
public class KuaiDiQueryServiceImpl implements KuaiDiQueryService {

	public static void main(String[] args) {
		String key = "hkjeyxNE1250";				//贵司的授权key
		String customer = "545F71CBAF8B28420DED50BC37BC2822";			//贵司的查询公司编号
		String com = "yunda";			//快递公司编码
		String num = "4300495437386";	//快递单号
		String phone = "";				//手机号码后四位
		String from = "";				//出发地
		String to = "";					//目的地
		int resultv2 = 0;				//开启行政规划解析

		KuaiDiQueryServiceImpl kuaiDiQueryService = new KuaiDiQueryServiceImpl();
		String result = kuaiDiQueryService.synQueryData(com, num, phone, from, to, resultv2);
		System.out.println(result);
	}
	
	/**
	 * 实时查询请求地址
	 */
	private static final String SYNQUERY_URL = "http://poll.kuaidi100.com/poll/query.do";
	
	private String key = "hkjeyxNE1250";		//授权key
	private String customer= "545F71CBAF8B28420DED50BC37BC2822";	//实时查询公司编号


	@Override
	public String synQueryData(String com, String num, String phone, String from, String to, int resultv2) {

		StringBuilder param = new StringBuilder("{");
		param.append("\"com\":\"").append(com).append("\"");
		param.append(",\"num\":\"").append(num).append("\"");
		param.append(",\"phone\":\"").append(phone).append("\"");
		param.append(",\"from\":\"").append(from).append("\"");
		param.append(",\"to\":\"").append(to).append("\"");
		if(1 == resultv2) {
			param.append(",\"resultv2\":1");
		} else {
			param.append(",\"resultv2\":0");
		}
		param.append("}");
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("customer", this.customer);
		String sign = MD5Utils.encode(param + this.key + this.customer);
		params.put("sign", sign);
		params.put("param", param.toString());
		
		return this.post(params);
	}
	

	@Override
	public String post(Map<String, String> params) {
		StringBuffer response = new StringBuffer("");
		
		BufferedReader reader = null;
		try {
			StringBuilder builder = new StringBuilder();
			for (Map.Entry<String, String> param : params.entrySet()) {
				if (builder.length() > 0) {
					builder.append('&');
				}
				builder.append(URLEncoder.encode(param.getKey(), "UTF-8"));
				builder.append('=');
				builder.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
			}
			byte[] bytes = builder.toString().getBytes("UTF-8");

			URL url = new URL(SYNQUERY_URL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(3000);
			conn.setReadTimeout(3000);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Content-Length", String.valueOf(bytes.length));
			conn.setDoOutput(true);
			conn.getOutputStream().write(bytes);

			reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			
			String line = "";
            while ((line = reader.readLine()) != null) {
            	response.append(line);
            }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != reader) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return response.toString();
	}
}

