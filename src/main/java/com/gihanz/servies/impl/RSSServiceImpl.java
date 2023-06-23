package com.gihanz.servies.impl;

import com.gihanz.dtos.ItemDTO;
import com.gihanz.dtos.RssMainDTO;
import com.gihanz.entities.Item;
import com.gihanz.repositories.ItemRepository;
import com.gihanz.servies.RSSService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import utils.ConvertingXML;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class RSSServiceImpl implements RSSService {

    private final RestTemplate restTemplate ;
    private final ItemRepository itemRepository;

    @Value("rss.url")
    private String url;

    public RSSServiceImpl(RestTemplate restTemplate, ItemRepository itemRepository) {
        this.restTemplate = restTemplate;
        this.itemRepository = itemRepository;
    }

    @Scheduled(fixedDelay = 1 ,fixedDelay = 5 ,timeUnit = TimeUnit.MINUTES)
    @Override
    public void rssContendUpdate() {
        try {
            String xml = restTemplate.getForEntity(url, String.class).getBody();
            RssMainDTO rssMainDTO = ConvertingXML.xmlToObject(xml);
            List<Item> list = new ArrayList<>();
            rssMainDTO.getItemDTOS().forEach(itemDTO -> {
                try {
                    list.add(itemDTO.getEntity());
                } catch (Exception e) {
                    //   Error
                }
            });
            List<Item> sorted = list.stream().sorted(Comparator.comparing(Item::getPubDate).reversed()).limit(10).collect(Collectors.toList());
            itemRepository.saveAll(sorted);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ItemDTO> getLastUpdatedTenItems() {
        try {
            List<Item> itemList = itemRepository.getLastUpdatedOrSaveTenItem();
            return itemList.stream().map(Item::getDTO).collect(Collectors.toList());
        } catch (Exception e) {
            return Collections.emptyList(); // we can create custom exceptions
        }
    }

    @Override
    public List<ItemDTO> getItemsListAsPageable(Integer page, Integer size, String sort, String direction) {
        try {
            Pageable pageable = PageRequest.of(page, size, sort.equals("asc") ? Sort.by(sort).ascending():Sort.by(sort).descending());
            Page<Item> itemList = itemRepository.findAll(pageable);
            return itemList.stream().map(Item::getDTO).collect(Collectors.toList());
        } catch (Exception e) {
            return Collections.emptyList(); // we can create custom exceptions
        }
    }
}
