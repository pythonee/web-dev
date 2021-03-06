/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mashup.resys.recommender;

import java.io.Serializable;

import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.common.RandomUtils;

//构造推荐商品类型，包含两个域：商品id和评分
/**
 * <p>
 * A simple implementation of {@link RecommendedItem}.
 * </p>
 */
public final class GenericRecommendedItem implements RecommendedItem,
		Serializable {

	private final long itemID;
	private final float value;

	/**
	 * @throws IllegalArgumentException
	 *           if item is null or value is NaN
	 */
	public GenericRecommendedItem(long itemID, float value) {
		if (Float.isNaN(value)) {
			throw new IllegalArgumentException("value is NaN");
		}
		this.itemID = itemID;
		this.value = value;
	}

	@Override
	public long getItemID() {
		return itemID;
	}

	@Override
	public float getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "RecommendedItem[item:" + itemID + ", value:" + value + ']';
	}

	@Override
	public int hashCode() {
		return (int) itemID ^ RandomUtils.hashFloat(value);
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof GenericRecommendedItem)) {
			return false;
		}
		GenericRecommendedItem other = (GenericRecommendedItem) o;
		return (itemID == other.getItemID()) && (value == other.getValue());
	}

	@Override
	public int compareTo(RecommendedItem o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
