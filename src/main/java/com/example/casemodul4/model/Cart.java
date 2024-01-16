package com.example.casemodul4.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product,Integer> products = new HashMap<>();

    public Cart() {
    }

    public Cart(Map<Product, Integer> products) {
        this.products = products;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }


    //Kiểm tra sản phẩm đã có trong giỏ hàng chưa
    private boolean checkItemInCart(Product product) {
        for (Map.Entry<Product, Integer> entry: products.entrySet()) {
            if (entry.getKey().getId().equals(product.getId())) {
                return true;
            }
        }
        return false;
    }

    private Map.Entry<Product, Integer> selectItemInCart(Product product) {
        for (Map.Entry<Product, Integer> entry: products.entrySet()) {
            if (entry.getKey().getId().equals(product.getId())) {
                return entry;
            }
        }
        return null;
    }

    //Thêm sản phẩm vào giỏ hàng
    public void addProduct(Product product) {
        System.out.println("Before add: " + products);
        if (!checkItemInCart(product)) {
            products.put(product, 1);
        } else {
            Map.Entry<Product, Integer> itemEntry = selectItemInCart(product);
            assert itemEntry != null;
            Integer newQuantity = itemEntry.getValue() + 1;
            products.replace(itemEntry.getKey(), newQuantity);
//            product.decreaseQuantity(1);
            System.out.println("After add: " + products);
        }
    }

    //Giảm số lượng của 1 sản phẩm
    public void subProduct(Product product) {
        if (checkItemInCart(product)) {
            Map.Entry<Product,Integer> itemEntry = selectItemInCart(product);
            assert itemEntry != null;
            Integer currentQuantity = itemEntry.getValue();
            if (currentQuantity > 1) {
                Integer newQuantity = itemEntry.getValue() - 1;
                products.replace(itemEntry.getKey(), newQuantity);
//                product.incrementQuantity(1);
            } else {
                products.remove(itemEntry.getKey());
            }
        }

    }

    //Đếm số lượng sản phẩm trong giỏ hàng
    public Integer countProductQuantity() {
        Integer productQuantity = 0;
        for (Map.Entry<Product, Integer> entry: products.entrySet()) {
            productQuantity += entry.getValue();
        }
        return productQuantity;
    };

    //Đếm tổng số lượng sản phẩm
    public Integer countItemQuantity() {
        return products.size();
    };

    //Tính tổng tiền cần thanh toán của giỏ hàng
    public Float countTotalPayment() {
        float payment = 0;
        for (Map.Entry<Product, Integer> entry: products.entrySet()) {
            payment += (float) (entry.getKey().getPrice() * (float) entry.getValue());
        }
        return payment;
    }

    public void deleteProductFromCart(Long id){
        for (Map.Entry<Product, Integer> entry: products.entrySet()) {
            if (entry.getKey().getId().equals(id)) {
                products.remove(entry.getKey());
                break;
            }
        }
    }

    public void deleteAllFromCart() {
        products.clear();
    }
}
