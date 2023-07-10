package com.emp.model;

import java.util.*;

public interface HotelOwnerDAO_interface {
          public void insert(HotelOwnerVO  hotelOwnerVO);
          public void update(HotelOwnerVO  hotelOwnerVO);
          public void delete(Integer hotelId); //hotelId��PK,�ھ�ID���R��
          public HotelOwnerVO  findByPrimaryKey(Integer hotelId);//hotelId��PK,�ھ�ID���d��
          public List<HotelOwnerVO > getAll();//�C�X�Ҧ����
        
}
