package com.gihanz.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gihanz.entities.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.BeanUtils;
import utils.DateTimeUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"guid", "title", "description", "pubDate","author","link"})
@ToString
public class ItemDTO {

    private String guid;
    private String title;
    @Lob
    private String description;
    private String pubDate;
    private String author;
    private String link;
//    private LocalDate publishDateTime;

    @JsonIgnore
    public Item getEntity() throws Exception{
        Item item = new Item();
        BeanUtils.copyProperties(this,item);
        item.setPubDate(DateTimeUtils.convertStringToLocalDate(pubDate));
        return item;
    }


}
