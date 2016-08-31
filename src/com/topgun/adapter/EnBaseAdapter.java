package com.topgun.adapter;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.content.Context;
import android.widget.BaseAdapter;

/**
 * �Զ���BaseAdapter�Ļ��࣬��д���ֹ��еķ���
 * 
 * @author liusx
 *
 * @param <T>
 *            item�Ķ�Ӧģ����
 */
public abstract class EnBaseAdapter<T> extends BaseAdapter implements Serializable{
	
	private static final long serialVersionUID = -4405955265757416474L;
	
	protected List<T> data;
	protected Context context;
	protected Comparator<T> comparator;
	
	public static String TAG;

	public EnBaseAdapter(List<T> data, Context context) {
		TAG = getClass().getName();
		this.data = data;
		this.context = context;
	}
	
	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public T getItem(int position) {
		if (data.size() != 0) {
			return data.get(position);
		}else {
			return null;
		}
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	/**
	 * ��������β������Ԫ��
	 * 
	 * @param items
	 *            Ԫ�ؼ���
	 * @return �ų��ظ���Ԫ�أ��������ӳɹ��ĸ���
	 */
	public int addItems(List<T> items) {
		int addCount = 0;
		for (T t : items) {
			if (!isContains(t)) {
				data.add(t);
				addCount++;
			}
		}
		if (addCount > 0)
			notifyDataSetChanged();
		return addCount;
	}

	/**
	 * ��������ͷ������Ԫ��
	 * 
	 * @param items
	 *            Ԫ�ؼ���
	 * @return �ų��ظ���Ԫ�أ��������ӳɹ��ĸ���
	 */
	public int addItemsToHead(List<T> items) {
		int addCount = 0;
		for (T t : items) {
			if (!isContains(t)) {
				data.add(addCount, t);
				addCount++;
			}
		}
		if (addCount > 0)
			notifyDataSetChanged();
		return addCount;
	}

	/**
	 * ����Ԫ�أ���comparator��Ϊ������������
	 * 
	 * @param obj
	 */
	public void insertItem(T obj) {
		data.add(obj);
		if (comparator != null)
			Collections.sort(data, comparator);
		notifyDataSetChanged();
	}

	/**
	 * ��comparator��Ϊ������������
	 */
	public void reSortData() {
		if (comparator != null)
			Collections.sort(data, comparator);
		notifyDataSetChanged();
	}
	
	public void orderData(){
		Collections.reverse(data);
		notifyDataSetChanged();
	}
	
	/**
	 * ������ݼ��е���������
	 */
	public void clear(){
		data.clear();
		notifyDataSetChanged();
	}

	/**
	 * ɾ��ָ��λ�õ�Ԫ��
	 * 
	 * @param location
	 *            Ҫɾ����Ԫ�ص�λ��
	 */
	public void reomveItem(int location) {
		data.remove(location);
		notifyDataSetChanged();
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	/**
	 * �ж��������������Ƿ��Ѿ������ö���
	 * 
	 * @param obj
	 * @return
	 */
	public abstract boolean isContains(T obj);
	
}