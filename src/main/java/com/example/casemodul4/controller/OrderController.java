package com.example.casemodul4.controller;

import com.example.casemodul4.model.*;
import com.example.casemodul4.service.IOrderDetailService;
import com.example.casemodul4.service.IOrderService;
import com.example.casemodul4.service.IProductService;
import com.example.casemodul4.service.IUserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Timestamp;
import java.util.Map;
import java.util.Optional;

@Controller
@SessionAttributes("cart")
@RequestMapping("/checkout")
public class OrderController {

    @ModelAttribute("cart")
    public Cart setUpCart() {
        return new Cart();
    }

    @Autowired
    private IProductService iProductService;

    @Autowired
    private IOrderService iorderService;
//
    @Autowired
    private IOrderDetailService iOrderDetailService;

    @Autowired
    private IUserService iUserService;

    @GetMapping("")
    public String showOrder(HttpSession session, RedirectAttributes redirectAttributes) {
        Cart cart = (Cart) session.getAttribute("cart");

        // Chuyển dữ liệu giỏ hàng sang trang order
        redirectAttributes.addFlashAttribute("cart", cart);

        return "redirect:/checkout/order-summary";
    }

    @GetMapping("/order-summary")
    public ModelAndView showOrderSummary(@ModelAttribute("cart") Cart cart) {
        ModelAndView modelAndView = new ModelAndView("order/list");

        modelAndView.addObject("cart", cart);
        return modelAndView;
    }

    @GetMapping("/place-order")
    public ModelAndView payment(@ModelAttribute("cart") Cart cart) {
//
        //giả sử một user mặc định. User sẽ lấy qua userid từ session
        Optional<User> user = iUserService.findById(Long.valueOf(1));

        if (user.isPresent()) {

            //Tạo đơn hàng cho user
            //Tạo đơn hàng cho customer
            Order order = new Order();

            order.setUser(user.get());
            order.setPhoneNumber("0968949246");
            order.setDeliveryAddress("Ha Noi");
            order.setOrderDate(new Timestamp(System.currentTimeMillis()));
            order.setStatus("Started");
            order.setTotalPrice(cart.countTotalPayment());

            iorderService.save(order);

            // Lấy danh sách products từ cart, cho vào orderdetail
            Map<Product, Integer> products = cart.getProducts();
            for (Map.Entry<Product, Integer> entry : products.entrySet()) {
                Product product = entry.getKey();
                Long quantity = Long.valueOf(entry.getValue());

                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrder(order);
                orderDetail.setProduct(product);
                orderDetail.setQuantity(quantity);
                orderDetail.setPrice(product.getPrice() * quantity);
                iOrderDetailService.save(orderDetail);

                // trừ đi số lượng sản phẩm
                product.setQuantity(product.getQuantity() - quantity);
                iProductService.save(product);
            }
            //xóa giỏ hàng
            cart.deleteAllFromCart();
            ModelAndView modelAndView = new ModelAndView("/order/success");
            return modelAndView;
        }
        return new ModelAndView("/error_404");
    }
}
