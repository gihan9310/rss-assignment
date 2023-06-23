package com.gihanz.controllers;

import com.gihanz.dtos.ItemDTO;
import com.gihanz.servies.RSSService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;
import java.util.List;

@RestController
@RequestMapping(value = "/items")
@CrossOrigin
public class RSSServiceController {

    private final RSSService rssService;

    public RSSServiceController(RSSService rssService) {
        this.rssService = rssService;
    }

    @GetMapping
    public ResponseEntity<List<ItemDTO>> getLastTenItem() {
        List<ItemDTO> list = rssService.getLastUpdatedTenItems();
        return ResponseEntity.ok(list);
    }

    @GetMapping(params = {"page","size","sort","direction"})
    public ResponseEntity<List<ItemDTO>> getItemsAsPage(@DefaultValue("1") @QueryParam("page") Integer page,
                                                        @DefaultValue("2") @QueryParam("size") Integer size,
                                                        @DefaultValue("PUB_DATE") @QueryParam("sort") String sort,
                                                        @DefaultValue("asc") @QueryParam("direction") String direction
    ) {
        List<ItemDTO> list = rssService.getItemsListAsPageable(page, size, sort, direction);
        return ResponseEntity.ok(list);
    }

}
