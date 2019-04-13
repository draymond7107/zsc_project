package com.zsc.base.utils;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import com.zsc.base.Config;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.xml.sax.InputSource;
/**
 * XML操作工具
 * @author lujun
 *
 */
public final class XmlUtils {
	private static Log log = LogFactory.getLog(XmlUtils.class);
	private Map<String,String> hashMap = null;
	private Document document = null;
	private XmlUtils() {}
	public Document getDoc(String xmlPath, boolean ifTruePath) {
		try {
			if (!ifTruePath) {
				xmlPath = XmlUtils.class.getResource("/") + xmlPath;
			}
			SAXReader saxReader = new SAXReader();
			saxReader.setEncoding(Config.ENC_UTF);
			document = saxReader.read(xmlPath);
		} catch (DocumentException e) {
			log.error("XML读取异常！");
		}
		return document;
	}

	public void initHashMap(String xmlPath) {
		if (document != null) {
			document = null;
		}
		getDoc(xmlPath, true);
		if (hashMap == null) {
			hashMap = new HashMap<String,String>();
		} else {
			hashMap.clear();
		}
		Element rootE = document.getRootElement();
		for (Iterator<?> fItems = rootE.elementIterator(); fItems.hasNext();) {
			Element firstE = (Element) fItems.next();
			for (Iterator<?> sItems = firstE.elementIterator(); sItems.hasNext();) {
				Element secondE = (Element) sItems.next();
				hashMap.put((secondE.getName() + "").trim(), (secondE.getStringValue() + "").trim());
			}
		}
	}
	/**
	 * 清空map
	 */
	public void clearHashMap() {
		hashMap.clear();
		hashMap = null;
	}
	/**
	 * 读取指定的格式
	 * @param xmlPath
	 * @param ifTruePath
	 * @return
	 */
	public Map<String,String> readXML(String xmlPath, boolean ifTruePath) {
		if (!ifTruePath) {
			xmlPath = XmlUtils.class.getResource("/") + xmlPath;
		}
		initHashMap(xmlPath);
		return hashMap;
	}
	/**
	 * 读取任何的xml
	 * @param list
	 * @param xmlPath 路径(src目录下的)
	 * @param itemPath 子阶段路径路径
	 * @return
	 */
	public static void readXml(List<Map<String,String>> list,String xmlPath,String itemPath){
		if(itemPath==null || itemPath.length()==0)return;
		if(itemPath.startsWith("/"))itemPath = itemPath.substring(1);
		if (list == null)list = new ArrayList<Map<String,String>>();
		else if (list.size() > 0)
			list.clear();
		InputStream in = null;
		try {
			in = XmlUtils.class.getResourceAsStream(xmlPath);
			SAXReader reader = new SAXReader();
			Document doc = reader.read(in);
			List<?> dataList = doc.selectNodes(itemPath);
			if(dataList!=null && dataList.size()>0)
			for(int i=0;i<dataList.size();i++){
				Element e = (Element)dataList.get(i);
				Iterator<?> it = e.attributeIterator();
				Map<String, String> item = new HashMap<String, String>();
				while (it.hasNext()){
					Attribute attr = (Attribute) it.next();
					String attrName = attr.getName();
					String attrValue = attr.getValue();
					item.put(attrName, attrValue);
				}
				item.put("text",e.getText()==null?"":e.getText());
				list.add(item);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			in = null;
		}
	}
	/**
	 * 更新XML时候要设置数据库
	 * @param hashMap
	 */
	public boolean updateXML(String xmlPath, boolean ifTruePath,Map<String,String> hashMap) {
		this.hashMap = hashMap;
		return updateXML(xmlPath,ifTruePath);
	}

	public boolean updateXML(String xmlPath, boolean ifTruePath) {
		int returnValue = 0;
		if (!ifTruePath) {
			xmlPath = XmlUtils.class.getResource("/") + xmlPath;
		}
		getDoc(xmlPath, true);
		OutputFormat format = OutputFormat.createPrettyPrint();
		FileOutputStream fos = null;
		XMLWriter writer = null;
		try {
			URL url = new URL(xmlPath);
			Element rootE = document.getRootElement();
			for (Iterator<?> fItems = rootE.elementIterator(); fItems.hasNext();) {
				Element firstE = (Element) fItems.next();
				for (Iterator<?> sItems = firstE.elementIterator(); sItems.hasNext();) {
					Element secondE = (Element) sItems.next();
					String tagName = secondE.getName() + "";
					String oldValue = secondE.getStringValue() + "";
					Object newValue = hashMap.get(tagName);
					if (newValue==null) {
						//如果HashMap中找不到这个值，则不更新这个值
						continue;
					}
					if (!oldValue.equals(newValue)) {
						secondE.setText(newValue.toString());
					}
				}
			}
			fos = new FileOutputStream(new File(url.toURI()));
			OutputStreamWriter osw = new OutputStreamWriter(fos, Config.ENC_UTF);
			format.setEncoding(Config.ENC_UTF);
			writer = new XMLWriter(osw, format);
			writer.write(document);
			writer.close();
			returnValue = 1;
		} catch (IOException e) {
			e.printStackTrace();
			log.error("XML输出异常！");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return returnValue == 1;
	}

	/**
	 * 更具字符串解析XML 返回一个HashMap
	 * @param data
	 * @return
	 */
	public static Map<String,String> xmlStr2Map(String data) {
		Map<String,String> hashMap = new HashMap<String, String>();
		try {
			SAXReader reader = new SAXReader();
			reader.setEncoding(Config.ENC_UTF);
			Reader r = new StringReader(data);
			Document document = reader.read(r);
			Element rootE = document.getRootElement();
			getHashMap(hashMap, rootE);
			r.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return hashMap;
	}
	/**
     * 转XMLmap
     * @author  
     * @param xmlBytes
     * @param charset
     * @return
     * @throws Exception
     */
    public static Map<String, String> toMap(byte[] xmlBytes,String charset) throws Exception{
        SAXReader reader = new SAXReader(false);
        InputSource source = new InputSource(new ByteArrayInputStream(xmlBytes));
        source.setEncoding(charset);
        Document doc = reader.read(source);
        Map<String, String> params = XmlUtils.toMap(doc.getRootElement());
        return params;
    }
    /**
     * 转MAP
     * @author  
     * @param element
     * @return
     */
    public static Map<String, String> toMap(Element element){
        Map<String, String> rest = new HashMap<String, String>();
        List<?> els = element.elements();
        for(Object obj : els){
        	Element el =(Element)obj;
            rest.put(el.getName().toLowerCase(), el.getTextTrim());
        }
        return rest;
    }
	/**
	 * 递归得到所有的KEY 和value
	 * @param hashMap
	 * @param e
	 */
	public static void getHashMap(Map<String,String> hashMap, Element e) {
		Iterator<?> it = e.elementIterator();
		if (it.hasNext()){
			while (it.hasNext()) {
				Element re = (Element) it.next();
				getHashMap(hashMap, re);
			}
		} else {
			hashMap.put((e.getName() + "").trim(), (e.getStringValue() + "").trim());
		}
	}

	/**
	 * 创建一个XML文件
	 * @param doc
	 * @param xmlTruePath
	 * @return
	 */
	public boolean buildXML(Document doc, String xmlTruePath) {
		OutputFormat format = OutputFormat.createPrettyPrint();
		FileOutputStream fos = null;
		XMLWriter writer = null;
		try {
			File file = new File(xmlTruePath);
			fos = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(fos, Config.ENC_UTF);
			format.setEncoding(Config.ENC_UTF);
			writer = new XMLWriter(osw, format);
			writer.write(doc);
			writer.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
    public static String map2Xml(SortedMap<String, String> parameters) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set<?> es = parameters.entrySet();
        Iterator<?> it = es.iterator();
        while (it.hasNext()) {
            Map.Entry<?,?> entry = (Map.Entry<?,?>)it.next();
            String k = (String)entry.getKey();
            String v = (String)entry.getValue();
            if (null != v && !"".equals(v) && !"appkey".equals(k)) {
                sb.append("<" + k + ">" + parameters.get(k) + "</" + k + ">\n");
            }
        }
        sb.append("</xml>");
        return sb.toString();
    }

	/**
	 * 这里不能用单例
	 * 
	 * @return
	 */
	public static XmlUtils getInstance() {
		return new XmlUtils();
	}
}
