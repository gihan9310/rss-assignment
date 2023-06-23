package com.gihanz.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gihanz.dtos.ItemDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import utils.DateTimeUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_t_items")
public class Item {

    @Id
    @Column(name = "GUID")
    private String guid;
    @Column(name = "TITLE")
    private String title;
    @Lob
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "PUB_DATE")
    private LocalDateTime pubDate;
    @Column(name = "AUTHOR")
    private String author;
    @Column(name = "LINK")
    private String link;

    @JsonIgnore
    public ItemDTO getDTO(){
        ItemDTO item = new ItemDTO();
        BeanUtils.copyProperties(this,item);
        item.setPubDate(pubDate ==null ?"": pubDate.toString());
        return item;
    }
}
