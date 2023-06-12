package springbootjpaprac.springbootjpaprac.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springbootjpaprac.springbootjpaprac.domain.Member;
import springbootjpaprac.springbootjpaprac.domain.Order;
import springbootjpaprac.springbootjpaprac.domain.items.Item;
import springbootjpaprac.springbootjpaprac.repository.OrderSearch;
import springbootjpaprac.springbootjpaprac.service.ItemService;
import springbootjpaprac.springbootjpaprac.service.MemberService;
import springbootjpaprac.springbootjpaprac.service.OrderService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String createForm(Model model){

        List<Member> members = memberService.findMembers();
        List<Item> allItems = itemService.findAllItems();

        model.addAttribute("members", members);
        model.addAttribute("items", allItems);

        return "order/orderForm";
    }

    @PostMapping("/order")      //식별자만 넘겨주고, 서비스에서 엔티티를 다루는게 좋음.
    public String order(@RequestParam("memberId") Long memberId,
                        @RequestParam("itemId") Long itemId,
                        @RequestParam("count") int count){

        orderService.order(memberId, itemId, count);

        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model){

        List<Order> orders = orderService.findOrders(orderSearch);

        model.addAttribute("orders", orders);

        return "order/orderList";
    }
}
