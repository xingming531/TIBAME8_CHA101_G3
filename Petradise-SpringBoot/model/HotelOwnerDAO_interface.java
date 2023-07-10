package com.emp.model;

import java.util.*;

public interface HotelOwnerDAO_interface {
          public void insert(HotelOwnerVO  hotelOwnerVO);
          public void update(HotelOwnerVO  hotelOwnerVO);
          public void delete(Integer hotelId); //hotelId為PK,根據ID做刪除
          public HotelOwnerVO  findByPrimaryKey(Integer hotelId);//hotelId為PK,根據ID做查詢
          public List<HotelOwnerVO > getAll();//列出所有資料
        
}
