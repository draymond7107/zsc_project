package com.zsc.base.abs;
import com.zsc.base.utils.JsonUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
public abstract class _bean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Override
	public String toString() {
		return JsonUtils.toString(this);
	}
	public String toJSONString() {
		return JsonUtils.toString(this);
	}
	@SuppressWarnings("unchecked")
	public <T> T deepClone(){
		ByteArrayOutputStream byteOut = null;
		ObjectOutputStream objOut = null;
		ByteArrayInputStream byteIn = null;
		ObjectInputStream objIn = null;
		try {
			byteOut = new ByteArrayOutputStream();
			objOut = new ObjectOutputStream(byteOut);
			objOut.writeObject(this);
			byteIn = new ByteArrayInputStream(byteOut.toByteArray());
			objIn = new ObjectInputStream(byteIn);
			return (T)objIn.readObject();
		} catch (IOException e){
			e.printStackTrace();
			throw new RuntimeException("Clone Object failed in IO.", e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Class not found.", e);
		} finally {
			try {
				byteIn = null;
				byteOut = null;
				if (objOut != null)
					objOut.close();
				if (objIn != null)
					objIn.close();
			} catch (IOException e) {}
		}
	}
}
