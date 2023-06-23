package com.gihanz.servies;

import com.gihanz.dtos.ItemDTO;
import com.gihanz.entities.Item;
import org.springframework.stereotype.Service;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;
import java.util.List;


public interface RSSService {

    void rssContendUpdate();

     List<ItemDTO> getLastUpdatedTenItems();
    List<ItemDTO> getItemsListAsPageable(Integer page , Integer size, String sort , String direction);

}
