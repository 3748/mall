package com.mall.manage.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.manage.bean.ItemCat;
import com.mall.manage.mapper.ItemCatMapper;
import com.mall.manage.service.ItemCatService;
import com.mall.manage.vo.ItemCatData;
import com.mall.manage.vo.ItemCatResult;

/**
 * @describe 商品类目
 * @author gp6
 * @date 2018-07-07
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private ItemCatMapper itemCatMapper;

	@Override
	public List<ItemCat> getListByParentId(int parentId) {
		ItemCat itemCat = new ItemCat();
		itemCat.setParentId(parentId);
		return itemCatMapper.select(itemCat);
	}

	@Override
	public ItemCatResult queryAllToTree() {
		List<ItemCat> cats = itemCatMapper.select(null);
		ItemCatResult result = new ItemCatResult();

		// 转为map存储，key为父节点ID，value为数据集合
		Map<Integer, List<ItemCat>> itemCatMap = new HashMap<Integer, List<ItemCat>>();
		for (ItemCat itemCat : cats) {
			if (!itemCatMap.containsKey(itemCat.getParentId())) {
				itemCatMap.put(itemCat.getParentId(), new ArrayList<ItemCat>());
			}
			itemCatMap.get(itemCat.getParentId()).add(itemCat);
		}

		// 封装一级对象
		List<ItemCat> itemCatList1 = itemCatMap.get(0);
		for (ItemCat itemCat : itemCatList1) {
			ItemCatData itemCatData = new ItemCatData();
			itemCatData.setUrl("/products/" + itemCat.getId() + ".html");
			itemCatData.setName("<a href='" + itemCatData.getUrl() + "'>" + itemCat.getName() + "</a>");
			result.getItemCats().add(itemCatData);
			if (0 == itemCat.getIsParent()) {
				continue;
			}

			// 封装二级对象
			List<ItemCat> itemCatList2 = itemCatMap.get(itemCat.getParentId());
			List<ItemCatData> itemCatData2 = new ArrayList<ItemCatData>();
			itemCatData.setItems(itemCatData2);
			for (ItemCat itemCat2 : itemCatList2) {
				ItemCatData id2 = new ItemCatData();
				id2.setName(itemCat2.getName());
				id2.setUrl("/products/" + itemCat2.getId() + ".html");
				itemCatData2.add(id2);
				if (1 == itemCat2.getIsParent()) {
					// 封装三级对象
					List<ItemCat> itemCatList3 = itemCatMap.get(itemCat2.getParentId());
					List<String> itemCatData3 = new ArrayList<String>();
					id2.setItems(itemCatData3);
					for (ItemCat itemCat3 : itemCatList3) {
						itemCatData3.add("/products/" + itemCat3.getId() + ".html|" + itemCat3.getName());
					}
				}
			}
			if (result.getItemCats().size() >= 14) {
				break;
			}
		}
		return result;
	}

}
