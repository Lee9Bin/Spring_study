package hello.itemservice.web.basic;


import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {
    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String update(@PathVariable("itemId") long itemId, Model model){
        Item item = itemRepository.findBy(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }

    // @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                       @RequestParam int price,
                       @RequestParam Integer quantity,
                       Model model){
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item",item);

        return "basic/item";
    }
    // @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item){

        itemRepository.save(item);
        // model.addAttribute("item",item); //@ModelAttribute 사용시 자동 추가, 따라서 Model을 파라미터로 받지 않아도 돼

        return "basic/item";
    }

    // @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item){

        //("item") 생략시 클래스 이름의 첫글자만 소문자로 바꿔서 추가
        itemRepository.save(item);

        return "basic/item";
    }

    @PostMapping("/add")
    public String addItemV4(Item item){

        itemRepository.save(item);

        return "basic/item";
    }

    @PostConstruct
    public void init(){
        itemRepository.save(new Item("itemA", 1000, 4));
        itemRepository.save(new Item("itemB", 2000, 4));
    }
}
