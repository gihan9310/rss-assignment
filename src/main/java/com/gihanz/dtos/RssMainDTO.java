package com.gihanz.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.ToString;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "rss")
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
public class RssMainDTO {

    @XmlElementWrapper(name = "channel")
    @XmlElement(name = "item",required = true)
    private List<ItemDTO> itemDTOS;

//    @XmlElement(required=true)
    public List<ItemDTO> getItemDTOS() {
        return itemDTOS;
    }
    @JsonIgnore
    public void setItemDTOS(List<ItemDTO> itemDTOS) {
        this.itemDTOS = itemDTOS;
    }
}
