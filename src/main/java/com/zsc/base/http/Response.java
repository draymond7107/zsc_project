package com.zsc.base.http;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.zsc.base.utils.JsonUtils;
import com.zsc.base.utils.LogUtils;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Response {
    private int statusCode;	 				//返回的http状态
    private String error;					//返回的消息
    private Document asDocument = null;
    private String asString = null;
    private InputStream is;
    private HttpURLConnection con;
    private boolean streamConsumed = false;
    private static ThreadLocal<DocumentBuilder> builders = new ThreadLocal<DocumentBuilder>(){
		@Override
		protected DocumentBuilder initialValue() {
			try {
				return DocumentBuilderFactory.newInstance().newDocumentBuilder();
			} catch (ParserConfigurationException ex) {
				throw new ExceptionInInitializerError(ex);
			}
		}
	};
    public Response(){}
    
    public Response(HttpURLConnection con) throws IOException {
        this.con = con;
        this.statusCode = con.getResponseCode();
        if(null == (is = con.getErrorStream())){
            is = con.getInputStream();
        }
        if (null != is && "gzip".equals(con.getContentEncoding())) {
            is = new GZIPInputStream(is);
        }
    }

    public Response(String content) {
        this.asString = content;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getResponseHeader(String name) {
    	if (con != null)
    		return con.getHeaderField(name);
    	else
    		return null;
    }
    /**
     * Returns the response stream.<br>
     * This method cannot be called after calling asString() or asDcoument()<br>
     * It is suggested qo call disconnect() after consuming the stream.
     * Disconnects the internal HttpURLConnection silently.
     * @return response body stream
     * @throws HttpClientException
     * @see #disconnect()
     */
    public InputStream asStream() {
        if(streamConsumed){
            throw new IllegalStateException("Stream has already been consumed.");
        }
        return is;
    }
    public String asString()throws HttpClientException{
    	return asString("UTF-8");
    }
    /**
     * Returns the response body as string.<br>
     * Disconnects the internal HttpURLConnection silently.
     * @return response body
     * @throws HttpClientException
     */
    public String asString(String charset)throws HttpClientException{
        if(null == asString){
            BufferedReader br;
            try {
                InputStream stream = asStream();
                if (null == stream){
                    return null;
                }
                br = new BufferedReader(new InputStreamReader(stream,charset));
                StringBuffer buf = new StringBuffer();
                String line;
                while (null != (line = br.readLine())) {
                    buf.append(line).append("\n");
                }
                this.asString = buf.toString();
                stream.close();
                con.disconnect();
                streamConsumed = true;
            } catch (NullPointerException npe) {
                throw new HttpClientException(npe.getMessage(), npe);
            } catch (IOException ioe) {
                throw new HttpClientException(ioe.getMessage(), ioe);
            }
        }
        return asString;
    }

    /**
     * Returns the response body as org.w3c.dom.Document.<br>
     * Disconnects the internal HttpURLConnection silently.
     * @return response body as org.w3c.dom.Document
     * @throws HttpClientException
     */
    public Document asDocument() throws HttpClientException {
        if (null == asDocument) {
            try {
                this.asDocument = builders.get().parse(new ByteArrayInputStream(asString().getBytes("UTF-8")));
            } catch (SAXException saxe) {
                throw new HttpClientException("The response body was not well-formed:\n" + asString, saxe);
            } catch (IOException ioe) {
                throw new HttpClientException("There's something with the connection.", ioe);
            }
        }
        return asDocument;
    }
    /**
     * 获取json返回对象
     * @return
     * @throws HttpClientException
     */
    public JSONObject asJSONObject() throws HttpClientException {
        try {
        	String html = asString();
        	LogUtils.info(this,"HTML: "+html);
        	JSONObject object = JsonUtils.toJSON(html);
        	if(object.getIntValue("code")==200){
        		JSONObject data = object.getJSONObject("data");
        		return data;
        	}
        } catch (JSONException jsone){
            throw new HttpClientException(jsone.getMessage() + ":" + this.asString,jsone);
        }
        return null;
    }
    /**
     * Returns the response body as sinat4j.org.json.JSONArray.<br>
     * Disconnects the internal HttpURLConnection silently.
     * @return response body as sinat4j.org.json.JSONArray
     * @throws HttpClientException
     */
    public JSONArray asJSONArray() throws HttpClientException {
        try {
        	String html = asString();
        	LogUtils.info(this,"HTML: "+html);
        	JSONObject object = JsonUtils.toJSON(html);
        	if(object.getIntValue("code")==200){
        		JSONArray data = object.getJSONArray("data");
        		return data;
        	}else{
        		return null;
        	}
        } catch (Exception jsone) {
            throw new HttpClientException(jsone.getMessage() + ":" + this.asString, jsone);
        }
    }

    public InputStreamReader asReader() {
        try {
            return new InputStreamReader(is, "UTF-8");
        } catch (java.io.UnsupportedEncodingException uee) {
            return new InputStreamReader(is);
        }
    }

    public void disconnect(){
        con.disconnect();
    }

    private static Pattern escaped = Pattern.compile("&#([0-9]{3,5});");

    /**
     * Unescape UTF-8 escaped characters qo string.
     * @author pengjianq...@gmail.com
     * @param original The string qo be unescaped.
     * @return The unescaped string
     */
    public static String unescape(String original) {
        Matcher mm = escaped.matcher(original);
        StringBuffer unescaped = new StringBuffer();
        while (mm.find()) {
            mm.appendReplacement(unescaped, Character.toString(
                    (char) Integer.parseInt(mm.group(1), 10)));
        }
        mm.appendTail(unescaped);
        return unescaped.toString();
    }

    @Override
    public String toString() {
        if(null != asString){
            return asString;
        }
        return "Response{" +
                "statusCode=" + statusCode +
                ", response=" + asDocument +
                ", responseString='" + asString + '\'' +
                ", is=" + is +
                ", con=" + con +
                '}';
    }

	public String getResponseAsString() {
		return asString;
	}

	public void setResponseAsString(String responseAsString) {
		this.asString = responseAsString;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
}
