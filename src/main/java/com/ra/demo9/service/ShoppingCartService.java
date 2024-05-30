package com.ra.demo9.service;

import com.ra.demo9.model.entity.*;
import com.ra.demo9.repository.OrderDao;
import com.ra.demo9.repository.ShoppingCartDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class ShoppingCartService
{
    @Autowired
    SessionFactory sessionFactory;
    @Autowired
    ShoppingCartDao shoppingCartDao;
    @Autowired
    OrderDao orderDao;
    public void addToCart(Product product, Users user){
        ShoppingCart shoppingCart = ShoppingCart.builder()
                .orderQuantity(1)
                .userId(user)
                .productId(product)
                .build();
        shoppingCartDao.addShoppingCart(shoppingCart);
    }
    public Double getShoppingCartTotal(Long userId){
       return shoppingCartDao.getShoppingCartTotal(userId);
    }

    public List<ShoppingCart> getShoppingCart(Long userId)
    {
      return   shoppingCartDao.getAllShoppingCarts(userId);
    }


    public void deleteAllShopCart (Long u){
        shoppingCartDao.deleteAllShoppingCart(u);
    }

    public void deleteItemShoppingCart(Integer shoppingCartId)
    {
        shoppingCartDao.deleteItemShoppingCart(shoppingCartId);
    }
    public Long totalPro(Long userId){
        return shoppingCartDao.getShoppingCartTotalPrd(userId);
    }
    public void cartToOrder(Long userId,Address address,Double price,List<Product> products){
        Order order = Order.builder()
                .userId(userId)
                .createdAt(new Date())
                .receiveAddress(address.getFullAddress())
                .receivedAt(new Date())
                .receiveName(address.getReceiveName())
                .receivePhone(address.getPhone())
                .totalPrice(price)
                .status("WAITING")
                .build();
        orderDao.save(order);
        for(Product product : products){
            OrderDetails orderDetails = OrderDetails.builder()
                    .orderQuantity(1)
                    .order(order)
                    .product(product)
                    .build();
            orderDao.saveDetail(orderDetails);
        }

    }

    public List<Product> getShoppingProduct(Long userId) {
        return shoppingCartDao.getShoppingProduct(userId);
    }


}
