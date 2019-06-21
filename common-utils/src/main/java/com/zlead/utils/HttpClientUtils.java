package com.zlead.utils;

import java.net.ConnectException;
import java.util.List;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.IdleConnectionTimeoutThread;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zlead.exception.SystemException;
import com.zlead.global.States;

public class HttpClientUtils
{

	private static final Logger		LOG									= LoggerFactory.getLogger(HttpClientUtils.class);

	private static String			DEFAULT_CHARSET						= "UTF-8";

  /** HTTP GET method */
	public static final String		METHOD_GET							= "GET";

	/** HTTP POST method */
	public static final String		METHOD_POST							= "POST";

	/** 连接超时时间，由bean factory设置，缺省为8秒钟 */
	private int						defaultConnectionTimeout			= 20000;

	/** 回应超时时间, 由bean factory设置，缺省为30秒钟 */
	private int						defaultSoTimeout					= 30000;

	/** 闲置连接超时时间, 由bean factory设置，缺省为60秒钟 */
	private int						defaultIdleConnTimeout				= 60000;

	private int						defaultMaxConnPerHost				= 30;

	private int						defaultMaxTotalConn					= 180;

	/** 默认等待HttpConnectionManager返回连接超时（只有在达到最大连接数时起作用）：1秒 */
	private static final long		defaultHttpConnectionManagerTimeout	= 3 * 1000;

	/**
	 * HTTP连接管理器，该连接管理器必须是线程安全的.
	 */
	private HttpConnectionManager	connectionManager;

	private static HttpClientUtils	clientUtils							= new HttpClientUtils();

	/**
	 * 工厂方法
	 * 
	 * @return
	 */
	public static HttpClientUtils getInstance()
	{
		return clientUtils;
	}

	/**
	 * 私有的构造方法
	 */
	private HttpClientUtils()
	{
		// 创建一个线程安全的HTTP连接池
		connectionManager = new MultiThreadedHttpConnectionManager();
		connectionManager.getParams().setDefaultMaxConnectionsPerHost(this.defaultMaxConnPerHost);
		connectionManager.getParams().setMaxTotalConnections(defaultMaxTotalConn);
		IdleConnectionTimeoutThread ict = new IdleConnectionTimeoutThread();
		ict.addConnectionManager(connectionManager);
		ict.setConnectionTimeout(defaultIdleConnTimeout);
		ict.start();
	}

	/**
	 * 执行Http请求
	 * 
	 * @param request
	 *            请求数据
	 * @param strParaFileName
	 *            文件类型的参数名
	 * @param strFilePath
	 *            文件路径
	 * @return
	 * @throws HttpException
	 *             , IOException
	 */
	public String execute(String url, List<NameValuePair> parameters, String method) throws Exception
	{
		return this.execute(url, parameters, method, DEFAULT_CHARSET);
	}

	/**
	 * 执行Http请求
	 * 
	 * @param url
	 * @param paramsJson
	 * @param encoding
	 * @return
	 * @throws Exception
	 */
	public String execute(String url, String paramsJson, String encoding) throws Exception
	{
		String content = "";
		HttpClient httpClient = this.getHttpClient();
		PostMethod httpMethod = new PostMethod(url);
		try
		{
			LOG.info("请求地址" + url + ":" + paramsJson);
			httpMethod.setRequestEntity(new StringRequestEntity(paramsJson, "application/json", "UTF-8"));
			int statusCode = httpClient.executeMethod(httpMethod);
			if (statusCode != HttpStatus.SC_OK)
			{
				LOG.info("【response code】" + statusCode);
				throw new SystemException(States.FAIL);
			}
			content = IOUtils.toString(httpMethod.getResponseBodyAsStream(), encoding);
			LOG.info("返回信息:" + content);
		}
		catch (ConnectException e)
		{
			LOG.error("无法连接，调用地址:{}", url, e);
		}
		catch (ConnectTimeoutException e)
		{
			LOG.error("连接超时，调用地址:{}", url, e);
		}
		finally
		{
			httpMethod.releaseConnection();
		}
		return content;
	}

	/**
	 * 执行Http请求
	 * 
	 * @param url
	 * @param paramsJson
	 * @return
	 * @throws Exception
	 */
	public String execute(String url, String data) throws Exception
	{
		return this.execute(url, data, DEFAULT_CHARSET);
	}

	/**
	 * 执行Http请求
	 * 
	 * @param url
	 * @param parameters
	 * @param method
	 * @param encoding
	 * @return
	 * @throws Exception
	 */
	public String execute(String url, List<NameValuePair> parameters, String method, String encoding) throws Exception
	{
		String content = "";
		HttpMethod httpMethod = null;
		try
		{
			HttpClient httpclient = this.getHttpClient();
			httpclient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, encoding);
			if (HttpClientUtils.METHOD_GET.equals(method))
			{
				httpMethod = new GetMethod(url);
				httpMethod.getParams().setCredentialCharset(encoding);// get模式且不带上传文件
				httpMethod.setQueryString(parameters.toArray(new NameValuePair[parameters.size()])); // parseNotifyConfig会保证使用GET方法时，request一定使用QueryString
			}
			else
			{
				// post模式且不带上传文件
				httpMethod = new PostMethod(url);
				((PostMethod) httpMethod).addParameters(parameters.toArray(new NameValuePair[parameters.size()]));
				httpMethod.addRequestHeader("Content-Type", "application/x-www-form-urlencoded; text/html; charset=" + encoding);
			}
			long beginTime = System.currentTimeMillis();
			LOG.info("begin verify coupon...");
			int httpStat = httpclient.executeMethod(httpMethod);
			LOG.info("the interface respond in " + (System.currentTimeMillis() - beginTime) + " MS.");
			if (httpStat != HttpStatus.SC_OK)
			{
				LOG.info("【response code】" + httpStat);
				throw new SystemException(States.FAIL);

			}
			content = IOUtils.toString(httpMethod.getResponseBodyAsStream(), encoding);
		}
		catch (ConnectException e)
		{
			LOG.error("无法连接，调用地址:{}", url, e);
		}
		catch (ConnectTimeoutException e)
		{
			LOG.error("连接超时，调用地址:{}", url, e);
		}
		finally
		{
			if (httpMethod != null)
			{
				try
				{
					httpMethod.releaseConnection();
				}
				catch (Exception e)
				{
					LOG.error("http method close error", e);
				}
			}
		}
		return content;
	}

	/**
	 * 获取默认HttpClient
	 * 
	 * @return
	 */
	public HttpClient getHttpClient()
	{
		HttpClient httpclient = new HttpClient(connectionManager);
		httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(defaultConnectionTimeout);
		httpclient.getHttpConnectionManager().getParams().setSoTimeout(defaultSoTimeout);// 设置回应超时
		httpclient.getParams().setConnectionManagerTimeout(defaultHttpConnectionManagerTimeout);// 设置等待ConnectionManager释放connection的时间
		httpclient.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
		return httpclient;
	}

	/**
	 * 将NameValuePairs数组转变为字符串
	 * 
	 * @param nameValues
	 * @return
	 */
	protected String toString(NameValuePair[] nameValues)
	{
		if (nameValues == null || nameValues.length == 0)
		{
			return "null";
		}

		StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < nameValues.length; i++)
		{
			NameValuePair nameValue = nameValues[i];

			if (i == 0)
			{
				buffer.append(nameValue.getName() + "=" + nameValue.getValue());
			}
			else
			{
				buffer.append("&" + nameValue.getName() + "=" + nameValue.getValue());
			}
		}

		return buffer.toString();
	}

}
